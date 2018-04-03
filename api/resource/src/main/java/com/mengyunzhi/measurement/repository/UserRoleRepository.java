package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.UserRole;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by panjie
 * 用户-角色
 */
public interface UserRoleRepository extends CrudRepository<UserRole, UserRole.Id>{
}
