package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.OauthClientDetails;
import org.springframework.data.repository.CrudRepository;

/**
 * @author panjie on 2017/12/27
 * oauth认证
 */
public interface OauthClientDetailsRepository extends CrudRepository<OauthClientDetails, String>{
}
