package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.repository.DistrictRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author panjie on 2018/1/15
 * 区域
 */
@Component
public class DistrictServiceImplTestData {
    private final static Logger logger = Logger.getLogger(DistrictServiceImplTestData.class.getName());
    @Autowired DistrictService districtService; // 区域
    @Autowired
    DistrictRepository districtRepository; // 区域
    public void getOneLevel3BinaryTree(District parentDistrict, District sonDistrict1, District sonDistrict2) {
        logger.info("新建一个深度为3的2叉树结构");

        sonDistrict1.setParentDistrict(parentDistrict);
        districtRepository.save(sonDistrict1);

        District district11 = districtService.getOneSavedDistrict();
        district11.setParentDistrict(sonDistrict1);
        districtRepository.save(district11);

        District district12 = districtService.getOneSavedDistrict();
        district12.setParentDistrict(sonDistrict1);
        districtRepository.save(district12);

        sonDistrict2.setParentDistrict(parentDistrict);
        districtRepository.save(sonDistrict2);

        District district21 = districtService.getOneSavedDistrict();
        district21.setParentDistrict(sonDistrict2);
        districtRepository.save(district21);

        District district22 = districtService.getOneSavedDistrict();
        district22.setParentDistrict(sonDistrict2);
        districtRepository.save(district22);
    }
}
