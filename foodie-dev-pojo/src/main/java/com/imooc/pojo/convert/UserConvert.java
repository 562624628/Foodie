package com.imooc.pojo.convert;

import com.imooc.pojo.Users;
import com.imooc.pojo.bo.CenterUsersBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    Users convertCenterUserTOUsers(CenterUsersBO centerUsersBO);
}
