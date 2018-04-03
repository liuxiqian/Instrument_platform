package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 器具受检率统计 实体
 * @Auther 朴世超
 */
@Entity
@ApiModel(value = "InstrumentCheckedRate(器具受检率统计)", description = "器具受检率统计实体")
public class InstrumentCheckedRate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty("年度")
    private Short year;
    @ApiModelProperty("是否受检")
    private Boolean checked = false;
    @ManyToOne
    @JsonView(NoneJsonView.class)
    @ApiModelProperty("器具二级类别")
    private InstrumentType instrumentType;
    @ManyToOne
    @JsonView(NoneJsonView.class)
    @ApiModelProperty("器具用户")
    private Department instrumentUserDepartment;
    @ManyToOne
    @JsonView(NoneJsonView.class)
    @ApiModelProperty("省")
    private District provinceDistrict;
    @ManyToOne
    @JsonView(NoneJsonView.class)
    @ApiModelProperty("市")
    private District cityDistrict;
    @ManyToOne
    @JsonView(NoneJsonView.class)
    @ApiModelProperty("县")
    private District countyDistrict;
    @ManyToOne
    @JsonView(NoneJsonView.class)
    @ApiModelProperty("用途")
    private Purpose purpose;

    public InstrumentCheckedRate() {
    }

    /**
     * 自身克隆一个 器具受检率统计 对象
     * @return
     */
    public InstrumentCheckedRate clone() {
        InstrumentCheckedRate instrumentCheckedRate = new InstrumentCheckedRate();
        // clone 所有属性
        instrumentCheckedRate.setYear(this.getYear());
        instrumentCheckedRate.setChecked(this.getChecked());
        instrumentCheckedRate.setInstrumentType(this.getInstrumentType());
        instrumentCheckedRate.setInstrumentUserDepartment(this.getInstrumentUserDepartment());
        instrumentCheckedRate.setProvinceDistrict(this.getProvinceDistrict());
        instrumentCheckedRate.setCityDistrict(this.getCityDistrict());
        instrumentCheckedRate.setCountyDistrict(this.getCountyDistrict());
        instrumentCheckedRate.setPurpose(this.getPurpose());
        return instrumentCheckedRate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public Department getInstrumentUserDepartment() {
        return instrumentUserDepartment;
    }

    public void setInstrumentUserDepartment(Department instrumentUserDepartment) {
        this.instrumentUserDepartment = instrumentUserDepartment;
    }

    public District getProvinceDistrict() {
        return provinceDistrict;
    }

    public void setProvinceDistrict(District provinceDistrict) {
        this.provinceDistrict = provinceDistrict;
    }

    public District getCityDistrict() {
        return cityDistrict;
    }

    public void setCityDistrict(District cityDistrict) {
        this.cityDistrict = cityDistrict;
    }

    public District getCountyDistrict() {
        return countyDistrict;
    }

    public void setCountyDistrict(District countyDistrict) {
        this.countyDistrict = countyDistrict;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "InstrumentCheckedRate{" +
                "id=" + id +
                ", year=" + year +
                ", checked=" + checked +
                ", instrumentType=" + instrumentType +
                ", instrumentUserDepartment=" + instrumentUserDepartment +
                ", province=" + provinceDistrict +
                ", city=" + cityDistrict +
                ", county=" + countyDistrict +
                ", purpose=" + purpose +
                '}';
    }
}
