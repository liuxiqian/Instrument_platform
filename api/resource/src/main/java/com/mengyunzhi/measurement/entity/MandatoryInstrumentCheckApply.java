package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.CheckApplyJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import com.mengyunzhi.measurement.jsonView.WorkJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * created by zhuchenshu
 */
@Entity
@DiscriminatorValue(MandatoryInstrumentCheckApply.CLASS_NAME)
@ApiModel(value = "MandatoryInstrumentCheckApply (检定申请)", description = "检定申请实体")
public class MandatoryInstrumentCheckApply extends Apply{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public static final String CLASS_NAME = "MandatoryInstrumentCheck";
    protected String className = CLASS_NAME;

    @ApiModelProperty("申请名称")
    protected String name = "强检器具检定申请";

    @ApiModelProperty("强检器具使用信息")
    @OneToMany
    @JsonView({NoneJsonView.class,
            CheckApplyJsonView.list.class,
            CheckApplyJsonView.get.class,
            CheckApplyJsonView.save.class,
            WorkJsonView.getById.class})
    @JoinColumn(name = "checkApply_id") // 防止删除中间表的关联实体时，发生异常
    private Set<MandatoryInstrument> mandatoryInstrumentSet = new HashSet<>();

    @ApiModelProperty("计划检定日期")
    private Date plannedCheckDate;

    @ApiModelProperty("备注")
    private String remarks;

    @ApiModelProperty("回复备注")
    private String replyRemarks;

    @ApiModelProperty("检定场所")
    private String checkPlace;

    public MandatoryInstrumentCheckApply() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<MandatoryInstrument> getMandatoryInstrumentSet() {
        return mandatoryInstrumentSet;
    }

    public void setMandatoryInstrumentSet(Set<MandatoryInstrument> mandatoryInstrumentSet) {
        this.mandatoryInstrumentSet = mandatoryInstrumentSet;
    }

    public Date getPlannedCheckDate() {
        return plannedCheckDate;
    }

    public void setPlannedCheckDate(Date plannedCheckDate) {
        this.plannedCheckDate = plannedCheckDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCheckPlace() {
        return checkPlace;
    }

    public String getReplyRemarks() {
        return replyRemarks;
    }

    public void setReplyRemarks(String replyRemarks) {
        this.replyRemarks = replyRemarks;
    }

    public void setCheckPlace(String checkPlace) {
        this.checkPlace = checkPlace;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public MandatoryInstrumentCheckApply clone() {
        MandatoryInstrumentCheckApply mandatoryInstrumentCheckApply = new MandatoryInstrumentCheckApply();
        mandatoryInstrumentCheckApply.setCheckPlace(this.getCheckPlace());
        mandatoryInstrumentCheckApply.setMandatoryInstrumentSet(this.getMandatoryInstrumentSet());
        mandatoryInstrumentCheckApply.setPlannedCheckDate(this.getPlannedCheckDate());
        mandatoryInstrumentCheckApply.setRemarks(this.getRemarks());
        mandatoryInstrumentCheckApply.setReplyRemarks(this.getReplyRemarks());
        return mandatoryInstrumentCheckApply;
    }
}
