package com.hrbsfdx.library.service.Impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.mapper.RechargeMapper;
import com.hrbsfdx.library.mapper.UserMapper;
import com.hrbsfdx.library.pojo.Recharge;
import com.hrbsfdx.library.pojo.User;
import com.hrbsfdx.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RechargeMapper rechargeMapper;

    @Override
    public List<User> list() {
        return userMapper.list();
    }

    @Override
    public PageInfo<User> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<User> users = userMapper.listByCondition(baseRequest);
        return new PageInfo<>(users);
    }

    //大立
    @Override
    public void save(User user) {
        Date date = new Date();
        // 当做卡号来处理
        user.setUsername(DateUtil.format(date, "yyyyMMdd") + Math.abs(IdUtil.fastSimpleUUID().hashCode()));
        userMapper.save(user);
    }

    @Override
    public User getById(Integer id) {
        return userMapper.getById(id);
    }

    @Override
    public void update(User user) {
        user.setUpdatetime(new Date());
        userMapper.updateById(user);
    }

    @Override
    public void deleteById(Integer id) {
        userMapper.deleteById(id);
    }

    //大立
    @Override
    public void handleAccount(User user) {
        Recharge recharge = new Recharge();
        Double score = user.getScore();
        if(score == null){
            return;
        }
        Integer id = user.getId();
        User dbuser = userMapper.getById(id);
        recharge.setUsername(dbuser.getName());
        recharge.setPreviousBalance(dbuser.getAccount());
        dbuser.setAccount(dbuser.getAccount() + score);
        recharge.setAfterBalance(dbuser.getAccount());
        rechargeMapper.save(recharge);
        userMapper.updateById(dbuser);
    }
}