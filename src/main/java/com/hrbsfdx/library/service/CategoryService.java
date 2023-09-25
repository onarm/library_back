package com.hrbsfdx.library.service;

import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.pojo.Category;

import java.util.List;

public interface CategoryService {
    
    List<Category> list();

    PageInfo<Category> page(BaseRequest baseRequest);

    void save(Category obj);

    Category getById(Integer id);

    void update(Category obj);

    void deleteById(Integer id);
}
