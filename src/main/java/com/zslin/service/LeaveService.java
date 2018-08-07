package com.zslin.service;


import com.alibaba.fastjson.JSON;
import com.zslin.dao.ILeaveDao;
import com.zslin.dao.IVerifyDao;
import com.zslin.dto.JsonResult;
import com.zslin.model.Leave;
import com.zslin.model.Verify;
import com.zslin.tools.DateTools;
import com.zslin.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {
    @Autowired
    private ILeaveDao leaveDao;
    @Autowired
    private IVerifyDao verifyDao;
    public JsonResult list(String params) {
        List<Leave> list = leaveDao.findAll();
        List<Verify> verifyList = verifyDao.findAll();
        return JsonResult.getInstance().set("size",list.size()).set("datas",list).set("verifySize", verifyList.size()).set("verifyList", verifyList);
    }
    public JsonResult addOrUpdate(String params) {
        Leave m = JSON.toJavaObject(JSON.parseObject(params),Leave.class);
        if(m.getId()!=null && m.getId()>0) {
            Leave old = leaveDao.findOne(m.getId());
            old.setApplicant(m.getApplicant());
            old.setFate(m.getFate());
            old.setReason(m.getReason());
            old.setState(m.getState());
            leaveDao.save(old);
            return JsonResult.success("修改成功");
        } else {
            m.setTime(DateTools.curTime());
            leaveDao.save(m);
            return JsonResult.succ(m);
        }
    }
    public JsonResult delete(String params) {
        try {
            Integer id = Integer.parseInt(JsonTools.getField(params,"id"));
            leaveDao.delete(id);
            List<Verify> verifyList = verifyDao.findByLeaveId(id);
            if(verifyList!=null && verifyList.size()>0) {
                return JsonResult.success("存在审批，不能删除");
            } else {
                return JsonResult.success("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(e.getMessage());
        }
    }
}
