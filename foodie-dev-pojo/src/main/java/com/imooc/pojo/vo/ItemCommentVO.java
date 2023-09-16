package com.imooc.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ItemCommentVO {
    private Integer commentLevel;

    private String content;

    private String specName;

    private Date createTime;

    private String userFace;

    private String nickname;

}
