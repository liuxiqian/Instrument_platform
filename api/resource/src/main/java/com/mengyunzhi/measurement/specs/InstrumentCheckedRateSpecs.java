package com.mengyunzhi.measurement.specs;

import com.mengyunzhi.measurement.entity.*;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.domain.Specification;
import org.apache.log4j.Logger;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

/**
 * @author zhangxishuo on 2018/3/22
 */
public class InstrumentCheckedRateSpecs {

    private static final Logger logger = Logger.getLogger(InstrumentCheckedRateSpecs.class);

    /**
     * 根据查询条件返回查询规格
     * @param onlyChecked  是否仅返回已检定的数据
     * @param map          查询条件
     *                     zhangxishuo
     */
    public static Specification<InstrumentCheckedRate> base(final boolean onlyChecked,final Map<String, Object> map) {
        return new Specification<InstrumentCheckedRate>() {

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

            @Override
            public Predicate toPredicate(Root<InstrumentCheckedRate> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                logger.info("设置私有变量");
                this.criteriaBuilder = criteriaBuilder;

                if (null != map.get("year")) {
                    logger.info("传入了年份信息");
                    Predicate yearPredicate = criteriaBuilder.equal(root.get("year").as(Short.class), (short)map.get("year"));
                    this.andPredicate(yearPredicate);
                }

                if (null != map.get("provinceDistrict")) {
                    logger.info("传入了省信息");
                    Predicate provincePredicate = criteriaBuilder.equal(root.join("provinceDistrict").get("id").as(Long.class), ((District)map.get("provinceDistrict")).getId());
                    this.andPredicate(provincePredicate);
                }

                if (null != map.get("cityDistrict")) {
                    logger.info("传入了市信息");
                    Predicate cityPredicate = criteriaBuilder.equal(root.join("cityDistrict").get("id").as(Long.class), ((District)map.get("cityDistrict")).getId());
                    this.andPredicate(cityPredicate);
                }

                if (null != map.get("countyDistrict")) {
                    logger.info("传入了区县信息");
                    Predicate countyPredicate = criteriaBuilder.equal(root.join("countyDistrict").get("id").as(Long.class), ((District)map.get("countyDistrict")).getId());
                    this.andPredicate(countyPredicate);
                }

                if (null != map.get("instrumentUserDepartment")) {
                    logger.info("传入了器具用户信息");
                    Predicate instrumentUserDepartmentPredicate = criteriaBuilder.equal(root.join("instrumentUserDepartment").get("id").as(Long.class), ((Department)map.get("instrumentUserDepartment")).getId());
                    this.andPredicate(instrumentUserDepartmentPredicate);
                }

                if (null != map.get("purpose")) {
                    logger.info("传入了用途信息");
                    Predicate purposePredicate = criteriaBuilder.equal(root.join("purpose").get("id").as(Long.class), ((Purpose)map.get("purpose")).getId());
                    this.andPredicate(purposePredicate);
                }

                if (null != map.get("instrumentType")) {
                    logger.info("传入了器具名称信息");
                    Predicate instrumentTypePredicate = criteriaBuilder.equal(root.join("instrumentType").get("id").as(Long.class), ((InstrumentType)map.get("instrumentType")).getId());
                    this.andPredicate(instrumentTypePredicate);
                }

                if (onlyChecked) {
                    logger.info("传入了只返回受检信息");
                    Predicate isCheckedPredicate = criteriaBuilder.equal(root.get("checked").as(Boolean.class), true);
                    this.andPredicate(isCheckedPredicate);
                }

                if (null != this.predicate) {
                    criteriaQuery.where(criteriaBuilder.and(this.predicate));
                }

                return criteriaQuery.getRestriction();
            }
        };
    }
}
