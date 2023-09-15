package com.imooc.controller.impl;

import com.imooc.controller.PassPortApiService;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UsersBO;
import com.imooc.service.UsersService;
import com.imooc.utils.CookieUtils;
import com.imooc.utils.JsonUtils;
import com.imooc.utils.ResultBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class PassportApiServiceImpl implements PassPortApiService {
    @Autowired
    UsersService usersService;
    @GetMapping("/getUsers")
    public Object hello(Integer id){
        Users t = new Users();
        return usersService.select(t);
    }
    @PostMapping("saveUsers")
    public void saveUsers(@RequestBody Users users){
        usersService.insert(users);
    }

    public ResultBase userNameIsExist(@RequestParam String username){
        if(!StringUtils.isNotBlank(username)){
            return ResultBase.errorMsg("用户名不能为空");
        }
        if (usersService.queryUserNameIsExist(username)) {
            return ResultBase.errorMsg("用户名已经存在");
        }
        return ResultBase.ok();
    }

    @Override
    public ResultBase regist(UsersBO usersBO,HttpServletRequest request,HttpServletResponse response) {
        log.info("regist:{}",JsonUtils.objectToJson(usersBO));

        if(StringUtils.isAnyBlank(usersBO.getUsername(),usersBO.getPassword(),usersBO.getConfirmPassword())){
            return ResultBase.errorMsg("用户名和密码不能为空");
        }
        if (usersService.queryUserNameIsExist(usersBO.getUsername())) {
            return ResultBase.errorMsg("用户名已经存在");
        }
        if(usersBO.getPassword().length()<6){
            return ResultBase.errorMsg("密码不能小于6位");
        }
        if(!usersBO.getPassword().equals(usersBO.getConfirmPassword())){
            return ResultBase.errorMsg("用户密码和确认密码不一样");
        }
        Users users = usersService.createUsers(usersBO);
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(users),true);

        return ResultBase.ok(users);
    }

    @Override
    public ResultBase login(UsersBO usersBO, HttpServletRequest request, HttpServletResponse response) {
        log.info("login:{}",JsonUtils.objectToJson(usersBO));
        if(StringUtils.isAnyBlank(usersBO.getUsername(),usersBO.getPassword())){
            return ResultBase.errorMsg("用户名和密码不能为空");
        }
        Users users = usersService.queryUserForLogin(usersBO.getUsername(), usersBO.getPassword());
        if(null == users){
            return ResultBase.errorMsg("用户名或者密码错误");
        }
        CookieUtils.setCookie(request,response,"user", JsonUtils.objectToJson(users),true);
        return ResultBase.ok(users);
    }

    @Override
    public ResultBase logout(String userId, HttpServletRequest request, HttpServletResponse response) {
        log.info("logout:{}",userId);
        //清除用户相关的cookie
        CookieUtils.deleteCookie(request,response,"user");
        return ResultBase.ok();
    }
}
