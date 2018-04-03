package com.mengyunzhi.measurement.dataInit;

import com.mengyunzhi.measurement.EntityInitDataListener;
import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.repository.DepartmentRepository;
import com.mengyunzhi.measurement.repository.DistrictRepository;
import com.mengyunzhi.measurement.repository.DistrictTypeRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by panjie on 17/7/16.
 * 区域数据初始化
 */
@Component
public class DistrictDataInit extends EntityInitDataListener {
    private Logger logger = Logger.getLogger(EntityInitDataListener.class.getName());
    @Autowired
    protected EntityInitDataListener entityInitDataListener;
    @Autowired protected DistrictRepository districtRepository; // 区域
    @Autowired protected DistrictTypeRepository districtTypeRepository; // 区域类型
    @Autowired protected DepartmentRepository departmentRepository;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        List<District> districts1 = (List<District>) districtRepository.findAll();
        if (districts1.size() == 0) {
            logger.info("----- 添加区域信息 panjie -----");

            logger.info("增加一个内蒙古自治区");
            District neimengguDistrict = new District();
            neimengguDistrict.setDistrictType(districtTypeRepository.findOneByName("省"));
            neimengguDistrict.setName("内蒙古自治区");
            neimengguDistrict.setPinyin("neimengguzizhiqu");
            districtRepository.save(neimengguDistrict);

            logger.info("增加一个赤峰市");
            District chifengDistrict = new District();
            chifengDistrict.setDistrictType(districtTypeRepository.findOneByName("市"));
            chifengDistrict.setName("赤峰市");
            chifengDistrict.setPinyin("chifengshi");
            chifengDistrict.setParentDistrict(neimengguDistrict);
            districtRepository.save(chifengDistrict);

            logger.info("添加测试市");
            District testCityDistrict = new District();
            testCityDistrict.setDistrictType(districtTypeRepository.findOneByName("市"));
            testCityDistrict.setName("测试市");
            testCityDistrict.setPinyin("ceshishi");
            testCityDistrict.setParentDistrict(neimengguDistrict);
            districtRepository.save(testCityDistrict);

            logger.info("添加测试区县");
            District testCountryDistrict = new District();
            testCountryDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            testCountryDistrict.setParentDistrict(testCityDistrict);
            testCountryDistrict.setName("测试区县");
            testCountryDistrict.setPinyin("ceshiquxian");
            districtRepository.save(testCountryDistrict);

            logger.info("增加区县");
            List<District> districts = new ArrayList<>();

            logger.info("红山区");
            District anCiDistrict = new District();
            anCiDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            anCiDistrict.setName("红山区");
            anCiDistrict.setPinyin("hongshanqu");
            anCiDistrict.setParentDistrict(chifengDistrict);
            districts.add(anCiDistrict);

            logger.info("元宝山区");
            District guangYangDistrict = new District();
            guangYangDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            guangYangDistrict.setName("元宝山区");
            guangYangDistrict.setPinyin("yuanbaoshanqu");
            guangYangDistrict.setParentDistrict(chifengDistrict);
            districts.add(guangYangDistrict);

            logger.info("松山区");
            District langFangJingJiJiShuKaiFaDistrict = new District();
            langFangJingJiJiShuKaiFaDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            langFangJingJiJiShuKaiFaDistrict.setName("松山区");
            langFangJingJiJiShuKaiFaDistrict.setPinyin("songshanqu");
            langFangJingJiJiShuKaiFaDistrict.setParentDistrict(chifengDistrict);
            districts.add(langFangJingJiJiShuKaiFaDistrict);

            logger.info("林西县");
            District sanHeDistrict = new District();
            sanHeDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            sanHeDistrict.setName("林西县");
            sanHeDistrict.setPinyin("linxixian");
            sanHeDistrict.setParentDistrict(chifengDistrict);
            districts.add(sanHeDistrict);

            logger.info("宁城县");
            District baZhouDistrict = new District();
            baZhouDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            baZhouDistrict.setName("宁城县");
            baZhouDistrict.setPinyin("ningchengxian");
            baZhouDistrict.setParentDistrict(chifengDistrict);
            districts.add(baZhouDistrict);

            logger.info("阿鲁科尔沁旗");
            District daChangDistrict = new District();
            daChangDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            daChangDistrict.setName("阿鲁科尔沁旗");
            daChangDistrict.setPinyin("alvkeerqinqi");
            daChangDistrict.setParentDistrict(chifengDistrict);
            districts.add(daChangDistrict);

            logger.info("巴林左旗");
            District xiangHeDistrict = new District();
            xiangHeDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            xiangHeDistrict.setName("巴林左旗");
            xiangHeDistrict.setPinyin("balinzuoqi");
            xiangHeDistrict.setParentDistrict(chifengDistrict);
            districts.add(xiangHeDistrict);


            logger.info("巴林右旗");
            District yongQingDistrict = new District();
            yongQingDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            yongQingDistrict.setName("巴林右旗");
            yongQingDistrict.setPinyin("balinyouqi");
            yongQingDistrict.setParentDistrict(chifengDistrict);
            districts.add(yongQingDistrict);

            logger.info("克什克腾旗");
            District guAnDistrict = new District();
            guAnDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            guAnDistrict.setName("克什克腾旗");
            guAnDistrict.setPinyin("keshenketengqi");
            guAnDistrict.setParentDistrict(chifengDistrict);
            districts.add(guAnDistrict);

            logger.info("翁牛特旗");
            District wenAnDistrict = new District();
            wenAnDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            wenAnDistrict.setName("翁牛特旗");
            wenAnDistrict.setPinyin("wengniuteqi");
            wenAnDistrict.setParentDistrict(chifengDistrict);
            districts.add(wenAnDistrict);

            logger.info("喀喇沁旗");
            District daChengDistrict = new District();
            daChengDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            daChengDistrict.setName("喀喇沁旗");
            daChengDistrict.setPinyin("halaqinqi");
            daChengDistrict.setParentDistrict(chifengDistrict);
            districts.add(daChengDistrict);

            logger.info("敖汉旗");
            District aohanqiDistrict = new District();
            aohanqiDistrict.setDistrictType(districtTypeRepository.findOneByName("区\\县"));
            aohanqiDistrict.setName("敖汉旗");
            aohanqiDistrict.setPinyin("aohanqi");
            aohanqiDistrict.setParentDistrict(chifengDistrict);
            districts.add(aohanqiDistrict);

            logger.info("保存区县");
            districtRepository.save(districts);
        }
        return;
    }

    @Override
    public int getOrder() {
        return entityInitDataListener.getOrder() + 10;
    }
}
