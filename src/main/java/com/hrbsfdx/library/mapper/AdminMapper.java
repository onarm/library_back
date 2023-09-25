package com.hrbsfdx.library.mapper;

import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.controller.request.LoginRequest;
import com.hrbsfdx.library.controller.request.PasswordRequest;
import com.hrbsfdx.library.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {


    List<Admin> list();

    List<Admin> listByCondition(BaseRequest baseRequest);

    void save(Admin obj);

    Admin getById(Integer id);

    void updateById(Admin obj);

    void deleteById(Integer id);

    Admin getByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

    int updatePassword(PasswordRequest request);

    Admin getByUsername(String username);
}