package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.ApplyJsonView;
import com.mengyunzhi.measurement.jsonView.MandatoryInstrumentApplyJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import com.mengyunzhi.measurement.jsonView.WorkJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by panjie on 17/7/14.
 * 强检器具送审
 */
@Entity
@DiscriminatorValue(MandatoryInstrumentApply.CLASS_NAME)
@ApiModel(value = "MandatoryInstrumentApply 强检器具送审申请", description = "记录了每们强检器具的申请信息")
public class MandatoryInstrumentApply extends Apply implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String CLASS_NAME = "MandatoryInstrument";
    protected String className = CLASS_NAME;

    @ApiModelProperty("强检器具使用信息")
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JsonView({NoneJsonView.class,
            ApplyJsonView.get.class,
            WorkJsonView.getById.class,
            MandatoryInstrumentApplyJsonView.findById.class})
    private Set<MandatoryInstrument> mandatoryInstruments = new HashSet<>();

    @ApiModelProperty("附件")
    @OneToMany
    @JoinColumn(name = "mandatoryInstrumentApply_id")           // 如果不加入该注解，删除附件时会因为没有删除中间表中的记录而抛出异常。参考链接：https://vladmihalcea.com/2017/03/29/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
    private List<Attachment> attachments = new ArrayList<Attachment>();

    @ApiModelProperty("备注")
    private String remarks = "";

    @ApiModelProperty("回复意见")
    private String replayOpinions = "";

    public MandatoryInstrumentApply() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<MandatoryInstrument> getMandatoryInstruments() {
        return mandatoryInstruments;
    }

    public void setMandatoryInstruments(Set<MandatoryInstrument> mandatoryInstruments) {
        this.mandatoryInstruments = mandatoryInstruments;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void addMandatoryInstrument(MandatoryInstrument mandatoryInstrument) {
        this.mandatoryInstruments.add(mandatoryInstrument);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getReplayOpinions() {
        return replayOpinions;
    }

    public void setReplayOpinions(String replayOpinions) {
        this.replayOpinions = replayOpinions;
    }
}
