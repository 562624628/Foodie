package com.imooc.service;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.UsersBO;

import java.util.List;

public interface UsersService {
    public List<Users> select(Users users);

    void insert(Users users);

    boolean queryUserNameIsExist(String name);

    Users createUsers(UsersBO users) ;

    Users queryUserForLogin(String username,String password);
}
