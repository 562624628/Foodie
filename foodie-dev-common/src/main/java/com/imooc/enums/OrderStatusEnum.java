package com.imooc.enums;

public enum OrderStatusEnum {
    WAIT_PAY(10,"待付款"),
    WAIT_DELIVERY(20,"已付款，待发货"),
    WAIT_RECEIVE(30,"已发货，待收货"),
    SUCCESS(40,"交易成功"),
    CLOSE(50,"交易关闭"),

            ;
    private Integer code;
    private String desc;
    OrderStatusEnum(Integer code, String desc){
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
