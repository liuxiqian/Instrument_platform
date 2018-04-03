package com.mengyunzhi.measurement.specs;

import com.mengyunzhi.measurement.entity.District;
import com.mengyunzhi.measurement.entity.Qualifier;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;

/**
 * Created by chuhang on 17-8-13.
 * 人员资质多条件查询
 */
public class QualifierSpecs {
    private final static Logger logger = Logger.getLogger(QualifierSpecs.class.getName());

    /**
     * 新建一个方法，这个方法的返回值类型为：org.springframework.data.jpa.domain.Specification <Qualifier>
     * 这个方法接收一个参数，由于只对这个参数进行读取，不进行写入，所以设置为:final，即该变量不可变。
     */
    public static Specification<Qualifier> base(final Map<String, Object> map) {

        /**
         * 直接返回了一个继承了 Specification 接口的对象，由于 Specification 接口中声明了
         * Predicate toPredicate(Root<T> var1, CriteriaQuery<?> var2, CriteriaBuilder var3);
         * 方法。所以在这里，我们需要有下面的这个函数：
         * public Predicate toPredicate(Root<MandatoryInstrument> root, CriteriaQuery<?> query,
         CriteriaBuilder criteriaBuilder)
         * 其实这我们先建立一个类，继承Specification接口，然后实现它的方法。然后我们在这里直接return new XXXX<>()意思是一样的。
         */
        return new Specification<Qualifier>() {
            private Predicate predicate = null;
            private CriteriaBuilder criteriaBuilder;

            // 设置and谓语.注意，这里只能设置and关系的谓语，如果谓语为OR，则需要手动设置
            private void andPredicate(Predicate predicate) {
                if (null != predicate) {
                    if (null == this.predicate) {
                        this.predicate = predicate;
                    } else {
                        this.predicate = this.criteriaBuilder.and(this.predicate, predicate);
                    }
                }
            }

            public Predicate toPredicate(Root<Qualifier> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                this.criteriaBuilder = criteriaBuilder;
                if (null != map.get("name")) {
                    String name = String.valueOf(map.get("name"));
                    if (!name.isEmpty()) {
                        // 人员资质名称模糊查询
                        Predicate predicate = criteriaBuilder.like(root.get("name").as(String.class), name + "%");
                        this.andPredicate(predicate);
                    }

                }

                if (null != map.get("departmentName")) {
                    String departmentName = String.valueOf(map.get("departmentName"));
                    if (!departmentName.isEmpty()) {
                        // 技术机构名称模糊查询
                        Predicate predicate = criteriaBuilder.like(root.join("department").get("name").as(String.class), departmentName + "%");
                        this.andPredicate(predicate);
                    }
                }

                logger.debug("设置区域查询条件");
                if (null != map.get("districts")) {
                    logger.debug("传入区域信息");
                    List<District> districts = (List<District>) map.get("districts");
                    if (districts.size() > 0) {
                        logger.debug("传入的区域大小不为0");
                        logger.debug("设置查询条件IN");
                        CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.join("department").join("district").get("id").as(Long.class));
                        logger.debug("为IN添加数组中");
                        for (District district : districts) {
                            logger.debug("区域id" + district.getId());
                            in.value(district.getId());
                        }
                        this.andPredicate(in);
                    }
                }

                /**
                 * 将前面得到的谓语，添加到 查询准则 中。其实除了能添加where外，还可以添加orderBy, groupBy等信息。
                 * 官方文档：http://docs.oracle.com/javaee/6/api/javax/persistence/criteria/CriteriaQuery.html
                 */
                if (null != this.predicate) {
                    query.where(criteriaBuilder.and(this.predicate));
                }

                /**
                 * 获取最后的谓语信息。由于，我们在query中，仅仅添加了.where(谓语信息),所以以下代码相当于return predicate;
                 *  但当我们并不仅仅设置where信息时，就不一样了
                 */
                return query.getRestriction();
            }
        };
    }

}
