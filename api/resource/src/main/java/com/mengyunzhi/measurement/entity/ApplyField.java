package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.ApplyFieldJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import com.mengyunzhi.measurement.jsonView.WorkJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by panjie on 17/10/9.
 * 申请字段
 */
@Entity
@ApiModel("申请字段")
public class ApplyField implements Serializable{
    final static private long serialVersionUID = 1L;
    // 声明字段类型
    public final static String TYPE_TEXT = "text";              // 普通文本
    public final static String TYPE_NUMBER = "number";          // 只允许为数字
    public final static String TYPE_PHONE = "phone";            // 只允许为手机号
    public final static String TYPE_ATTACHMENT = "attachment";  // 附件
    public final static String TYPE_IMAGE = "image";            // 图片
    public final static String TYPE_IMAGES = "images";          // 多图片
    public final static String TYPE_TEXTAREA = "textarea";      // 文本区域

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("标签名")
    private String label;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("权重")
    private int weight = 0;

    @ApiModelProperty("字段类型")
    @Column(columnDefinition = "char(20)")
    private String type;

    @ApiModelProperty("是否为必须字段")
    private boolean required = false;

    @ApiModelProperty("警告信息，不符合要求时")
    private String warnMessage;


    @ManyToOne
    @JsonView({NoneJsonView.class, ApplyFieldJsonView.getById.class})
    @ApiModelProperty("申请类型")
    private ApplyType applyType;

    @ApiModelProperty("申请字段配置")
    @OneToMany(mappedBy = "applyField")
    @JsonView({NoneJsonView.class, ApplyFieldJsonView.getById.class})
    private List<ApplyFieldConfig> applyFieldConfigs = new ArrayList<>();

    @ApiModelProperty("申请字段对应的工作流读写权限")
    @OneToMany(mappedBy = "applyField")
    @JsonView({NoneJsonView.class, WorkJsonView.getById.class})
    private List<WorkflowNodeAndApplyFieldAccess> workflowNodeAndApplyFieldAccesses = new ArrayList<>();

    public ApplyField() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ApplyType getApplyType() {
        return applyType;
    }

    public void setApplyType(ApplyType applyType) {
        this.applyType = applyType;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }



    public void addApplyFieldConfig(ApplyFieldConfig applyFieldConfig) {
        this.applyFieldConfigs.add(applyFieldConfig);
    }

    public List<ApplyFieldConfig> getApplyFieldConfigs() {
        return applyFieldConfigs;
    }

    public void setApplyFieldConfigs(List<ApplyFieldConfig> applyFieldConfigs) {
        this.applyFieldConfigs = applyFieldConfigs;
    }

    public void addWorkflowNodeAndApplyFieldAccesses(WorkflowNodeAndApplyFieldAccess workflowNodeAndApplyFieldAccess) {
        this.workflowNodeAndApplyFieldAccesses.add(workflowNodeAndApplyFieldAccess);
    }


    public List<WorkflowNodeAndApplyFieldAccess> getWorkflowNodeAndApplyFieldAccesses() {
        return workflowNodeAndApplyFieldAccesses;
    }

    public void setWorkflowNodeAndApplyFieldAccesses(List<WorkflowNodeAndApplyFieldAccess> workflowNodeAndApplyFieldAccesses) {
        this.workflowNodeAndApplyFieldAccesses = workflowNodeAndApplyFieldAccesses;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWarnMessage() {
        return warnMessage;
    }

    public void setWarnMessage(String warnMessage) {
        this.warnMessage = warnMessage;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
