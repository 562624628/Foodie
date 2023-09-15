package com.imooc.controller.impl;

import com.imooc.controller.IndexApiService;
import com.imooc.enums.YesNoEnum;
import com.imooc.pojo.Carousel;
import com.imooc.pojo.vo.CategoryVO;
import com.imooc.pojo.vo.NewItemsVO;
import com.imooc.service.CarouseService;
import com.imooc.service.CategoryService;
import com.imooc.utils.ResultBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class IndexApiServiceImpl implements IndexApiService {

    @Autowired
    private CarouseService carouseService;

    @Autowired
    private CategoryService categoryService;

    @Override
    public ResultBase carousel() {
        List<Carousel> carousels = carouseService.queryAll(YesNoEnum.YES.getCode());
        return ResultBase.ok(carousels);
    }

    @Override
    public ResultBase cats() {
        return ResultBase.ok(categoryService.queryAllRootLevelCat());
    }

    @Override
    public ResultBase subCat(Integer rootCatId) {
        if(rootCatId == null){
            return ResultBase.errorMsg("分类不存在");
        }
        List<CategoryVO> subCatList = categoryService.getSubCatList(rootCatId);
        return ResultBase.ok(subCatList);
    }

    @Override
    public ResultBase sixNewItems(Integer rootCatId) {
        if(rootCatId == null){
            return ResultBase.errorMsg("分类不存在");
        }
        List<NewItemsVO> sixNewItemsLazy = categoryService.getSixNewItemsLazy(rootCatId);
        return ResultBase.ok(sixNewItemsLazy);
    }
}
