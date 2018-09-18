package com.m.liu.studyapp;

/**
 * Created by luculent on 2018/9/5.
 * 对返回的数据进行json解析
 */

public class Jgson {
    public String message;
    public String success;
    public String errorType;
    public data data;

    public static class data {
        public String nickName;

    }
    @Override
    public String toString() {
        return "Jgson{" +
                "message='" + message + '\'' +
                ", success='" + success + '\'' +
                ", errorType='" + errorType + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
