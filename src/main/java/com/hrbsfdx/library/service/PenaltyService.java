package com.hrbsfdx.library.service;

import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.request.PenaltyPageRequest;
import com.hrbsfdx.library.controller.request.RechargePageRequest;
import com.hrbsfdx.library.pojo.Penalty;

import java.util.List;

public interface PenaltyService {

    List<Penalty> list();


    void deleteById(Integer id);

    PageInfo<Penalty> page(PenaltyPageRequest penaltyPageRequest);
}
