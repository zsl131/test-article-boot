package com.zslin.dao;

import com.zslin.model.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by zsl on 2018/8/4.
 */
public interface IMessagesDao extends JpaRepository<Messages, Integer>, JpaSpecificationExecutor<Messages> {
}
