package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.ApplyFieldService;
import com.mengyunzhi.measurement.jsonView.ApplyFieldJsonView;
import com.mengyunzhi.measurement.jsonView.ApplyTypeJsonView;
import com.mengyunzhi.measurement.jsonView.WorkJsonView;
import com.mengyunzhi.measurement.entity.ApplyField;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "ApplyField (申请字段)", description = "申请字段实体的C层")
@RestController
@RequestMapping("/ApplyField")
public class ApplyFieldController {
    @Autowired
    ApplyFieldService applyFieldService;

    @ApiOperation(value = "getById", notes = "获取某个实体", nickname = "ApplyField_getById")
    @GetMapping("/{id}")
    @JsonView(ApplyFieldJsonView.getById.class)
    public ApplyField getById(@ApiParam("id") @PathVariable("id") Long id) {
        return applyFieldService.findById(id);
    }

    @PostMapping("/")
    @ApiOperation(value = "saveWorkWithCurrentUserAudit", notes = "保存申请字段实体", nickname = "ApplyField_save")
    public ApplyField save(@ApiParam("申请字段实体") @RequestBody ApplyField applyField) {
        return applyFieldService.save(applyField);
    }

    @PutMapping("/{id}")
    @ApiResponse(code = 204, message = "更新成功")
    @ApiOperation(value = "update", notes = "更新申请字段实体", nickname = "ApplyField_update")
    public void update(@ApiParam("id") @PathVariable Long id, @ApiParam("申请字段实体") @RequestBody ApplyField applyField, HttpServletResponse response)
    {
        try{
            applyFieldService.update(id, applyField);
            response.setStatus(204);
        }
        catch (ObjectNotFoundException e){
            response.setStatus(404);
        }
        return;
    }

    @ApiOperation(value = "delete", notes = "删除申请字段实体", nickname = "ApplyField_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@ApiParam("ID") @PathVariable Long id){
        applyFieldService.delete(id);
        return;
    }


    @ApiOperation(value = "getAll", notes = "申请类型获取全部实体", nickname = "ApplyField_getAll")
    @GetMapping("/getAll")
    @JsonView({WorkJsonView.getById.class})
    public List<ApplyField> getAll(){
        return applyFieldService.getAll();
    }

    @ApiOperation(value = "getAllByApplyTypeId", notes = "通过申请类型id获取全部的申请字段", nickname = "ApplyField_getAllByApplyTypeId")
    @GetMapping("/getAllByApplyTypeId/{id}")
    @JsonView(ApplyTypeJsonView.getOneByName.class)
    public List<ApplyField> getAllByApplyTypeId(@ApiParam("申请类型id") @PathVariable Long id)
    {
        return applyFieldService.getAllByApplyTypeId(id);
    }
}
