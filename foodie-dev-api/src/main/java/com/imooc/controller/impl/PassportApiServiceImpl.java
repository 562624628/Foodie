package com.imooc.controller.impl;

import com.imooc.controller.PassPortApiService;
import com.imooc.pojo.Users;
import com.imooc.service.UsersService;
import com.imooc.utils.ResultBase;
import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.util.StringUtil;

@RestController

public class PassportController implements PassPortApiService {
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
}
