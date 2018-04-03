package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentCheckApplyRepository;
import com.mengyunzhi.measurement.repository.DepartmentRepository;
import com.mengyunzhi.measurement.repository.InstrumentCheckInfoRepository;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentRepository;
import com.mengyunzhi.measurement.specs.MandatoryInstrumentCheckInfoSpecs;
import org.apache.log4j.Logger;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.expression.AccessException;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.domain.Specification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chuhang on 17-8-2.
 * 强制器具检定信息的service实现
 */
@Service
public class MandatoryInstrumentCheckInfoServiceImpl extends InstrumentCheckInfoServiceImpl implements MandatoryInstrumentCheckInfoService {
    private Logger logger = Logger.getLogger(DepartmentServiceImpl.class.getName());

    @Autowired
    private InstrumentCheckInfoRepository instrumentCheckInfoRepository;    // 器具检定信息
    @Autowired
    private UserService userService;
    @Autowired
    private MandatoryInstrumentCheckInfoService mandatoryInstrumentCheckInfoService;
    @Autowired
    private DistrictService districtService;        //区域
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private MandatoryInstrumentService mandatoryInstrumentService;
    @Autowired
    private MandatoryInstrumentCheckApplyRepository mandatoryInstrumentCheckApplyRepository;   // 检定申请
    @Autowired
    MandatoryInstrumentCheckApplyService mandatoryInstrumentCheckApplyService; // 检定申请
    @Autowired
    MandatoryInstrumentRepository mandatoryInstrumentRepository;    // 强检器具

    @Override
    public void saveViaTechnologyDepartment(InstrumentCheckInfo instrumentCheckInfo) throws AccessException {
        logger.debug("获取传入的检定申请中，是否包含这个强检器具， 如果没有找到，则抛出异常");
        instrumentCheckInfo.setMandatoryInstrumentCheckApply(mandatoryInstrumentCheckApplyRepository.findOne(instrumentCheckInfo.getMandatoryInstrumentCheckApply().getId()));
        if (!this.validateMandatoryInstrumentInCheckApply(instrumentCheckInfo)) {
            throw new AccessException("传入的器具ID与检定申请不匹配");
        }

        logger.debug("获取当前用户所在的部门, 保存创建用户");
        Department department = userService.getCurrentLoginUser().getDepartment();
        instrumentCheckInfo.setCheckDepartment(department);
        instrumentCheckInfo.setCreateUser(userService.getCurrentLoginUser());

        if (!instrumentCheckInfo.getMandatoryInstrumentCheckApply().getCurrentWork().getWork().getAuditDepartment().getId().equals(department.getId())) {
            logger.warn("当前申请的检定机构与您的登录信息不相符");
            throw new AccessException("您不是指定的检定机构，无权限上传检定结果");
        }

        this.saveNewRecordAndDeleteRelated(instrumentCheckInfo);
    }

    @Override
    public void saveViaApplyInstrumentUserDepartment(InstrumentCheckInfo instrumentCheckInfo) throws AccessException {
        logger.debug("获取传入的检定申请中，是否包含这个强检器具， 如果没有找到，则抛出异常");
        instrumentCheckInfo.setMandatoryInstrumentCheckApply(mandatoryInstrumentCheckApplyRepository.findOne(instrumentCheckInfo.getMandatoryInstrumentCheckApply().getId()));
        if (instrumentCheckInfo.getMandatoryInstrumentCheckApply().getCurrentWork().getWork().getAuditDepartment().getDepartmentType().getName().equals("技术机构")) {
            throw new AccessException("存在检定机构的检定申请，只有相关检定机构有权限进行上传，您无权上传相关检定记录");
        }

        if (!this.validateMandatoryInstrumentInCheckApply(instrumentCheckInfo)) {
            throw new AccessException("传入的器具ID与检定申请不匹配");
        }

        logger.debug("获取当前用户所在的部门, 保存创建用户");
        Department department = userService.getCurrentLoginUser().getDepartment();
        instrumentCheckInfo.setCreateUser(userService.getCurrentLoginUser());

        logger.debug("器具用户与检定机构，均有上传检定记录的权限。");
        if (!instrumentCheckInfo.getMandatoryInstrumentCheckApply().getDepartment().getId().equals(department.getId())) {
            throw new AccessException("当前检定申请的发起者不是您所在的部门");
        }

        logger.debug("按传入的检定部门名称，查询检定部门。如果该检定部门已存在，则取数据表中已存在检定部门");
        instrumentCheckInfo.getCheckDepartment().setName(instrumentCheckInfo.getCheckDepartment().getName().trim());
        Department checkDepartment = departmentRepository.findTopOneByName(instrumentCheckInfo.getCheckDepartment().getName());
        if (checkDepartment != null) {
            instrumentCheckInfo.setCheckDepartment(checkDepartment);
        }

        this.saveNewRecordAndDeleteRelated(instrumentCheckInfo);
        return;
    }

