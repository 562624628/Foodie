package com.imooc.enums;
/*性别枚举*/
public enum SexEnum {
    WOMAN(0,"女"),
    MAN(1,"男"),
    SECRET(2,"保密");
    private Integer code;
    private String desc;
    SexEnum(Integer code, String desc){
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
