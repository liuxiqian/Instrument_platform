package com.mengyunzhi.measurement.specs;

import com.mengyunzhi.measurement.entity.MandatoryInstrumentCheckApply;
import com.mengyunzhi.measurement.entity.Department;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.Map;

/**
 * 检定申请
 *
 * @author panjie on 2018/1/5
 */
public class CheckApplySpecs {
    final static Logger logger = Logger.getLogger(CheckApplySpecs.class.getName());

    public static Specification<MandatoryInstrumentCheckApply> base(final Map<String, Object> map) {
        return new Specification<MandatoryInstrumentCheckApply>() {
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

            public Predicate toPredicate(Root<MandatoryInstrumentCheckApply> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                // 设置CriteriaBuilder，用于合并谓语
                this.criteriaBuilder = criteriaBuilder;

                if (null != map.get("applyInstrumentUserDepartment")) {
                    logger.debug("器具用户");
                    Department applyInstrumentUserDepartment = (Department) map.get("applyInstrumentUserDepartment");
                    this.andPredicate(criteriaBuilder.equal(
                            root.get("applyInstrumentUserDepartment").as(Department.class),
                            applyInstrumentUserDepartment));
                } else if (null != map.get("instrumentUserName")) {
                    logger.debug("器具用户名称");
                    this.andPredicate(criteriaBuilder.like(root.join("applyInstrumentUserDepartment").get("name").as(String.class),
                            "%" + String.valueOf(map.get("instrumentUserName")) + "%"));
                }

                if (null != map.get("acceptTechnicalInstitutionDepartment")) {
                    logger.debug("接收申请的部门");
                    Department acceptTechnicalInstitutionDepartment = (Department) map.get("acceptTechnicalInstitutionDepartment");
                    this.andPredicate(criteriaBuilder.equal(root.get("acceptTechnicalInstitutionDepartment").as(Department.class),
                            acceptTechnicalInstitutionDepartment));
                }

                if (null != map.get("startApplyDate")) {
                    logger.debug("开始日期");
                    Date startApplyDate = (Date) map.get("startApplyDate");
                    Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get("applyDate").as(Date.class), startApplyDate);
                    this.andPredicate(predicate);
                }

                if (null != map.get("endApplyDate")) {
                    logger.debug("结束日期");
                    Date endApplyDate = (Date) map.get("endApplyDate");
                    Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get("applyDate").as(Date.class), endApplyDate);
                    this.andPredicate(predicate);
                }

                //将上面的谓词添加的查询准则中去
                if (null != this.predicate) {
                    criteriaQuery.where(criteriaBuilder.and(this.predicate));
                }

                return criteriaQuery.getRestriction();
            }
        };
    }
}
