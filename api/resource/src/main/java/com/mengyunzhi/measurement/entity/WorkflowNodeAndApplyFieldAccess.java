package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import com.mengyunzhi.measurement.jsonView.WorkflowNodeAndApplyFieldAccessJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by panjie on 17/10/9.
 */
@Entity
@ApiModel("工作流结点 - 申请字段 权限")
public class WorkflowNodeAndApplyFieldAccess implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("申请字段")
    @ManyToOne
    @JsonView({NoneJsonView.class, WorkflowNodeAndApplyFieldAccessJsonView.get.class})
    private ApplyField applyField;

    @ApiModelProperty("是否可读")
    private Boolean isRead;

    @ApiModelProperty("是否可写")
    private Boolean isWrite;

    public WorkflowNodeAndApplyFieldAccess() {
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

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public Boolean getWrite() {
        return isWrite;
    }

    public void setWrite(Boolean write) {
        isWrite = write;
    }
}
