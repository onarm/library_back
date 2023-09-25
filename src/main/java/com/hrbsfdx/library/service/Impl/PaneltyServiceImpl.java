package com.hrbsfdx.library.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.request.PenaltyPageRequest;
import com.hrbsfdx.library.controller.request.RechargePageRequest;
import com.hrbsfdx.library.mapper.PenaltyMapper;
import com.hrbsfdx.library.pojo.Penalty;
import com.hrbsfdx.library.service.PenaltyService;
import com.hrbsfdx.library.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaneltyServiceImpl implements PenaltyService {
    @Autowired
    PenaltyMapper penaltyMapper;

    @Override
    public List<Penalty> list() {
        return penaltyMapper.list();
    }

    @Override
    public void deleteById(Integer id) {
        penaltyMapper.deleteById(id);
    }

    @Override
    public PageInfo<Penalty> page(PenaltyPageRequest penaltyPageRequest) {
        PageHelper.startPage(penaltyPageRequest.getPageNum(), penaltyPageRequest.getPageSize());
        List<Penalty> penaltyList = penaltyMapper.listByCondition(penaltyPageRequest);
        return new PageInfo<>(penaltyList);
    }
}