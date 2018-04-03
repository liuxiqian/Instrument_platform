package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.WebAppMenuRole;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by panjie on 2017/6/1.
 * 前台菜单-角色  实体仓库
 */
public interface WebAppMenuRoleRepository extends CrudRepository<WebAppMenuRole, WebAppMenuRole.Id>{
}
