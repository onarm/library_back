package com.hrbsfdx.library.controller;


import com.hrbsfdx.library.config.Result;
import com.hrbsfdx.library.controller.request.PenaltyPageRequest;
import com.hrbsfdx.library.controller.request.RechargePageRequest;
import com.hrbsfdx.library.pojo.Penalty;
import com.hrbsfdx.library.service.PenaltyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/penalty")
public class PenaltyController {
    @Autowired
    PenaltyService penaltyService;


    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        penaltyService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list(){
        List<Penalty> rechargeList = penaltyService.list();
        return Result.success(rechargeList);
    }
    @GetMapping("/page")
    public Result page(PenaltyPageRequest penaltyPageRequest){
        return Result.success(penaltyService.page(penaltyPageRequest));
    }
}