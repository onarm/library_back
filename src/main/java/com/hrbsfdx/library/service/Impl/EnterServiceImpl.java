package com.hrbsfdx.library.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.request.EnterPageRequest;
import com.hrbsfdx.library.controller.request.PenaltyPageRequest;
import com.hrbsfdx.library.mapper.EnterMapper;
import com.hrbsfdx.library.pojo.Enter;
import com.hrbsfdx.library.pojo.Penalty;
import com.hrbsfdx.library.service.EnterService;
import com.hrbsfdx.library.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterServiceImpl implements EnterService {
    @Autowired
    EnterMapper enterMapper;

    @Override
    public List<Enter> list() {
        return enterMapper.list();
    }

    @Override
    public void deleteById(Integer id) {
        enterMapper.deleteById(id);
    }

    @Override
    public PageInfo<Enter> page(EnterPageRequest enterPageRequest) {
        PageHelper.startPage(enterPageRequest.getPageNum(), enterPageRequest.getPageSize());
        List<Enter> enterList = enterMapper.listByCondition(enterPageRequest);
        return new PageInfo<>(enterList);
    }
}