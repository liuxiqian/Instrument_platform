package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.ApplyFieldJsonView;
import com.mengyunzhi.measurement.jsonView.ApplyTypeJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ApiModel(value = "ApplyType (申请类型)", description = "申请类型")
public class ApplyType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("名称")
    @Column(unique = true)
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ManyToOne
    @ApiModelProperty("工作流类型")
    @JsonView({
            NoneJsonView.class,
            ApplyFieldJsonView.getById.class,
            ApplyTypeJsonView.getOneByName.class
    })
    private WorkflowType workflowType;

    public ApplyType() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkflowType getWorkflowType() {
        return workflowType;
    }

    public void setWorkflowType(WorkflowType workflowType) {
        this.workflowType = workflowType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