    /**
     * 检定要上传检定记录的强检器具是否存在于指定的检定申请中
     *
     * @param instrumentCheckInfo
     * @return
     * @Author panjie
     */
    private Boolean validateMandatoryInstrumentInCheckApply(InstrumentCheckInfo instrumentCheckInfo) {
        Boolean found = false;
        for (MandatoryInstrument mandatoryInstrument : instrumentCheckInfo.getMandatoryInstrumentCheckApply().getMandatoryInstrumentSet()) {
            if (!found && (mandatoryInstrument.getId().equals(instrumentCheckInfo.getMandatoryInstrument().getId()))) {
                found = true;
            }
        }
        return found;
    }

    /**
     * 保存检定记录(同时，删除历史上相关的记录)
     * 当用户重复上传检定记录时，保证只保留最后一次上传的记录
     *
     * @param instrumentCheckInfo panjie
     */
    protected void saveNewRecordAndDeleteRelated(InstrumentCheckInfo instrumentCheckInfo) {
        logger.debug("查询是否已存在历史数据，已存在，则删除。即，只记录最后一次上传的检定记录");
        List<InstrumentCheckInfo> instrumentCheckInfoList
                = (List<InstrumentCheckInfo>) instrumentCheckInfoRepository.findAllByMandatoryInstrumentAndMandatoryInstrumentCheckApply(
                instrumentCheckInfo.getMandatoryInstrument(),
                instrumentCheckInfo.getMandatoryInstrumentCheckApply()
        );

        logger.debug("保存强制器具检定信息");
        instrumentCheckInfoRepository.save(instrumentCheckInfo);

        logger.debug("更新器具最近一次的检定信息");
        MandatoryInstrument mandatoryInstrument = mandatoryInstrumentRepository.findOne(instrumentCheckInfo.getMandatoryInstrument().getId());
        mandatoryInstrument.setLastInstrumentCheckInfo(instrumentCheckInfo);
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        instrumentCheckInfo.setMandatoryInstrument(mandatoryInstrument);

        logger.debug("删除原检定记录");
        if (instrumentCheckInfoList.size() > 0) {
            instrumentCheckInfoRepository.delete(instrumentCheckInfoList);
        }

        return;
    }

    @Override
    public void delete(Long id) throws AccessException {
        InstrumentCheckInfo instrumentCheckInfo = instrumentCheckInfoRepository.findOne(id);

        if (null == instrumentCheckInfo) {
            throw new ObjectNotFoundException(404, "未找到相关的强制器具检定信息实体");
        }

        //判断器具检定部门是否为当前用户所在部门
        try {
            if (userService.getCurrentLoginUser().getDepartment().getId() != instrumentCheckInfo.getCheckDepartment().getId()) {
                throw new SecurityException("该用户无此权限");
            }
        } catch (Exception e) {
            logger.error("获取当前登录用户部门或检定信息审核部门时发生异常");
            throw new AccessException("获取当前登录用户部门或检定信息审核部门时发生异常");
        }

        instrumentCheckInfoRepository.delete(instrumentCheckInfo);
        return;
    }

    @Override
    public void update(Long id, InstrumentCheckInfo instrumentCheckInfo) {
        instrumentCheckInfo.setId(id);
        instrumentCheckInfoRepository.save(instrumentCheckInfo);
        return;
    }

    @Override
    public Page<InstrumentCheckInfo> pageAllByMandatoryInstrumentId(Long mandatoryInstrumentId, Pageable pageable) {
        Page<InstrumentCheckInfo> instrumentCheckInfos = instrumentCheckInfoRepository.findAllByMandatoryInstrumentId(mandatoryInstrumentId, pageable);
        return instrumentCheckInfos;
    }

    @Override
    public Page<InstrumentCheckInfo> pageAllOfManagementUser(User currentUser, Pageable pageable) {
        //获取当前用户的所以子区域
        List<District> districts = districtService.getSonsListByDistrict(currentUser.getDepartment().getDistrict());

        Page<InstrumentCheckInfo> instrumentCheckInfos = instrumentCheckInfoRepository.findAllByDistricts(districts, pageable);
        return instrumentCheckInfos;
    }

