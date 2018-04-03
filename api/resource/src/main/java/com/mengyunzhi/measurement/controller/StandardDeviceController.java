package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.StandardDeviceService;
import com.mengyunzhi.measurement.jsonView.StandardDeviceJsonView;
import com.mengyunzhi.measurement.entity.StandardDevice;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by liming on 17-5-9.
 */
@Api(tags = "StandardDevice (标准装置 标准器)", description = "标准装置 标准器")
@RestController
@RequestMapping("/StandardDevice")
public class StandardDeviceController {
    @Autowired
    private StandardDeviceService standardDeviceService;

    @ApiOperation(value = "findById", notes = "查找某个标准器信息", nickname="standardDevice_findById")
    @GetMapping("/{id}")
    @JsonView(StandardDeviceJsonView.baseJsonView.class)
    public StandardDevice findById(@PathVariable("id") Long id) {
        return standardDeviceService.findOne(id);
    }

    @ApiOperation(value = "saveWorkWithCurrentUserAudit (保存)", notes = "保存标准装置标准器实体", nickname = "standardDevice_save")
    @PostMapping
    @JsonView(StandardDeviceJsonView.baseJsonView.class)
    public StandardDevice save(@RequestBody StandardDevice standardDevice) {
        return standardDeviceService.save(standardDevice);
    }

    @JsonView(StandardDeviceJsonView.baseJsonView.class)
    @ApiOperation(value = "getAllByDeviceSetId (获取标准器)", notes = "通过标准装置id获取相应的标准器", nickname = "standardDevice_getAllByDeviceSetId")
    @GetMapping("/getAllByDeviceSetId/{deviceSetId}")
    public List<StandardDevice> getAllByDeviceSetId(@ApiParam(value = "标准装置ID") @PathVariable Long deviceSetId) {
        //返回数据
        return standardDeviceService.getAllByDeviceSetId(deviceSetId);
    }

    @ApiOperation(value = "update (更新)", notes = "更新标准器实体", nickname = "standardDevice_update")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    public void update(@ApiParam(value = "标准器ID") @PathVariable Long id, @ApiParam(value = "标准器实体") @RequestBody StandardDevice standardDevice) {
        standardDeviceService.update(id, standardDevice);
        return;
    }

    @ApiOperation(value = "delete (删除)", notes = "删除标准器", nickname = "standardDevice_delete")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@ApiParam(value = "标准器ID") @PathVariable Long id) {
        standardDeviceService.delete(id);
        return;
    }


    @ApiOperation(value = "pageAllByDeviceSetId (获取所有的标准器)", notes = "通过标准装置id获取相应的标准器", nickname = "standardDevice_pageAllByDeviceSetId")
    @GetMapping("/pageAllByDeviceSetId/{deviceSetId}")
    @JsonView(StandardDeviceJsonView.baseJsonView.class)
    public Page<StandardDevice> pageAllByDeviceSetId(@ApiParam(value = "标准装置ID") @PathVariable Long deviceSetId, @ApiParam("分页信息") @SortDefault.SortDefaults(@SortDefault(sort = "id", direction = Sort.Direction.DESC)) Pageable pageable) {
        return standardDeviceService.pageAllByDeviceSetId(deviceSetId, pageable);
    }

    /**
     * 获取所有预警的标准器
     * @param pageable
     * @return
     * zhuchenshu
     */
    @ApiOperation(value = "pageAllWarnStandardDevice (获取所有预警的标准器)", notes = "通过下次检定日期获取所有预警的标准器", nickname = "standardDevice_pageAllWarnStandardDevice")
    @GetMapping("/pageAllWarnStandardDevice/")
    @JsonView(StandardDeviceJsonView.baseJsonView.class)
    public Page<StandardDevice> pageAllWarnStandardDevice(@SortDefault.SortDefaults(@SortDefault(sort = "nextCheckDate", direction = Sort.Direction.ASC)) Pageable pageable) {
        return standardDeviceService.pageAllWarnStandardDevice(pageable);
    }

    /**
     * 获取所有报警的标准器
     * @param pageable
     * @return
     * zhuchenshu
     */
    @ApiOperation(value = "pageAllAlarmStandardDevice (获取所有报警的标准器)", notes = "通过下次检定日期获取所有报警的标准器", nickname = "standardDevice_pageAllAlarmStandardDevice")
    @GetMapping("/pageAllAlarmStandardDevice/")
    @JsonView(StandardDeviceJsonView.baseJsonView.class)
    public Page<StandardDevice> pageAllAlarmStandardDevice(@SortDefault.SortDefaults(@SortDefault(sort = "nextCheckDate", direction = Sort.Direction.ASC)) Pageable pageable) {
        return standardDeviceService.pageAllAlarmStandardDevice(pageable);
    }
}
