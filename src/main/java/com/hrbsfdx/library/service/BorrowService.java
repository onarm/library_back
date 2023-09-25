package com.hrbsfdx.library.service;

import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.pojo.Borrow;
import com.hrbsfdx.library.pojo.Ret;

import java.util.List;
import java.util.Map;

public interface BorrowService {
    
    List<Borrow> list();

    PageInfo<Borrow> page(BaseRequest baseRequest);

    void save(Borrow obj);

    PageInfo<Ret> pageRet(BaseRequest baseRequest);

    void saveRet(Ret obj);

    Borrow getById(Integer id);

    void update(Borrow obj);

    void deleteById(Integer id);

    void deleteRetById(Integer id);

    Map<String, Object> getCountByTimeRange(String timeRange);

}
