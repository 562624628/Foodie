package com.imooc.controller;

import com.imooc.pojo.bo.UserAddressBO;
import com.imooc.utils.ResultBase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("address")
public interface IAddressApiService {
    @PostMapping("/add")
    ResultBase addAddress(@RequestBody UserAddressBO userAddressBO);

    @PostMapping("/list")
    ResultBase listAddress(@RequestParam String userId);

    @PostMapping("/update")
    ResultBase update(@RequestBody UserAddressBO userAddressBO);

    @PostMapping("/delete")
    ResultBase delete(@RequestParam String userId,@RequestParam String addressId);

    @PostMapping("/setDefalut")
    ResultBase setDefalut(@RequestParam String userId,@RequestParam String addressId);
}
