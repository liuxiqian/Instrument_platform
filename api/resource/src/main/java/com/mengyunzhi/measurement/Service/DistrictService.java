package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.District;

import java.util.List;

/**
 * Created by liming on 17-6-2.
 * 区域实体的Service
 */
public interface DistrictService {
    //保存
    District save(District district);
    //获取所有数据
    List<District> getAll();
    //获取一条数
    District get(Long id);
    //删除一条数据
    void delete(Long id);
    // 获取某个区域的树状结构
    District getTreeByDistrict(District district);
    District getTreeByDistrictId(Long districtId);

    List<District> getSonsListByDistrict(District district);    // 获取所有的子区域信息
    List<District> getSonsListByDistrictId(Long districtId);
    List<District> getClassOneSonListByDistrict(District district);
    List<District> getClassOneSonListByDistrictId(Long districtId);     // 获取所有的一级子区域信息

    // 获取某个根区域下的子区域列表（如果子区域未在根区域中，则返回null）
    List<District> getSonsListByRootDistrictAndSonDistrictId(District district, Long districtId);
    District getSonTreeByRootDistrictAndSonDistrictId(District rootDistrict, Long districtId);
    District getSonTreeByDistrictTreeAndSonDistrictId(District rootDistrictTree, Long districtId);


    List<District> getParentListByDistrict(District district);
    List<District> getParentListByDistrictId(Long id);

    District getOneSavedDistrict();

    // 获取根区域
    District getRootDistrictTree();

    // 判断一个区域是否是一个区域的子区域
    boolean preOneIsParentNextOne(District district, District district1);

    // 获取一个区县级的区域
    District getOneSavedCountryDistrict();
}
