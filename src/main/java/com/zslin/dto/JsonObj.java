package com.zslin.dto;

/**
 * Created by zsl on 2018/7/23.
 */
public class JsonObj {

    private Integer size;

    private Object datas;

    public JsonObj() {
    }

    public JsonObj(Integer size, Object datas) {
        this.size = size;
        this.datas = datas;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }
}
