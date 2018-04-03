package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.FileService;
import com.mengyunzhi.measurement.Service.MandatoryInstrumentService;
import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.MandatoryInstrument;
import com.mengyunzhi.measurement.jsonView.MandatoryInstrumentJsonView;
import com.mengyunzhi.measurement.jsonView.MandatoryInstrumentWorkJsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

/**
 * Created by panjie on 17/5/5.
 * 强检器具
 */
@Api(tags = "MandatoryInstrument (强检器具)", description = "强检器具 综合查询")
@RestController
@RequestMapping("/MandatoryInstrument")
public class MandatoryInstrumentController {
    private static Logger logger = Logger.getLogger(MandatoryInstrumentController.class.getName());
    @Autowired
    private MandatoryInstrumentService mandatoryInstrumentService;  // 强检器具

    @ApiOperation(value = "GET /{id} ", notes = "获取单个实体", nickname = "MandatoryInstrument_findById")
    @GetMapping("/{id}")
    @JsonView(MandatoryInstrumentJsonView.findById.class)
    public MandatoryInstrument findById(@ApiParam("id") @PathVariable("id") Long id) {
        return mandatoryInstrumentService.findById(id);
    }

    @ApiOperation(value = "/ 保存", notes = "保存强检器具", nickname = "MandatoryInstrument_save")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @JsonView(MandatoryInstrumentJsonView.class)
    public MandatoryInstrument save(@RequestBody MandatoryInstrument mandatoryInstrument) {
        return mandatoryInstrumentService.save(mandatoryInstrument);
    }

    @ApiOperation(value = "computerCheckAbilityByDepartmentIdOfMandatoryInstruments 计算某个部门在传入强检器具数组上是否具有检定能力",
            notes = "计算某个部门在传入强检器具数组上是否具有检定能力",
            nickname = "MandatoryInstrument_computerCheckAbilityByDepartmentIdOfMandatoryInstruments")
    @PostMapping("/computerCheckAbilityByDepartmentIdOfMandatoryInstruments/{departmentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<MandatoryInstrument> computerCheckAbilityByDepartmentIdOfMandatoryInstruments(
            @ApiParam("departmentId 部门id") @PathVariable Long departmentId,
            @ApiParam("mandatoryInstruments强检器具（Set）") @RequestBody List<MandatoryInstrument> mandatoryInstruments) throws Exception {
        return mandatoryInstrumentService.computerCheckAbilityByDepartmentIdOfMandatoryInstruments(departmentId, mandatoryInstruments);
    }

    @ApiOperation(value = "pageByCheckDepartmentOfCurrentDepartment", notes = "获取当前部门（技术机构）被管理部门指定的强检器具", nickname = "MandatoryInstrument_pageByCheckDepartmentOfCurrentDepartment")
    @GetMapping("/pageByCheckDepartmentOfCurrentDepartment")
    @JsonView(MandatoryInstrumentJsonView.pageByCheckDepartmentOfCurrentDepartment.class)
    public Page<MandatoryInstrument> pageByCheckDepartmentOfCurrentDepartment(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return mandatoryInstrumentService.pageByCheckDepartmentOfCurrentDepartment(pageable);
    }

    @ApiOperation(value = "updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId 更新强检器具的指定审核部门",
            notes = "更新强检器具(s)的指定审核部门",
            nickname = "MandatoryInstrument_updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId")
    @PostMapping("/updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId/{departmentId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCheckDepartmentOfMandatoryInstrumentsByDepartmentId(
            @ApiParam("部门ID") @PathVariable Long departmentId,
            @ApiParam("强制检定器具(s)") @RequestBody List<MandatoryInstrument> mandatoryInstruments) {
        mandatoryInstrumentService.updateLastCheckDepartmentByMandatoryInstrumentsAndDepartmentId(
                mandatoryInstruments, departmentId);
        return;
    }

    @ApiOperation(value = "updateCheckCycleAndFirstCheckDate 更新强检器具的检定周期和首次检定日期",
            notes = "更新强检器具的检定周期和首次检定日期",
            nickname = "MandatoryInstrument_updateCheckCycleAndFirstCheckDate")
    @PostMapping("/updateCheckCycleAndFirstCheckDate/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateCheckCycleAndFirstCheckDate(
            @ApiParam("强检器具ID") @PathVariable Long id,
            @ApiParam("强制检定器具") @RequestBody MandatoryInstrument mandatoryInstrument) {
        mandatoryInstrumentService.updateCheckCycleAndFirstCheckDate(id, mandatoryInstrument);
        return;
    }

