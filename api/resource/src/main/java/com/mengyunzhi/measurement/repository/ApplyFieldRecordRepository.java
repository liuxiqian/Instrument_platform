package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Apply;
import com.mengyunzhi.measurement.entity.ApplyFieldRecord;
import com.mengyunzhi.measurement.entity.ApplyType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by panjie on 17/10/9.
 * 申请字段记录
 */
public interface ApplyFieldRecordRepository extends CrudRepository<ApplyFieldRecord, Long> {
    /**
     * 获取某条记录
     * @param apply 申请
     * @param applyType 申请类型
     * @param applyFieldName 申请字段名
     * @return
     */
    @Query("SELECT applyFieldRecord FROM #{#entityName} applyFieldRecord WHERE applyFieldRecord.apply = :apply AND applyFieldRecord.applyField.applyType =:applyType AND applyFieldRecord.applyField.name =:applyFieldName")
    ApplyFieldRecord findByApplyAndApplyTypeAndApplyFieldName(@Param("apply") Apply apply, @Param("applyType") ApplyType applyType, @Param("applyFieldName") String applyFieldName);

}
