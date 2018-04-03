package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.*;
import com.mengyunzhi.measurement.output.InstrumentCheckedRateView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 器具受检率统计 service
 * @Author 朴世超
 */
public interface InstrumentCheckedRateService {
    /**
     * 为当前年份克隆上一年份的信息
     * 1.获取当前年份,计算上一年份
     * 2.通过上一年份获取InstrumentCheckedRate对象数组
     * 3.新建一个数组,将年份赋为当前年份,受检总数赋为0,其他参数赋值为上一年份的信息
     * 4.每次赋值后,将当前年份对象加进新数组中
     */
    void clonePreYearForCurrentYear();

    /**
     * 数据查询思想  zhangxishuo
     * 目标：在InstrumentCheckedRate表中拼出所有的字段
     * 1.获取所有状态为正常的强检器具
     * 2.获取该强检器具关联的器具用户，器具用户属于某区域(省/市/县)
     * (虽然使用省/市/县为查询条件，但是与器具关联的基本单位是器具用户，有了器具用户这些数据就有了)
     * 3.递归获取省/市/县信息
     * 4.获取所有相关联的检定信息
     * (强检器具关联器具检定信息)
     * 5.循环从器具生产到当前年份，判断所关联的检定信息中合格的信息的创建时间是否为当前循环年份
     * (如果是，表示该器具该年检定过，反之则未检定)
     * 6.前台请求
     * 6.1后台根据条件返回符合条件的记录数(即：器具总数)
     * 6.2后台根据条件返回符合条件且受检状态为true的记录数(即：受检总数)
     * (年度/省/市/县/器具用户/用途/器具名称)都是前台查询时的参数
     * 无需从后台获取，直接在用户选择之后再前台循环显示即可
     */
    void generateInstrumentCheckedInfo();

    /**
     * 根据对象获取多年的强检器具检定率信息
     * (获取单年的方法应与此方法重载)
     * @param years                    多年
     * @param province                 省
     * @param city                     市
     * @param county                   县
     * @param instrumentUserDepartment 器具用户
     * @param purpose                  用途
     * @param instrumentType           器具类别
     * @return                         强检器具检定率信息数组
     * zhangxishuo
     */
    List<InstrumentCheckedRateView> getAllCheckedRate(List<Short> years, District province, District city, District county, Department instrumentUserDepartment, Purpose purpose, InstrumentType instrumentType);

    /**
     * 根据ID获取多年的强检器具检定率信息
     * @param years                      多年
     * @param districtId                 区域id
     * @param instrumentUserDepartmentId 器具用户id
     * @param purposeId                  用途id
     * @param instrumentTypeId           器具类别id
     * @return                           强检器具检定率信息数组
     * zhangxishuo
     */
    List<InstrumentCheckedRateView> getAllCheckedRate(List<Short> years, Long districtId, Long instrumentUserDepartmentId, Long purposeId, Long instrumentTypeId);

    /**
     * 根据对象获取单年的强检器具检定率信息
     * @param year                       单年
     * @param province                   省
     * @param city                       市
     * @param county                     区县
     * @param purpose                    用途
     * @param instrumentType             器具类别
     * @return                           强检器具检定率信息数组
     * zhangxishuo
     */
    List<InstrumentCheckedRateView> getAllCheckedRate(Short year, District province, District city, District county, Purpose purpose, InstrumentType instrumentType);

    /**
     * 根据ID获取单年的强检器具检定率信息
     * @param year                       年份
     * @param districtId                 区域id
     * @param purposeId                  用途id
     * @param instrumentTypeId           器具类别id
     * @return                           强检器具检定率信息数组
     * zhangxishuo
     */
    List<InstrumentCheckedRateView> getAllCheckedRate(Short year, Long districtId, Long purposeId, Long instrumentTypeId);
}
