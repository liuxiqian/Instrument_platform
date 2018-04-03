package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.repository.MandatoryInstrumentCheckApplyRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by panjie on 17/11/22.
 */
@Component
public class CheckApplyServiceTestData {

    private static Logger logger = Logger.getLogger(CheckApplyServiceTestData.class.getName());

    @Autowired
    MandatoryInstrumentCheckApplyRepository mandatoryInstrumentCheckApplyRepository;                                        // 检定申请

    @Autowired
    UserService userService;                                                          // 用户
    @Autowired
    PurposeService purposeService;                                                    // 用途
    @Autowired
    AccuracyService accuracyService;                                                  // 精确度
    @Autowired
    MandatoryInstrumentCheckApplyService mandatoryInstrumentCheckApplyService;                                              // 检定申请
    @Autowired
    MeasureScaleService measureScaleService;                                          // 测量范围
    @Autowired
    MandatoryInstrumentService mandatoryInstrumentService;                            // 强检器具
    @Autowired
    TechnicalInstitutionDepartmentService technicalInstitutionDepartmentService;      // 技术机构

    @Qualifier("MandatoryInstrumentTypeService")
    @Autowired
    InstrumentTypeService instrumentTypeService;                                      // 强检器具类别
    @Qualifier("DepartmentService")
    @Autowired
    DepartmentService departmentService;                                              // 部门

    @Autowired
    MandatoryInstrumentServiceTestData mandatoryInstrumentServiceTestData;            // 强检器具测试数据

    @Autowired
    @Qualifier("WorkService") WorkService workService;  // 工作

    public Long reply(User currentLoginUser) {
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = new MandatoryInstrumentCheckApply();
        mandatoryInstrumentCheckApplyRepository.save(mandatoryInstrumentCheckApply);
        Work work = new Work();
        work.setApply(mandatoryInstrumentCheckApply);
        workService.saveWorkWithCurrentUserAudit(work);
        return mandatoryInstrumentCheckApply.getId();
    }

    public MandatoryInstrumentCheckApply save(User user) {
        logger.debug("获取有检定能力的器具");
        Set<MandatoryInstrument> mandatoryInstruments = mandatoryInstrumentServiceTestData.getHasCheckAbilityMandatoryInstruments(user);

        logger.debug("新建一个技术机构");
        Department technicalInstitutionDepartment = technicalInstitutionDepartmentService.getOneTechnicalInstitutionDepartment();

        logger.debug("新建一个申请");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = new MandatoryInstrumentCheckApply();
        mandatoryInstrumentCheckApply.setMandatoryInstrumentSet(new HashSet<>(mandatoryInstruments));

        return mandatoryInstrumentCheckApply;
    }

    /**
     * 生成用于CheckApplyServiceTest中
     * generatePdfApplyByIdWhenHaveAcceptDepartment
     * generatePdfApplyByIdWhenNoAcceptDepartment
     * 使用的已添加好基础数据的检定申请
     * @return MandatoryInstrumentCheckApply 检定申请
     * @author zhangxishuo
     */
    public MandatoryInstrumentCheckApply generateCheckApplyData(User currentUser) {
        logger.info("获取测试部门");
        Department department  = currentUser.getDepartment();
        department.setCode("测试部门代码");
        department.setCode("测试部门");

        logger.info("获取申请, 设置属性, 并保存");
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = mandatoryInstrumentCheckApplyService.getOneSavedCheckApply();
        mandatoryInstrumentCheckApply.setMandatoryInstrumentSet(this.getMandatoryInstrumentSet());
        mandatoryInstrumentCheckApply.setCreateUser(currentUser);
        mandatoryInstrumentCheckApply.setDepartment(department);

        return mandatoryInstrumentCheckApply;
    }

    /**
     * 生成一个含有两个元素的已含有基础数据的强检器具集合
     * @return Set<MandatoryInstrument> 强检器具集合
     * @author zhangxishuo
     */
    public Set<MandatoryInstrument> getMandatoryInstrumentSet() {
        Set<MandatoryInstrument> mandatoryInstrumentSet = new HashSet<MandatoryInstrument>();

        for (int i = 0; i < 2; i ++) {
            logger.info("获取强检器具");
            MandatoryInstrument mandatoryInstrument = mandatoryInstrumentService.getOneSavedMandatoryInstrument();
            mandatoryInstrument.setName("测试器具");

            logger.info("设置精度");
            Accuracy accuracy = accuracyService.getOneSavedAccuracy();
            mandatoryInstrument.setAccuracy(accuracy);

            logger.info("设置测量范围");
            MeasureScale measureScale = measureScaleService.getOneSavedMeasureScale();
            MeasureScale measureScale1 = measureScaleService.getOneSavedMeasureScale();
            mandatoryInstrument.setMinMeasureScale(measureScale);
            mandatoryInstrument.setMaxMeasureScale(measureScale1);

            logger.info("设置器具类型");
            InstrumentType instrumentType = instrumentTypeService.getOneSavedInstrumentType();
            mandatoryInstrument.setInstrumentType(instrumentType);

            logger.info("设置生产部门");
            Department department = departmentService.getOneSavedDepartment();
            mandatoryInstrument.setGenerativeDepartment(department);

            logger.info("设置规格型号");
            mandatoryInstrument.setSpecificationName("规格型号");

            logger.info("设置用途");
            Purpose purpose = purposeService.getOneSavedPurpose();
            mandatoryInstrument.setPurpose(purpose);

            mandatoryInstrumentSet.add(mandatoryInstrument);
        }

        return mandatoryInstrumentSet;
    }
}
