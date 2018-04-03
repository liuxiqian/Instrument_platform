package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.jsonView.CheckAbilityStatisticsJsonView;
import com.mengyunzhi.measurement.output.CheckAbilityStatisticsView;
import com.mengyunzhi.measurement.repository.*;
import com.mengyunzhi.measurement.specs.CheckAbilityStatisticsSpecs;
import org.apache.poi.util.Internal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * 检定能力（覆盖率）统计
 *
 * @author panjie
 */
@Service
public class CheckAbilityStatisticsServiceImpl implements CheckAbilityStatisticsService {
    private Boolean inExecute = false;          // 函数是否正在执行中
    private final static Logger logger = LoggerFactory.getLogger(CheckAbilityStatisticsServiceImpl.class.getName());
    private final DistrictRepository districtRepository;    // 区域
    private final MandatoryInstrumentRepository mandatoryInstrumentRepository;    // 强检器具
    private final DeviceInstrumentService deviceInstrumentService; // 授权检定项目
    private List<CheckAbilityStatistics> checkAbilityStatisticsList;        // 检定能力列表(用于批量保存)
    private final CheckAbilityStatisticsRepository checkAbilityStatisticsRepository;    // 器具检定能力
    private final DeviceInstrumentRepository deviceInstrumentRepository;        // 装置授权检定项目
    private final DepartmentRepository departmentRepository;                    // 部门
    private final InstrumentTypeRepository instrumentTypeRepository;            // 器具类别
    private final DisciplineRepository disciplineRepository;                    // 学科类别
    private final AccuracyRepository accuracyRepository;                        // 精确度等级
    @Autowired
    public CheckAbilityStatisticsServiceImpl(DistrictRepository districtRepository, MandatoryInstrumentRepository mandatoryInstrumentRepository, DeviceInstrumentService deviceInstrumentSer, CheckAbilityStatisticsRepository checkAbilityStatisticsRepository, DeviceInstrumentRepository deviceInstrumentRepository, DepartmentRepository departmentRepository, InstrumentTypeRepository instrumentTypeRepository, DisciplineRepository disciplineRepository, AccuracyRepository accuracyRepository) {
        this.districtRepository = districtRepository;
        this.mandatoryInstrumentRepository = mandatoryInstrumentRepository;
        this.deviceInstrumentService = deviceInstrumentSer;
        this.checkAbilityStatisticsList = new ArrayList<>();
        this.checkAbilityStatisticsRepository = checkAbilityStatisticsRepository;
        this.deviceInstrumentRepository = deviceInstrumentRepository;
        this.departmentRepository = departmentRepository;
        this.instrumentTypeRepository = instrumentTypeRepository;
        this.disciplineRepository = disciplineRepository;
        this.accuracyRepository = accuracyRepository;
    }

    /**
     * 数据初始化
     *
     * @author panjie
     */
    @Override
    public void dataInit() {
        logger.debug("清空数据表");
        checkAbilityStatisticsRepository.deleteAll();

        logger.debug("初始化区域授权检定能力");
        HashMap<District, HashMap<InstrumentType, List<DeviceInstrument>>> districtDeviceInstrumentHashMap = new HashMap<>();

        logger.debug("获取所有的区域信息");
        List<District> districtList = (List<District>) districtRepository.findAll();

        logger.debug("获取区域的检定能力");
        addDeviceInstrumentListOfDistrictList(districtDeviceInstrumentHashMap, districtList);

        logger.debug("按区域计算检定能力，并进行数据初始化");
        computerAbilityAndDataInit(districtDeviceInstrumentHashMap, districtList);
    }

