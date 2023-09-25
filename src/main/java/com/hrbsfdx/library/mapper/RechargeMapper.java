package com.hrbsfdx.library.mapper;

import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.pojo.Category;
import com.hrbsfdx.library.pojo.Recharge;
import com.hrbsfdx.library.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RechargeMapper {


    List<Recharge> list();

    List<Recharge> listByCondition(BaseRequest baseRequest);

    void save(Recharge obj);

    void deleteById(Integer id);

}