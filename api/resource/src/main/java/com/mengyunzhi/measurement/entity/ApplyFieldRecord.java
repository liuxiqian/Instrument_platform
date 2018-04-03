package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.entity.Apply;
import com.mengyunzhi.measurement.entity.ApplyField;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import com.mengyunzhi.measurement.jsonView.WorkJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by panjie on 17/10/9.
 * 申请字段记录
 */
@Entity
@ApiModel("申请字段记录")
public class ApplyFieldRecord implements Serializable {
    final static private long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @ApiModelProperty("申请")
    @JsonView(NoneJsonView.class)
    private Apply apply;

    @ApiModelProperty("申请字段")
    @ManyToOne
    @JsonView({NoneJsonView.class, WorkJsonView.getById.class})
    private ApplyField applyField;

    @ApiModelProperty("字段记录ID")
    private String value;

    public ApplyFieldRecord() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }

    public ApplyField getApplyField() {
        return applyField;
    }

    public void setApplyField(ApplyField applyField) {
        this.applyField = applyField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
