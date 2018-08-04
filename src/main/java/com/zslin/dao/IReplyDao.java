package com.zslin.dao;

import com.zslin.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by zsl on 2018/8/4.
 */
public interface IReplyDao extends JpaRepository<Reply, Integer>, JpaSpecificationExecutor<Reply> {

    List<Reply> findByMessageId(Integer messageId);
}
