package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

/**
 * 检定能力(覆盖率)统计
 * @author panjie
 */
@Entity
@ApiModel(value = "CheckAbilityStatistics 检定能力(覆盖率)统计")
public class CheckAbilityStatistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("精确度等级权重")
    private Integer accuracyWeight;
    @ApiModelProperty("最小测量范围权重")
    private Integer minMeasureScaleWeight;
    @ApiModelProperty("最大测量范围权重")
    private Integer maxMeasureScaleWeight;
    @ApiModelProperty("区县是否有检定能力")
    private Boolean countyHasCheckAbility = false;
    @ApiModelProperty("市是否有检定能力")
    private Boolean cityHasCheckAbility = false;
    @ApiModelProperty("省是否有检定能力")
    private Boolean provinceHasCheckAbility = false;
    @ApiModelProperty("器具")
    @ManyToOne
    @JsonView(NoneJsonView.class)
    private MandatoryInstrument mandatoryInstrument;
    @ApiModelProperty("器具用户(部门)")
    @ManyToOne
    @JsonView(NoneJsonView.class)
    private Department instrumentUserDepartment;
    @ApiModelProperty("器具(二级)类别")
    @ManyToOne
    @JsonView(NoneJsonView.class)
    private InstrumentType instrumentType;
    @ApiModelProperty("学科类别")
    @ManyToOne
    @JsonView(NoneJsonView.class)
    private Discipline discipline;
    @ApiModelProperty("区县")
    @ManyToOne
    @JsonView(NoneJsonView.class)
    private District countyDistrict;
    @ApiModelProperty("市")
    @ManyToOne
    @JsonView(NoneJsonView.class)
    private District cityDistrict;
    @ApiModelProperty("省")
    @ManyToOne
    @JsonView(NoneJsonView.class)
    private District provinceDistrict;

    public CheckAbilityStatistics() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getCountyHasCheckAbility() {
        return countyHasCheckAbility;
    }

    public void setCountyHasCheckAbility(Boolean countyHasCheckAbility) {
        this.countyHasCheckAbility = countyHasCheckAbility;
    }

    public Boolean getCityHasCheckAbility() {
        return cityHasCheckAbility;
    }

    public void setCityHasCheckAbility(Boolean cityHasCheckAbility) {
        this.cityHasCheckAbility = cityHasCheckAbility;
    }

    public Boolean getProvinceHasCheckAbility() {
        return provinceHasCheckAbility;
    }

    public void setProvinceHasCheckAbility(Boolean provinceHasCheckAbility) {
        this.provinceHasCheckAbility = provinceHasCheckAbility;
    }

    public MandatoryInstrument getMandatoryInstrument() {
        return mandatoryInstrument;
    }

    public void setMandatoryInstrument(MandatoryInstrument mandatoryInstrument,Integer accuracyWeight, Integer minMeasureScaleWeight, Integer maxMeasureScaleWeight, InstrumentType instrumentType, Discipline discipline, Department instrumentUserDepartment, District district) {
        this.mandatoryInstrument = mandatoryInstrument;                 // 强检器具
        if (mandatoryInstrument != null) {
            this.accuracyWeight = accuracyWeight;                       // 精确度权重
            this.minMeasureScaleWeight = minMeasureScaleWeight;         // 最小测量范围权重
            this.maxMeasureScaleWeight = maxMeasureScaleWeight;         // 最大测量范围权重
            this.setInstrumentType(instrumentType, discipline);                         // 器具类别,学科类别
            this.setInstrumentUserDepartment(instrumentUserDepartment, district);       // 器具用户,区域
        }
    }

    private void setInstrumentUserDepartment(Department instrumentUserDepartment, District district) {
        this.instrumentUserDepartment = instrumentUserDepartment;   // 器具用户
        this.provinceDistrict = null;                               // 省
        this.cityDistrict = null;                                   // 市
        this.countyDistrict = null;                                 // 区县
        if (instrumentUserDepartment != null) {
            if (district.getDistrictType().getName().equals("区县")) {
                this.countyDistrict = district;
                this.cityDistrict = district.getParentDistrict();
                this.provinceDistrict = district.getParentDistrict().getParentDistrict();
            } else if (district.getDistrictType().getName().equals("市")) {
                this.cityDistrict = district;
                this.provinceDistrict = district.getParentDistrict();
            } else if (district.getDistrictType().getName().equals("省")) {
                this.provinceDistrict = district;
            }
        }
    }

    public Department getInstrumentUserDepartment() {
        return instrumentUserDepartment;
    }

    private void setInstrumentType(InstrumentType instrumentType, Discipline discipline) {
        this.instrumentType = instrumentType;       // 器具类别
        if (instrumentType != null) {
            this.discipline = discipline;           // 学科类别
        }
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

    @Override
    public String toString() {
        return "CheckAbilityStatistics{" +
                "id=" + id +
                ", accuracyWeight=" + accuracyWeight +
                ", minMeasureScaleWeight=" + minMeasureScaleWeight +
                ", maxMeasureScaleWeight=" + maxMeasureScaleWeight +
                ", countyHasCheckAbility=" + countyHasCheckAbility +
                ", cityHasCheckAbility=" + cityHasCheckAbility +
                ", provinceHasCheckAbility=" + provinceHasCheckAbility +
                ", mandatoryInstrument=" + mandatoryInstrument +
                ", instrumentUserDepartment=" + instrumentUserDepartment +
                ", instrumentType=" + instrumentType +
                ", discipline=" + discipline +
                ", countyDistrict=" + countyDistrict +
                ", cityDistrict=" + cityDistrict +
                ", provinceDistrict=" + provinceDistrict +
                '}';
    }
}
