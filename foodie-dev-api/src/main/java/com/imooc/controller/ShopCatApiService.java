package com.imooc.controller;

import com.imooc.pojo.bo.ShopcartBO;
import com.imooc.utils.ResultBase;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("shopcart")
public interface ShopCatApiService {
    @PostMapping("/add")
    ResultBase add(@RequestParam String userId, @RequestBody ShopcartBO shopcartBO, HttpServletRequest request, HttpServletResponse response);

    @PostMapping("del")
    ResultBase del(@RequestParam String userId,@RequestParam String itemSpecId,HttpServletRequest request, HttpServletResponse response);
}
