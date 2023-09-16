package com.imooc.controller.impl;

import com.imooc.controller.ItemApiService;
import com.imooc.pojo.*;
import com.imooc.pojo.vo.ItemInfoVO;
import com.imooc.service.ItemService;
import com.imooc.utils.PagedGridResult;
import com.imooc.utils.ResultBase;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemApiServiceImpl extends BaseController implements ItemApiService {

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

    @Override
    public ResultBase commentLevel(String itemId) {
        if(StringUtils.isBlank(itemId)){
            return ResultBase.errorMsg(null);
        }
        return ResultBase.ok(itemService.queryCommentCounts(itemId));
    }

    @Override
    public ResultBase comments(String itemId, Integer level, Integer page, Integer pageSize) {
        if(StringUtils.isBlank(itemId)){
            return ResultBase.errorMsg(null);
        }
        if(page == null){
            page = 1;
        }
        if(pageSize == null){
            pageSize = COMMENT_PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.queryPagedComments(itemId, level, page, pageSize);

        return ResultBase.ok(pagedGridResult);
    }

    @Override
    public ResultBase comments(String keywords, String sort, Integer page, Integer pageSize) {
        if(StringUtils.isBlank(keywords)){
            return ResultBase.errorMsg(null);
        }
        if(page == null){
            page = 1;
        }
        if(pageSize == null){
            pageSize = PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.searchItems(keywords, sort, page, pageSize);
        return ResultBase.ok(pagedGridResult);
    }

    @Override
    public ResultBase comments(Integer catId, String sort, Integer page, Integer pageSize) {
        if(catId == null){
            return ResultBase.errorMsg(null);
        }
        if(page == null){
            page = 1;
        }
        if(pageSize == null){
            pageSize = PAGE_SIZE;
        }
        PagedGridResult pagedGridResult = itemService.searchItems(catId, sort, page, pageSize);
        return ResultBase.ok(pagedGridResult);
    }

    @Override
    public ResultBase refresh(String itemSpecIds) {
        if(StringUtils.isBlank(itemSpecIds)){
            return ResultBase.ok();
        }

        return ResultBase.ok(itemService.queryItemsBySpecIds(itemSpecIds));
    }
}
