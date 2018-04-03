package com.mengyunzhi.measurement.specs;

import com.mengyunzhi.measurement.entity.*;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Calendar;
import java.util.Map;

/**
 * @author panjie on 2018/1/18
 * 当前工作
 */
public class CurrentWorkSpecs {
    final static Logger logger = Logger.getLogger(CurrentWorkSpecs.class.getName());
    public static Specification<CurrentWork> base(final Map<String, Object> map) {
        /**
         * 直接返回了一个继承了 Specification 接口的对象，由于 Specification 接口中声明了
         * Predicate toPredicate(Root<T> var1, CriteriaQuery<?> var2, CriteriaBuilder var3);
         * 方法。所以在这里，我们需要有下面的这个函数：
         * public Predicate toPredicate(Root<> root, CriteriaQuery<?> query,
         CriteriaBuilder criteriaBuilder)
         * 其实这我们先建立一个类，继承Specification接口，然后实现它的方法。然后我们在这里直接return new XXXX<>()意思是一样的。
         */
        return new Specification<CurrentWork>() {
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

            public Predicate toPredicate(Root<CurrentWork> currentWorkRoot, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                logger.debug("设置私有变量");
                this.criteriaBuilder = criteriaBuilder;
                Join<CurrentWork, Work> workJoin  = currentWorkRoot.join("work");
                Join<CurrentWork, Department> auditDepartmentJoin = workJoin.join("auditDepartment");
                Join<CurrentWork, Apply> applyJoin = workJoin.join("apply");

                if (map.get("auditDepartmentId") != null) {
                    logger.debug("查询工作的检定部门");
                    Predicate predicate = criteriaBuilder.equal(auditDepartmentJoin.get("id").as(Long.class), Long.valueOf(String.valueOf(map.get("auditDepartmentId"))));
                    this.andPredicate(predicate);
                }

                if (map.get("applyClass") != null) {
                    logger.debug("查询工作的申请类型标识");
                    Predicate predicate = criteriaBuilder.equal(applyJoin.type(), criteriaBuilder.literal(map.get("applyClass")));
                    this.andPredicate(predicate);
                }

                if (map.get("instrumentUserName") != null) {
                    logger.debug("申请用户名称");
                    Predicate predicate = criteriaBuilder.like(applyJoin.join("department").get("name").as(String.class), "%" + String.valueOf(map.get("instrumentUserName")) + "%");
                    this.andPredicate(predicate);
                }

                if (map.get("applyDepartment") != null) {
                    logger.debug("申请用户");
                    Department applyDepartment = (Department) map.get("applyDepartment");
                    if (null != applyDepartment.getId()) {
                        Predicate predicate = criteriaBuilder.equal(applyJoin.get("department").as(Department.class), map.get("applyDepartment"));
                        this.andPredicate(predicate);
                    }
                }

                if (map.get("applyStartCalendar") != null ) {
                    logger.debug("获取申请起始日期");
                    Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(applyJoin.get("applyTime").as(Calendar.class), (Calendar) map.get("applyStartCalendar"));
                    this.andPredicate(predicate);
                }

                if( map.get("applyEndCalendar") != null) {
                    logger.debug("查询当前工作的申请的申请日期");
                    Calendar applyEndCalendar = (Calendar) map.get("applyEndCalendar");
                    applyEndCalendar.add(Calendar.DAY_OF_YEAR, 1);
                    Predicate predicate = criteriaBuilder.lessThanOrEqualTo(applyJoin.get("applyTime").as(Calendar.class), applyEndCalendar);
                    this.andPredicate(predicate);
                }

                // 将前面得到的谓语，添加到 查询准则 中
                query.where(criteriaBuilder.and(this.predicate));

                // 获取最后的谓语信息
                return query.getRestriction();
            }
        };
    }

}
