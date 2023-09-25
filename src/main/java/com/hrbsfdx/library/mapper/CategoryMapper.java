package com.hrbsfdx.library.mapper;

import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.controller.request.PasswordRequest;
import com.hrbsfdx.library.pojo.Admin;
import com.hrbsfdx.library.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {


    List<Category> list();

    List<Category> listByCondition(BaseRequest baseRequest);

    void save(Category obj);

    Category getById(Integer id);

    void updateById(Category obj);

    void deleteById(Integer id);

}