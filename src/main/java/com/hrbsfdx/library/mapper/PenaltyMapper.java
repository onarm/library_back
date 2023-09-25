package com.hrbsfdx.library.mapper;

import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.pojo.Penalty;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PenaltyMapper {


    List<Penalty> list();

    List<Penalty> listByCondition(BaseRequest baseRequest);

    void save(Penalty obj);

    void deleteById(Integer id);

}