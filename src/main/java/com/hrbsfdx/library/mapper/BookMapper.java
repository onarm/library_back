package com.hrbsfdx.library.mapper;

import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.pojo.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    List<Book> list();

    List<Book> listByCondition(BaseRequest baseRequest);

    void save(Book obj);

    Book getById(Integer id);

    void updateById(Book obj);

    void deleteById(Integer id);

    Book getByNo(String bookNo);

    void updateNumByNo(String bookNo);
}