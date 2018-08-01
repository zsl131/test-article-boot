package com.zslin.service;

import com.alibaba.fastjson.JSON;
import com.zslin.dao.IUserDao;
import com.zslin.dto.JsonObj;
import com.zslin.model.User;
import com.zslin.tools.JsonTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zsl on 2018/7/30.
 */
@Service
public class UserService {

    @Autowired
    private IUserDao userDao;

    public JsonObj loadOne(String params) {
        Integer id = Integer.parseInt(JsonTools.getField(params, "id"));
        User u = userDao.findOne(id);
        if(u==null) {
            return new JsonObj(0, "用户不存在");
        }
        return new JsonObj(1, u);
    }

    public JsonObj delete(String params) {
        Integer id = Integer.parseInt(JsonTools.getField(params, "id"));
        userDao.delete(id);
        return new JsonObj(1, "删除成功");
    }

    public JsonObj login(String params) {
        String username = JsonTools.getField(params, "username");
        String password = JsonTools.getField(params, "password");
        if(username == null || password == null || "".equalsIgnoreCase(username) || "".equalsIgnoreCase(password)) {
            return new JsonObj(0, "用户名或密码为空");
        }
        User u = userDao.findByUsername(username);
        if(u==null) {
            return new JsonObj(0, "用户不存在");
        }
        if(!password.equals(u.getPassword())) {
            return new JsonObj(0, "密码不正确");
        }
        return new JsonObj(1, u);
    }

    public JsonObj findAll(String params) {
        List<User> list = userDao.findAll();
        return new JsonObj(list.size(), list);
    }

    public JsonObj saveOrUpdate(String params) {
        System.out.println("params::::"+params);
        User u = JSON.toJavaObject(JSON.parseObject(params), User.class);
        if(u.getId()==null || u.getId()<=0) {
            //表示是新增
            User oldUser = userDao.findByUsername(u.getUsername());
            if(oldUser!=null) {
                //判断usename是否存在，如果存在肯定不能再添加
                return new JsonObj(0, "用户名["+ u.getUsername()+"]已经存在");
            }
            userDao.save(u);
        } else {
            //表示修改
            User user = userDao.findOne(u.getId());
            user.setEmail(u.getEmail());
            user.setPhone(u.getPhone());
            user.setNickname(u.getNickname());
            user.setPassword(u.getPassword());
            userDao.save(user);
        }

        return new JsonObj(1, "保存成功");
    }
}
