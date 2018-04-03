package com.mengyunzhi.measurement.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.Service.MeasureScaleService;
import com.mengyunzhi.measurement.entity.MeasureScale;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author panjie on 2018/1/3
 */
@RestController
@RequestMapping("/MeasureScale")
@Api(tags = "MeasureScale 测量范围", description = "测量范围")
public class MeasureScaleController {
    public interface MeasureScaleJsonView {}
    public interface GetByInstrumentTypeIdJsonView extends MeasureScaleJsonView {}

    @Autowired
    private MeasureScaleService measureScaleService;    // 测量范围

    @GetMapping("/getAllByInstrumentTypeId/{instrumentTypeId}")
    @ApiOperation(value = "/MeasureScale/getAllByInstrumentTypeId/{instrumentTypeId}",
            notes = "获取某个器具类别下的所有实体",
            nickname = "MeasureScale_getAllByInstrumentTypeId")
    @JsonView(GetByInstrumentTypeIdJsonView.class)
    public Iterable<MeasureScale> getAllByInstrumentTypeId(@ApiParam("器具类别ID") @PathVariable Long instrumentTypeId) {
        return measureScaleService.getAllByInstrumentTypeId(instrumentTypeId);
    }

}
