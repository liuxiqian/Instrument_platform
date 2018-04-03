package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.InstrumentCheckInfo;
import com.mengyunzhi.measurement.entity.MandatoryInstrument;
import com.mengyunzhi.measurement.entity.User;
import com.mengyunzhi.measurement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by liming on 17-8-2.
 * 强检器具检定信息测试数据
 */
@Component
public class MandatoryInstrumentCheckInfoServiceTestData {
    @Autowired protected UserService userService;
    @Autowired protected DepartmentRepository departmentRepository;
    @Autowired protected MandatoryInstrumentRepository mandatoryInstrumentRepository;
    @Autowired protected InstrumentCheckInfoRepository instrumentCheckInfoRepository;

    public void getOneMandatoryInstrumentCheckInfo(Department checkDepartment, User currentLoginUser, MandatoryInstrument mandatoryInstrument, InstrumentCheckInfo instrumentCheckInfo) {

        //用户
        userService.setCurrentLoginUser(currentLoginUser);
        //强检器具信息
        mandatoryInstrumentRepository.save(mandatoryInstrument);
        //器具检定信息
        instrumentCheckInfo.setCheckDepartment(checkDepartment);
        instrumentCheckInfo.setMandatoryInstrument(mandatoryInstrument);
        instrumentCheckInfoRepository.save(instrumentCheckInfo);
    }


}
