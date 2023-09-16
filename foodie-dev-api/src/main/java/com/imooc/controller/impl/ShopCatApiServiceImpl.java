package com.imooc.controller.impl;

import com.imooc.controller.ShopCatApiService;
import com.imooc.pojo.bo.ShopcartBO;
import com.imooc.service.ItemService;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.ResultBase;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class ShopCatApiServiceImpl implements ShopCatApiService {

    @Autowired
    private ItemService itemService;
    @Override
    public ResultBase add(String userId, ShopcartBO shopcartBO, HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.isBlank(userId)) {
            return ResultBase.errorMsg(null);
        }
        //TODO 前端用户在登陆的情况下，添加商品到购物车 会在后段同步到redis
        log.info("shopcart:{}", JsonUtils.objectToJson(shopcartBO));
        return ResultBase.ok();
    }

    @Override
    public ResultBase del(String userId, String itemSpecId, HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.isBlank(userId) || StringUtils.isBlank(itemSpecId)) {
            return ResultBase.errorMsg(null);
        }
        log.info("shopcart del:{},userId:{}", itemSpecId,userId);
        return ResultBase.ok();
    }

}