    /**
     * 计算检定能力，并进行数据的初始持久化操作
     *
     * @param districtDeviceInstrumentHashMap 所有区域对器具类别的检定能力
     * @param districtList                    区域列表
     * @author panjie
     */
    @Override
    public void computerAbilityAndDataInit(HashMap<District, HashMap<InstrumentType, List<DeviceInstrument>>> districtDeviceInstrumentHashMap, List<District> districtList) {
            for (District district : districtList) {
                logger.debug("获取某个区域内的所有的器具信息");
                List<MandatoryInstrument> mandatoryInstrumentList = new ArrayList<>();
                List<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentRepository.findAllByDistrict(district);
                mandatoryInstrumentList.addAll(mandatoryInstruments);

                logger.debug("计算当前区域及父区域的检定能力，并持久化");
                for (MandatoryInstrument mandatoryInstrument : mandatoryInstrumentList) {
                    try {
                        logger.debug("初始化检定能力实体");
                        CheckAbilityStatistics checkAbilityStatistics = new CheckAbilityStatistics();
                        // 初始化器具，器具用户，器具二级类别，学科类别，准确度等级权重，最小测试范围权重，最大测试范围权重
                        if (mandatoryInstrument.getAccuracy() == null || mandatoryInstrument.getMinMeasureScale() == null || mandatoryInstrument.getMaxMeasureScale() == null) {
                            logger.warn("准确度等级 最小测量范围或最大测量范围为空");
                            continue;
                        }
                        Integer accuracyWeight = mandatoryInstrument.getAccuracy().getWeight();
                        Integer min = mandatoryInstrument.getMinMeasureScale().getWeight();
                        Integer max = mandatoryInstrument.getMaxMeasureScale().getWeight();
                        Discipline discipline = mandatoryInstrument.getMaxMeasureScale().getInstrumentType().getInstrumentFirstLevelType().getDiscipline();

                        InstrumentType instrumentType = mandatoryInstrument.getInstrumentType();
                        Department department = mandatoryInstrument.getDepartment();
                        checkAbilityStatistics.setMandatoryInstrument(mandatoryInstrument, accuracyWeight, min, max, instrumentType, discipline, department, district);
                        this.computerCheckAbility(district, districtDeviceInstrumentHashMap, mandatoryInstrument, checkAbilityStatistics);
                        this.add(checkAbilityStatistics);
                    } catch (Exception e){
                        logger.warn("器具id为" + mandatoryInstrument.getId().toString() + "信息错误;区域id为" + district.getId().toString() + "信息错误");
                    }

                }
            }

        this.addAll();
    }

    /**
     * 为区域添加授权检定能力
     *
     * @param districtDeviceInstrumentHashMap 所有区域对器具类别的检定能力
     * @param districtList                    区域
     * @author panjie
     */
    @Override
    public void addDeviceInstrumentListOfDistrictList(HashMap<District, HashMap<InstrumentType, List<DeviceInstrument>>> districtDeviceInstrumentHashMap, List<District> districtList) {
        logger.debug("获取区域检定的二级目录");
        for (District district : districtList) {
            logger.debug("获取本区域上的所有控权检定项目");
            List<DeviceInstrument> deviceInstrumentList = new ArrayList<>();
            List<DeviceInstrument> deviceInstruments = deviceInstrumentRepository.findAllByDistrict(district);
            deviceInstrumentList.addAll(deviceInstruments);

            logger.debug("按器具类别进行分组");
            HashMap<InstrumentType, List<DeviceInstrument>> deviceInstrumentHashMap = new HashMap<>();
            for (DeviceInstrument deviceInstrument : deviceInstrumentList) {
                if (!deviceInstrumentHashMap.containsKey(deviceInstrument.getInstrumentType())) {
                    deviceInstrumentHashMap.put(deviceInstrument.getInstrumentType(), new ArrayList<>());
                }
                deviceInstrumentHashMap.get(deviceInstrument.getInstrumentType()).add(deviceInstrument);
            }

            districtDeviceInstrumentHashMap.put(district, deviceInstrumentHashMap);
        }
    }

    /**
     * 持久化已添加但未保存的实体
     */
    private void addAll() {
        logger.debug("持久化尚未持久化的数据");
        checkAbilityStatisticsRepository.save(checkAbilityStatisticsList);
        logger.debug("然后清空列表");
        checkAbilityStatisticsList.clear();
    }

