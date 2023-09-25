package com.hrbsfdx.library.controller;


import com.hrbsfdx.library.controller.request.UserPageRequest;
import com.hrbsfdx.library.pojo.User;
import com.hrbsfdx.library.service.UserService;
import com.hrbsfdx.library.config.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/save")
    public Result save(@RequestBody User user){
        userService.save(user);
        return Result.success();
    }

    @PostMapping("/account")
    public Result account(@RequestBody User user){
        userService.handleAccount(user);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user){

        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        userService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        User user = userService.getById(id);
        return Result.success(user);
    }

    @GetMapping("/list")
    public Result list(){
        List<User> users = userService.list();
        return Result.success(users);
    }

    @GetMapping("/page")
    public Result page(UserPageRequest userPageRequest){
        return Result.success(userService.page(userPageRequest));
    }
}