package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.CommonService;
import com.mengyunzhi.measurement.Service.FileService;
import com.mengyunzhi.measurement.Service.MandatoryInstrumentApplyService;
import com.mengyunzhi.measurement.jsonView.ApplyJsonView;
import com.mengyunzhi.measurement.jsonView.MandatoryInstrumentApplyJsonView;
import com.mengyunzhi.measurement.entity.Apply;
import com.mengyunzhi.measurement.entity.MandatoryInstrument;
import com.mengyunzhi.measurement.entity.MandatoryInstrumentApply;
import com.mengyunzhi.measurement.entity.Work;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.SortDefault;
import org.springframework.expression.AccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by liming on 17-7-15.
 * 强检器具申请C层
 */
@RestController
@RequestMapping("/MandatoryInstrumentApply")
@Api(tags = "MandatoryInstrumentApply 强制检定器具申请")
public class MandatoryInstrumentApplyController {
    @Autowired
    ApplicationContext applicationContext;  // 上下文
    @Autowired
    protected MandatoryInstrumentApplyService mandatoryInstrumentApplyService;  // 强检器具备案申请

    @Autowired
    FileService fileService;    // 文件服务

    @GetMapping("/getPageOfCurrentDepartment")
    @ApiOperation(value = "/getPageOfCurrentDepartment 获取当前部门的申请列表", notes = "获取当前用户下的所有技术机构", nickname = "MandatoryInstrumentApply_getPageOfCurrentDepartment")
    public Page<Apply> getPageOfCurrentDepartment(@ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        Page<Apply> applies = mandatoryInstrumentApplyService.getPageOfCurrentDepartment(pageable);
        return applies;
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value = "/findById", notes = "获取强制检定器具申请", nickname = "MandatoryInstrumentApply_findById")
    @JsonView(MandatoryInstrumentApplyJsonView.findById.class)
    public MandatoryInstrumentApply findById(@Param("申请ID") @PathVariable("id") Long id) {
        return mandatoryInstrumentApplyService.findById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "/ 新建申请", notes = "新建申请", nickname = "MandatoryInstrumentApply_save")
    @JsonView(MandatoryInstrumentApplyJsonView.class)
    public MandatoryInstrumentApply save(@ApiParam("强检器具申请") @RequestBody MandatoryInstrumentApply mandatoryInstrumentApply) {
        return mandatoryInstrumentApplyService.save(mandatoryInstrumentApply);
    }

