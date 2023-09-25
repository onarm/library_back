package com.hrbsfdx.library.controller;


import com.hrbsfdx.library.config.Result;
import com.hrbsfdx.library.controller.request.EnterPageRequest;
import com.hrbsfdx.library.pojo.Enter;
import com.hrbsfdx.library.service.EnterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/enter")
public class EnterController {
    @Autowired
    EnterService enterService;


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        enterService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Enter> rechargeList = enterService.list();
        return Result.success(rechargeList);
    }
    @GetMapping("/page")
    public Result page(EnterPageRequest enterPageRequest){
        return Result.success(enterService.page(enterPageRequest));
    }
}