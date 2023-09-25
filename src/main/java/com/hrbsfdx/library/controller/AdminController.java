package com.hrbsfdx.library.controller;


import com.hrbsfdx.library.controller.dto.LoginDTO;
import com.hrbsfdx.library.controller.request.AdminPageRequest;
import com.hrbsfdx.library.controller.request.LoginRequest;
import com.hrbsfdx.library.controller.request.PasswordRequest;
import com.hrbsfdx.library.pojo.Admin;
import com.hrbsfdx.library.service.AdminService;
import com.hrbsfdx.library.config.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @PostMapping("/login")
    public Result login(@RequestBody LoginRequest request){
        LoginDTO login = adminService.login(request);
        return Result.success(login);
    }

    @PutMapping("/password")
    public Result password(@RequestBody PasswordRequest request){
        adminService.changePass(request);
        return Result.success();
    }

    @PostMapping("/save")
    public Result save(@RequestBody Admin obj){
        adminService.save(obj);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Admin obj){
        adminService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        adminService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Admin admin = adminService.getById(id);
        return Result.success(admin);
    }

    @GetMapping("/list")
    public Result list(){
        List<Admin> admins = adminService.list();
        return Result.success(admins);
    }

    @GetMapping("/page")
    public Result page(AdminPageRequest adminPageRequest){
        return Result.success(adminService.page(adminPageRequest));
    }
}