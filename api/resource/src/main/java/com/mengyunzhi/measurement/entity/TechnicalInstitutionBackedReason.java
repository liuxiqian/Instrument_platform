package com.mengyunzhi.measurement.entity;

import com.mengyunzhi.measurement.entity.BackedReason;
import io.swagger.annotations.ApiModel;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by chuhang on 17-11-9
 * 技术机构退回原因
 */
@Entity
@DiscriminatorValue("TIBackedReason")   // 这里使用TechnicalInstitutionBackedReason简写，否则会抛出db_type太长的异常
@ApiModel(value = "TechnicalInstitutionBackedReason 技术机构退回原因", description = "管理部门退回原因")
public class TechnicalInstitutionBackedReason extends BackedReason {
}
