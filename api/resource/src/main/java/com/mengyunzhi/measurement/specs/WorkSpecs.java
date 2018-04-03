package com.mengyunzhi.measurement.specs;

import com.mengyunzhi.measurement.entity.MandatoryInstrumentApply;
import com.mengyunzhi.measurement.entity.Work;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by chuhang on 2017/12/27
 * 工作
 */
public class WorkSpecs {
    private final static Logger logger = Logger.getLogger(WorkSpecs.class.getName());
    /**
     * 新建一个方法，这个方法的返回值类型为：org.springframework.data.jpa.domain.Specification <Work>
     * 这个方法接收一个参数，由于只对这个参数进行读取，不进行写入，所以设置为:final，即该变量不可变。
     */
    public static Specification<Work> base(final Map<String, Object> map) {
        /**
         * 直接返回了一个继承了 Specification 接口的对象，由于 Specification 接口中声明了
         * Predicate toPredicate(Root<T> var1, CriteriaQuery<?> var2, CriteriaBuilder var3);
         * 方法。所以在这里，我们需要有下面的这个函数：
         * public Predicate toPredicate(Root<> root, CriteriaQuery<?> query,
         CriteriaBuilder criteriaBuilder)
         * 其实这我们先建立一个类，继承Specification接口，然后实现它的方法。然后我们在这里直接return new XXXX<>()意思是一样的。
         */
        return new Specification<Work>() {
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

            public Predicate toPredicate(Root<Work> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                logger.debug("设置私有变量");
                this.criteriaBuilder = criteriaBuilder;

                if (map.get("auditDepartmentId") != null) {
                    logger.debug("查询工作的检定部门");
                    Predicate predicate = criteriaBuilder.equal(root.join("auditDepartment").get("id").as(Long.class), Long.valueOf(String.valueOf(map.get("auditDepartmentId"))));
                    this.andPredicate(predicate);
                }

                if (map.get("applyTypeFlag") != null) {
                    logger.debug("查询工作的申请类型标识");
                    Predicate predicate = criteriaBuilder.equal(root.join("apply").join("applyType").get("flag").as(String.class), String.valueOf(map.get("applyTypeFlag")));
                    this.andPredicate(predicate);
                }

                if (map.get("instrumentUserName") != null) {
                    logger.debug("查询工作的器具用户名称");
                    Predicate predicate = criteriaBuilder.like(root.join("apply").join("department").get("name").as(String.class), "%" + String.valueOf(map.get("instrumentUserName")) + "%");
                    this.andPredicate(predicate);
                }


                if (map.get("startApplyDate") != null ) {
                    logger.debug("获取申请起始日期");
                    Calendar startApplyDate = Calendar.getInstance();
                    startApplyDate.setTime(new Date(Long.parseLong(map.get("startApplyDate").toString())));

                    Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.join("apply").get("applyTime").as(Calendar.class), startApplyDate);
                    this.andPredicate(predicate);
                }

                if( map.get("endApplyDate") != null) {
                    Calendar endApplyDate = Calendar.getInstance();
                    endApplyDate.setTimeInMillis(Long.parseLong(map.get("endApplyDate").toString()));
                    logger.debug("查询当前工作的申请的申请日期");
                    Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.join("apply").get("applyTime").as(Calendar.class), endApplyDate);
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
