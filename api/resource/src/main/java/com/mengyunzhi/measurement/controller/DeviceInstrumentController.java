package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.DeviceInstrumentService;
import com.mengyunzhi.measurement.Service.DeviceSetService;
import com.mengyunzhi.measurement.jsonView.DeviceInstrumentJsonView;
import com.mengyunzhi.measurement.entity.DeviceInstrument;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.client.HttpResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liming on 17-7-20.
 * 授权检定项目Controller
 */
@RestController
@RequestMapping("/DeviceInstrument")
@Api(tags = "DeviceInstrument 授权检定项目", description = "授权检定项目")
public class DeviceInstrumentController {
    @Autowired
    protected DeviceInstrumentService deviceInstrumentService;
    @Autowired
    private DeviceSetService deviceSetService;                           // 标准装置

    @JsonView(DeviceInstrumentJsonView.ToDeviceSet.class)
    @ApiOperation(value = "pageAllByCurrentUserOfDeviceInstrument (获取当前登录用户的所有授权检定项目)", notes = "获取当前登录用户的授权检定项目", nickname = "DeviceInstrument_pageAllByCurrentUserOfDeviceInstrument")
    @GetMapping("/pageAllByCurrentUserOfDeviceInstrument")
    public Page<?> pageAllByCurrentUserOfDeviceInstrument(@ApiParam(value = "分页信息") Pageable pageable) {
        return deviceInstrumentService.pageAllByCurrentUserOfDeviceInstrument(pageable);
    }

    @JsonView(DeviceInstrumentJsonView.ToDeviceSet.class)
    @ApiOperation(value = "pageByDeviceSetIdOfCurrentUser", notes = "根据标准装置实体ID（传入0时，表示所有），并且是属于本部门的标准装置的", nickname = "DeviceInstrument_pageByDeviceSetIdOfCurrentUser")
    @GetMapping("/pageByDeviceSetIdOfCurrentUser/{deviceSetId}")
    public Page<DeviceInstrument> pageByDeviceSetIdOfCurrentUser(@ApiParam("deviceSetId 标准装置ID") @PathVariable("deviceSetId") Long deviceSetId, Pageable pageable) {
        return deviceInstrumentService.pageByDeviceSetIdOfCurrentUser(deviceSetId, pageable);
    }

    @JsonView(DeviceInstrumentJsonView.ToDeviceSet.class)
    @ApiOperation(value = "pageOfCurrentUser", notes = "获取当前用户的所有授权检定项目信息", nickname = "DeviceInstrument_pageOfCurrentUser")
    @GetMapping("/pageOfCurrentUser")
    public Page<DeviceInstrument> pageOfCurrentUser(Pageable pageable) {
        return deviceInstrumentService.pageOfCurrentUser(pageable);
    }


    @JsonView(DeviceInstrumentJsonView.ToDeviceSet.class)
    @ApiOperation(value = "pageAllOfCurrentUserBySpecification", notes = "授权检定项目综合查询", nickname = "DeviceInstrument_pageAllOfCurrentUserBySpecification")
    @GetMapping("/pageAllOfCurrentUserBySpecification")
    public Page<DeviceInstrument> pageAllOfCurrentUserBySpecification(
            @ApiParam(value = "标准装置id") @RequestParam(name = "deviceSetId", required = false) Long deviceSetId,
            @ApiParam(value = "部门id") @RequestParam(name = "departmentId", required = false) Long departmentId,
            @ApiParam(value = "区域id") @RequestParam(name = "districtId", required = false) Long districtId,
            @ApiParam(value = "学科类别id") @RequestParam(name = "disciplineId", required = false) Long disciplineId,
            @ApiParam(value = "一级类别id") @RequestParam(name = "instrumentTypeFirstLevelId", required = false) Long instrumentTypeFirstLevelId,
            @ApiParam(value = "二级类别id") @RequestParam(name = "instrumentTypeId", required = false) Long instrumentTypeId,
            @ApiParam(value = "标准装置名称") @RequestParam(name = "name", required = false) String name,
            @ApiParam(value = "分页信息") @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return deviceInstrumentService.pageAllOfCurrentManageDepartmentBySpecification(deviceSetId, districtId, departmentId, disciplineId, instrumentTypeFirstLevelId, instrumentTypeId, name, pageable);
    }

