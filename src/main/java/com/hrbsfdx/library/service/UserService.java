package com.hrbsfdx.library.service;

import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.pojo.User;

import java.util.List;

public interface UserService {
    List<User> list();

    PageInfo<User> page(BaseRequest baseRequest);

    void save(User user);

    User getById(Integer id);

    void update(User user);

    void deleteById(Integer id);

    void handleAccount(User user);
}
