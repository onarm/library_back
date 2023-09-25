package com.hrbsfdx.library.mapper;

import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.pojo.Borrow;
import com.hrbsfdx.library.pojo.BorrowRetCountPO;
import com.hrbsfdx.library.pojo.Ret;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BorrowMapper {

    List<Borrow> list();

    List<Borrow> listByCondition(BaseRequest baseRequest);

    List<Ret> listRetByCondition(BaseRequest baseRequest);

    void save(Borrow obj);

    void saveRet(Ret obj);

    Borrow getById(Integer id);

    void updateById(Borrow obj);

    void deleteById(Integer id);

    void deleteRetById(Integer id);

    void updateStatus(String status, Integer id);

    List<BorrowRetCountPO> getCountByTimeRange(@Param("timeRange") String timeRange, @Param("type") int type);
}