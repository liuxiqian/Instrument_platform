package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@ApiModel(value = "Work (工作)", description = "工作实体")
public class Work implements Serializable {
    public static final String AUDIT_TYPE_SEND_TO_NEXT_DEPARTMENT = "sendToNextDepartment";        // 送下一审核部门
    public static final String AUDIT_TYPE_BACK_TO_APPLY_DEPARTMENT = "backToApplyDepartment";      // 送申请部门
    public static final String AUDIT_TYPE_BACK_TO_PRE_DEPARTMENT = "backToPreDepartment";          // 送上一审核部门
    public static final String AUDIT_TYPE_DONE = "done";                                           // 办结
    public static final String AUDIT_TYPE_DELETE = "delete";                                       // 删除

    public final static Byte UN_HANDLE = 0;  // 待办
    public final static Byte HANDLING = 1;  // 在办
    public final static Byte HANDLED = 2;   // 已办
    public final static Byte DONE = 3;      // 办结

    public static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @ApiModelProperty("上一工作")
    @JsonView({NoneJsonView.class,
            WorkJsonView.getById.class,
            MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class})
    @Lazy
    private Work preWork;

    @ApiModelProperty("状态")
    private Byte status = UN_HANDLE;

    @OneToOne
    @ApiModelProperty("下一工作")
    @JsonView({NoneJsonView.class})
    @Lazy
    private Work nextWork;

    @ApiModelProperty("审核类型")
    private String auditType;

    @OneToOne
    @ApiModelProperty("工作的别名")
    @JsonView({NoneJsonView.class})
    @Lazy
    private Work aliasWork;
    @ApiModelProperty("审核意见")
    private String opinion;
    @ApiModelProperty("创建时间")
    @CreationTimestamp
    private Calendar createTime;
    @ApiModelProperty("更新时间")
    @UpdateTimestamp
    private Calendar updateTime;
    @ApiModelProperty("点击时间")
    private Calendar clickTime;

    @ManyToOne(cascade = CascadeType.MERGE)
    @ApiModelProperty("关联申请实体")
    @JsonView({
            ApplyJsonView.page.class,
            NoneJsonView.class,
            MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class,
            OverdueCheckApplyControllerJsonView.page.class,
            WorkJsonView.getById.class
           })
    private Apply apply;

    @ManyToOne
    @ApiModelProperty("关联工作流节点实体")
    @JsonView({
            NoneJsonView.class,
            WorkJsonView.getById.class})
    private WorkflowNode workflowNode;

    @ManyToOne
    @ApiModelProperty("需要审核的部门")
    private Department auditDepartment;

    @ManyToOne
    @ApiModelProperty("审核人")
    private User auditUser;

    public static Long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Department getAuditDepartment() {
        return auditDepartment;
    }

    public void setAuditDepartment(Department auditDepartment) {
        this.auditDepartment = auditDepartment;
    }

    public User getAuditUser() {
        return auditUser;
    }

    public void setAuditUser(User auditUser) {
        this.auditUser = auditUser;
    }

    public Work() {
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Work getNextWork() {
        return nextWork;
    }

    public void setNextWork(Work nextWork) {
        this.nextWork = nextWork;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Work getPreWork() {
        return preWork;
    }

    public void setPreWork(Work preWork) {
        this.preWork = preWork;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
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

    public Calendar getClickTime() {
        return clickTime;
    }

    public void setClickTime(Calendar clickTime) {
        this.clickTime = clickTime;
    }



    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public Apply getApply() {
        return apply;
    }

    public void setApply(Apply apply) {
        this.apply = apply;
    }

    public WorkflowNode getWorkflowNode() {
        return workflowNode;
    }

    public void setWorkflowNode(WorkflowNode workflowNode) {
        this.workflowNode = workflowNode;
    }

    public Work getAliasWork() {
        return aliasWork;
    }

    public void setAliasWork(Work aliasWork) {
        this.aliasWork = aliasWork;
    }

    @Override
    public String toString() {
        return "Work{" +
                "id=" + id +
                ", preWork=" + preWork +
                ", nextWork=" + nextWork +
                ", auditType='" + auditType + '\'' +
                ", aliasWork=" + aliasWork +
                ", opinion='" + opinion + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", clickTime=" + clickTime +
                ", apply=" + apply +
                ", workflowNode=" + workflowNode +
                ", auditDepartment=" + auditDepartment +
                ", auditUser=" + auditUser +
                '}';
    }
}
