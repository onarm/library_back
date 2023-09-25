package com.hrbsfdx.library.mapper;

import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.pojo.Enter;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EnterMapper {


    List<Enter> list();

    List<Enter> listByCondition(BaseRequest baseRequest);

    void save(Enter obj);

    void deleteById(Integer id);

}