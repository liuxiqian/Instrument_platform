package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.DistrictType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by liming on 17-5-16.
 * 区域类型
 */
public interface DistrictTypeRepository extends CrudRepository<DistrictType, Long> {
    DistrictType findOneByName(String name);
    @Query("select d from #{#entityName} d where pinyin = 'quxian'")
    DistrictType findOneQuXianDistrictType();
    @Query("select d from #{#entityName} d where name = '市'")
    DistrictType findOneShiDistrictType();
    @Query("select d from #{#entityName} d where name = '省'")
    DistrictType findOneShengDistrictType();
}
