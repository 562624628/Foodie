package com.imooc.mapper;

import com.imooc.my.mapper.MyMapper;
import com.imooc.pojo.Items;
import com.imooc.pojo.vo.ItemCommentVO;
import com.imooc.pojo.vo.SearchItemsVO;
import com.imooc.pojo.vo.ShopcartVO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom extends MyMapper<Items> {
    List<ItemCommentVO> queryItemComments(@Param("paramsMap")Map<String,Object> map);

    List<SearchItemsVO> searchItems(@Param("paramsMap")HashMap<String, Object> map);

    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap")HashMap<String, Object> map);

    List<ShopcartVO> queryItemsBySpecIds(@Param("paramsList") List specIds);

    Integer decreaseItemSpecStock(@Param("specId")String specId, @Param("pendingCounts") int count);
}