package com.zslin.dao;

import com.zslin.model.Verify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IVerifyDao extends JpaRepository<Verify,Integer>,JpaSpecificationExecutor<Verify> {
    List<Verify> findByLeaveId(Integer leaveId);
}
