package com.imooc.controller.impl;

import com.imooc.controller.ItemApiService;
import com.imooc.pojo.*;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.service.ItemService;
import com.imooc.utils.ResultBase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemApiServiceImpl implements ItemApiService {

    @Autowired
    private ItemService itemService;
    @Override
    public ResultBase info(String itemId) {
        if(StringUtils.isBlank(itemId)){
            return ResultBase.errorMsg(null);
        }
        ItemInfoVO itemInfoVO = new ItemInfoVO();
        Items items = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgs = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);
        itemInfoVO.setItem(items);
        itemInfoVO.setItemImgList(itemsImgs);
        itemInfoVO.setItemSpecList(itemsSpecs);
        itemInfoVO.setItemParams(itemsParam);
        return ResultBase.ok(itemInfoVO);
    }
}
