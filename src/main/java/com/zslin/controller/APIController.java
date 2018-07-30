package com.zslin.controller;

import com.zslin.dto.JsonObj;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by zsl on 2018/7/23.
 */
@RestController
@RequestMapping(value = "api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class APIController {

    @Autowired
    private BeanFactory factory;

    @GetMapping(value = "handler2")
    public JsonObj handler2(HttpServletRequest request, String params, HttpServletResponse response) {
        System.out.println(request.getHeader("api-code"));
        String apiCode = request.getHeader("api-code");
        try {
            String [] array = apiCode.split("\\.");
            String serviceName = array[0];
            String methodName = array[1];
            Object obj = factory.getBean(serviceName);
            Method method = obj.getClass().getMethod(methodName, params.getClass());
            JsonObj result = (JsonObj) method.invoke(obj, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonObj(0, "出错了");
        }
    }

    @GetMapping(value = "handler")
    public JsonObj handler(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getHeader("Access-Control-Allow-Origin"));
//        response.setHeader("Access-Control-Allow-Origin", "*");
        //这里填写你允许进行跨域的主机ip
        response.setHeader("Access-Control-Allow-Origin", "*");
        //允许的访问方法
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE, PATCH");
        //Access-Control-Max-Age 用于 CORS 相关配置的缓存
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        System.out.println(request.getHeader("Access-Control-Allow-Origin"));
        try {
            String apiCode = request.getHeader("api-code"); //newsService.list
            String [] array = apiCode.split("\\.");
            String serviceName = array[0];
            String methodName = array[1];

            Object obj = factory.getBean(serviceName);
            Method method = obj.getClass().getMethod(methodName);
            JsonObj result = (JsonObj) method.invoke(obj);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonObj(0, "出错了");
        }
    }
}