    /**
     * 添加实体
     * @param checkAbilityStatistics 检定能力（覆盖率)
     */
    private void add(CheckAbilityStatistics checkAbilityStatistics) {
        logger.debug("添加进检定能力列表");
        this.checkAbilityStatisticsList.add(checkAbilityStatistics);

        logger.debug("列表数为1000时,进行持久化,并清空列表");
        if (checkAbilityStatisticsList.size() == 1000) {
            checkAbilityStatisticsRepository.save(checkAbilityStatisticsList);
            checkAbilityStatisticsList.clear();
        }
    }

    @Override
    public void computerCheckAbility(District district,
                                     HashMap<District, HashMap<InstrumentType, List<DeviceInstrument>>> districtDeviceInstrumentHashMap,
                                     MandatoryInstrument mandatoryInstrument,
                                     CheckAbilityStatistics checkAbilityStatistics) {
        logger.debug("计算检定能力");
        Boolean hasAbility = false;
        if (districtDeviceInstrumentHashMap.get(district).containsKey(mandatoryInstrument.getInstrumentType())) {
            hasAbility = deviceInstrumentService.getCheckAbility(
                    mandatoryInstrument.getAccuracy(),
                    mandatoryInstrument.getMinMeasureScale(),
                    mandatoryInstrument.getMaxMeasureScale(),
                    districtDeviceInstrumentHashMap.get(district).get(mandatoryInstrument.getInstrumentType()));
        }

        logger.debug("判断区域是区县、市或省，然后设置checkAbilityStatistics,并持久化");
        String districtType = district.getDistrictType().getName();
        if ( districtType.equals("区县")) {
            checkAbilityStatistics.setCountyHasCheckAbility(hasAbility);
        } else if (districtType.equals("市")) {
            checkAbilityStatistics.setCityHasCheckAbility(hasAbility);
        } else if (districtType.equals("省")) {
            checkAbilityStatistics.setProvinceHasCheckAbility(hasAbility);
        }
        checkAbilityStatisticsRepository.save(checkAbilityStatistics);

        if (district.getParentDistrict() != null) {
            this.computerCheckAbility(district.getParentDistrict(), districtDeviceInstrumentHashMap, mandatoryInstrument, checkAbilityStatistics);
        }
    }

    @Override
    public List<CheckAbilityStatistics> getAllBySpecification(Long districtId, Long disciplineId, Long instrumentTypeId, Integer minMeasureScaleWeight, Integer maxMeasureScaleWeight) {
        logger.debug("初始化省市县的区域id");
        Long countyDistrictId = null;       // 区县
        Long cityDistrictId = null;         // 市
        Long provinceDistrictId = null;     // 省

        logger.debug("判断传入的区域是省市县的哪一个");
        if (districtId != null) {
            DistrictType districtType = districtRepository.findOne(districtId).getDistrictType();

            if (districtType.getName().equals("区县")) {
                countyDistrictId = districtId;
            } else if (districtType.getName().equals("市")) {
                cityDistrictId = districtId;
            } else {
                provinceDistrictId = districtId;
            }
        }

        logger.debug("格式化数据");
        Map<String, Object> map = new HashMap<>();
        map.put("countyDistrictId", countyDistrictId);                  // 区县id
        map.put("cityDistrictId", cityDistrictId);                      // 市id
        map.put("provinceDistrictId", provinceDistrictId);              // 省id
        map.put("disciplineId", disciplineId);                          // 学科类别id
        map.put("instrumentTypeId", instrumentTypeId);                  // 器具类别id
        map.put("minMeasureScaleWeight", minMeasureScaleWeight);        // 最小测量范围权重
        map.put("maxMeasureScaleWeight", maxMeasureScaleWeight);        // 最大测量范围权重

        org.springframework.data.jpa.domain.Specification specification = CheckAbilityStatisticsSpecs.base(map);
        List<CheckAbilityStatistics> checkAbilityStatisticss = checkAbilityStatisticsRepository.findAll(specification);
        return checkAbilityStatisticss;
    }