    @ApiOperation(value = "delete", notes = "删除一条数据", nickname = "MandatoryInstrument_delete")
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)      //删除成功返回状态码204
    public void delete(@ApiParam(value = "强检器具实体id") @PathVariable Long id) {
        //强检器具使用信息的删除
        mandatoryInstrumentService.delete(id);
        return;
    }

    @JsonView(MandatoryInstrumentJsonView.pageAllOfCurrentUser.class)
    @ApiOperation(value = "pageAllOfCurrentUser", notes = "获取当前登录用户下的所有强检器具", nickname = "MandatoryInstrument_pageAllOfCurrentUser")
    @GetMapping("/pageAllOfCurrentUser")
    public Page<MandatoryInstrument> pageAllOfCurrentUser(@ApiParam(value = "分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "nextCheckDate", direction = Sort.Direction.DESC)) Pageable pageable) {
        return mandatoryInstrumentService.pageAllOfCurrentUser(pageable);
    }

    @JsonView(MandatoryInstrumentJsonView.pageAllOfCurrentUser.class)
    @ApiOperation(value = "pageAllOfCurrentUserBySpecification 获取当前登录用户下的所有强检器具", notes = "获取当前登录用户下的所有强检器具", nickname = "MandatoryInstrument_pageAllOfCurrentUserBySpecification")
    @GetMapping("/pageAllOfCurrentUserBySpecification")
    public Page<MandatoryInstrument> pageAllOfCurrentUserBySpecification(
            @ApiParam(value = "系统编号") @RequestParam(name = "id", required = false) Long id,
            @ApiParam(value = "学科类别id") @RequestParam(name = "disciplineId", required = false) Long disciplineId,
            @ApiParam(value = "一级类别id") @RequestParam(name = "instrumentTypeFirstLevelId", required = false) Long instrumentTypeFirstLevelId,
            @ApiParam(value = "二级类别id") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId,
            @ApiParam(value = "是否通过审核") @RequestParam(name = "audit", required = false) Boolean audit,
            @ApiParam(value = "器具名称 ") @RequestParam(name = "name", required = false) String name,
            @ApiParam(value = "是否超期未检") @RequestParam(name = "overdue", required = false) Boolean overdue,
            @ApiParam(value = "器具状态") @RequestParam(name = "status", required = false) Byte status,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return mandatoryInstrumentService.pageAllOfCurrentUserBySpecification(id, disciplineId, instrumentTypeFirstLevelId, instrumentTypeId, audit, name, overdue, status, pageable);
    }

    @JsonView(MandatoryInstrumentJsonView.pageAllOfCurrentUser.class)
    @ApiOperation(value = "pageOverdueDataOfCurrentUserBySpecification 获取当前登录用户下的所有超期未检强检器具",
            notes = "获取当前登录用户下的所有超期未检强检器具(存在计划检定日期，但计划检定日期小于当前日期的)",
            nickname = "MandatoryInstrument_pageOverdueDataOfCurrentUserBySpecification")
    @GetMapping("/pageOverdueDataOfCurrentUserBySpecification")
    public Page<MandatoryInstrument> pageOverdueDataOfCurrentUserBySpecification(
            @ApiParam(value = "系统编号") @RequestParam(name = "id", required = false) Long id,
            @ApiParam(value = "学科类别id") @RequestParam(name = "disciplineId", required = false) Long disciplineId,
            @ApiParam(value = "一级类别id") @RequestParam(name = "instrumentTypeFirstLevelId", required = false) Long instrumentTypeFirstLevelId,
            @ApiParam(value = "二级类别id") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId,
            @ApiParam(value = "器具名称") @RequestParam(name = "name", required = false) String name,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return mandatoryInstrumentService.pageOverdueDataOfCurrentUserBySpecification(id, disciplineId, instrumentTypeFirstLevelId, instrumentTypeId, name, pageable);
    }

    @JsonView(MandatoryInstrumentJsonView.pageAllOfCurrentUser.class)
    @ApiOperation(value = "pageAllOfCurrentManageDepartmentBySpecification 获取当前登录的管理部门下的所有强检器具", notes = "获取当前登录的管理部门下的所有强检器具", nickname = "MandatoryInstrument_pageAllOfCurrentManageDepartmentBySpecification")
    @GetMapping("/pageAllOfCurrentManageDepartmentBySpecification")
    public Page<MandatoryInstrument> pageAllOfCurrentManageDepartmentBySpecification(
            @ApiParam(value = "系统编号") @RequestParam(name = "id", required = false) Long id,
            @ApiParam(value = "区域id") @RequestParam(name = "districtId", required = false) Long districtId,
            @ApiParam(value = "器具用户id") @RequestParam(name = "instrumentUserId", required = false) Long instrumentUserId,
            @ApiParam(value = "学科类别id") @RequestParam(name = "disciplineId", required = false) Long disciplineId,
            @ApiParam(value = "一级类别id") @RequestParam(name = "instrumentTypeFirstLevelId", required = false) Long instrumentTypeFirstLevelId,
            @ApiParam(value = "二级类别id") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId,
            @ApiParam(value = "是否通过审核") @RequestParam(name = "audit", required = false) Boolean audit,
            @ApiParam(value = "器具名称") @RequestParam(name = "name", required = false) String name,
            @ApiParam(value = "是否超期未检") @RequestParam(name = "overdue", required = false) Boolean overdue,
            @ApiParam(value = "检定机构") @RequestParam(name = "checkDepartmentId", required = false) Long checkDepartmentId,
            @ApiParam(value = "状态") @RequestParam(name = "status", required = false) Byte status,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return mandatoryInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(id, districtId, instrumentUserId, checkDepartmentId, disciplineId, instrumentTypeFirstLevelId, instrumentTypeId, audit, name, overdue, status, pageable);
    }

    @JsonView(MandatoryInstrumentJsonView.pageAllOfCurrentUser.class)
    @ApiOperation(value = "exportExcelOfCurrentManageDepartmentBySpecification 导出当前登录的管理部门下的所有强检器具",
            notes = "导出当前登录的管理部门下的所有强检器具",
            nickname = "MandatoryInstrument_exportExcelOfCurrentManageDepartmentBySpecification_withToken")
    @GetMapping("/exportExcelOfCurrentManageDepartmentBySpecification/withToken")
    public void exportExcelOfCurrentManageDepartmentWithTokenBySpecification(
            @ApiParam(value = "系统编号") @RequestParam(name = "id", required = false) Long id,
            @ApiParam(value = "区域id") @RequestParam(name = "districtId", required = false) Long districtId,
            @ApiParam(value = "器具用户id") @RequestParam(name = "instrumentUserId", required = false) Long instrumentUserId,
            @ApiParam(value = "学科类别id") @RequestParam(name = "disciplineId", required = false) Long disciplineId,
            @ApiParam(value = "一级类别id") @RequestParam(name = "instrumentTypeFirstLevelId", required = false) Long instrumentTypeFirstLevelId,
            @ApiParam(value = "二级类别id") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId,
            @ApiParam(value = "是否通过审核") @RequestParam(name = "audit", required = false) Boolean audit,
            @ApiParam(value = "器具名称") @RequestParam(name = "name", required = false) String name,
            @ApiParam(value = "是否超期未检") @RequestParam(name = "overdue", required = false) Boolean overdue,
            @ApiParam(value = "检定机构") @RequestParam(name = "checkDepartmentId", required = false) Long checkDepartmentId,
            @ApiParam(value = "状态") @RequestParam(name = "status", required = false) Byte status,
            HttpServletResponse response) throws Exception {

        File file = mandatoryInstrumentService.exportExcelOfCurrentManageDepartmentBySpecification(id, districtId, instrumentUserId, checkDepartmentId, disciplineId, instrumentTypeFirstLevelId, instrumentTypeId, audit, name, overdue, status);
        logger.info("调用文件下载（只所以在c层中调用两个方法，是由于文件下载过程在使用mockmvc进行测试时，会报出乱码）");
        fileService.downloadFileAndDeleteOnFinishByFileAndExtensionNameAndHttpServletResponse(
                file, FileService.EXTENSION_NAME_XLSX, response);
    }

    @JsonView(MandatoryInstrumentJsonView.pageAllOfCurrentUser.class)
    @ApiOperation(value = "exportExcelOfCurrentUserWithTokenBySpecification 按查询条件导出当前登录的器具用户的强检器具",
            notes = "按查询条件导出当前登录的器具用户的强检器具",
            nickname = "MandatoryInstrument_exportExcelOfCurrentUserWithTokenBySpecification_withToken")
    @GetMapping("/exportExcelOfCurrentUserWithTokenBySpecification/withToken")
    public void exportExcelOfCurrentUserWithTokenBySpecification(
            @ApiParam(value = "系统编号") @RequestParam(name = "id", required = false) Long id,
            @ApiParam(value = "学科类别id") @RequestParam(name = "disciplineId", required = false) Long disciplineId,
            @ApiParam(value = "一级类别id") @RequestParam(name = "instrumentTypeFirstLevelId", required = false) Long instrumentTypeFirstLevelId,
            @ApiParam(value = "二级类别id") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId,
            @ApiParam(value = "是否通过审核") @RequestParam(name = "audit", required = false) Boolean audit,
            @ApiParam(value = "器具名称 ") @RequestParam(name = "name", required = false) String name,
            @ApiParam(value = "是否超期未检") @RequestParam(name = "overdue", required = false) Boolean overdue,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            HttpServletResponse response) throws Exception {
        File file = mandatoryInstrumentService.exportExcelOfCurrentUserBySpecification(id, disciplineId, instrumentTypeFirstLevelId, instrumentTypeId, audit, name, overdue, pageable);
        logger.info("调用文件下载（只所以在c层中调用两个方法，是由于文件下载过程在使用mockmvc进行测试时，会报出乱码）");
        fileService.downloadFileAndDeleteOnFinishByFileAndExtensionNameAndHttpServletResponse(
                file, FileService.EXTENSION_NAME_XLSX, response);
    }

    @JsonView(MandatoryInstrumentJsonView.pageAllOfCurrentUser.class)
    @ApiOperation(value = "exportExcelOfCurrentTechnicalInstitutionWithTokenBySpecification 按查询条件导出当前登录的技术机构的强检器具",
            notes = "按查询条件导出当前登录的技术机构的强检器具",
            nickname = "MandatoryInstrument_exportExcelOfCurrentTechnicalInstitutionWithTokenBySpecification_withToken")
    @GetMapping("/exportExcelOfCurrentTechnicalInstitutionWithTokenBySpecification/withToken")
    public void exportExcelOfCurrentTechnicalInstitutionWithTokenBySpecification(
            @ApiParam(value = "系统编号") @RequestParam(name = "id", required = false) Long id,
            @ApiParam(value = "区域id") @RequestParam(name = "districtId", required = false) Long districtId,
            @ApiParam(value = "器具用户id") @RequestParam(name = "instrumentUserId", required = false) Long instrumentUserId,
            @ApiParam(value = "学科类别id") @RequestParam(name = "disciplineId", required = false) Long disciplineId,
            @ApiParam(value = "一级类别id") @RequestParam(name = "instrumentTypeFirstLevelId", required = false) Long instrumentTypeFirstLevelId,
            @ApiParam(value = "二级类别id") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId,
            @ApiParam(value = "器具名称") @RequestParam(name = "name", required = false) String name,
            @ApiParam(value = "是否已被技术机构确认") @RequestParam(name = "isConfirmedByTechnologyDepartment", required = false) Boolean isConfirmedByTechnologyDepartment,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable,
            HttpServletResponse response) throws Exception {
        File file = mandatoryInstrumentService.exportExcelOfCurrentTechnicalInstitutionWithTokenBySpecification(id, districtId, instrumentUserId, disciplineId, instrumentTypeFirstLevelId, instrumentTypeId, name, isConfirmedByTechnologyDepartment, pageable);
        logger.info("调用文件下载（只所以在c层中调用两个方法，是由于文件下载过程在使用mockmvc进行测试时，会报出乱码）");
        fileService.downloadFileAndDeleteOnFinishByFileAndExtensionNameAndHttpServletResponse(
                file, FileService.EXTENSION_NAME_XLSX, response);
    }

    @Autowired private FileService fileService;
    @JsonView(MandatoryInstrumentJsonView.pageAllOfCurrentUser.class)
    @ApiOperation(value = "pageAllOfCurrentTechnicalInstitutionBySpecification 获取当前登录的技术机构下的所有强检器具", notes = "获取当前登录的技术机构下的所有强检器具", nickname = "MandatoryInstrument_pageAllOfCurrentTechnicalInstitutionBySpecification")
    @GetMapping("/pageAllOfCurrentTechnicalInstitutionBySpecification")
    public Page<MandatoryInstrument> pageAllOfCurrentTechnicalInstitutionBySpecification(
            @ApiParam(value = "系统编号") @RequestParam(name = "id", required = false) Long id,
            @ApiParam(value = "区域id") @RequestParam(name = "districtId", required = false) Long districtId,
            @ApiParam(value = "器具用户id") @RequestParam(name = "instrumentUserId", required = false) Long instrumentUserId,
            @ApiParam(value = "学科类别id") @RequestParam(name = "disciplineId", required = false) Long disciplineId,
            @ApiParam(value = "一级类别id") @RequestParam(name = "instrumentTypeFirstLevelId", required = false) Long instrumentTypeFirstLevelId,
            @ApiParam(value = "二级类别id") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId,
            @ApiParam(value = "器具名称") @RequestParam(name = "name", required = false) String name,
            @ApiParam(value = "是否已被技术机构确认") @RequestParam(name = "isConfirmedByTechnologyDepartment", required = false) Boolean isConfirmedByTechnologyDepartment,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return mandatoryInstrumentService.pageAllOfCurrentTechnicalInstitutionBySpecification(id, districtId, instrumentUserId, disciplineId, instrumentTypeFirstLevelId, instrumentTypeId, name, isConfirmedByTechnologyDepartment, pageable);
    }


    @ApiOperation(value = "update", notes = "更新", nickname = "MandatoryInstrument_update")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@ApiParam("强检器具ID") @PathVariable("id") Long id, @ApiParam("MandatoryInstrument 强检器具实体") @RequestBody MandatoryInstrument mandatoryInstrument) {
        mandatoryInstrumentService.update(id, mandatoryInstrument);
        return;
    }

    @JsonView(MandatoryInstrumentJsonView.pageAllOfCurrentUser.class)
    @ApiOperation(value = "getAllOfCurrentUser", notes = "获取当前登录用户的所有强检器具使用信息", nickname = "MandatoryInstrument_getAllOfCurrentUser")
    @GetMapping("/getAllOfCurrentUser")
    public List<MandatoryInstrument> getAllOfCurrentUser() {
        return mandatoryInstrumentService.getAllOfCurrentUser();
    }

    @ApiOperation(value = "updateFixSite", notes = "更新强检器具使用信息的安装地点和器具名称", nickname = "MandatoryInstrument_updateFixSite")
    @PutMapping("/updateFixSite/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateFixSite(
            @ApiParam("强检器具ID") @PathVariable Long id,
            @ApiParam("强制检定器具") @RequestBody MandatoryInstrument mandatoryInstrument) {
        mandatoryInstrumentService.updateFixSite(id, mandatoryInstrument);
        return;
    }

    @ApiOperation(value = "setStatusToBackById 设置器具状态为 被退回", notes = "设置器具状态为 被退回", nickname = "MandatoryInstrument_setStatusToBackById")
    @PutMapping("/setStatusToBackById/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void setStatusToBackById(@ApiParam("强检器具ID") @PathVariable("id") Long id) {
        mandatoryInstrumentService.setStatusToBackById(id);
        return;
    }


    @ApiOperation(value = "batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser 批量确认已指定器具",
            notes = "技术机构在指定的日期内，可以批量确认指定由其检定的器具",
            nickname = "MandatoryInstrument_batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser")
    @PatchMapping("/batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser(
            @ApiParam("由强检器具组成的数组") @RequestBody Set<MandatoryInstrument> mandatoryInstruments
    ) throws IOException {
        mandatoryInstrumentService.batchConfirmAssignedInstrumentByMandatoryInstrumentSetOfCurrentUser(mandatoryInstruments);
    }

    @GetMapping("/withToken/{token}")
    public void exportExcelByToken() {
        return;
    }

    @GetMapping("/getAllCurrentUserWorkOfOverTimeWarn/")
    @ApiOperation(value = "getAllCurrentUserWorkOfOverTimeWarn 获取当前登录用户的所有超期预警鉴定",
            notes = "获取当前登录用户的所有超期预警鉴定",
            nickname = "Work_getAllCurrentUserWorkOfOverTimeWarn")
    @JsonView(MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class)
    public Page<MandatoryInstrument> getAllCurrentUserWorkOfOverTimeWarn(@SortDefault.SortDefaults(@SortDefault(sort = "nextCheckDate", direction = Sort.Direction.ASC)) Pageable pageable) {
        return mandatoryInstrumentService.getAllCurrentUserWorkOfOverTimeWarn(pageable);
    }

    @GetMapping("/getAllCurrentUserWorkOfOverTime/")
    @ApiOperation(value = "getAllCurrentUserWorkOfOverTime 获取当前登录用户的所有超期鉴定",
            notes = "获取当前登录用户的所有超期鉴定",
            nickname = "Work_getAllCurrentUserWorkOfOverTime")
    @JsonView(MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class)
    public Page<MandatoryInstrument> getAllCurrentUserWorkOfOverTime(@SortDefault.SortDefaults(@SortDefault(sort = "nextCheckDate", direction = Sort.Direction.ASC)) Pageable pageable) {
        return mandatoryInstrumentService.getAllCurrentUserWorkOfOverTime(pageable);
    }

    @ApiOperation(value = "getAllGenerativeDepartment 获取所有的器具生产厂",
            notes = "获取所有的器具生产厂",
            nickname = "getAllGenerativeDepartment")
    @GetMapping("/getAllGenerativeDepartment")
    public List<Department> getAllGenerativeDepartment() {

        return mandatoryInstrumentService.getAllGenerativeDepartment();
    }

}
