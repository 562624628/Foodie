package com.imooc.controller.impl;

import com.imooc.controller.IPaymentApiService;
import com.imooc.enums.OrderStatusEnum;
import com.imooc.pojo.OrderStatus;
import com.imooc.service.OrderService;
import com.imooc.utils.ResultBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
@RestController
public class PaymentApiServiceImp implements IPaymentApiService {

    @Autowired
    private OrderService orderService;
    @Override
    public ResultBase getWXPayQRCode(String merchantUserId, String merchantOrderId) {
        //正常是客户付款后 微信异步通知 然后更新订单信息 现在没有公司无法是实现这个功能，所以直接在这里更新订单状态
        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVERY.getCode());
        HashMap<String, String> map = new HashMap<>();
        map.put("qrCodeUrl","/img/weixinQrCode.jpg");
        map.put("amount","1000");
        return ResultBase.ok(map);
    }
}
