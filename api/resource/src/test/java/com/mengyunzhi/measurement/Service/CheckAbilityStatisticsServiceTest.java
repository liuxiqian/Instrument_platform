package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.output.CheckAbilityStatisticsView;
import com.mengyunzhi.measurement.repository.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

/**
 * 器具检定能力统计 service测试
 * @Author poshichao
 */
public class CheckAbilityStatisticsServiceTest extends ServiceTest {
    private static final Logger logger = Logger.getLogger(CheckAbilityStatisticsServiceTest.class.getName());
    @Autowired
    private DepartmentRepository departmentRepository;                      // 部门
    @Autowired
    private CheckAbilityStatisticsService checkAbilityStatisticsService;    // 器具检定能力统计
    @Autowired
    private DistrictRepository districtRepository;                          // 区域
    @Autowired
    private DisciplineRepository disciplineRepository;                      // 学科类别
    @Autowired
    private MandatoryInstrumentRepository mandatoryInstrumentRepository;    // 强检器具
    @Autowired
    private CheckAbilityStatisticsRepository checkAbilityStatisticsRepository;  // 器具检定能力统计
    @Autowired
    private DistrictTypeRepository districtTypeRepository;                  // 区域类别
    @Autowired
    private DeviceSetRepository deviceSetRepository;                        // 标准装置
    @Autowired
    private DeviceInstrumentRepository deviceInstrumentRepository;          // 授权检定项目
    @Autowired
    private AccuracyRepository accuracyRepository;                          // 精确度
    @Autowired
    private MeasureScaleRepository measureScaleRepository;                  // 测量范围
    @Autowired
    private InstrumentFirstLevelTypeRepository instrumentFirstLevelTypeRepository;  // 一级类别
    @Autowired
    private InstrumentTypeRepository instrumentTypeRepository;              // 器具类别
    @Test
    public void getAllBySpecification() throws Exception {
        logger.info("创建一个器具受检能力 对象");
        CheckAbilityStatistics checkAbilityStatistics = new CheckAbilityStatistics();

        logger.info("为对象赋值");
        District district = new District();
        district.setName("测试市");
        DistrictType districtType = new DistrictType();
        districtType.setName("市");
        districtTypeRepository.save(districtType);
        district.setDistrictType(districtType);
        districtRepository.save(district);
        Department department = new Department();
        department.setDistrict(district);
        departmentRepository.save(department);
        Discipline discipline = new Discipline();
        discipline.setName("测试学科类别");
        disciplineRepository.save(discipline);
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        checkAbilityStatistics.setMandatoryInstrument(mandatoryInstrument, null, 1, 1, null, discipline, department, district);
        checkAbilityStatisticsRepository.save(checkAbilityStatistics);

        logger.info("调用service方法进项查询");
        List<CheckAbilityStatistics> checkAbilityStatisticsPage = checkAbilityStatisticsService.getAllBySpecification(district.getId(), null, null, 1, 1);

        logger.info("断言查询到的条数为1");
        assertThat(checkAbilityStatisticsPage.size()).isEqualTo(1);
    }

    @Test
    public void dataInit() throws Exception {
        logger.info("新建一个器具检定能力对象");
        CheckAbilityStatistics checkAbilityStatistics = new CheckAbilityStatistics();
        checkAbilityStatisticsRepository.save(checkAbilityStatistics);
        Long id = checkAbilityStatistics.getId();

        logger.info("按id查找,断言对象存在");
        CheckAbilityStatistics oldCheckAbilityStatistics = checkAbilityStatisticsRepository.findOne(id);
        assertThat(oldCheckAbilityStatistics).isNotNull();

        logger.info("调用service方法");
        checkAbilityStatisticsService.dataInit();

        logger.info("由于重新清空过数据表,所以原来的数据不存在了");
        CheckAbilityStatistics newCheckAbilityStatistics = checkAbilityStatisticsRepository.findOne(id);
        assertThat(newCheckAbilityStatistics).isNull();

    }

    @Test
    public void computerAbilityAndDataInit() throws Exception {
        logger.info("初始化区域授权检定能力");
        HashMap<District, HashMap<InstrumentType, List<DeviceInstrument>>> districtDeviceInstrumentHashMap = new HashMap<>();

        logger.info("获取所有的区域信息");
        List<District> districtList = (List<District>) districtRepository.findAll();

        logger.info("新建器具并持久化");
        MandatoryInstrument mandatoryInstrument = new MandatoryInstrument();
        Department department = new Department();
        department.setDistrict(districtList.get(2));
        departmentRepository.save(department);
        Accuracy accuracy1 = new Accuracy();
        accuracy1.setWeight(0);
        accuracyRepository.save(accuracy1);
        MeasureScale minMeasureScale1 = new MeasureScale();
        MeasureScale maxMeasureScale1 = new MeasureScale();
        minMeasureScale1.setWeight(0);
        maxMeasureScale1.setWeight(0);
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        Discipline discipline = new Discipline();
        disciplineRepository.save(discipline);
        instrumentFirstLevelType.setDiscipline(discipline);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);
        instrumentTypeRepository.save(instrumentType);
        maxMeasureScale1.setInstrumentType(instrumentType);
        measureScaleRepository.save(minMeasureScale1);
        measureScaleRepository.save(maxMeasureScale1);
        mandatoryInstrument.setDepartment(department);
        mandatoryInstrument.setAccuracy(accuracy1);
        mandatoryInstrument.setMinMeasureScale(minMeasureScale1);
        mandatoryInstrument.setMaxMeasureScale(maxMeasureScale1);
        mandatoryInstrumentRepository.save(mandatoryInstrument);

