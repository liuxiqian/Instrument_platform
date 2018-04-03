package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * 器具使用信息 实体
 * 修改人 高黎明
 */
@Entity
@ApiModel(value = "InstrumentEmploymentInfo (器具使用信息)", description = "器具使用信息实体")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 继承（策略 = 单表继承）
@DiscriminatorColumn(name = "DB_TYPE")  // 鉴别的列名为 DB_TYPE，将在数据表中生成该字段，用与区分强检与非强检
public abstract class InstrumentEmploymentInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final Byte STATUS_BACKED = -2;   // 被退回
    public static final Byte STATUS_NEW = -1;     // 新建
    public static final Byte STATUS_NORMAL = 0;   // 正常
    public static final Byte STATUS_STOP = 1;     // 停用
    public static final Byte STATUS_SCRAP = 2;    // 报废

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty("新增时间")
    @CreationTimestamp
    private Calendar createTime;
    @ApiModelProperty("安装地点")
    private String fixSite = "";
    @ManyToOne @JsonView({NoneJsonView.class})
    @ApiModelProperty("操作用户")
    private User createUser;

    @ApiModelProperty("出厂编号")
    private String serialNum = "";

    @ApiModelProperty("许可证号")
    private String licenseNum  = "";
    @ApiModelProperty("器具名称")
    private String name  = "";
    @ApiModelProperty("出厂名称")
    private String outOfFactoryName  = "";
    @ApiModelProperty("规格型号")
    private String specificationName  = "";
    @ManyToOne
    @ApiModelProperty("用途")
    @JsonView({NoneJsonView.class,
            ApplyJsonView.get.class,
            WorkJsonView.getById.class,
            CheckApplyJsonView.get.class,
            MandatoryInstrumentJsonView.findById.class,
            MandatoryInstrumentApplyJsonView.findById.class,
            MandatoryInstrumentJsonView.pageAllOfCurrentUser.class,
            InstrumentCheckInfoJsonView.getAllOfCurrentUser.class
    })
    private Purpose purpose;
    @ApiModelProperty("拥有企业")
    @ManyToOne
    private Department department;
    @ApiModelProperty("器具生产信息")
    @ManyToOne
    @JsonView({NoneJsonView.class,
            ApplyJsonView.get.class,
            MandatoryInstrumentApplyJsonView.findById.class})
    private InstrumentProduction instrumentProduction;
    @ApiModelProperty("精确度")
    @ManyToOne @JsonView({NoneJsonView.class,
            ApplyJsonView.get.class,
            WorkJsonView.getById.class,
            CheckApplyJsonView.get.class,
            MandatoryInstrumentJsonView.findById.class,
            MandatoryInstrumentApplyJsonView.findById.class,
            MandatoryInstrumentJsonView.pageAllOfCurrentUser.class,
            MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class,
            InstrumentCheckInfoJsonView.getAllOfCurrentUser.class})
    private Accuracy accuracy;
    @ApiModelProperty("最小测量范围")
    @ManyToOne @JsonView({
            NoneJsonView.class,
            ApplyJsonView.get.class,
            WorkJsonView.getById.class,
            CheckApplyJsonView.get.class,
            MandatoryInstrumentJsonView.findById.class,
            MandatoryInstrumentApplyJsonView.findById.class,
            MandatoryInstrumentJsonView.pageAllOfCurrentUser.class,
            MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class
            })
    private MeasureScale minMeasureScale;

    @ApiModelProperty("最大测量范围")
    @ManyToOne @JsonView({
            NoneJsonView.class,
            ApplyJsonView.get.class,
            WorkJsonView.getById.class,
            CheckApplyJsonView.get.class,
            MandatoryInstrumentJsonView.findById.class,
            MandatoryInstrumentApplyJsonView.findById.class,
            MandatoryInstrumentJsonView.pageAllOfCurrentUser.class,
            MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class
    })
    private MeasureScale maxMeasureScale;

    @ApiModelProperty("测量范围(计算后得出，并不存在于数据表中)")
    @Transient		// 该字段并不存在数据表中
    private String measureScale;

    @ApiModelProperty("适用规格型号")
    @ManyToOne @JsonView({NoneJsonView.class,
            MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class,
            ApplyJsonView.get.class,
            MandatoryInstrumentJsonView.findById.class,
            MandatoryInstrumentApplyJsonView.findById.class})
    private Specification specification;

    @ApiModelProperty("器具类别")
    @ManyToOne
    @JsonView({NoneJsonView.class,
            ApplyJsonView.get.class,
            WorkJsonView.getById.class,
            CheckApplyJsonView.get.class,
            MandatoryInstrumentJsonView.findById.class,
            MandatoryInstrumentApplyJsonView.findById.class,
            MandatoryInstrumentJsonView.pageAllOfCurrentUser.class,
            InstrumentCheckInfoJsonView.getAllOfCurrentUser.class,
            MandatoryInstrumentWorkJsonView.pageOfCurrentLoginUser.class,
            })
    private InstrumentType instrumentType;

    @ManyToOne
    @ApiModelProperty("生产企业（生产部门 制造单位）")
    @JsonView({NoneJsonView.class,
            ApplyJsonView.get.class,
            WorkJsonView.getById.class,
            CheckApplyJsonView.get.class,
            MandatoryInstrumentJsonView.findById.class,
            MandatoryInstrumentApplyJsonView.findById.class,
            MandatoryInstrumentJsonView.pageAllOfCurrentUser.class,
            InstrumentCheckInfoJsonView.getAllOfCurrentUser.class
    })
    private Department generativeDepartment;
    @ApiModelProperty("器具状态： -1：未通过审核； 0：正常； 1：停用 2：报废 3：被退回")
    private Byte status = STATUS_NEW; // 器具状态：

    public InstrumentEmploymentInfo() {
    }


    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public String getFixSite() {
        return fixSite;
    }

    public void setFixSite(String fixSite) {
        this.fixSite = fixSite;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Department getGenerativeDepartment() {
        return generativeDepartment;
    }

    public void setGenerativeDepartment(Department generativeDepartment) {
        this.generativeDepartment = generativeDepartment;
    }

    public InstrumentProduction getInstrumentProduction() {
        return instrumentProduction;
    }

    public void setInstrumentProduction(InstrumentProduction instrumentProduction) {
        this.instrumentProduction = instrumentProduction;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOutOfFactoryName() {
        return outOfFactoryName;
    }

    public void setOutOfFactoryName(String outOfFactoryName) {
        this.outOfFactoryName = outOfFactoryName;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public void setPurpose(Purpose purpose) {
        this.purpose = purpose;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Accuracy getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Accuracy accuracy) {
        this.accuracy = accuracy;
    }

    public MeasureScale getMinMeasureScale() {
        return minMeasureScale;
    }

    public void setMinMeasureScale(MeasureScale minMeasureScale) {
        this.minMeasureScale = minMeasureScale;
    }

    public Specification getSpecification() {
        return specification;
    }

    public void setSpecification(Specification specification) {
        this.specification = specification;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public MeasureScale getMaxMeasureScale() {
        return maxMeasureScale;
    }

    public void setMaxMeasureScale(MeasureScale maxMeasureScale) {
        this.maxMeasureScale = maxMeasureScale;
    }

    static public String getStatusName(int status) {
        if (status == STATUS_NEW) {
            return "审核中";
        } else if (status == STATUS_NORMAL) {
            return "正常";
        } else if (status == STATUS_STOP) {
            return "停用";
        } else if (status == STATUS_SCRAP) {
            return "报废";
        } else {
            return "被退回";
        }
    }

    public String getMeasureScale() {
        MeasureScale maxMeasureScale = this.getMaxMeasureScale();
        MeasureScale minMeasureScale = this.getMinMeasureScale();
        if (maxMeasureScale != null && minMeasureScale != null) {
            if (minMeasureScale.getWeight() < maxMeasureScale.getWeight()) {
                return minMeasureScale.getValue() + " ~ " + maxMeasureScale.getValue();
            }

            if (minMeasureScale.getWeight() > maxMeasureScale.getWeight()) {
                return maxMeasureScale.getValue() + " ~ " + minMeasureScale.getValue();
            }

            if (minMeasureScale.getWeight() == maxMeasureScale.getWeight()) {
                return minMeasureScale.getValue();
            }
        }
        return " ~ ";
    }
}
