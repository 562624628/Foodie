package com.imooc.controller.impl;

import com.imooc.controller.IOrdersApiService;
import com.imooc.enums.PayMethodEnum;
import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.service.OrderService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.ResultBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class OrdersApiServiceImpl extends BaseController implements IOrdersApiService {
    @Autowired
    private OrderService orderService;
    @Override
    public ResultBase create(SubmitOrderBO submitOrderBO, HttpServletRequest request, HttpServletResponse response) {
        if((!PayMethodEnum.WEIXIN.getCode().equals(submitOrderBO.getPayMethod()))
                && !PayMethodEnum.ZHIFUBAO.getCode().equals(submitOrderBO.getPayMethod())){
            return ResultBase.errorMsg("支付方式错误");
        }
        //创建订单
        //2。创建订单之后，移除购物车中已结算的
        //CookieUtils.setCookie(request,response,FOODIE_SHOPCART,"",true);
        //3。像支付中心发送订单
        String order = orderService.createOrder(submitOrderBO);
        return ResultBase.ok(order);
    }
}
