package com.imooc.pojo.vo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MySubOrdersItemVO {
    private String itemId;
    private String itemName;
    private String itemImg;
    private String itemSpecId;
    private String itemSpecName;
    private Integer buyCounts;
    private Integer price;
}
