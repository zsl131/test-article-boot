package com.zslin.dao;


import com.zslin.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ILeaveDao extends JpaRepository<Leave,Integer>,JpaSpecificationExecutor<Leave> {

}