    @PatchMapping("/{mandatoryInstrumentApplyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "/{mandatoryInstrumentApplyId} 更新申请", notes = "更新申请情况以及申请对应的强制检定器具", nickname = "MandatoryInstrumentApply_update_all")
    public MandatoryInstrumentApply updateAll(@ApiParam("强检申请ID") @PathVariable Long mandatoryInstrumentApplyId, @Param("强制检定申请") @RequestBody MandatoryInstrumentApply mandatoryInstrumentApply) {
        return mandatoryInstrumentApplyService.updateById(mandatoryInstrumentApplyId, mandatoryInstrumentApply);
    }

    @GetMapping("/computeCheckAbilityBy/MandatoryInstrumentApplyId/{mandatoryInstrumentApplyId}/DepartmentId/{departmentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "computeCheckAbilityBy/MandatoryInstrumentApplyId/{MandatoryInstrumentApplyId}/DepartmentId/{departmentId} 计算某部门对某个强检申请上的所有强检器具是否具备检定能力",
            notes = "计算某部门对某个强检申请上的所有强检器具是否具备检定能力",
            nickname = "MandatoryInstrumentApply_computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId")
    @JsonView(ApplyJsonView.get.class)
    public MandatoryInstrumentApply computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId(
            @ApiParam("强制检定申请ID") @PathVariable("mandatoryInstrumentApplyId") Long mandatoryInstrumentApplyId,
            @ApiParam("部门ID") @PathVariable("departmentId") Long departmentId) throws Exception {
        return mandatoryInstrumentApplyService.computeCheckAbilityByMandatoryInstrumentApplyIdAndDepartmentId(mandatoryInstrumentApplyId, departmentId);
    }

    @ApiOperation(value = "generateDoNotHaveCheckAbilityPdfReportByApplyId/{id}/withToken", notes = "获取没有检定能力的报告", nickname = "MandatoryInstrumentApply_generateDoNotHaveCheckAbilityPdfReportByApplyId/{id}/withToken")
    @GetMapping("/generateDoNotHaveCheckAbilityPdfReportByApplyId/{id}/withToken")
    public void generateDoNotHaveCheckAbilityPdfReportByApplyId(@PathVariable Long id,  HttpServletResponse response) throws Exception {
        File file = mandatoryInstrumentApplyService.generateDoNotHaveCheckAbilityPdfReportByApplyId(id);
        fileService.downloadFileByFilenameAndFileAndExtensionNameAndHttpServletResponse(CommonService.getManageDepartmentName() + "强检器具备案申请" + Calendar.getInstance().getTimeInMillis(), file, "application/pdf", response);
    }

    @ApiOperation(value = "generatePdfReportByApplyId/{id}/checkDepartmentId/{checkDepartmentId}/withToken",
            notes = "获取某个申请对应的某个检定机构的申请报告",
            nickname = "MandatoryInstrumentApply_generatePdfReportByApplyId/{id}/checkDepartmentId/{checkDepartmentId}/withToken")
    @GetMapping("/generatePdfReportByApplyId/{id}/checkDepartmentId/{checkDepartmentId}/withToken")
    public void generatePdfReportByApplyIdAndCheckDepartmentId(@PathVariable Long id, @PathVariable Long checkDepartmentId,  HttpServletResponse response) throws Exception {
        File file = mandatoryInstrumentApplyService.generatePdfReportByApplyIdAndCheckDepartmentId(id ,checkDepartmentId);
        fileService.downloadFileByFilenameAndFileAndExtensionNameAndHttpServletResponse(CommonService.getManageDepartmentName() + "强检器具备案申请_" + id.toString() + "_" + checkDepartmentId.toString() +  "_" + Calendar.getInstance().getTimeInMillis() + ".pdf", file, "application/pdf", response);
    }


    @ApiOperation(value = "getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId",
            notes = "获取带有各级技术机构检定能力的强检申请",
            nickname = "MandatoryInstrumentApply_getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId")
    @GetMapping("/getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId/{id}")
    @JsonView(ApplyJsonView.get.class)
    public MandatoryInstrumentApply getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId(
            @ApiParam("id 强检申请id") @PathVariable("id") Long id
    ) {
        return mandatoryInstrumentApplyService.getMandatoryInstrumentApplyWithTechnicalInstitutionsAbilityByMandatoryInstrumentApplyId(id);
    }

    @ApiOperation(value = "batchPass",
            notes = "批量通过审核",
            nickname = "MandatoryInstrumentApply_batchPass")
    @PatchMapping("/batchPass/{mandatoryInstrumentApplyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void batchPassByMandatoryInstrumentsAndMandatoryInstrumentApplyId(
            @ApiParam("强检器具列表") @RequestBody List<MandatoryInstrument> mandatoryInstruments,
            @ApiParam("强检申请ID") @PathVariable("mandatoryInstrumentApplyId") Long mandatoryInstrumentApplyId) {
        mandatoryInstrumentApplyService.batchPassByMandatoryInstrumentsAndMandatoryInstrumentApplyId(mandatoryInstruments, mandatoryInstrumentApplyId);
        return;
    }

    @ApiOperation(value = "batchBackByMandatoryInstrumentsAndReasonAndApplyId",
            notes = "批量退回",
            nickname = "MandatoryInstrumentApply_batchBackByMandatoryInstrumentsAndReason")
    @PatchMapping("/batchBack/{applyId}/{backReason}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void batchBackByMandatoryInstrumentsAndReason(@ApiParam("强检器具列表") @RequestBody List<MandatoryInstrument> mandatoryInstruments,
                                                         @ApiParam("强检申请") @PathVariable("applyId") Long applId,
                                                         @ApiParam("退回理由") @PathVariable("backReason") String reason) {
        mandatoryInstrumentApplyService.batchBackByMandatoryInstrumentsAndReasonAndApplyId(mandatoryInstruments, reason, applId);
        return;
    }

    @PatchMapping("/handleWhenApplyDoneByApplyId/{applyId}")
    @ApiOperation(value = "doWhenApplyDoneByApplyId/{applyId}",
            notes = "当申请办结时触发的操作",
            nickname = "MandatoryInstrumentApply_handleWhenApplyDoneByApplyId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleWhenApplyDoneByApplyId(@ApiParam("applyId 申请id") @PathVariable("applyId") Long applyId) throws AccessException, IOException {
        mandatoryInstrumentApplyService.handleWhenApplyDoneByApplyId(applyId);
        return;
    }

    @PatchMapping("/reply/{applyId}")
    @ApiOperation(value = "/reply/{applyId}", notes = "管理部门，回复申请", nickname = "MandatoryInstrumentApply_reply")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reply(@ApiParam("申请ID") @PathVariable("applyId") Long applyId, @RequestBody MandatoryInstrumentApply mandatoryInstrumentApply) {
        mandatoryInstrumentApplyService.replyByIdAndReplyOpinions(applyId, mandatoryInstrumentApply.getReplayOpinions());
    }

    static public class WorkMandatoryInstrumentApply {
        private Work work;
        private MandatoryInstrumentApply mandatoryInstrumentApply;

        public WorkMandatoryInstrumentApply() {
        }

        public Work getWork() {
            return work;
        }

        public void setWork(Work work) {
            this.work = work;
        }

        public MandatoryInstrumentApply getMandatoryInstrumentApply() {
            return mandatoryInstrumentApply;
        }

        public void setMandatoryInstrumentApply(MandatoryInstrumentApply mandatoryInstrumentApply) {
            this.mandatoryInstrumentApply = mandatoryInstrumentApply;
        }
    }
}
