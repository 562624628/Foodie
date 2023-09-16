package com.imooc.controller.impl;

import com.imooc.controller.IAddressApiService;
import com.imooc.enums.YesNoEnum;
import com.imooc.pojo.bo.UserAddressBO;
import com.imooc.service.UserAddressService;
import com.imooc.utils.ResultBase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserAddressApiServiceImpl implements IAddressApiService {

    @Autowired
    private UserAddressService userAddressService;
    @Override
    public ResultBase addAddress(UserAddressBO userAddressBO) {
        if(StringUtils.isAnyBlank(userAddressBO.getUserId()
                ,userAddressBO.getCity()
        ,userAddressBO.getDetail(),userAddressBO.getMobile(),userAddressBO.getReceiver())){
            return ResultBase.errorMsg("重要信息需要必填");
        }
        userAddressService.addUserAddress(userAddressBO);
        return ResultBase.ok();
    }

    @Override
    public ResultBase listAddress(String userId) {
        if(StringUtils.isBlank(userId)){
            return ResultBase.errorMsg("no userId");
        }
        List<UserAddressBO> userAddressBOS =  userAddressService.listAddress(userId);
        return ResultBase.ok(userAddressBOS);
    }

    @Override
    public ResultBase update(UserAddressBO userAddressBO) {
        if(StringUtils.isAnyBlank(userAddressBO.getUserId()
                ,userAddressBO.getCity()
                ,userAddressBO.getDetail()
                ,userAddressBO.getMobile()
                ,userAddressBO.getReceiver()
                ,userAddressBO.getAddressId())){
            return ResultBase.errorMsg("重要信息需要必填");
        }
        userAddressService.update(userAddressBO);
        return ResultBase.ok();
    }

    @Override
    public ResultBase delete(String userId, String addressId) {
        if(StringUtils.isAnyBlank(userId,addressId)){
            return ResultBase.errorMsg(null);
        }
        userAddressService.delete(userId,addressId);
        return ResultBase.ok();
    }

    @Override
    public ResultBase setDefalut(String userId, String addressId) {
        //先查看当前地址是否存在
        UserAddressBO userAddressBO = userAddressService.selectByUserIdAndAddressId(userId,addressId);
        if (null == userAddressBO){
            return ResultBase.errorMsg("没有这个地址");
        }
        List<UserAddressBO> userAddressBOS = userAddressService.listAddress(userId);
        for (UserAddressBO addressBO : userAddressBOS) {
            if(YesNoEnum.YES.getCode().toString().equals(addressBO.getIsDefault()) && !addressBO.getId().equals(addressId)) {
                addressBO.setIsDefault(YesNoEnum.NO.getCode().toString());
                userAddressService.update(addressBO);
            }else if(addressBO.getId().equals(addressId)){
                addressBO.setIsDefault(YesNoEnum.YES.getCode().toString());
                userAddressService.update(addressBO);
            }
        }
        return ResultBase.ok();
    }
}
