package com.imooc.service;

import com.imooc.pojo.bo.UserAddressBO;

import java.util.List;

public interface UserAddressService {
    void addUserAddress(UserAddressBO userAddressBO);

    List<UserAddressBO> listAddress(String userId);

    void update(UserAddressBO userAddressBO);

    void delete(String userId, String addressId);

    UserAddressBO selectByUserIdAndAddressId(String userId, String addressId);
}
