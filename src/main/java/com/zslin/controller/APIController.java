package com.zslin.controller;

import com.zslin.dto.JsonObj;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by zsl on 2018/7/23.
 */
@RestController
@RequestMapping(value = "api")
public class APIController {

    @Autowired
    private BeanFactory factory;

    @GetMapping(value = "handler")
    public JsonObj handler(HttpServletRequest request) {
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
