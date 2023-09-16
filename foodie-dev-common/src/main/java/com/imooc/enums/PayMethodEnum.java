package com.imooc.enums;

public enum PayMethodEnum {
    WEIXIN(1,"微信"),
    ZHIFUBAO(2,"支付宝");
    private Integer code;
    private String desc;
    PayMethodEnum(Integer code, String desc){
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
