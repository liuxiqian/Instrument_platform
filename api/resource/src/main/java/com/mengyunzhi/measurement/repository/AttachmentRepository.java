package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.Attachment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by administrator on 2017/5/20.
 */
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {
    Attachment findBySaveName(String saveName);
}
