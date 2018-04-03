package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.ApplyJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by panjie on 17/7/13.
 * 强检器具
 */
@Entity
@DiscriminatorValue("mandatory")
@ApiModel(value = "MandatoryInstrument 强检器具", description = "强检器具使用信息")
public class MandatoryInstrument extends InstrumentEmploymentInfo {
    public static final Byte WARN_BEFORE_DAYS = 30;    // 提前预警天数
    public final static String MAX_ALLOW_BACK_DAY_KEY = "maxAllowBackDay";
    public final static String MAX_ALLOW_BACK_DAY_VALUE = "10";
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("是否通过审核")
    private Boolean audit = false;
    @ApiModelProperty("审核日期(管理部门审核通过, 或未通过审核退回的日期)")
    private Date auditDate;

    @ApiModelProperty("对应的授权检定项目")
    @Transient
    @JsonView(NoneJsonView.class)
    private DeviceInstrument deviceInstrument = new DeviceInstrument();
    @ApiModelProperty("当前器具对应的检定部门")
    @Transient
    @JsonView({NoneJsonView.class,
            ApplyJsonView.get.class})
    private List<Department> checkTechnicalInstitutionDepartments = new ArrayList<>();
    @ApiModelProperty("附件")
    @OneToMany
    @JoinColumn(name = "mandatoryInstrument_id")
    // 如果不加入该注解，删除附件时会因为没有删除中间表中的记录而抛出异常。参考链接：https://vladmihalcea.com/2017/03/29/the-best-way-to-map-a-onetomany-association-with-jpa-and-hibernate/
    private List<Attachment> attachments = new ArrayList<Attachment>();
    @ManyToOne
    @ApiModelProperty("管理部门退回原因")
    private ManagementDepartmentBackedReason managementDepartmentBackedReason;

    // ---以下信息属于一个整体，需要在进行set时，统一赋值-------------
    @ManyToOne
    @ApiModelProperty("最近的一次检定申请")
    @JsonView(NoneJsonView.class)
    private MandatoryInstrumentCheckApply lastMandatoryInstrumentCheckApply;
    @ManyToOne
    @ApiModelProperty("指定检定技术机构(对应最近一次的检定申请)")
    @JsonView(NoneJsonView.class)
    private Department lastCheckDepartment;
    // ---------------------------------------------------------

    // ---以下信息属于一个整体，需要在进行set时，统一赋值-------------
    @ApiModelProperty("最近一次检定记录")
    @ManyToOne
    @JsonView(NoneJsonView.class)
    private InstrumentCheckInfo lastInstrumentCheckInfo;
    @ApiModelProperty("下次检定日期，与最近一次的检定信息中的下次检定日期冗余，作用是优化查询效率")
    private Date nextCheckDate;
    @ApiModelProperty("最近检定日期，与最近一次的检定信息中的检定日期冗余，作用是优化查询效率")
    private Date lastCheckDate;
    // ---------------------------------------------------------

    @Transient
    private Boolean isOverdue = false;  // 是否超级未检器具

    public MandatoryInstrument() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public void setStatus(Byte status) {
        super.setStatus(status);
        if (status == InstrumentEmploymentInfo.STATUS_NEW || status == InstrumentEmploymentInfo.STATUS_BACKED) {
            this.setAudit(false);
        } else {
            this.setAudit(true);
        }
    }

    public Boolean getOverdue() {
        if (this.getNextCheckDate() != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, -1); // 下次检定日期当天，不算过期。超期下次的检定日期，算过期.
            if (this.getNextCheckDate().getTime() < calendar.getTimeInMillis()) {
                return true;
            }
        }

        return false;
    }

    public Boolean getAudit() {
        return audit;
    }

    public void setAudit(Boolean audit) {
        this.audit = audit;
    }

    public Department getLastCheckDepartment() {
        return lastCheckDepartment;
    }

    public void setLastCheckDepartment(Department lastCheckDepartment) {
        this.lastCheckDepartment = lastCheckDepartment;
    }

    public void setDeviceInstrument(DeviceInstrument deviceInstrument) {
        this.deviceInstrument = deviceInstrument;
    }

    public List<Department> getCheckTechnicalInstitutionDepartments() {
        return checkTechnicalInstitutionDepartments;
    }

    public void addCheckTechnicalInstitutionDepartments(Department checkTechnicalInstitutionDepartment) {
        this.checkTechnicalInstitutionDepartments.add(checkTechnicalInstitutionDepartment);
    }

    public void setCheckTechnicalInstitutionDepartments(List<Department> checkTechnicalInstitutionDepartments) {
        this.checkTechnicalInstitutionDepartments = checkTechnicalInstitutionDepartments;
    }

    public Date getNextCheckDate() {
        return nextCheckDate;
    }

    public void setNextCheckDate(Date nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
    }

    public Date getLastCheckDate() {
        return lastCheckDate;
    }

    public void setLastCheckDate(Date lastCheckDate) {
        this.lastCheckDate = lastCheckDate;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public ManagementDepartmentBackedReason getManagementDepartmentBackedReason() {
        return managementDepartmentBackedReason;
    }

    public void setManagementDepartmentBackedReason(ManagementDepartmentBackedReason managementDepartmentBackedReason) {
        this.managementDepartmentBackedReason = managementDepartmentBackedReason;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public DeviceInstrument getDeviceInstrument() {
        return deviceInstrument;
    }

    public MandatoryInstrumentCheckApply getLastMandatoryInstrumentCheckApply() {
        return lastMandatoryInstrumentCheckApply;
    }

    public void setLastMandatoryInstrumentCheckApply(MandatoryInstrumentCheckApply lastMandatoryInstrumentCheckApply) {
        this.lastMandatoryInstrumentCheckApply = lastMandatoryInstrumentCheckApply;
        if (lastMandatoryInstrumentCheckApply != null) {
            // 设置最近一次检定申请对应的检定机构
            this.setLastCheckDepartment(lastMandatoryInstrumentCheckApply.getCurrentWork().getWork().getAuditDepartment());
        }
    }

    public InstrumentCheckInfo getLastInstrumentCheckInfo() {
        return lastInstrumentCheckInfo;
    }

    public void setLastInstrumentCheckInfo(InstrumentCheckInfo lastInstrumentCheckInfo) {
        this.lastInstrumentCheckInfo = lastInstrumentCheckInfo;
        if (lastInstrumentCheckInfo != null) {
            // 设置最后检定日期
            this.setLastCheckDate(lastInstrumentCheckInfo.getCheckDate());
            // 设置下次检定日期（检定有效截至日期)
            this.setNextCheckDate(lastInstrumentCheckInfo.getValidityDate());
        }
    }

}
