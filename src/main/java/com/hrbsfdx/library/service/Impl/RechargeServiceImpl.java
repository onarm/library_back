package com.hrbsfdx.library.service.Impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.controller.request.RechargePageRequest;
import com.hrbsfdx.library.mapper.RechargeMapper;
import com.hrbsfdx.library.pojo.Recharge;
import com.hrbsfdx.library.pojo.User;
import com.hrbsfdx.library.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    RechargeMapper rechargeMapper;

    @Override
    public List<Recharge> list() {
        return rechargeMapper.list();
    }

    @Override
    public void deleteById(Integer id) {
        rechargeMapper.deleteById(id);
    }

    @Override
    public PageInfo<Recharge> page(RechargePageRequest rechargePageRequest) {
        PageHelper.startPage(rechargePageRequest.getPageNum(), rechargePageRequest.getPageSize());
        List<Recharge> rechargeList = rechargeMapper.listByCondition(rechargePageRequest);
        return new PageInfo<>(rechargeList);
    }
}