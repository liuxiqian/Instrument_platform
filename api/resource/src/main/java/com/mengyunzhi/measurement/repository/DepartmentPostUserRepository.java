package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.DepartmentPostUser;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by 安强 on 2017/5/31.
 * 部门岗位用户 实体仓库
 */
public interface DepartmentPostUserRepository extends CrudRepository<DepartmentPostUser, Long> {
}
