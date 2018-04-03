package com.mengyunzhi.measurement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * 退回理由
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 继承（策略 = 单表继承）
@DiscriminatorColumn(name = "DB_TYPE")  // 鉴别的列名为 DB_TYPE
@ApiModel(value = "BackedReason (退回理由)", description = "退回理由")
public abstract class BackedReason implements Serializable {
    static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ApiModelProperty("理由")
    @Column(unique = true)
    private String reason;
    @ApiModelProperty("更新时间")
    @UpdateTimestamp
    private Calendar updateTime;

    public BackedReason() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Calendar getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Calendar updateTime) {
        this.updateTime = updateTime;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
