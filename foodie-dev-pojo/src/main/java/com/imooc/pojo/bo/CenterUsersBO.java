package com.imooc.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class CenterUsersBO {
    private String username;
//    private String password;
//    private String confirmPassword;
    private String nickname;
    private String realname;
    private String mobile;
    private String email;
    private Integer sex;
    private Date birthday;
}
