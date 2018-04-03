package com.mengyunzhi.measurement.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Created By chuhang on 17-10-12
 * 消息 实体
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 继承（策略 = 单表继承）
@DiscriminatorColumn(name = "DB_TYPE")  // 鉴别的列名为 DB_TYPE，将在数据表中生成该字段，用与区分接收消息和发送消息
@ApiModel(value = "Message (消息)",description = "用户或者部门收发消息")
public abstract class Message implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty("内容")
    @Type(type="text")
    private String content;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("已读。0：否， 1：是")
    private Boolean isRead = Boolean.FALSE;

    @ApiModelProperty("发送部门")
    @ManyToOne
    private Department fromDepartment;

    @ApiModelProperty("收件部门")
    @ManyToOne
    private Department toDepartment;

    @ApiModelProperty("首次阅读时间")
    private Long firstReadTime;

    @ApiModelProperty("首次阅读用户")
    @ManyToOne
    @JsonView(NoneJsonView.class)
    private User firstReadUser;

    @ApiModelProperty("创建用户")
    @ManyToOne
    private User createUser;

    @ApiModelProperty("创建时间")
    @CreationTimestamp
    private Calendar createTime;

    public Message() {
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                ", isRead=" + isRead +
                ", fromDepartment=" + fromDepartment +
                ", toDepartment=" + toDepartment +
                ", firstReadTime=" + firstReadTime +
                ", firstReadUser=" + firstReadUser +
                ", createUser=" + createUser +
                ", createTime=" + createTime +
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public Department getFromDepartment() {
        return fromDepartment;
    }

    public void setFromDepartment(Department fromDepartment) {
        this.fromDepartment = fromDepartment;
    }

    public Department getToDepartment() {
        return toDepartment;
    }

    public void setToDepartment(Department toDepartment) {
        this.toDepartment = toDepartment;
    }

    public Long getFirstReadTime() {
        return firstReadTime;
    }

    public void setFirstReadTime(Long firstReadTime) {
        this.firstReadTime = firstReadTime;
    }

    public User getFirstReadUser() {
        return firstReadUser;
    }

    public void setFirstReadUser(User firstReadUser) {
        this.firstReadUser = firstReadUser;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Calendar getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Calendar createTime) {
        this.createTime = createTime;
    }

    public Message(String content, String title, Boolean isRead, Department fromDepartment, Department toDepartment, Long firstReadTime, User firstReadUser, User createUser, Calendar createTime) {

        this.content = content;
        this.title = title;
        this.isRead = isRead;
        this.fromDepartment = fromDepartment;
        this.toDepartment = toDepartment;
        this.firstReadTime = firstReadTime;
        this.firstReadUser = firstReadUser;
        this.createUser = createUser;
        this.createTime = createTime;
    }
}
