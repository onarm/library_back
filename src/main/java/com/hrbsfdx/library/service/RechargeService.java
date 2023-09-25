package com.hrbsfdx.library.service;

import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.controller.request.RechargePageRequest;
import com.hrbsfdx.library.controller.request.UserPageRequest;
import com.hrbsfdx.library.pojo.Category;
import com.hrbsfdx.library.pojo.Recharge;
import com.hrbsfdx.library.pojo.User;

import java.util.List;

public interface RechargeService {

    List<Recharge> list();


    void deleteById(Integer id);

    PageInfo<Recharge> page(RechargePageRequest rechargePageRequest);
}
