package com.imooc.enums;
/*性别枚举*/
public enum Sex {
    WOMAN(0,"女"),
    MAN(1,"男"),
    SECRET(2,"保密");
    private Integer code;
    private String desc;
    Sex(Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

}
