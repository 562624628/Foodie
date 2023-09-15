package com.imooc.controller;

import com.imooc.utils.ResultBase;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("items")
@Api(value = "首页",tags = "首页展示的相关接口")
public interface ItemApiService {
    @GetMapping("/info/{itemId}")
    ResultBase info(@PathVariable String itemId);
}
