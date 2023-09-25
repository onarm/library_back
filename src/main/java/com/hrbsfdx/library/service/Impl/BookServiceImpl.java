package com.hrbsfdx.library.service.Impl;

import cn.hutool.core.collection.CollUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.exception.ServiceException;
import com.hrbsfdx.library.mapper.BookMapper;
import com.hrbsfdx.library.mapper.EnterMapper;
import com.hrbsfdx.library.pojo.Book;
import com.hrbsfdx.library.pojo.Enter;
import com.hrbsfdx.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Resource
    BookMapper bookMapper;

    @Autowired
    EnterMapper enterMapper;

    @Override
    public List<Book> list() {
        return bookMapper.list();
    }

    @Override
    public PageInfo<Book> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        return new PageInfo<>(bookMapper.listByCondition(baseRequest));
    }

    //大立
    @Override
    public void save(Book obj) {
        try {
            List<String> categories = obj.getCategories();
            StringBuilder sb = new StringBuilder();
            if(CollUtil.isNotEmpty(categories)){
                categories.forEach(v -> sb.append(v).append(" >"));
            }
            obj.setCategory(sb.substring(0,sb.lastIndexOf(" >")));
            Enter enter = new Enter();
            enter.setName(obj.getName());
            enter.setAuthor(obj.getAuthor());
            enter.setSalary((double)obj.getScore());
            enter.setCount(obj.getNums());
            enterMapper.save(enter);
            bookMapper.save(obj);
        }catch (Exception e){
            throw new ServiceException("数据插入错误，可能是卡号重复");
        }
    }

    @Override
    public Book getById(Integer id) {
        return bookMapper.getById(id);
    }

    //大立
    @Override
    public void update(Book obj) {
        try {
            List<String> categories = obj.getCategories();
            StringBuilder sb = new StringBuilder();
            if(CollUtil.isNotEmpty(categories)){
                categories.forEach(v -> sb.append(v).append(" >"));
            }
            obj.setCategory(sb.substring(0,sb.lastIndexOf(" >")));
            obj.setUpdatetime(LocalDate.now());
            bookMapper.updateById(obj);
        }catch (Exception e){
            throw new ServiceException("数据更新错误，可能是卡号重复",e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        bookMapper.deleteById(id);
    }

}
