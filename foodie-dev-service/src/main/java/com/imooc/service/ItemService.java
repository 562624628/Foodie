package com.imooc.service;

import com.imooc.pojo.*;
import com.imooc.pojo.vo.CommentLevelCountsVO;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.ShopcartVO;
import com.imooc.utils.PagedGridResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemService {
    //    根据商品id查询信息
    Items queryItemById(String id);

    List<ItemsImg> queryItemImgList(String itemId);

    List<ItemsSpec> queryItemSpecList(String itemId);

    ItemsParam queryItemParam(String itemId);

    CommentLevelCountsVO queryCommentCounts(String itemId);

    /*
     * 根据商品id查询商品的评价
     * */
    PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    PagedGridResult searchItems(String keywords, String sort, Integer page, Integer pageSize);

    PagedGridResult searchItems(Integer catId, String sort, Integer page, Integer pageSize);

    List<ShopcartVO> queryItemsBySpecIds(String specIds);

    ItemsSpec queryitemSpecById(String specId);

    String queryItemMainImgById(String itemId);

    void decreaseItemSpecStock(String specId,int count);
}
