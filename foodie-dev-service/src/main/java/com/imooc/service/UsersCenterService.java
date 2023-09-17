package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.CenterUsersBO;
import com.imooc.pojo.bo.UsersBO;

import java.util.List;

public interface UsersCenterService {
    Users queryUserInfo(String userId);

    Users updateUserInfo(String userId, CenterUsersBO centerUsersBO);

    Users updateUserInfo(String userId, String faceUrl);

}
