package com.luobo.common.lang;

import lombok.Data;

import java.io.Serializable;
/**
 * @title 结果类
 * @description 将返回的结果进行一个结构化操作
 * @author davidmorgan
 * @updateTime 2020/11/26 11:13
 */

@Data
public class Result implements Serializable {

    private int code;
    private String msg;
    private Object data;

    public static Result succ(Object data){
        Result m = new Result();
        m.setCode(200);
        m.setData(data);
        m.setMsg("操作成功");
        return m;
    }

    public static Result succ(String mess, Object data){
        Result m = new Result();
        m.setCode(200);
        m.setData(data);
        m.setMsg(mess);
        return m;
    }

    public static Result fail(String mess){
        Result m = new Result();
        m.setCode(-1);
        m.setData(null);
        m.setMsg(mess);
        return m;
    }

    public static Result fail(String mess, Object data) {
        Result m = new Result();
        m.setCode(-1);
        m.setData(data);
        m.setMsg(mess);
        return m;
 }

    public static Result fail(int i, String message, Object data) {
        Result m = new Result();
        m.setCode(i);
        m.setData(message);
        m.setMsg(message);
        return m;
    }
}
