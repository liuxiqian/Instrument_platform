package com.mengyunzhi.measurement.entity;


import com.mengyunzhi.measurement.entity.BackedReason;
import io.swagger.annotations.ApiModel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MDBackedReason")      // 这里使用ManagementDepartmentBackedReason简写，否则会抛出db_type太长的异常
@ApiModel(value = "ManagementDepartmentBackedReason 管理部门退回原因", description = "管理部门退回原因")
public class ManagementDepartmentBackedReason extends BackedReason {
}