    @GetMapping("/countByAccuracyIdAndMeasureScaleIdAndDepartmentId/accuracyId/{accuracyId}/measureScaleId/{measureScaleId}/departmentId/{departmentId}")
    @ApiOperation(value = "getCheckAbilityByAccuracyIdAndMinMeasureScaleIdAndMaxMeasureScaleIdAndInstrumentTypeIdAndDepartmentId 获取某部门拥有某个器具种类的可检定数",
            notes = "获取某个技术机构（器具用户）是否拥有检测某器具种类的能力（大于1则说明拥有检测能力）",
            nickname = "DeviceInstrument_countByAccuracyIdAndMeasureScaleIdAndDepartmentId")
    @ResponseStatus(value = HttpStatus.GONE, reason = "该接口已过期，新的接口地址为：/DeviceSetDeviceInstrument/getCheckAbilityByAccuracyIdAndMinMeasureScaleIdAndMaxMeasureScaleIdAndInstrumentTypeIdAndDepartmentId")
    public void countByAccuracyIdAndMeasureScaleIdAndDepartmentId(@PathVariable("accuracyId") @ApiParam("精度ID") Long accuracyId,
                                                                  @PathVariable("measureScaleId") @ApiParam("测量范围ID") Long measureScaleId,
                                                                  @PathVariable("departmentId") @ApiParam("技术机构（建标用户）ID") Long departmentId) throws HttpResponseException {
        throw new HttpResponseException(410, "");
    }

    @GetMapping("/checkAbilityByAccuracyIdAndMinMeasureScaleIdAndMaxMeasureScaleIdAndInstrumentTypeIdAndDepartmentId/accuracyId/{accuracyId}/minMeasureScaleId/{minMeasureScaleId}/maxMeasureScaleId/{maxMeasureScaleId}/instrumentTypeId/{instrumentTypeId}/departmentId/{departmentId}")
    @ApiOperation(value = "checkAbilityByAccuracyIdAndMinMeasureScaleIdAndMaxMeasureScaleIdAndInstrumentTypeIdAndDepartmentId 获取某部门拥有某个器具种类的可检定数",
            notes = "获取某个技术机构（器具用户）是否拥有检测某器具种类的能力（大于1则说明拥有检测能力）",
            nickname = "DeviceInstrument_checkAbilityByAccuracyIdAndMinMeasureScaleIdAndMaxMeasureScaleIdAndInstrumentTypeIdAndDepartmentId")
    public Boolean checkAbilityByAccuracyIdAndMinMeasureScaleIdAndMaxMeasureScaleIdAndInstrumentTypeIdAndDepartmentId(
            @PathVariable("accuracyId") @ApiParam("精度ID") Long accuracyId,
            @PathVariable("minMeasureScaleId") @ApiParam("最小测量范围ID") Long minMeasureScaleId,
            @PathVariable("maxMeasureScaleId") @ApiParam("最大测量范围ID") Long maxMeasureScaleId,
            @PathVariable("instrumentTypeId") @ApiParam("器具二级类别ID") Long instrumentTypeId,
            @PathVariable("departmentId") @ApiParam("技术机构（建标用户）ID") Long departmentId) throws Exception {
        return deviceSetService.getCheckAbilityByAccuracyIdAndMinMeasureScaleIdAndMaxMeasureScaleIdAndInstrumentTypeIdAndDepartmentId(accuracyId, minMeasureScaleId, maxMeasureScaleId, instrumentTypeId, departmentId);
    }

    @ApiOperation(value = "delete (删除实体)", notes = "删除实体", nickname = "DeviceInstrument_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") @ApiParam("id") Long id) {
        deviceInstrumentService.delete(id);
        return;
    }

    @ApiOperation(value = "get 获取实体", notes = "获取某个 标准装置 及 器具类别 对应的实体", nickname = "DeviceInstrument_getByDeviceSetIdAndInstrumentTypeId")
    @GetMapping("/deviceSetId/{deviceSetId}/instrumentTypeId/{instrumentTypeId}")
    public DeviceInstrument get(@ApiParam("标准装置ID") @PathVariable("deviceSetId") Long deviceSetId,
                                @ApiParam("器具类别ID") @PathVariable("instrumentTypeId") Long instrumentTypeId) {
        return deviceInstrumentService.findByDeviceSetIdAndInstrumentTypeId(deviceSetId, instrumentTypeId);
    }

    @ApiOperation(value = "get 获取实体", notes = "获取某个ID对应的实体", nickname = "DeviceInstrument_findById")
    @GetMapping("/{id}")
    public DeviceInstrument findById(@ApiParam("id") @PathVariable("id") Long id) {
        return deviceInstrumentService.findById(id);
    }

    @ApiOperation(value = "post 保存实体", notes = "保存新建实体", nickname = "DeviceInstrument_save")
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@ApiParam("授权检定项目") @RequestBody DeviceInstrument deviceInstrument) {
        deviceInstrumentService.save(deviceInstrument);
        return;
    }
}
