package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.ApplyTypeService;
import com.mengyunzhi.measurement.jsonView.ApplyTypeJsonView;
import com.mengyunzhi.measurement.entity.ApplyType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by panjie on 17/7/28.
 * 申请类型
 */
@RestController
@RequestMapping("/ApplyType")
@Api(tags = "ApplyType 申请类型")
public class ApplyTypeController {
    @Autowired private ApplyTypeService applyTypeService;   // 申请

    @GetMapping("/getOneByName/{name}")
    @ApiOperation(value = "getOneByName 根据名称获取申请类型", nickname = "ApplyType_getOneByName")
    @JsonView(ApplyTypeJsonView.getOneByName.class)
    public ApplyType getOneByName(@Param("申请类型名称") @PathVariable String name) {
        return applyTypeService.getOneByName(name);
    }

    @GetMapping("/getById/{id}")
    @ApiOperation(value = "getById", nickname = "ApplyType_getById")
    @JsonView(ApplyTypeJsonView.getOneByName.class)
    public ApplyType getById(@Param("申请类型id") @PathVariable("id") Long id) {
        return applyTypeService.getById(id);
    }

    @PostMapping("/")
    @ApiOperation(value = "saveWorkWithCurrentUserAudit", notes = "申请类型保存实体", nickname = "ApplyType_save")
    public ApplyType save(@ApiParam("申请类型实体") @RequestBody ApplyType applyType){
        return applyTypeService.save(applyType);
    }

    @ApiOperation(value = "update", notes = "申请类型更新实体", nickname = "ApplyType_update")
    @ApiResponse(code = 204, message = "修改成功")
    @PutMapping("/{id}")
    public void update(@ApiParam("ID") @PathVariable Long id, @ApiParam("申请类型实体") @RequestBody ApplyType applyType, HttpServletResponse response){
        try{
            applyTypeService.update(id, applyType);
            response.setStatus(204);
        }
        catch(ObjectNotFoundException e){
            response.setStatus(404);
        }
        return;
    }

    @ApiOperation(value = "delete", notes = "删除申请类型实体", nickname = "ApplyType_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@ApiParam("ID") @PathVariable Long id){
        applyTypeService.delete(id);
        return;
    }

    @ApiOperation(value = "getAll", notes = "申请类型获取全部实体", nickname = "ApplyType_getAll")
    @GetMapping("/getAll")
    @JsonView(ApplyTypeJsonView.getOneByName.class)
    public List<ApplyType> getAll(){
        return applyTypeService.getAll();
    }
}