        logger.info("设置一个该区域的授权检定装置");
        DeviceInstrument deviceInstrument = new DeviceInstrument();
        DeviceSet deviceSet = new DeviceSet();
        deviceSet.setDepartment(department);
        deviceSetRepository.save(deviceSet);
        deviceInstrument.setDeviceSet(deviceSet);

        logger.info("为该授权检定装置设置精确度 测量范围权重");
        Accuracy accuracy = new Accuracy();
        accuracy.setWeight(1);
        accuracyRepository.save(accuracy);
        MeasureScale minMeasureScale = new MeasureScale();
        MeasureScale maxMeasureScale = new MeasureScale();
        minMeasureScale.setWeight(0);
        maxMeasureScale.setWeight(1);
        measureScaleRepository.save(minMeasureScale);
        measureScaleRepository.save(maxMeasureScale);
        deviceInstrument.setAccuracy(accuracy);
        deviceInstrument.setMinMeasureScale(minMeasureScale);
        deviceInstrument.setMaxMeasureScale(maxMeasureScale);

        logger.info("持久化授权检定装置");
        deviceInstrumentRepository.save(deviceInstrument);

        logger.info("调用service方法,为区域添加检定能力");
        checkAbilityStatisticsService.addDeviceInstrumentListOfDistrictList(districtDeviceInstrumentHashMap, districtList);

        logger.info("调用service方法,计算检定能力,并进行数据的初始持久化操作");
        checkAbilityStatisticsService.computerAbilityAndDataInit(districtDeviceInstrumentHashMap, districtList);

        logger.info("断言器具检定能力不为空");
        List<CheckAbilityStatistics> checkAbilityStatisticsList = (List<CheckAbilityStatistics>) checkAbilityStatisticsRepository.findAll();
        assertThat(checkAbilityStatisticsList.size()).isNotZero();

        logger.info("断言器具检定能力 市具有检定能力");
        assertThat(checkAbilityStatisticsList.get(0).getCityHasCheckAbility()).isTrue();
    }

    @Test
    public void addDeviceInstrumentListOfDistrictList() throws Exception {
        logger.info("初始化区域授权检定能力");
        HashMap<District, HashMap<InstrumentType, List<DeviceInstrument>>> districtDeviceInstrumentHashMap = new HashMap<>();

        logger.info("获取所有的区域信息");
        List<District> districtList = (List<District>) districtRepository.findAll();

        logger.info("调用service方法,为区域添加检定能力");
        checkAbilityStatisticsService.addDeviceInstrumentListOfDistrictList(districtDeviceInstrumentHashMap, districtList);
        logger.info("断言区域检定能力大小不为0");
        assertThat(districtDeviceInstrumentHashMap.size()).isNotZero();
    }

    @Test
    public  void hasFinished() throws Exception {
        logger.info("调用检测函数");
        Boolean result = checkAbilityStatisticsService.hasFinished();

        logger.info("断言函数的返回值为true");
        assertThat(result).isTrue();
    }

    @Test
    public void getAllByDistrictAndDiscipline() throws Exception {
        logger.info("设置区域");
        DistrictType districtType = new DistrictType();
        districtType.setName("省");
        districtTypeRepository.save(districtType);
        District district = new District();
        district.setDistrictType(districtType);
        districtRepository.save(district);

        logger.info("设置学科类别");
        Discipline discipline = new Discipline();
        disciplineRepository.save(discipline);
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        instrumentFirstLevelType.setDiscipline(discipline);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);
        instrumentTypeRepository.save(instrumentType);

        logger.info("调用service函数");
        List<CheckAbilityStatisticsView> checkAbilityStatisticsViewList = checkAbilityStatisticsService.getAllByDistrictAndDiscipline(district.getId(), discipline.getId());

        logger.info("断言返回的结果不为空");
        assertThat(checkAbilityStatisticsViewList.size()).isNotZero();
    }

    @Test
    public void getAllByDistrictAndInstrumentType() throws Exception {
        logger.info("设置区域");
        DistrictType districtType = new DistrictType();
        districtType.setName("省");
        districtTypeRepository.save(districtType);
        District district = new District();
        district.setDistrictType(districtType);
        districtRepository.save(district);
        Long districtId = district.getId();

        logger.info("设置学科类别");
        Discipline discipline = new Discipline();
        disciplineRepository.save(discipline);
        InstrumentFirstLevelType instrumentFirstLevelType = new InstrumentFirstLevelType();
        instrumentFirstLevelType.setDiscipline(discipline);
        instrumentFirstLevelTypeRepository.save(instrumentFirstLevelType);

        logger.info("设置器具名称");
        InstrumentType instrumentType = new InstrumentType();
        instrumentType.setInstrumentFirstLevelType(instrumentFirstLevelType);
        instrumentTypeRepository.save(instrumentType);
        Long instrumentId = instrumentType.getId();

        logger.info("设置精确地等级");
        Accuracy accuracy = new Accuracy();
        accuracy.setInstrumentType(instrumentType);
        accuracyRepository.save(accuracy);

        logger.info("调用service函数");
        List<CheckAbilityStatisticsView> checkAbilityStatisticsViewList = checkAbilityStatisticsService.getAllByDistrictAndInstrumentType(districtId, instrumentId);
        logger.info("断言返回的结果不为空");
        assertThat(checkAbilityStatisticsViewList.size()).isNotZero();
    }
}