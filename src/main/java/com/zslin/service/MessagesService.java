package com.zslin.service;

import com.alibaba.fastjson.JSON;
import com.zslin.dao.IMessagesDao;
import com.zslin.dao.IReplyDao;
import com.zslin.dto.JsonObj;
import com.zslin.dto.JsonResult;
import com.zslin.model.Messages;
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
public class MessagesService {

    @Autowired
    private IMessagesDao messagesDao;

    @Autowired
    private IReplyDao replyDao;

    public JsonResult list(String params) {
        List<Messages> list = messagesDao.findAll();
//        return new JsonObj(list.size(), list); //object :: time, list
        return JsonResult.getInstance().set("size", list.size()).set("datas", list);
    }

    public JsonResult addOrUpdate(String params) {
        Messages m = JSON.toJavaObject(JSON.parseObject(params), Messages.class);
        if(m.getId()!=null && m.getId()>0) { //修改
            Messages old = messagesDao.findOne(m.getId());
            old.setAuthor(m.getAuthor());
            old.setContent(m.getContent());
            return JsonResult.success("修改成功");
        } else {
            m.setCreateTime(DateTools.curTime());
            messagesDao.save(m);
            return JsonResult.succ(m);
        }
    }

    public JsonResult delete(String params) {
        try {
            Integer id = Integer.parseInt(JsonTools.getField(params, "id"));
            messagesDao.delete(id);

            List<Reply> replyList = replyDao.findByMessageId(id);
            if(replyList!=null && replyList.size()>0) {
                return JsonResult.success("存在回复，不能删除");
            } else {
                return JsonResult.success("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.error(e.getMessage());
        }
    }
}
