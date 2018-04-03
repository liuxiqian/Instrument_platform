package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.ApplyFieldJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 此表存储工作流类型，目前需五种工作流才能满足三种审核，因为“技术机构考核”涉及两种工作流。
 * 即存4条数据
 * id     名称
 * 1       器具用户计量标准建标考核
 * 2       生产企业计量标准建标考核
 * 3       区县技术机构考核
 * 4       市技术机构考核
 * 5       生产许可证考核
 */
@ApiModel(value = "WorkflowType (工作流类型)", description = "存储工作流类型")
@Entity
public class WorkflowType {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ApiModelProperty("名称")
	@Column(unique = true)
	private String name;
	@ApiModelProperty("拼音")
	@Column(unique = true)
	private String pinyin;
	@ApiModelProperty("创建时间")
	private Long createTime;
	@ApiModelProperty("更新时间")
	private Long updateTime;
	@ApiModelProperty("描述信息")
	private String description;
	@ApiModelProperty("创建此工作流类型的用户")
	@ManyToOne
	private User CreateUser;

	@ApiModelProperty("工作流结点")
	@OneToMany(mappedBy = "workflowType")
	@JsonView({NoneJsonView.class, ApplyFieldJsonView.getById.class})
	private List<WorkflowNode> workflowNodes = new ArrayList<>();

	public WorkflowType() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public User getCreateUser() {
		return CreateUser;
	}

	public void setCreateUser(User createUser) {
		CreateUser = createUser;
	}

	public List<WorkflowNode> getWorkflowNodes() {
		return workflowNodes;
	}

	public void addWorkflowNodes(WorkflowNode workflowNode) {
		this.workflowNodes.add(workflowNode);
	}
	public void setWorkflowNodes(List<WorkflowNode> workflowNodes) {
		this.workflowNodes = workflowNodes;
	}

	@Override
	public String toString() {
		return "WorkflowType{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pinyin='" + pinyin + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", CreateUser=" + CreateUser +
				'}';
	}
}
