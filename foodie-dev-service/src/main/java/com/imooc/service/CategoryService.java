package com.imooc.service;

import com.imooc.pojo.Carousel;
import com.imooc.pojo.Category;
import com.imooc.pojo.vo.CategoryVO;

import java.util.List;

public interface CategoryService {
    /*
    * 查询所有一级分类
    * */
    List<Category> queryAllRootLevelCat();
    List<CategoryVO> getSubCatList(Integer rootCatId);
}
