package com.mengyunzhi.measurement.output;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;

import java.util.Map;


/**
 * 器具检定能力统计 返回显示信息
 * poshichao
 */
public class CheckAbilityStatisticsView {
    private Integer accuracyWeight;                     // 精确度等级
    private Integer minMeasureScaleWeight;              // 最小测量范围
    private Integer maxMeasureScaleWeight;              // 最大测量范围
    @JsonView(NoneJsonView.class)
    private MandatoryInstrument mandatoryInstrument;    // 器具
    @JsonView(NoneJsonView.class)
    private InstrumentType instrumentType;              // 器具类型
    @JsonView(NoneJsonView.class)
    private Discipline discipline;                      // 学科类别
    @JsonView(NoneJsonView.class)
    private District countyDistrict;                    // 区县
    @JsonView(NoneJsonView.class)
    private District cityDistrict;                      // 市
    @JsonView(NoneJsonView.class)
    private District provinceDistrict;                  // 省
    private int totalNum;                               // 器具总数
    private int authorizedNum;                          // 已授权数量

    public CheckAbilityStatisticsView(Map<String, Object> map, int totalNum, int authorizedNum) {
        this.accuracyWeight = (Integer) map.get("accuracyWeight");
        this.minMeasureScaleWeight = (Integer)map.get("minMeasureScaleWeight");
        this.maxMeasureScaleWeight = (Integer)map.get("maxMeasureScaleWeight");
        this.mandatoryInstrument = (MandatoryInstrument) map.get("mandatoryInstrument");
        this.instrumentType = (InstrumentType)map.get("instrumentType");
        this.discipline = (Discipline)map.get("discipline");
        this.countyDistrict = (District)map.get("countyDistrict");
        this.cityDistrict = (District)map.get("cityDistrict");
        this.provinceDistrict = (District)map.get("provinceDistrict");
        this.totalNum = totalNum;
        this.authorizedNum = authorizedNum;
    }

    public Integer getAccuracyWeight() {
        return accuracyWeight;
    }

    public Integer getMinMeasureScaleWeight() {
        return minMeasureScaleWeight;
    }

    public Integer getMaxMeasureScaleWeight() {
        return maxMeasureScaleWeight;
    }

    public MandatoryInstrument getMandatoryInstrument() {
        return mandatoryInstrument;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public District getCountyDistrict() {
        return countyDistrict;
    }

    public District getCityDistrict() {
        return cityDistrict;
    }

    public District getProvinceDistrict() {
        return provinceDistrict;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public int getAuthorizedNum() {
        return authorizedNum;
    }
}