    @Override
    public List<CheckAbilityStatisticsView> getAllByDistrictAndDiscipline(Long districtId, Long disciplineId) {
        logger.debug("初始化省市县的区域");
        District countyDistrict = this.setDistrictById(districtId).get(0);       // 区县
        District cityDistrict = this.setDistrictById(districtId).get(1);         // 市
        District provinceDistrict = this.setDistrictById(districtId).get(2);     // 省

        logger.debug("初始化学科类别");
        Discipline discipline = null;
        if (disciplineId != null) {
            discipline = disciplineRepository.findOne(disciplineId);
        }

        logger.debug("格式化信息");
        Map<String, Object> map = new HashMap<>();
        map.put("countyDistrict", countyDistrict);      // 区县
        map.put("cityDistrict", cityDistrict);          // 市
        map.put("provinceDistrict", provinceDistrict);  // 省
        map.put("discipline", discipline);              // 学科类别

        logger.debug("获取学科类别下的所有器具");
        List<InstrumentType> instrumentTypeList = instrumentTypeRepository.findAllByDisciplineId(disciplineId);

        logger.debug("初始化器具检定能力统计数组");
        List<CheckAbilityStatisticsView> checkAbilityStatisticsViewList = new ArrayList<CheckAbilityStatisticsView>();

        logger.debug("按器具类别进行分组");
        for (InstrumentType instrumentType: instrumentTypeList) {
            logger.debug("将器具类别进行格式化");
            map.put("instrumentType", instrumentType);    // 器具类别
            logger.debug("为器具总数和已授权数量进行赋值");
            int totalNum = (int) instrumentTypeRepository.count();
            int authorizedNum = this.countByMapAndDistrict(map, districtId);
            logger.debug("获取返回信息,并将返回信息放入列表中");
            CheckAbilityStatisticsView checkAbilityStatisticsView = new CheckAbilityStatisticsView(map, totalNum, authorizedNum);
            checkAbilityStatisticsViewList.add(checkAbilityStatisticsView);
        }

        return checkAbilityStatisticsViewList;
    }

    /**
     * 通过相关参数和区域id获取已授权的器具数量
     * @param map
     * @param districtId
     * @return
     */
    private int countByMapAndDistrict(Map<String, Object> map, Long districtId) {
        int authorizedNum = 0;
        logger.debug("获取所有的器具检定能力统计");
        Specification<CheckAbilityStatistics> specification = CheckAbilityStatisticsSpecs.base(map);
        List<CheckAbilityStatistics> checkAbilityStatisticsList = checkAbilityStatisticsRepository.findAll(specification);

        logger.debug("通过区域id获取区域");
        District district = districtRepository.findOne(districtId);
        String districtTypeName = district.getDistrictType().getName();

        logger.debug("遍历所有的器具检定能力统计");
        for (CheckAbilityStatistics checkAbilityStatistics: checkAbilityStatisticsList) {
            logger.debug("查询对应区域是否有检定能力");
            if (districtTypeName.equals("区县")) {
                logger.debug("如果有检定能力, 已授权数量加一");
                if (checkAbilityStatistics.getCountyHasCheckAbility().equals(true)) {
                    authorizedNum++;
                }
            } else if (districtTypeName.equals("市")) {
                if (checkAbilityStatistics.getCityHasCheckAbility().equals(true)) {
                    authorizedNum++;
                }
            } else {
                if (checkAbilityStatistics.getProvinceHasCheckAbility().equals(true)) {
                    authorizedNum++;
                }
            }
        }

        logger.debug("返回已授权数量");
        return authorizedNum;
    }

