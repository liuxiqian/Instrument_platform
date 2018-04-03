package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import com.mengyunzhi.measurement.jsonView.OverdueCheckApplyControllerJsonView;
import com.mengyunzhi.measurement.jsonView.WorkJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author panjie on 2017/12/12
 * 超期送检申请
 */
@Entity
@DiscriminatorValue("OverdueCheck")
@ApiModel(value = "OverdueCheckApply 超期送检申请", description = "器具超期后申请时，将发起本超期送检申请")
public class OverdueCheckApply extends Apply implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String CLASS_NAME = "OverdueCheck";
    protected String className = CLASS_NAME;

    @ApiModelProperty("申请原因")
    private String reason;

    @ApiModelProperty("最迟检定日期")
    private Date latestCheckDate;


    @ApiModelProperty("对应的强检器具列表")
    @ManyToMany(cascade = CascadeType.MERGE)
    @JsonView({NoneJsonView.class,
            OverdueCheckApplyControllerJsonView.save.class,
            WorkJsonView.getById.class})
    Set<MandatoryInstrument> mandatoryInstrumentSet = new HashSet<>();

    @ApiModelProperty("管理部门意见")
    private String opinion = "";

    public OverdueCheckApply() {
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<MandatoryInstrument> getMandatoryInstrumentSet() {
        return mandatoryInstrumentSet;
    }

    public void setMandatoryInstrumentSet(Set<MandatoryInstrument> mandatoryInstrumentSet) {
        this.mandatoryInstrumentSet = mandatoryInstrumentSet;
    }

    public Date getLatestCheckDate() {
        return latestCheckDate;
    }

    public void setLatestCheckDate(Date latestCheckDate) {
        this.latestCheckDate = latestCheckDate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }
}
