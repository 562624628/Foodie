package com.imooc.controller;

import com.imooc.utils.ResultBase;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("index")
@Api(value = "首页",tags = "首页展示的相关接口")
public interface IndexApiService {
    @GetMapping("/carousel")
    ResultBase carousel();

    @GetMapping("/cats")
    ResultBase cats();

    @GetMapping("/subCat/{rootCatId}")
    ResultBase subCat(@PathVariable Integer rootCatId);

    @GetMapping("/sixNewItems/{rootCatId}")
    ResultBase sixNewItems(@PathVariable Integer rootCatId);
}
