package com.mengyunzhi.measurement.specs;


import com.mengyunzhi.measurement.entity.DeviceSet;
import com.mengyunzhi.measurement.entity.District;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by liming on 17-8-13.
 * 标准装置多条件查询
 */
public class DeviceSetSpecs {
    private final static Logger logger = Logger.getLogger(DeviceSetSpecs.class.getName());

    /**
     * 新建一个方法，这个方法的返回值类型为：org.springframework.data.jpa.domain.Specification <DeviceSet>
     * 这个方法接收一个参数，由于只对这个参数进行读取，不进行写入，所以设置为:final，即该变量不可变。
     */
    public static Specification<DeviceSet> base(final Map<String, Object> map) {
        return new Specification<DeviceSet>() {
            @Override
            public Predicate toPredicate(Root<DeviceSet> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //谓词
                Predicate predicate = criteriaBuilder.like(root.get("code").as(String.class), String.valueOf(map.get("code")) + "%");
                Predicate predicate2 = criteriaBuilder.like(root.get("name").as(String.class), String.valueOf(map.get("name")) + "%");
                //两个谓词合成一个大谓词
                predicate = criteriaBuilder.and(predicate, predicate2);

                if (null != map.get("districts")) {
                    logger.info("传入区域信息");
                    List<District> districts = (List<District>) map.get("districts");

                    if (districts.size() > 0) {
                        logger.info("设置查询条件IN");
                        CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.join("department").join("district").get("id").as(Long.class));
                        logger.info("为IN添加数组");
                        for (District district: districts) {
                            in.value(district.getId());
                        }
                        predicate = criteriaBuilder.and(predicate, in);
                    }
                }

                //声明第三个谓词
                Predicate predicate1 = null;
                if (null != map.get("departmentId")) {
                    logger.info("授权检定项目所属部门");
                    Long departmentId = Long.valueOf((String.valueOf(map.get("departmentId"))));
                    predicate1 = criteriaBuilder.equal(root.join("department").get("id"), departmentId);
                }

                //如果第三个谓语成立，则建立合成一个大谓语
                if (predicate1 != null) {
                    predicate = criteriaBuilder.and(predicate, predicate1);
                }

                //查询
                criteriaQuery.where(predicate);

                //返回
                return criteriaQuery.getRestriction();
            }
        };
    }
}
