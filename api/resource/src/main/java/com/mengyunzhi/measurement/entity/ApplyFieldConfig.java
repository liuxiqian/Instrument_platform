package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.entity.ApplyField;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by panjie on 17/10/9.
 */
@Entity
@ApiModel("申请字段配置")
public class ApplyFieldConfig implements Serializable {
    final static long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("申请字段")
    @ManyToOne
    @JsonView(NoneJsonView.class)
    private ApplyField applyField;

    @ApiModelProperty("健")
    @Column(columnDefinition = "char(20)")
    private String k;

    @ApiModelProperty("值")
    private String value;

    public ApplyFieldConfig() {
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

    public ApplyField getApplyField() {
        return applyField;
    }

    public void setApplyField(ApplyField applyField) {
        this.applyField = applyField;
    }

    public String getK() {
        return k;
    }

    public void setK(String k) {
        this.k = k;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
