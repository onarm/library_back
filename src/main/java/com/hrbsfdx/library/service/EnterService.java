package com.hrbsfdx.library.service;

import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.request.EnterPageRequest;
import com.hrbsfdx.library.controller.request.PenaltyPageRequest;
import com.hrbsfdx.library.pojo.Enter;

import java.util.List;

public interface EnterService {

    List<Enter> list();


    void deleteById(Integer id);

    PageInfo<Enter> page(EnterPageRequest enterPageRequest);
}
