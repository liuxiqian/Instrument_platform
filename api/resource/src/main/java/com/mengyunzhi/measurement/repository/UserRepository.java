package com.mengyunzhi.measurement.repository;

import com.mengyunzhi.measurement.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by liming on 17-5-17.
 * 用户
 */
public interface UserRepository extends CrudRepository<User, Long> , JpaSpecificationExecutor {
    @Query("SELECT user FROM #{#entityName} user WHERE user.username =:username")
    User findOneByUsername(@Param("username") String username);
    Page<User> findAll(Pageable pageable);

    @Query("SELECT user FROM #{#entityName} user WHERE user.department.id =:departmentId")
    List<User> getByDepartmentId(@Param("departmentId") Long departmentId);

    // 获取某个用户
    User findByUsernameAndMobile(String username, String mobile);
}
