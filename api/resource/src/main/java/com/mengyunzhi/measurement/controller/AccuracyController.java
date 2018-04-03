package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.AccuracyService;
import com.mengyunzhi.measurement.entity.Accuracy;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Created by panjie on 17/6/28.
 * 精度
 */
@RequestMapping("/Accuracy")
@Api(tags = "Accuracy 精度", description = "精度")
@RestController
public class AccuracyController {
    public interface AccuracyJsonView {}
    public interface GetAllByInstrumentTypeIdJsonView extends AccuracyJsonView {}
    @Autowired
    private AccuracyService accuracyService; // 精度

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "/Accuracy/ 新增实体", notes = "增加一个新实体", nickname = "Accuracy_save")
    public Accuracy save(@ApiParam("Accuracy 精度实体") @RequestBody Accuracy accuracy) {
        accuracyService.save(accuracy);
        return accuracy;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "/Accuracy/{id} 删除实体", notes = "删除某个ID号的实体", nickname = "Accuracy_delete")
    public void delete(@ApiParam("id") @PathVariable("id") Long id) {
        accuracyService.deleteById(id);
    }

    @GetMapping("/getAllByInstrumentTypeId/{instrumentTypeId}")
    @ApiOperation(value = "/Accuracy/getAllByInstrumentTypeId/{instrumentTypeId}",
            notes = "获取某个器具类别下的所有实体",
            nickname = "Accuracy_getAllByInstrumentTypeId")
    @JsonView(GetAllByInstrumentTypeIdJsonView.class)
    public Iterable<Accuracy> getAllByInstrumentTypeId(@ApiParam("器具类别ID") @PathVariable  Long instrumentTypeId) {
        return accuracyService.getAllByInstrumentTypeId(instrumentTypeId);
    }
}
