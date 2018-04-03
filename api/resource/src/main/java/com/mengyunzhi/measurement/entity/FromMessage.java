package com.mengyunzhi.measurement.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created By chuhang on 17-10-17
 * 发送消息
 */
@Entity
@DiscriminatorValue("FromMessage")
@ApiModel(value = "FromMessage 发送消息", description = "发送消息")
public class FromMessage extends Message {

    public FromMessage() {
    }

}
