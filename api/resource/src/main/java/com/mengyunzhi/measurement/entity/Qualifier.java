package com.mengyunzhi.measurement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.log4j.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

@Entity
@ApiModel(value = "Qualifier (人员)", description = "人员实体")
public class Qualifier implements Serializable {
	private static final Logger logger = Logger.getLogger(Qualifier.class.getName());
	private static final long serialVersionUID = 1L;
	private static final long MILLISECS_PER_DAY = 86400000L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("从业年限")
	@Transient
	private int practitionersYears;
	@ApiModelProperty("首次从业时间")
	private Date firstPractitionerDate;
	@ApiModelProperty("学历")
	private String education;
	@ApiModelProperty("出生日期")
	private Date birthDate;
	@ApiModelProperty("职称")
	private String jobTitle;
	@ApiModelProperty("拼音")
	private String pinyin;
	@ApiModelProperty("创建时间")
	private Calendar createTime;
	@ApiModelProperty("更新时间")
	private Calendar updateTime;

	@ManyToOne
	@ApiModelProperty("操作用户id（FK）")
	private User createUser;

	@ManyToOne
	@ApiModelProperty("部门id（FK）")
	private Department department;

	public Qualifier() {
	}

	public Qualifier(String name, int practitionersYears, String education, Date birthDate, String jobTitle, String pinyin, Calendar createTime, Calendar updateTime, User createUser, Department department) {
		this.name = name;
		this.practitionersYears = practitionersYears;
		this.education = education;
		this.birthDate = birthDate;
		this.jobTitle = jobTitle;
		this.pinyin = pinyin;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.createUser = createUser;
		this.department = department;
	}

	@Override
	public String toString() {
		return "Qualifier{" +
				"id=" + id +
				", name='" + name + '\'' +
				", practitionersYears=" + practitionersYears +
				", education='" + education + '\'' +
				", birthDate=" + birthDate +
				", jobTitle='" + jobTitle + '\'' +
				", pinyin='" + pinyin + '\'' +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", createUser=" + createUser +
				", department=" + department +
				'}';
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

	public int getPractitionersYears() {
		logger.info("获取当前时间，减去首次从业时间，得到YEAR");
		long endL = new Date(Calendar.getInstance().getTimeInMillis()).getTime();
		long startL = endL;
		if (null != this.getFirstPractitionerDate()) {
			logger.info("设置了首次从业时间,执行减法运算");
			startL = this.getFirstPractitionerDate().getTime();
		}
		return (int) ((endL - startL) / (MILLISECS_PER_DAY * 365));
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public Calendar getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Calendar updateTime) {
		this.updateTime = updateTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Date getFirstPractitionerDate() {
		return firstPractitionerDate;
	}

	public void setFirstPractitionerDate(Date firstPractitionerDate) {
		this.firstPractitionerDate = firstPractitionerDate;
	}
}
