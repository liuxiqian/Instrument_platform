package com.mengyunzhi.measurement.specs;

import com.mengyunzhi.measurement.entity.CheckAbilityStatistics;
import com.mengyunzhi.measurement.entity.MandatoryInstrument;
import org.apache.log4j.Logger;
import org.apache.poi.util.Internal;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Map;

/**
 * 器具检定能力统计多条件查询
 * 该类的创建参考MandatoryInstrumentSpecs
 * poshichao
 */
public class CheckAbilityStatisticsSpecs {
    private static final Logger logger = Logger.getLogger(CheckAbilityStatisticsSpecs.class.getName());

    public static Specification<CheckAbilityStatistics> base(final Map<String, Object> map) {
        return new Specification<CheckAbilityStatistics>() {
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

            public Predicate toPredicate(Root<CheckAbilityStatistics> root, CriteriaQuery<?> query,
                                         CriteriaBuilder criteriaBuilder) {
                logger.info("设置私有变量");
                this.criteriaBuilder = criteriaBuilder;

                logger.info("设置第一个谓语");
                Predicate predicate1 = null;
                if(map.get("countyDistrictId") != null) {
                    logger.info("区县");
                    Long countyDistrictId = Long.valueOf(String.valueOf(map.get("countyDistrictId")));
                    predicate1 = criteriaBuilder.equal(root.join("countyDistrict").get("id").as(Long.class), countyDistrictId);
                } else if(map.get("cityDistrictId") != null) {
                    logger.info("市");
                    Long cityDistrictId = Long.valueOf(String.valueOf(map.get("cityDistrictId")));
                    predicate1 = criteriaBuilder.equal(root.join("cityDistrict").get("id").as(Long.class), cityDistrictId);
                } else if(map.get("provinceDistrictId") != null) {
                    logger.info("省");
                    Long provinceDistrictId = Long.valueOf(String.valueOf(map.get("provinceDistrictId")));
                    predicate1 = criteriaBuilder.equal(root.join("provinceDistrict").get("id").as(Long.class), provinceDistrictId);
                }

                this.andPredicate(predicate1);

                logger.info("设置第二个谓语");
                Predicate predicate2 = null;
                if (map.get("instrumentTypeId") != null) {
                    logger.info("传入器具类别ID不为null");
                    Long instrumentTypeId = Long.valueOf(String.valueOf(map.get("instrumentTypeId")));
                    predicate2 = criteriaBuilder.equal(root.join("instrumentType").get("id").as(Long.class), instrumentTypeId);
                } else if (map.get("disciplineId") != null) {
                    logger.info("传入器具类别ID为null, 但学科类别ID不为null");
                    Long disciplineId = Long.valueOf(String.valueOf(map.get("disciplineId")));
                    predicate2 = criteriaBuilder.equal(
                            root.join("discipline").get("id").as(Long.class),
                            disciplineId);
                }

                this.andPredicate(predicate2);

                if (map.get("minMeasureScaleWeight") != null) {
                    logger.info("传入最小测量范围权重不为null");
                    Predicate minMeasureScaleWeightPredicate = criteriaBuilder.equal(root.get("minMeasureScaleWeight"), Integer.valueOf(String.valueOf(map.get("minMeasureScaleWeight"))));
                    this.andPredicate(minMeasureScaleWeightPredicate);
                }

                if (map.get("maxMeasureScaleWeight") != null) {
                    logger.info("传入最大测量范围权重不为null");
                    Predicate maxMeasureScaleWeightPredicate = criteriaBuilder.equal(root.get("maxMeasureScaleWeight"), Integer.valueOf(String.valueOf(map.get("maxMeasureScaleWeight"))));
                    this.andPredicate(maxMeasureScaleWeightPredicate);
                }

                if (null != this.predicate) {
                    query.where(criteriaBuilder.and(this.predicate));
                }

                return query.getRestriction();
            }
        };
    }
}
