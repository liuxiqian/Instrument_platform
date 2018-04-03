package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.repository.DistrictRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.logging.Logger;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by liming on 17-6-2.
 */
public class DistrictServiceImplTest extends ServiceTest{
    static private Logger logger = Logger.getLogger(DistrictServiceImplTest.class.getName());
    @Autowired
    private DistrictService districtService;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private DistrictServiceImplTestData districtServiceImplTestData;

    private District district;
    @Before
    public void before() {
        logger.info("创建一个区域实体");
        district = new District();
        logger.info("保存实体");
        districtService.save(district);
    }

    @Test
    public void save() throws Exception {
        logger.info("断言保存成功");
        assertThat(district.getId()).isNotNull();
    }

    @Test
    public void getAll() throws Exception {
        logger.info("取出所有数据");
        List<District> districts = districtService.getAll();
        logger.info("断言");
        assertThat(districts.size()).isNotEqualTo(0);
    }

    @Test
    public void get() throws Exception {
        logger.info("取出一条数据");
        District district1 = districtService.get(district.getId());
        logger.info("断言取出来的区域与保存的区域一是一个区域");
        assertThat(district1.getId()).isEqualTo(district.getId());

        District district = districtService.get(1L);
    }

    @Test
    public void delete() throws Exception {
        logger.info("删除实体");
        districtService.delete(district.getId());
        logger.info("断言实体不存在");
        assertThat(districtService.get(district.getId())).isNull();
    }

    @Test
    public void getTreeByDistrict() {
        District district = districtRepository.findOne(1L);
        districtService.getTreeByDistrict(district);
        return;
    }

    @Test
    public void getSonTreeByDistrict() {
        logger.info("增加一个根区域");
        District district = districtService.getOneSavedDistrict();
        districtRepository.save(district);

        logger.info("增加两个子区域");
        for(int i = 0; i < 2; i++) {
            District district1 = districtService.getOneSavedDistrict();
            district1.setParentDistrict(district);
            districtRepository.save(district1);

            logger.info("增加两个孙子区域");
            for(int j = 0; j < 3; j++) {
                District district2 = districtService.getOneSavedDistrict();
                district2.setParentDistrict(district1);
                districtRepository.save(district2);
            }
        }

        logger.info("取出树状结构, 并断言树的大小");
        districtService.getTreeByDistrict(district);
        assertThat(district.getSonDistricts().size()).isEqualTo(2);
        for(District district1 : district.getSonDistricts()) {
            assertThat(district1.getSonDistricts().size()).isEqualTo(3);
        }

        District district2 = districtService.getTreeByDistrictId(district.getId());
        assertThat(district2.getSonDistricts().size()).isEqualTo(2);
        for(District district1 : district2.getSonDistricts()) {
            assertThat(district1.getSonDistricts().size()).isEqualTo(3);
        }

        logger.info("取出数组，并断言数组的大小");
        District district3 = districtRepository.findOne(district.getId());
        List<District> districts;
        districts = districtService.getSonsListByDistrict(district3);
        assertThat(districts.size()).isEqualTo(9);

        districts = districtService.getSonsListByDistrictId(district3.getId());
        assertThat(districts.size()).isEqualTo(9);

    }

    @Test
    public void getRootDistrictTree() {
        District district = districtService.getRootDistrictTree();
        assertThat(district.getId()).isNotNull();
        assertThat(district.getSonDistricts().size()).isGreaterThan(0);
    }

    @Test
    public void getParentListByDistrictTest() {
        District district = this.getParentListByDistrictData();
        List<District> districts = districtService.getParentListByDistrict(district);
        assertThat(districts.size()).isEqualTo(3);
    }

    @Test
    public void getParentListByDistrictIdTest() {
        District district = this.getParentListByDistrictData();
        List<District> districts = districtService.getParentListByDistrictId(district.getId());
        assertThat(districts.size()).isEqualTo(3);
    }

    private District getParentListByDistrictData() {
        District district = new District();
        districtRepository.save(district);

        District district1 = new District();
        district1.setParentDistrict(district);
        districtRepository.save(district1);

        District district2 = new District();
        district2.setParentDistrict(district1);
        districtRepository.save(district2);
        return district2;
    }

    @Test
    public void getSonsListByDistrict() {
    }

    @Test
    public void getSonsListByRootDistrictAndSonDistrictId() {
        District district = districtService.getOneSavedDistrict();
        District district1 = districtService.getOneSavedDistrict();
        District district2 = districtService.getOneSavedDistrict();
        districtServiceImplTestData.getOneLevel3BinaryTree(district, district1, district2);

        List<District> districts = districtService.getSonsListByRootDistrictAndSonDistrictId(district, district1.getId());
        assertThat(districts.size()).isEqualTo(3);

        List<District> districts1 = districtService.getSonsListByRootDistrictAndSonDistrictId(district, district.getId());
        assertThat(districts1.size()).isEqualTo(7);

    }

    @Test
    public void getSonTreeByRootDistrictAndSonDistrictId() {
        District district = districtService.getOneSavedDistrict();
        District district1 = districtService.getOneSavedDistrict();
        District district2 = districtService.getOneSavedDistrict();
        districtServiceImplTestData.getOneLevel3BinaryTree(district, district1, district2);

        District districtTree = districtService.getSonTreeByRootDistrictAndSonDistrictId(district, district.getId());
        assertThat(districtTree.getId()).isEqualTo(district.getId());

        District district1Tree = districtService.getSonTreeByRootDistrictAndSonDistrictId(district, district1.getId());
        assertThat(district1Tree.getId()).isEqualTo(district1.getId());

        District district2Tree = districtService.getSonTreeByRootDistrictAndSonDistrictId(district, district2.getId());
        assertThat(district2Tree.getId()).isEqualTo(district2.getId());
    }

    @Test
    public void getSonTreeByDistrictTreeAndDistrictId() {

    }

    @Test
    public void getParentListByDistrict() {
    }

    @Test
    public void getParentListByDistrictId() {
    }

    @Test
    public void getOneSavedDistrict() {
    }

    @Test
    public void preOneIsParentNextOne() {
    }

    @Test
    public void getTreeByDistrictId() {
    }

    @Test
    public void addSonDistrictsByDistrict() {
    }
}