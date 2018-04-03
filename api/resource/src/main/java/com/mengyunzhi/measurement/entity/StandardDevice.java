package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import com.mengyunzhi.measurement.jsonView.StandardDeviceJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

/**
 * Created by liming on 17-5-9.
 * 标准器
 */
@Entity
@ApiModel(value = "StandardDevice (标准器)", description = "标准器实体")
public class StandardDevice implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @ManyToOne @JsonView({NoneJsonView.class, StandardDeviceJsonView.baseJsonView.class})
    @ApiModelProperty("学科类别")
    private Discipline discipline;
    @ManyToOne @ApiModelProperty("最后一次的检定信息") @JsonView({NoneJsonView.class})
    private StandardDeviceCheckDetail lastCheckDetail;
    @ApiModelProperty("最后一次的检定日期")
    private Date lastCheckDate;
    @ApiModelProperty("有效期至（下次检定日期")
    private Date nextCheckDate;
    @ApiModelProperty("是否主标准器")
    private Boolean isMain;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("代码")
    private String code;
    @ApiModelProperty("出厂编号")
    private String factoryNum;
    @ApiModelProperty("许可证号")
    private String licenseNum;
    @ApiModelProperty("生产企业")
    @ManyToOne @JsonView({NoneJsonView.class, StandardDeviceJsonView.baseJsonView.class})
    private Department manufacturerDepartment;
    @ManyToOne @ApiModelProperty("操作用户")
    private User currentUser;
    @ManyToOne @ApiModelProperty("计量标准装置")
    @JsonView({NoneJsonView.class, StandardDeviceJsonView.baseJsonView.class})
    private DeviceSet deviceSet;
    @ApiModelProperty("规格型号")
    private String specification;
    @ApiModelProperty("测量范围")
    private String measureScale;
    @ApiModelProperty("精确度")
    private String accuracy;
    @ApiModelProperty("创建时间") @CreationTimestamp
    private Calendar createTime;
    @ApiModelProperty("更新时间") @UpdateTimestamp
    private Calendar updateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public StandardDevice() {
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public StandardDeviceCheckDetail getLastCheckDetail() {
        return lastCheckDetail;
    }

    public void setLastCheckDetail(StandardDeviceCheckDetail lastCheckDetail) {
        if (null != lastCheckDetail) {
            // 设置最后一次的检定记录时，给最后一次的检定日期以及下次检定日期赋值
            this.setLastCheckDate(lastCheckDetail.getCheckDate());
            this.setNextCheckDate(lastCheckDetail.getNextCheckDate());
        }

        this.lastCheckDetail = lastCheckDetail;
    }

    public Date getLastCheckDate() {
        return lastCheckDate;
    }

    public void setLastCheckDate(Date lastCheckDate) {
        this.lastCheckDate = lastCheckDate;
    }

    public Date getNextCheckDate() {
        return nextCheckDate;
    }

    public void setNextCheckDate(Date nextCheckDate) {
        this.nextCheckDate = nextCheckDate;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Boolean getMain() {
        return isMain;
    }

    public void setMain(Boolean main) {
        isMain = main;
    }

    public Department getManufacturerDepartment() {
        return manufacturerDepartment;
    }

    public void setManufacturerDepartment(Department manufacturerDepartment) {
        this.manufacturerDepartment = manufacturerDepartment;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getMeasureScale() {
        return measureScale;
    }

    public void setMeasureScale(String measureScale) {
        this.measureScale = measureScale;
    }

    public String getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(String factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public DeviceSet getDeviceSet() {
        return deviceSet;
    }

    public void setDeviceSet(DeviceSet deviceSet) {
        this.deviceSet = deviceSet;
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

    @Override
    public String toString() {
        return "StandardDevice{" +
                "Id=" + Id +
                ", discipline=" + discipline +
                ", lastCheckDetail=" + lastCheckDetail +
                ", lastCheckDate=" + lastCheckDate +
                ", nextCheckDate=" + nextCheckDate +
                ", isMain=" + isMain +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", factoryNum='" + factoryNum + '\'' +
                ", licenseNum='" + licenseNum + '\'' +
                ", manufacturerDepartment=" + manufacturerDepartment +
                ", currentUser=" + currentUser +
                ", deviceSet=" + deviceSet +
                ", specification='" + specification + '\'' +
                ", measureScale='" + measureScale + '\'' +
                ", accuracy='" + accuracy + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
