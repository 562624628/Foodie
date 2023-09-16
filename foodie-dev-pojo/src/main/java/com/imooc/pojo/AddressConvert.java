package com.imooc.pojo;


import com.imooc.pojo.bo.UserAddressBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AddressConvert {
    AddressConvert INSTANCE = Mappers.getMapper(AddressConvert.class);
    UserAddress convertUserAddressBOtoUserAddress(UserAddressBO userAddressBO);
    UserAddressBO convertUserAddressToUserAddressBO(UserAddress userAddress);
    List<UserAddressBO> convertUserAddressListToUserAddressBOList(List<UserAddress> userAddress);

}
