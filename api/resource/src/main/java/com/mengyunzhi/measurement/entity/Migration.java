package com.mengyunzhi.measurement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.repository.query.Param;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 数据迁移实体
 * 用来控制初始化数据
 * @author panjie
 */
@Entity
@ApiModel(value = "Migration 数据版本", description = "用于进行版本更新时数据初始化")
public class Migration implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    @ApiModelProperty("版本号")
    private String batch;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }
}
