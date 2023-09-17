package com.imooc.service.impl;

import com.imooc.enums.YesNoEnum;
import com.imooc.mapper.UserAddressMapper;
import com.imooc.pojo.convert.AddressConvert;
import com.imooc.pojo.UserAddress;
import com.imooc.pojo.bo.UserAddressBO;
import com.imooc.service.UserAddressService;
import org.apache.commons.lang3.StringUtils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private Sid sid;
    @Override
    public void addUserAddress(UserAddressBO userAddressBO) {
        UserAddress userAddress = AddressConvert.INSTANCE.convertUserAddressBOtoUserAddress(userAddressBO);
        userAddress.setProvince(StringUtils.isBlank(userAddressBO.getProvince())?userAddressBO.getCity():userAddressBO.getProvince());
        userAddress.setId(sid.nextShort());
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        userAddress.setIsDefault(YesNoEnum.NO.getCode());
        userAddressMapper.insert(userAddress);
    }

    @Override
    public List<UserAddressBO> listAddress(String userId) {
        UserAddress t = new UserAddress();
        t.setUserId(userId);
        List<UserAddress> select = userAddressMapper.select(t);

        return AddressConvert.INSTANCE.convertUserAddressListToUserAddressBOList(select);
    }

    @Override
    public void update(UserAddressBO userAddressBO) {
        UserAddress userAddress = AddressConvert.INSTANCE.convertUserAddressBOtoUserAddress(userAddressBO);
        userAddress.setProvince(StringUtils.isBlank(userAddressBO.getProvince())?userAddressBO.getCity():userAddressBO.getProvince());
        userAddress.setId(StringUtils.isBlank(userAddressBO.getId())? userAddressBO.getAddressId() : userAddressBO.getId());
        userAddress.setUpdatedTime(new Date());
        userAddress.setCreatedTime(new Date());
        userAddressMapper.updateByPrimaryKey(userAddress);
    }

    @Override
    public void delete(String userId, String addressId) {
        UserAddress userAddress = new UserAddress();
        userAddress.setUserId(userId);
        userAddress.setId(addressId);
        userAddressMapper.delete(userAddress);
    }

    @Override
    public UserAddressBO selectByUserIdAndAddressId(String userId, String addressId) {
        UserAddress t = new UserAddress();
        t.setId(addressId);
        t.setUserId(userId);
        UserAddressBO userAddressBO = AddressConvert.INSTANCE.convertUserAddressToUserAddressBO(userAddressMapper.selectOne(t));
        return userAddressBO;
    }
}
