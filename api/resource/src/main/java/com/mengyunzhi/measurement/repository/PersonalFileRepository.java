package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.PersonalFile;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by liming on 17-4-28.
 */
public interface PersonalFileRepository extends PagingAndSortingRepository<PersonalFile, Long> {
}