    @Override
    public List<CheckAbilityStatisticsView> getAllByDistrictAndInstrumentType(Long districtId, Long instrumentTypeId) {
        logger.debug("初始化省市县的区域");
        District countyDistrict = this.setDistrictById(districtId).get(0);       // 区县
        District cityDistrict = this.setDistrictById(districtId).get(1);         // 市
        District provinceDistrict = this.setDistrictById(districtId).get(2);     // 省

        logger.debug("初始化器具类别");
        InstrumentType instrumentType = null;
        Discipline discipline = null;
        if (instrumentTypeId != null) {
            instrumentType = instrumentTypeRepository.findOne(instrumentTypeId);
            logger.debug("初始化学科类别");
            discipline = instrumentType.getInstrumentFirstLevelType().getDiscipline();
        }

        logger.debug("格式化数据");
        Map<String, Object> map = new HashMap<>();
        map.put("countyDistrict", countyDistrict);          // 区县
        map.put("cityDistrict", cityDistrict);              // 市
        map.put("provinceDistrict", provinceDistrict);      // 省
        map.put("InstrumentType", instrumentType);          // 器具类别
        map.put("discipline", discipline);                  // 学科类别

        logger.debug("通过器具类别获取所有的精确度等级");
        List<Accuracy> accuracyList = (List<Accuracy>) accuracyRepository.findAllByInstrumentType(instrumentType);

        logger.debug("初始化器具检定能力统计数组");
        List<CheckAbilityStatisticsView> checkAbilityStatisticsViewList = new ArrayList<CheckAbilityStatisticsView>();

        logger.debug("按精确度等级进行分类");
        for (Accuracy accuracy: accuracyList) {
            logger.debug("格式化准确度等级");
            map.put("accuracy", accuracy);
            logger.debug("为器具总数和已授权的数量赋值");
            int totalNum = mandatoryInstrumentRepository.countByAccuracyAndInstrumentTypeAndDistrict(accuracy, instrumentType, districtId);
            int authorizedNum = this.countByMapAndDistrict(map, districtId);
            logger.debug("获取返回信息,并将返回信息放入返回列表中");
            CheckAbilityStatisticsView checkAbilityStatisticsView = new CheckAbilityStatisticsView(map, totalNum, authorizedNum);
            checkAbilityStatisticsViewList.add(checkAbilityStatisticsView);
        }
        return checkAbilityStatisticsViewList;
    }

    /**
     * 通过传入的区域Id设置区市县
     * @param districtId
     * @return
     */
    private List<District> setDistrictById(Long districtId) {
        List<District> districtList = new ArrayList<>();
        logger.debug("初始化将区市省都赋值为空");
        District countyDistrict = null;       // 区县
        District cityDistrict = null;         // 市
        District provinceDistrict = null;     // 省

        logger.debug("通过传入的区域Id判断对应的区域的值");
        if (districtId != null) {
            District district = districtRepository.findOne(districtId);
            DistrictType districtType = district.getDistrictType();

            if (districtType.getName().equals("区县")) {
                countyDistrict = district;
                cityDistrict = district.getParentDistrict();
                provinceDistrict = district.getParentDistrict().getParentDistrict();
            } else if (districtType.getName().equals("市")) {
                cityDistrict = district;
                provinceDistrict = district.getParentDistrict();
            } else {
                provinceDistrict = district;
            }
        }

        districtList.add(countyDistrict);
        districtList.add(cityDistrict);
        districtList.add(provinceDistrict);
        return districtList;
    }

    @Override
    public Boolean hasFinished() {
        return !inExecute;
    }

    @Override
    public void asyncDataInit() {
        // 由于该函数的调用时间过长,所以采用异步调用的方式
        CompletableFuture.runAsync(() -> {
            if (!inExecute) {
                // 设置函数是否执行为true,函数开始执行
                inExecute = true;
                // 调用函数
                this.dataInit();
                // 设置函数是否执行为false,函数结束执行
                inExecute = false;
            }
        });
    }
}
