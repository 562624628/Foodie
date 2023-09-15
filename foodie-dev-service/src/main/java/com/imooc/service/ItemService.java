package com.imooc.service;

import com.imooc.pojo.*;

import java.util.List;

public interface ItemService {
    //    根据商品id查询信息
    Items queryItemById(String id);

    List<ItemsImg> queryItemImgList(String itemId);

    List<ItemsSpec> queryItemSpecList(String itemId);

    ItemsParam queryItemParam(String itemId);
}
