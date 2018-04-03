package com.mengyunzhi.measurement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ApiModel(value = "DepartmentType (部门类型)", description = "部门类型实体")
public class DepartmentType implements Serializable {
	final public static String NAME_GUAN_LI_BU_MEN = "管理部门";
	final public static String NAME_JI_SHU_JI_GOU = "技术机构";
	final public static String NAME_SHENG_CHANG_QI_YE = "生产企业";
	final public static String NAME_QI_JU_YONG_HU = "器具用户";
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
	@ManyToOne
	@ApiModelProperty("创建部门类型的用户")
	private User createUser;

	public DepartmentType() {
	}

	public DepartmentType(String name, String pinyin, Long createTime, Long updateTime, User createUser) {
		this.name = name;
		this.pinyin = pinyin;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.createUser = createUser;
	}

	@Override
	public String toString() {
		return "DepartmentType{" +
				"id=" + id +
				", name='" + name + '\'' +
				", pinyin='" + pinyin + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", createUser=" + createUser +
				'}';
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
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}
}
