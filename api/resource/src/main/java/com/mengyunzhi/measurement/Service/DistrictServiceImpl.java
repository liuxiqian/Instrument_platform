package com.mengyunzhi.measurement.Service;

import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.repository.DistrictRepository;
import com.mengyunzhi.measurement.entity.DistrictType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liming on 17-6-2.
 */
@Service
public class DistrictServiceImpl implements DistrictService {
    private final static Logger logger = Logger.getLogger(DistrictServiceImpl.class.getName());
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private DistrictService districtService;

    @Override
    public District save(District district) {
        districtRepository.save(district);
        return district;
    }

    @Override
    public List<District> getAll() {
        //取出所有数据
        List<District> districts = (List<District>) districtRepository.findAll();
        return districts;
    }

    @Override
    public District get(Long id) {
        return districtRepository.findOne(id);
    }

    @Override
    public void delete(Long id) {
        districtRepository.delete(id);
        return;
    }

    @Override
    public District getTreeByDistrict(District district) {
        district.getDistrictType();
        List<District> districts = districtRepository.getAllByParentDistrictId(district.getId());
        for (District district1 : districts) {
            this.getTreeByDistrict(district1);
        }
        district.setSonDistricts(districts);
        return district;
    }


    @Override
    public List<District> getSonsListByDistrict(District district) {
        List<District> districts = new ArrayList<>();
        this.getTreeByDistrict(district);
        this.addAllNodes(district, districts);
        return districts;
    }

    private static void addAllNodes(District district, List<District> districts) {
        if (district != null) {
            districts.add(district);
            List<District> children = district.getSonDistricts();
            if (children != null) {
                for (District child : children) {
                    addAllNodes(child, districts);
                }
            }
        }
    }

    @Override
    public List<District> getSonsListByDistrictId(Long districtId) {
        List<District> districts = new ArrayList<>();
        District district = this.getTreeByDistrictId(districtId);
        this.addAllNodes(district, districts);
        return districts;
    }

    /**
     * 根据区域获取旗下的一级子区域
     * @param district  当前区域
     * @return          所有一级子区域
     * zhangxishuo
     */
    @Override
    public List<District> getClassOneSonListByDistrict(District district) {
        return this.getClassOneSonListByDistrictId(district.getId());
    }

    /**
     * 获取当前区域的一级子区域
     * @param districtId
     * @return
     */
    @Override
    public List<District> getClassOneSonListByDistrictId(Long districtId) {
        List<District> districts = districtRepository.getAllByParentDistrictId(districtId);
        return districts;
    }

    /**
     * 获取子区域（包含自己）的区域列表
     * 未找到则返回整个根区域下的所有区域
     *
     * @param rootDistrict 根区域
     * @param districtId   子区域ID
     * @return
     * @Author panjie
     */
    @Override
    public List<District> getSonsListByRootDistrictAndSonDistrictId(District rootDistrict, Long districtId) {
        District sonDistrictTree = this.getSonTreeByRootDistrictAndSonDistrictId(rootDistrict, districtId);
        List<District> districts = new ArrayList<>();
        this.treeToList(sonDistrictTree, districts);
        return districts;
    }

    /**
     * 将树型转换为LIST
     *
     * @param districtTree 区域（树）
     * @param districts    区域iD
     * @Author panjie
     */
    private void treeToList(District districtTree, List<District> districts) {
        districts.add(districtTree);
        for (District district : districtTree.getSonDistricts()) {
            this.treeToList(district, districts);
        }
    }

    /**
     * 获取树状的区域
     *
     * @param rootDistrict 根区域
     * @param districtId   子区域ID
     * @return
     * @Author panjie
     */
    @Override
    public District getSonTreeByRootDistrictAndSonDistrictId(District rootDistrict, Long districtId) {
        logger.debug("获取根区域的树状结构, 调用获取子区域树的函数，返回区域树");
        District rootDistrictTree = this.getTreeByDistrict(rootDistrict);
        return this.getSonTreeByDistrictTreeAndSonDistrictId(rootDistrictTree, districtId);
    }

    /**
     * 获取子树
     *
     * @param rootDistrictTree 根区域（包含子区域的树型）
     * @param districtId       子区域结点
     * @return 未找到(子区域结并不属于传于的根区域)，则返回null
     * @author panjie
     */
    @Override
    public District getSonTreeByDistrictTreeAndSonDistrictId(District rootDistrictTree, Long districtId) {
        if (rootDistrictTree.getId().equals(districtId)) {
            return rootDistrictTree;
        } else {
            for (District district : rootDistrictTree.getSonDistricts()) {
                District district1 = this.getSonTreeByDistrictTreeAndSonDistrictId(district, districtId);
                if (null != district1) {
                    return district1;
                }
            }
        }

        return null;
    }

    @Override
    public List<District> getParentListByDistrict(District district) {
        List<District> districts = new ArrayList<>();
        districts.add(district);
        while (district.getParentDistrict() != null) {
            districts.add(district.getParentDistrict());
            district = district.getParentDistrict();
        }
        return districts;
    }

    @Override
    public List<District> getParentListByDistrictId(Long id) {
        District district = districtRepository.findOne(id);
        return this.getParentListByDistrict(district);
    }

    @Autowired
    DistrictTypeService districtTypeService;

    @Override
    public District getOneSavedDistrict() {
        District district = new District();
        DistrictType districtType = districtTypeService.getOneSavedDistrictType();
        district.setDistrictType(districtType);
        district.setName(CommonService.getRandomStringByLength(10));
        districtRepository.save(district);
        return district;
    }

    @Override
    public District getRootDistrictTree() {
        District district = districtRepository.findTopOneByParentDistrictIsNullOrderByIdAsc();
        this.addSonDistrictsByDistrict(district);
        return district;
    }

    @Override
    public boolean preOneIsParentNextOne(District district, District district1) {
        //取出一个区域的所有子区域
        List<District> districts = districtService.getSonsListByDistrict(district);

        //看看是否包district1
        for (District district2 : districts) {
            if (district2.getId() == district1.getId()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public District getOneSavedCountryDistrict() {
        District district = new District();
        DistrictType districtType = districtTypeService.findOneByName("区\\县");
        district.setDistrictType(districtType);
        districtRepository.save(district);
        return district;
    }

    @Override
    public District getTreeByDistrictId(Long districtId) {
        District district = districtRepository.findOne(districtId);
        this.addSonDistrictsByDistrict(district);
        return district;
    }

    /**
     * 添加子区域
     *
     * @param district 区域
     */
    protected void addSonDistrictsByDistrict(District district) {
        List<District> districts = districtRepository.getAllByParentDistrictId(district.getId());
        for (District district1 : districts) {
            this.getTreeByDistrict(district1);
        }
        district.setSonDistricts(districts);
    }
}
