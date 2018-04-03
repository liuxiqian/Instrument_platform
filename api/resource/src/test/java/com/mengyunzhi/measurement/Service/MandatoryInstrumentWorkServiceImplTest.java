package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.User;
import com.mengyunzhi.measurement.entity.Work;
import com.mengyunzhi.measurement.repository.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by panjie on 17/7/16.
 * 强制检定器具申请 工作
 */
public class MandatoryInstrumentWorkServiceImplTest extends ServiceTest {
    private Logger logger = Logger.getLogger(MandatoryInstrumentWorkServiceImplTest.class.getName());
    @Autowired
    protected DepartmentRepository departmentRepository;        // 部门
    @Autowired protected UserService userService;               // 用户
    @Autowired protected MandatoryInstrumentWorkService mandatoryInstrumentWorkService; // 强制检定申请工作
    @Autowired
    protected UserRepository userRepository;        // 用户
    @Autowired @Qualifier("DepartmentService") DepartmentService departmentService;  // 部门
    @Autowired @Qualifier("WorkService") protected WorkService workService;          // 工作
    @Autowired ApplyRepository applyRepository;                                         // 申请
    @Autowired WorkRepository workRepository;
    @Autowired
    MandatoryInstrumentApplyService mandatoryInstrumentApplyService;
    @Autowired
    MandatoryInstrumentApplyRepository mandatoryInstrumentApplyRepository;
    @Autowired @Qualifier("ApplyService") ApplyService applyService;// 工作仓库

    @Test
    public void pageOfCurrentLoginUser() {
        logger.info("新建一个部门");
        Department department = departmentService.getOneDepartment();
        departmentRepository.save(department);

        logger.info("新建一个用户");
        User user = UserService.getOneUser();
        user.setDepartment(department);
        userService.setCurrentLoginUser(user);

        logger.info("调用方法");
        PageRequest pageRequest = new PageRequest(0,1);
        Page<Work> works = mandatoryInstrumentWorkService.pageOfCurrentLoginUser(pageRequest);

        logger.info("断言获取的总数为0");
        assertThat(works.getTotalElements()).isEqualTo(0);
    }

    @Test
    public void pageAllOfCurrentLoginUserBySpecification() {
        //todo: 增加单元测试
    }

}