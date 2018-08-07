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
public class VerifyService {
    @Autowired
    private IVerifyDao verifyDao;

    @Autowired
    private ILeaveDao leaveDao;

    public JsonResult list(String params) {
        try {
            Integer lid = Integer.parseInt(JsonTools.getField(params,"lid"));
            List<Verify> list = verifyDao.findByLeaveId(lid);
            return JsonResult.getInstance().set("size",list.size()).set("verifyList",list);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(e.getMessage());
        }
    }
    public JsonResult addOrUpdate(String params) {
        System.out.println(params);
        Verify r = JSON.toJavaObject(JSON.parseObject(params),Verify.class);
        if(r.getId()!=null && r.getId()>0) {
            Verify ver = verifyDao.findOne(r.getId());
            ver.setApproval(r.getApproval());
            ver.setResult(r.getResult());
            verifyDao.save(ver);
            return JsonResult.success("修改成功");
        } else {
            r.setTime(DateTools.curTime());
//            Integer leaveId = Integer.parseInt(JsonTools.getField(params, "leaveId"));
//            Leave leave = leaveDao.findOne(leaveId);
            verifyDao.save(r);
            return JsonResult.success("保存成功");
        }
    }
    public JsonResult delete(String params) {
        try {
            Integer id = Integer.parseInt(JsonTools.getField(params,"id"));
            verifyDao.delete(id);
            return JsonResult.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(e.getMessage());
        }
    }
}
