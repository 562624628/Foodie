package com.imooc.controller;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UsersBO;
import com.imooc.utils.ResultBase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping("passport")
@Api(value = "注册登陆",tags = "用于注册登陆相关的接口")
public interface PassPortApiService {
    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "POST")
    @GetMapping("/usernameIsExist")
    ResultBase userNameIsExist(@RequestParam String username);

    @PostMapping("/regist")
    ResultBase regist(@RequestBody UsersBO usersBO,HttpServletRequest request,HttpServletResponse response);

    @PostMapping("/login")
    ResultBase login(@RequestBody UsersBO usersBO, HttpServletRequest request, HttpServletResponse response);
}
