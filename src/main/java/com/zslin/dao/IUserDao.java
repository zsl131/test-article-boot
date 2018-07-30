package com.zslin.dao;

import com.zslin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zsl on 2018/7/30.
 */
public interface IUserDao extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);
}
