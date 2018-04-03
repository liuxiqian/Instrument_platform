package com.mengyunzhi.measurement.output;

import com.fasterxml.jackson.annotation.JsonView;
import com.mengyunzhi.measurement.entity.Department;
import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.jsonView.BackQualifiedInstrumentJsonView;
import com.mengyunzhi.measurement.jsonView.NoneJsonView;

import java.io.Serializable;

/**
 * 用于返回查询强检器具合格率的数据
 * zhuchenshu
 */
public class BackQualifiedInstrument implements Serializable {
    private static final long serialVersionUID = 1L;
    // 区域
    @JsonView({NoneJsonView.class,
            BackQualifiedInstrumentJsonView.class})
    District district;
    // 器具用户
    @JsonView({NoneJsonView.class,
            BackQualifiedInstrumentJsonView.class})
    Department department;
    // 受检总数
    int sum;
    // 合格数
    int qualifiedNumber;
    // 年份
    int year;
    // 构造函数返回区域，总数，合格数
    public BackQualifiedInstrument(District district,Department department, int year, int sum, int qualifiedNumber) {
        this.district = district;
        this.sum = sum;
        this.department = department;
        this.qualifiedNumber = qualifiedNumber;
        this.year = year;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepratment(Department intrumentTypeId) {
        this.department = intrumentTypeId;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getQualifiedNumber() {
        return qualifiedNumber;
    }

    public void setQualifiedNumber(int qualifiedNumber) {
        this.qualifiedNumber = qualifiedNumber;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