    /**
     * 获取分页数据
     *
     * @param year                       年度
     * @param districts                  区域
     * @param applyDepartmentId          申请部门ID
     * @param checkDepartmentId          审核部门ID
     * @param disciplineId               学科类别id
     * @param instrumentFirstLevelTypeId 一级类别
     * @param instrumentTypeId           （二级）类别
     * @param certificateNum             证书编号
     * @param checkResultId              检定结果
     * @param mandatoryInstrumentId      强检器具
     * @param name                       器具名称
     * @param accuracyId                 精确度
     * @param minMeasuringScaleWeight    最小测量范围权重
     * @param maxMeasuringScaleWeight    最大测量范围权重
     * @param pageable                   分页信息
     * @return
     * @panjie
     */
    @Override
    public Page<InstrumentCheckInfo> pageAllBySpecification(Integer year, List<District> districts, Long applyDepartmentId, Long checkDepartmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, String certificateNum, Long checkResultId, Long mandatoryInstrumentId, String name, Long accuracyId, Integer minMeasuringScaleWeight, Integer maxMeasuringScaleWeight, Pageable pageable) {
        // 根据用户的查询条件获取检定信息
        Map<String, Object> map = new HashMap<>();
        map.put("checkDepartmentId", checkDepartmentId);
        map.put("disciplineId", disciplineId);
        map.put("instrumentFirstLevelTypeId", instrumentFirstLevelTypeId);
        map.put("instrumentTypeId", instrumentTypeId);
        map.put("certificateNum", certificateNum);
        map.put("checkResultId", checkResultId);
        map.put("mandatoryInstrumentId", mandatoryInstrumentId);
        map.put("districts", districts);
        map.put("name", name);
        map.put("applyDepartmentId", applyDepartmentId);
        map.put("year", year);
        map.put("accuracyId", accuracyId);
        map.put("minMeasuringScaleWeight", minMeasuringScaleWeight);
        map.put("maxMeasuringScaleWeight", maxMeasuringScaleWeight);

        Specification specification = MandatoryInstrumentCheckInfoSpecs.base(map);
        Page<InstrumentCheckInfo> instrumentCheckInfos = instrumentCheckInfoRepository.findAll(specification, pageable);
        return instrumentCheckInfos;
    }

    @Override
    public Page<InstrumentCheckInfo> pageAllOfManagementDepartmentBySpecification(
            Integer year, Long districtId, Long departmentId, Long checkDepartmentId, Long disciplineId,
            Long instrumentFirstLevelTypeId, Long instrumentTypeId, String certificateNum, Long checkResultId,
            Long mandatoryInstrumentId, String name, Long accuracyId, Integer minMeasuringScaleWeight,
            Integer maxMeasuringScaleWeight, Pageable pageable) {

        logger.info("获取当前用户");
        User user = userService.getCurrentLoginUser();

        logger.info("声明用户所查询的区域及子区域");
        List<District> districts = mandatoryInstrumentService.getRightDistrictsByRootDistrictAndSonDistrictId(user.getDepartment().getDistrict(), districtId);

        return this.pageAllBySpecification(
                year, districts, departmentId, checkDepartmentId, disciplineId, instrumentFirstLevelTypeId, instrumentTypeId,
                certificateNum, checkResultId, mandatoryInstrumentId, name, accuracyId, minMeasuringScaleWeight, maxMeasuringScaleWeight, pageable);
    }

    @Override
    public Page<InstrumentCheckInfo> pageAllOfMeasureUserBySpecification(
            Integer year, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, Long checkResultId, Long mandatoryInstrumentId, String name, Pageable pageable) {
        logger.info("获取当前用户所在部门");
        Department currentDepartment = userService.getCurrentLoginUser().getDepartment();
        return this.pageAllBySpecification(year,
                null,
                currentDepartment.getId(),
                null,
                disciplineId,
                instrumentFirstLevelTypeId,
                instrumentTypeId,
                null,
                checkResultId,
                mandatoryInstrumentId,
                name,
                null,
                null,
                null,
                pageable);
    }

    @Override
    public Page<InstrumentCheckInfo> pageAllOfTechnicalInstitutionDepartmentBySpecification(
            Integer year, Long districtId, Long departmentId, Long disciplineId, Long instrumentFirstLevelTypeId, Long instrumentTypeId, String certificateNum, Long checkResultId, Long accuracyId, Long mandatoryInstrumentId, String name, Pageable pageable) {
        logger.info("获取当前用户");
        User user = userService.getCurrentLoginUser();

        logger.info("声明用户所查询的区域及子区域");
        List<District> districts = null;
        if (districtId != null) {
            districts = mandatoryInstrumentService.getRightDistrictsByRootDistrictAndSonDistrictId(user.getDepartment().getDistrict(), districtId);
        }

        return this.pageAllBySpecification(
                year,
                districts,
                null,
                user.getDepartment().getId(),
                disciplineId,
                instrumentFirstLevelTypeId,
                instrumentTypeId,
                certificateNum,
                checkResultId,
                mandatoryInstrumentId,
                name,
                accuracyId,
                null,
                null,
                pageable);
    }

    @Override
    public Page<InstrumentCheckInfo> pageAllOfCurrentUser(Pageable pageable) {
        //取出当前用用户
        User currentUser = userService.getCurrentLoginUser();
        Page<InstrumentCheckInfo> instrumentCheckInfos;
        //根据当前登录用户获取相应相应强检器具的检定信息
        switch (currentUser.getDepartment().getDepartmentType().getName()) {
            case "技术机构":
                instrumentCheckInfos = instrumentCheckInfoRepository.getAllByCheckDepartment(currentUser.getDepartment(), pageable);
                break;
            case "器具用户":
                instrumentCheckInfos = instrumentCheckInfoRepository.findAllByDepartment(currentUser.getDepartment(), pageable);
                break;
            case "管理部门":
                instrumentCheckInfos = mandatoryInstrumentCheckInfoService.pageAllOfManagementUser(currentUser, pageable);
                break;
            default:
                instrumentCheckInfos = null;
                break;
        }

        return instrumentCheckInfos;
    }


}
