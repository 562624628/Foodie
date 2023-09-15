package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户对象BO",description = "从客户端传入的用户对象")
public class UsersBO {
    @ApiModelProperty(value = "用户名",name = "username")
    private String username;
    private String password;
    private String confirmPassword;
}
