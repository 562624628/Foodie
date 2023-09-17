package com.imooc.controller;

import com.imooc.pojo.bo.CenterUsersBO;
import com.imooc.utils.ResultBase;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IUserCenterApiService {
    @GetMapping("center/userInfo")
    ResultBase userInfo(@RequestParam String userId);
    @PostMapping("userInfo/update")
    ResultBase update(@RequestParam String userId, @RequestBody CenterUsersBO centerUsersBO, HttpServletRequest request, HttpServletResponse response);
    @PostMapping("userInfo/uploadFace")
    ResultBase update(@RequestParam String userId, MultipartFile file,HttpServletRequest request,HttpServletResponse response);

    @PostMapping("myorders/query")
    ResultBase query(@RequestParam String userId,@RequestParam Integer orderStatus,@RequestParam Integer page,@RequestParam Integer pageSize);
}
