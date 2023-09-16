package com.imooc.controller;

import com.imooc.utils.ResultBase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("payment")
public interface IPaymentApiService {
    @PostMapping("/getWXPayQRCode")
    ResultBase getWXPayQRCode(@RequestParam String merchantUserId,@RequestParam String merchantOrderId);
}
