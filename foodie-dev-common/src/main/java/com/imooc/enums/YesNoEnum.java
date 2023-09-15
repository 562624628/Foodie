package com.imooc.enums;

public enum YesNoEnum {
    YES(1,"Y"),
    NO(0,"N");
    private Integer code;
    private String desc;
    YesNoEnum(Integer code, String desc){
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
