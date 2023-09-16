package com.imooc.controller;

import com.imooc.pojo.bo.SubmitOrderBO;
import com.imooc.utils.ResultBase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("orders")
public interface IOrdersApiService {
    @PostMapping("/create")
    ResultBase create(@RequestBody SubmitOrderBO submitOrderBO, HttpServletRequest request, HttpServletResponse response);

    @PostMapping("/getPaidOrderInfo")
    ResultBase getPaidOrderInfo(@RequestParam String orderId);
}
