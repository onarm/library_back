package com.hrbsfdx.library.controller;


import com.hrbsfdx.library.config.Result;
import com.hrbsfdx.library.controller.request.RechargePageRequest;
import com.hrbsfdx.library.controller.request.UserPageRequest;
import com.hrbsfdx.library.pojo.Recharge;
import com.hrbsfdx.library.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/recharge")
public class RechargeController {
    @Autowired
    RechargeService rechargeService;


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        rechargeService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Recharge> rechargeList = rechargeService.list();
        return Result.success(rechargeList);
    }
    @GetMapping("/page")
    public Result page(RechargePageRequest rechargePageRequest){
        return Result.success(rechargeService.page(rechargePageRequest));
    }
}