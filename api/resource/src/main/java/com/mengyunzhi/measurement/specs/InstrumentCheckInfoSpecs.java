package com.mengyunzhi.measurement.specs;

import com.mengyunzhi.measurement.entity.InstrumentCheckInfo;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

/**
 * 强检器具检定多条件查询
 * zhuchenshu
 */
public class InstrumentCheckInfoSpecs {
    final static Logger logger = Logger.getLogger(CheckApplySpecs.class.getName());

    public static Specification<InstrumentCheckInfo> base(final Map<String, Object> map) {

        return new Specification<InstrumentCheckInfo>() {
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

            public Predicate toPredicate(Root<InstrumentCheckInfo> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                // 设置CriteriaBuilder，用于合并谓语
                this.criteriaBuilder = criteriaBuilder;

                if (null != map.get("year")) {
                    logger.debug("年份");
                    Long  year = (Long) map.get("year");
                    Predicate predicate = criteriaBuilder.equal(
                            root.get("checkYear").as(Long.class), year);
                    this.andPredicate(predicate);
                }

                if (null != map.get("district")) {
                    logger.debug("区域");
                    Long  districtId = (Long) map.get("district");
                    Predicate predicate = criteriaBuilder.equal(
                            root.join("mandatoryInstrument")
                                    .join("department")
                                    .join("district").get("id").as(Long.class), districtId);
                    this.andPredicate(predicate);
                }

                if (null != map.get("department")) {
                    logger.debug("器具用户");
                    Long  departmentId = (Long) map.get("department");
                    Predicate predicate = criteriaBuilder.equal(
                            root.join("mandatoryInstrument")
                                    .join("department")
                                    .get("id").as(Long.class), departmentId);
                    this.andPredicate(predicate);
                }

                if (null != map.get("purposeId")) {
                    logger.debug("用途");
                    Long  purposeId = (Long) map.get("purposeId");
                    Predicate predicate = criteriaBuilder.equal(
                            root.join("mandatoryInstrument").join("purpose").get("id").as(Long.class), purposeId);
                    this.andPredicate(predicate);
                }

                if (null != map.get("intrumentTypeId")) {
                    logger.debug("器具名称");
                    Long  intrumentTypeId = (Long) map.get("intrumentTypeId");
                    Predicate predicate = criteriaBuilder.equal(
                            root.join("mandatoryInstrument").get("id").as(Long.class), intrumentTypeId);
                    this.andPredicate(predicate);
                }

                if (null != map.get("generativeDepartmentId")) {
                    logger.debug("生产企业");
                    Long  generativeDepartmentId = (Long) map.get("generativeDepartmentId");
                    Predicate predicate = criteriaBuilder.equal(
                            root.join("mandatoryInstrument").join("generativeDepartment").get("id").as(Long.class), generativeDepartmentId);
                    this.andPredicate(predicate);
                }

                //将上面的谓词添加的查询准则中去
                if (null != this.predicate) {
                    criteriaQuery.where(criteriaBuilder.and(this.predicate));
                }
                // 获取最后的谓语信息
                return criteriaQuery.getRestriction();
            }
        };

    }
}
