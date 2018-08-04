package com.zslin.service;

import com.alibaba.fastjson.JSON;
import com.zslin.dao.IReplyDao;
import com.zslin.dto.JsonResult;
import com.zslin.model.Reply;
import com.zslin.tools.DateTools;
import com.zslin.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zsl on 2018/8/4.
 */
@Service
public class ReplyService {

    @Autowired
    private IReplyDao replyDao;

    public JsonResult list(String params) {
        try {
            Integer mid = Integer.parseInt(JsonTools.getField(params, "mid"));
            List<Reply> list = replyDao.findByMessageId(mid);
            return JsonResult.getInstance().set("size", list.size()).set("replyList", list);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(e.getMessage());
        }
    }

    public JsonResult addOrUpdate(String params) {
        System.out.println("==========params:::"+params);
        Reply r = JSON.toJavaObject(JSON.parseObject(params), Reply.class);
        if(r.getId()!=null && r.getId()>0) {
            Reply reply = replyDao.findOne(r.getId());
            reply.setAuthor(r.getAuthor());
            reply.setContent(r.getContent());

            return JsonResult.success("修改成功");
        } else {
            r.setCreateTime(DateTools.curTime());
            replyDao.save(r);
            return JsonResult.success("保存成功");
        }
    }

    public JsonResult delete(String params) {
        try {
            Integer id = Integer.parseInt(JsonTools.getField(params, "id"));
            replyDao.delete(id);
            return JsonResult.success("删除成功");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return JsonResult.error(e.getMessage());
        }
    }
}
