package com.imooc.service.impl;

import com.imooc.mapper.UsersMapper;
import com.imooc.pojo.Users;
import com.imooc.pojo.bo.CenterUsersBO;
import com.imooc.pojo.convert.UserConvert;
import com.imooc.service.UsersCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserCenterServiceImpl implements UsersCenterService {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Users queryUserInfo(String userId) {

        Users users = usersMapper.selectByPrimaryKey(userId);
        users.setPassword(null);
        return users;
    }

    @Override
    public Users updateUserInfo(String userId, CenterUsersBO centerUsersBO) {
        Users users = UserConvert.INSTANCE.convertCenterUserTOUsers(centerUsersBO);
        users.setId(userId);
        users.setUpdatedTime(new Date());
        usersMapper.updateByPrimaryKeySelective(users);

        return usersMapper.selectByPrimaryKey(userId);
    }

    @Override
    public Users updateUserInfo(String userId, String faceUrl) {
        Users users = new Users();
        users.setId(userId);
        users.setFace(faceUrl);
        users.setUpdatedTime(new Date());
        usersMapper.updateByPrimaryKeySelective(users);

        return usersMapper.selectByPrimaryKey(userId);
    }
}
