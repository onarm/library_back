package com.hrbsfdx.library.controller;


import cn.hutool.core.collection.CollUtil;
import com.hrbsfdx.library.config.Result;
import com.hrbsfdx.library.controller.request.CategoryPageRequest;
import com.hrbsfdx.library.pojo.Book;
import com.hrbsfdx.library.pojo.Category;
import com.hrbsfdx.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/save")
    public Result save(@RequestBody Category obj){
        categoryService.save(obj);
        return Result.success();
    }

    @PutMapping("/update")
    public Result update(@RequestBody Category obj){
        categoryService.update(obj);
        return Result.success();
    }

    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        categoryService.deleteById(id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Category admin = categoryService.getById(id);
        return Result.success(admin);
    }

    @GetMapping("/list")
    public Result list(){
        List<Category> admins = categoryService.list();
        return Result.success(admins);
    }
    @GetMapping("/tree")
    public Result tree(){
        List<Category> list = categoryService.list();
        return Result.success(createTree(null,list));
    }

    //实现递归树
    private List<Category> createTree(Integer pid, List<Category> categories) {
        List<Category> treeList = new ArrayList<>();
        for (Category category : categories) {
            if(pid == null){
                if(category.getPid() == null){
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
            } else{
                if (pid.equals(category.getPid())) {
                    treeList.add(category);
                    category.setChildren(createTree(category.getId(), categories));
                }
            }
            if(CollUtil.isEmpty(category.getChildren())){
                category.setChildren(null);
            }
        }
        return treeList;
    }

    @GetMapping("/page")
    public Result page(CategoryPageRequest categoryPageRequest){
        return Result.success(categoryService.page(categoryPageRequest));
    }

}