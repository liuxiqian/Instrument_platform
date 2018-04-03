package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.MandatoryInstrumentCheckApplyService;
import com.mengyunzhi.measurement.Service.CommonService;
import com.mengyunzhi.measurement.Service.FileService;
import com.mengyunzhi.measurement.entity.CurrentWork;
import com.mengyunzhi.measurement.entity.MandatoryInstrumentCheckApply;
import com.mengyunzhi.measurement.jsonView.ApplyJsonView;
import com.mengyunzhi.measurement.jsonView.CheckApplyJsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.sql.Date;

/**
 * Created by panjie on 17/11/21.
 */
@Api(tags = "MandatoryInstrumentCheckApply 检定申请", description = "强检器具检定申请")
@RestController
@RequestMapping("/MandatoryInstrumentCheckApply")
public class MandatoryInstrumentCheckApplyController {
    private final static Logger logger = Logger.getLogger(MandatoryInstrumentCheckApplyController.class.getName());

    @Autowired
    MandatoryInstrumentCheckApplyService mandatoryInstrumentCheckApplyService;    // 检定申请
    @Autowired
    FileService fileService;                // 文件服务

    @GetMapping("/{id}")
    @JsonView(CheckApplyJsonView.get.class)
    @ApiOperation(value = "getById",
            notes = "获取某个记录",
            nickname = "MandatoryInstrumentCheckApply_getById")
    public MandatoryInstrumentCheckApply getById(@ApiParam("id") @PathVariable("id") Long id) {
        return mandatoryInstrumentCheckApplyService.findById(id);
    }

    @GetMapping("/pageOfCurrentUser")
    @JsonView(ApplyJsonView.page.class)
    @ApiOperation(value = "pageOfCurrentUser",
            notes = "获取当前登录技术机构的分页数据",
            nickname = "MandatoryInstrumentCheckApply_pageOfCurrentUser")
    public Page<CurrentWork> pageOfCurrentUser(
            @ApiParam("器具用户名称") @RequestParam(required = false) String instrumentUserName,
            @ApiParam("申请开始日期") @RequestParam(required = false) Date startApplyDate,
            @ApiParam("申请结束日期") @RequestParam(required = false) Date endApplyDate,
            @ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable
    ) {
        return mandatoryInstrumentCheckApplyService.pageOfCurrentUser(instrumentUserName, startApplyDate, endApplyDate, pageable);
    }

    @PostMapping("")
    @JsonView(CheckApplyJsonView.save.class)
    @ApiOperation(value = "saveWorkWithCurrentUserAudit 新增数据", notes = "新装数据", nickname = "MandatoryInstrumentCheckApply_save")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void save(@RequestBody MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply, HttpServletResponse response) {

        mandatoryInstrumentCheckApplyService.save(mandatoryInstrumentCheckApply);
        return;
    }

    @PatchMapping("/audit/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "/audit/{id}",
            notes = "回复",
            nickname = "MandatoryInstrumentCheckApply_audit")
    public void audit(@ApiParam("id") @PathVariable("id") Long id, @ApiParam("检定申请") @RequestBody MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply) throws AccessException {
        mandatoryInstrumentCheckApplyService.auditById(id, mandatoryInstrumentCheckApply);
    }

    @GetMapping("/generatePdfApply/{checkApplyId}/withToken")
    @ApiOperation(value = "/generatePdfApply/{checkApplyId}/withToken",
            notes = "根据检定申请id生成pdf检定文档",
            nickname = "MandatoryInstrumentCheckApply_generatePdfApplyById")
    public void generatePdfApplyById(@ApiParam("检定申请id") @PathVariable("checkApplyId") Long checkApplyId, HttpServletResponse response) throws Exception {
        File file = mandatoryInstrumentCheckApplyService.generatePdfApplyById(checkApplyId);
        fileService.downloadFileByFilenameAndFileAndExtensionNameAndHttpServletResponse(CommonService.getManageDepartmentName() + "检定申请" + checkApplyId, file, "application/pdf", response);
    }

}
