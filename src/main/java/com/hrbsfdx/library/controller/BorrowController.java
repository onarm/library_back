package com.hrbsfdx.library.controller;

import com.hrbsfdx.library.config.Result;
import com.hrbsfdx.library.controller.request.BorrowPageRequest;
import com.hrbsfdx.library.pojo.Borrow;
import com.hrbsfdx.library.pojo.Ret;
import com.hrbsfdx.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    BorrowService borrowService;

    @PostMapping("/save")
    public Result save(@RequestBody Borrow obj){

        borrowService.save(obj);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Borrow obj){
        borrowService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        borrowService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Borrow admin = borrowService.getById(id);
        return Result.success(admin);
    }

    @GetMapping("/list")
    public Result list(){
        List<Borrow> admins = borrowService.list();
        return Result.success(admins);
    }

    @GetMapping("/page")
    public Result page(BorrowPageRequest borrowPageRequest){
        return Result.success(borrowService.page(borrowPageRequest));
    }

    @GetMapping("/pageRet")
    public Result pageRet(BorrowPageRequest borrowPageRequest){
        return Result.success(borrowService.pageRet(borrowPageRequest));
    }

    @PostMapping("/saveRet")
    public Result pageRet(@RequestBody Ret obj){
        borrowService.saveRet(obj);
        return Result.success();
    }

    @DeleteMapping("/deleteRet/{id}")
    public Result deleteRet(@PathVariable Integer id){
        borrowService.deleteRetById(id);
        return Result.success();
    }

    //week month halfYear year
    @GetMapping("/lineCharts/{timeRange}")
    public Result lineCharts(@PathVariable String timeRange){
        return Result.success(borrowService.getCountByTimeRange(timeRange));
    }
}