package com.zslin.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Created by zsl on 2018/7/30.
 */
public class JsonTools {

    /**
     *  {'username':"zsl", password: "123"}
     * @param jsonStr
     * @param field
     * @return
     */
    public static String getField(String jsonStr, String field) {
        JSONObject jsonObj = JSON.parseObject(jsonStr);
        String str = jsonObj.getString(field);
        return str;
    }
}
