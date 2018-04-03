package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Inheritance = 继承
@DiscriminatorColumn(name = "table_type")
@ApiModel(value = "Apply (申请)", description = "申请实体")
public class Apply implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public static final String CLASS_NAME = "Apply";
    protected String className = CLASS_NAME;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("是否同意")
    private Boolean isAgree;

    @ApiModelProperty("申请时间")
    @CreationTimestamp
    private Calendar applyTime;
    @ApiModelProperty("更新时间")
    @UpdateTimestamp
    private Calendar updateTime;
    @ApiModelProperty("是否办结")
    private Boolean isDone = false;

    @ManyToOne
    @ApiModelProperty("创建此申请的用户")
    private User createUser;

    @ApiModelProperty("申请部门")
    @ManyToOne
    private Department department;

    @ApiModelProperty("当前审核部门")
    @Transient
    private Department auditingDepartment;

    @ApiModelProperty("当前受理人")
    @Transient
    private User auditingUser;

    @ApiModelProperty("当前工作（获取待、在办部门)")
    @ManyToOne
    @JsonView(NoneJsonView.class)
    private CurrentWork currentWork;

    public Apply() {
    }

    public Department getAuditingDepartment() {
        if (this.getCurrentWork() != null && this.getCurrentWork().getWork() != null) {
            return this.getCurrentWork().getWork().getAuditDepartment();
        }
        return null;
    }

    public CurrentWork getCurrentWork() {
        return currentWork;
    }

    public void setCurrentWork(CurrentWork currentWork) {
        this.currentWork = currentWork;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Calendar getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Calendar applyTime) {
        this.applyTime = applyTime;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getDone() {
        if (null != this.currentWork) {
            if (this.currentWork.getWork().getStatus().equals(Work.DONE)) {
                return true;
            }
        }

        return false;
    }

    public User getAuditingUser() {
        if (this.getCurrentWork() != null && this.getCurrentWork().getWork() != null) {
            return this.getCurrentWork().getWork().getAuditUser();
        }

        return null;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
        if (createUser != null && createUser.getDepartment() != null) {
            this.setDepartment(createUser.getDepartment());
        }
    }



    public Boolean getAgree() {
        return isAgree;
    }

    public void setAgree(Boolean agree) {
        isAgree = agree;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
