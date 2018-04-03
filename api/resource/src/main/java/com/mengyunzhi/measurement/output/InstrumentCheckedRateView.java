package com.mengyunzhi.measurement.output;

import com.mengyunzhi.measurement.entity.*;

import java.util.Map;

/**
 * @author zhangxishuo on 2018/3/23
 * 器具检定率前台返回实体
 * 用于将后台信息转换，减少前台数据的复杂度
 */
public class InstrumentCheckedRateView {

    private Short year;                           // 年份

    private District provinceDistrict;            // 省

    private District cityDistrict;                // 市

    private District countyDistrict;              // 县

    private Department instrumentUserDepartment;  // 器具用户

    private Purpose purpose;                      // 用途

    private InstrumentType instrumentType;        // 器具类型

    private int instrumentTotal;                  // 器具总数

    private int checkedTotal;                     // 受检总数

    public InstrumentCheckedRateView(Map<String, Object> map, int instrumentTotal, int checkedTotal) {
        this.year = (Short) map.get("year");
        this.provinceDistrict = (District) map.get("provinceDistrict");
        this.cityDistrict = (District) map.get("cityDistrict");
        this.countyDistrict = (District) map.get("countyDistrict");
        this.instrumentUserDepartment = (Department) map.get("instrumentUserDepartment");
        this.purpose = (Purpose) map.get("purpose");
        this.instrumentType = (InstrumentType) map.get("instrumentType");
        this.instrumentTotal = instrumentTotal;
        this.checkedTotal = checkedTotal;
    }

    public Short getYear() {
        return year;
    }

    public District getProvinceDistrict() {
        return provinceDistrict;
    }

    public District getCityDistrict() {
        return cityDistrict;
    }

    public District getCountyDistrict() {
        return countyDistrict;
    }

    public Department getInstrumentUserDepartment() {
        return instrumentUserDepartment;
    }

    public Purpose getPurpose() {
        return purpose;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public int getInstrumentTotal() {
        return instrumentTotal;
    }

    public int getCheckedTotal() {
        return checkedTotal;
    }
}
