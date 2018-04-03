package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.TechnicalInstitutionBackedReasonService;
import com.mengyunzhi.measurement.entity.TechnicalInstitutionBackedReason;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by chuhang on 17-11-10
 * 技术机构退回原因
 */

@RestController
@RequestMapping("/TechnicalInstitutionBackedReason")
@Api(tags = "TechnicalInstitutionBackedReason 技术机构退回原因", description = "技术机构退回原因")
public class TechnicalInstitutionBackedReasonController {
    @Autowired
    TechnicalInstitutionBackedReasonService managementDepartmentBackedReasonService;

    @ApiOperation(value = "getTop10TechnicalInstitutionBackedReasons", notes = "获取最近10条技术机构退回原因", nickname = "TechnicalInstitutionBackedReason_getTop10TechnicalInstitutionBackedReasons")
    @GetMapping("/getTop10TechnicalInstitutionBackedReasons")
    public List<TechnicalInstitutionBackedReason> getTop10TechnicalInstitutionBackedReasons() {
        List<TechnicalInstitutionBackedReason> managementDepartmentBackedReasons = managementDepartmentBackedReasonService.getTop10TechnicalInstitutionBackedReasons();
        return managementDepartmentBackedReasons;
    }
}
