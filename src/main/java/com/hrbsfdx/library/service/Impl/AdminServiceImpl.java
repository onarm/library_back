package com.hrbsfdx.library.service.Impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.dto.LoginDTO;
import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.controller.request.LoginRequest;
import com.hrbsfdx.library.controller.request.PasswordRequest;
import com.hrbsfdx.library.exception.ServiceException;
import com.hrbsfdx.library.mapper.AdminMapper;
import com.hrbsfdx.library.pojo.Admin;
import com.hrbsfdx.library.service.AdminService;
import com.hrbsfdx.library.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    private static final String DEFAULT_PASS = "123";
    private static final String PASS_SALT = "dubuyin";

    @Override
    public List<Admin> list() {
        return adminMapper.list();
    }

    @Override
    public PageInfo<Admin> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Admin> users = adminMapper.listByCondition(baseRequest);
        return new PageInfo<>(users);
    }

    @Override
    public void save(Admin obj) {
        //默认密码123
        if(StrUtil.isBlank(obj.getPassword())){
            obj.setPassword(DEFAULT_PASS);
        }
        obj.setPassword(securePass(obj.getPassword())); //设置md5 加密，加盐
        try{
            adminMapper.save(obj);
        }catch (DuplicateKeyException e){
            log.error("添加管理员{}失败",obj.getUsername(), e);
            throw new ServiceException("用户名重复");
        }
    }


    @Override
    public Admin getById(Integer id) {
        return adminMapper.getById(id);
    }

    @Override
    public void update(Admin user) {
        user.setUpdatetime(new Date());
        adminMapper.updateById(user);
    }

    @Override
    public void deleteById(Integer id) {
        adminMapper.deleteById(id);
    }

    //李辉叶
    @Override
    public LoginDTO login(LoginRequest request) {
        Admin admin = null;
        try{
            admin = adminMapper.getByUsername(request.getUsername());
        }catch (Exception e){
            log.error("根据用户名{}，查询出错",request.getUsername());
            throw new ServiceException("用户名错误");
        }
        if(admin == null){
            throw new ServiceException("用户名或密码错误");
        }
        // 判断密码是否合法
        String securePass = securePass(request.getPassword());
        if(!securePass.equals(admin.getPassword())){
            throw new ServiceException("用户名或密码错误");
        }
        if(!admin.isStatus()){
            throw new ServiceException("当前用户处于禁用状态");
        }
        LoginDTO loginDTO = new LoginDTO();
        BeanUtils.copyProperties(admin, loginDTO);

        //生成token
        String token = TokenUtils.genToken(String.valueOf(admin.getId()), admin.getPassword());
        loginDTO.setToken(token);
        return loginDTO;
    }

    @Override
    public void changePass(PasswordRequest request) {
        request.setNewPass(securePass(request.getNewPass()));
        int i = adminMapper.updatePassword(request);
        if( i <= 0){
            throw new ServiceException("修改失败");
        }
    }

    private String securePass(String password) {
        return SecureUtil.md5(password + PASS_SALT);
    }

}