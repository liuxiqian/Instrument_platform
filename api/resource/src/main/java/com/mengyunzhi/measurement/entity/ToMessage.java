package com.mengyunzhi.measurement.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created By chuhang on 17-10-17
 * 接收消息 实体
 */
@Entity
@DiscriminatorValue("ToMessage")
@ApiModel(value = "ToMessage 接收消息", description = "接收消息")
public class ToMessage extends Message {

    public ToMessage() {

    }
}
