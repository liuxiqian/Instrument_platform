package com.mengyunzhi.measurement.controller;

import com.mengyunzhi.measurement.Service.InstrumentFirstLevelTypeService;
import com.mengyunzhi.measurement.entity.InstrumentFirstLevelType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by panjie on 17/7/26.
 * 器具一级类别
 */
@RestController
@Api(tags = "InstrumentFirstLevelType 器具一级类别")
@RequestMapping("/InstrumentFirstLevelType")
public class InstrumentFirstLevelTypeController {
    private Logger logger = Logger.getLogger(InstrumentFirstLevelTypeController.class.getName());
    @Autowired
    InstrumentFirstLevelTypeService instrumentFirstLevelTypeService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = " 保存", nickname = "InstrumentFirstLevelType_save")
    public InstrumentFirstLevelType save(
            @ApiParam("器具一级类别") @RequestBody InstrumentFirstLevelType instrumentFirstLevelType){
        return instrumentFirstLevelTypeService.save(instrumentFirstLevelType);
    }

    @GetMapping("/getAllByDisciplineId/{disciplineId}")
    @ApiOperation(
            value = "getAllByDisciplineId 获取某个学科类的下的列表",
            nickname = "InstrumentFirstLevelType_getAllByDisciplineId")
    public List<InstrumentFirstLevelType> getAllByDisciplineId(
            @Param("学科类别id") @PathVariable("disciplineId") Long disciplineId) {
                return instrumentFirstLevelTypeService.getAllByDisciplineId(disciplineId);
    }

    @ApiOperation(value = "delete(删除)", notes = "删除某条数据", nickname = "InstrumentFirstLevelType_delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{id}")
    public void delete(@ApiParam(value = "实体ID") @PathVariable Long id)
    {
        instrumentFirstLevelTypeService.delete(id);
        return;
    }

    @ApiOperation(value = "update(修改)", notes = "修改某条数据的名称和拼音两个字段", nickname = "InstrumentFirstLevelType_update")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/update/{id}")
    public void update(@ApiParam("ID") @PathVariable Long id, @RequestBody InstrumentFirstLevelType instrumentFirstLevelType,  HttpServletResponse response)
    {
        logger.info("---- 更新实体 ----");
        logger.info("直接调用M层方法");
        try
        {
            instrumentFirstLevelTypeService.update(id, instrumentFirstLevelType);
            response.setStatus(204);
        }
        catch (ObjectNotFoundException e)
        {
            response.setStatus(404);
        }
        return;
    }

    @ApiOperation(value = "get（获取某条数据）", notes = "获取某条数据", nickname = "InstrumentFirstLevelType_get")
    @GetMapping("/get/{id}")
    public InstrumentFirstLevelType get(@ApiParam("ID") @PathVariable Long id)
    {
        InstrumentFirstLevelType instrumentFirstLevelType = instrumentFirstLevelTypeService.get(id);
        return instrumentFirstLevelType;
    }
}
