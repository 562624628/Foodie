package com.imooc.pojo.bo;

import lombok.Data;

@Data
public class UserAddressBO {
    private String id;
    private String userId;
    private String receiver;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detail;
    private String addressId;
    private String isDefault;
}
