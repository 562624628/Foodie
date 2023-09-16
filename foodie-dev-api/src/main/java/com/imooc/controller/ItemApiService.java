package com.imooc.controller;

import com.imooc.utils.ResultBase;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("items")
@Api(value = "首页",tags = "首页展示的相关接口")
public interface ItemApiService {
    @GetMapping("/info/{itemId}")
    ResultBase info(@PathVariable String itemId);

    @GetMapping("/commentLevel")
    ResultBase commentLevel(@RequestParam String itemId);

    @GetMapping("/comments")
    ResultBase comments(@RequestParam String itemId,@RequestParam Integer level,@RequestParam Integer page,@RequestParam Integer pageSize);

    @GetMapping("/search")
    ResultBase comments(@RequestParam String keywords,@RequestParam String sort,@RequestParam Integer page,@RequestParam Integer pageSize);

}
