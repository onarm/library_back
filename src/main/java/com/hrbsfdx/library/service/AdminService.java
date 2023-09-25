package com.hrbsfdx.library.service;

import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.dto.LoginDTO;
import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.controller.request.LoginRequest;
import com.hrbsfdx.library.controller.request.PasswordRequest;
import com.hrbsfdx.library.pojo.Admin;

import java.util.List;


public interface AdminService {

    List<Admin> list();

    PageInfo<Admin> page(BaseRequest baseRequest);

    void save(Admin obj);

    Admin getById(Integer id);

    void update(Admin obj);

    void deleteById(Integer id);

    LoginDTO login(LoginRequest request);

    void changePass(PasswordRequest request);
}
