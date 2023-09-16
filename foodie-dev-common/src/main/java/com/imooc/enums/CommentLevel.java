package com.imooc.enums;

public enum CommentLevel {
    GOOD(1,"好评"),
    NORMAL(2,"中评"),
    BAD(3,"差评");
    private Integer code;
    private String desc;
    CommentLevel(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode(){
        return this.code;
    }
    public String getDesc(){
        return this.desc;
    }
}
