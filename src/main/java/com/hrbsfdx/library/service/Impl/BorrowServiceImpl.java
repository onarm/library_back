package com.hrbsfdx.library.service.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hrbsfdx.library.controller.request.BaseRequest;
import com.hrbsfdx.library.exception.ServiceException;
import com.hrbsfdx.library.mapper.BookMapper;
import com.hrbsfdx.library.mapper.BorrowMapper;
import com.hrbsfdx.library.mapper.PenaltyMapper;
import com.hrbsfdx.library.mapper.UserMapper;
import com.hrbsfdx.library.pojo.*;
import com.hrbsfdx.library.service.BorrowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Service
@Slf4j
public class BorrowServiceImpl implements BorrowService {
    @Resource
    BorrowMapper borrowMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PenaltyMapper penaltyMapper;

    @Autowired
    BookMapper bookMapper;

    @Override
    public List<Borrow> list() {
        return borrowMapper.list();
    }

    //尹琦惠
    @Override
    public PageInfo<Borrow> page(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Borrow> borrows = borrowMapper.listByCondition(baseRequest);
        for (Borrow b : borrows){
            LocalDate returnDate = b.getReturnDate();
            LocalDate now = LocalDate.now();
            if(now.plusDays(1).isEqual(returnDate)){
                b.setNote("即将到期");
            } else if(now.isEqual(returnDate)) {
                b.setNote("已到期");
            } else if(now.isAfter(returnDate)){
                b.setNote("已过期");
            }else {
                b.setNote("正常");
            }
        }
        return new PageInfo<>(borrows);
    }

    //李辉叶
    @Override
    public void save(Borrow obj) {
        //校验
        String userNo = obj.getUserNo();
        User user = userMapper.getByNo(userNo);
        if(Objects.isNull(user)) {
            throw new ServiceException("用户不存在");
        }
        Book book = bookMapper.getByNo(obj.getBookNo());
        if(Objects.isNull(user)) {
            throw new ServiceException("所借图书不存在");
        }
        if(book.getNums() < 1){
            throw new ServiceException("图书数量不足");
        }
        Double account = user.getAccount();
        Integer score = book.getScore() * obj.getDays();
        if(score > account){
            throw new ServiceException("用户余额不足");
        }

        user.setAccount(user.getAccount() - score);
        userMapper.updateById(user);

        book.setNums(book.getNums() - 1);
        bookMapper.updateById(book);

        obj.setReturnDate(LocalDate.now().plus(obj.getDays(), ChronoUnit.DAYS)); // 当前的日期 + days 得到 归还的日期
        obj.setScore(score);
        borrowMapper.save(obj);
    }

    @Override
    public PageInfo<Ret> pageRet(BaseRequest baseRequest) {
        PageHelper.startPage(baseRequest.getPageNum(), baseRequest.getPageSize());
        List<Ret> borrows = borrowMapper.listRetByCondition(baseRequest);
        return new PageInfo<>(borrows);
    }

    //李辉叶
    @Transactional
    @Override
    public void saveRet(Ret obj) {
        obj.setStatus("已归还");
        borrowMapper.updateStatus("已归还",obj.getId());
        obj.setRealDate(LocalDate.now());
        borrowMapper.saveRet(obj);
        bookMapper.updateNumByNo(obj.getBookNo());

        Book book = bookMapper.getByNo(obj.getBookNo());

        if(book != null){
            long until = 0;
            if(obj.getRealDate().isAfter(obj.getReturnDate())) { // 逾期归还 要扣钱
                until = -obj.getReturnDate().until(obj.getRealDate(), ChronoUnit.DAYS);
            }
            double score = (double) until * book.getScore();
            if(score < 0){
                User user = userMapper.getByNo(obj.getUserNo());
                Penalty penalty = new Penalty();
                penalty.setName(user.getName());
                penalty.setUsername(user.getUsername());
                penalty.setPhone(user.getPhone());
                penalty.setPreviousBalance(user.getAccount());
                penalty.setPenalty(score);
                double account = user.getAccount() + score;
                penalty.setAfterBalance(account);
                if(account < 0){
                    user.setAccount(account);
                    user.setStatus(false);
                }
                penaltyMapper.save(penalty);
                userMapper.updateById(user);
            }
        }
    }

    @Override
    public Borrow getById(Integer id) {
        return borrowMapper.getById(id);
    }

    @Override
    public void update(Borrow obj) {
        obj.setUpdatetime(LocalDate.now());
        borrowMapper.updateById(obj);
    }

    @Override
    public void deleteById(Integer id) {
        borrowMapper.deleteById(id);
    }

    @Override
    public void deleteRetById(Integer id) {
        borrowMapper.deleteRetById(id);
    }


    //袁博
    @Override
    public Map<String, Object> getCountByTimeRange(String timeRange) {
        Map<String, Object> map = new HashMap<>();
        Date today = new Date();
        List<DateTime> dateRange;
        switch (timeRange){
            case "week":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today,-6),today, DateField.DAY_OF_WEEK);
                break;
            case "month":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today,-29),today, DateField.DAY_OF_MONTH);
                break;
            case "halfYear":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today,-179),today, DateField.DAY_OF_MONTH);
                break;
            case "year":
                dateRange = DateUtil.rangeToList(DateUtil.offsetDay(today,-359),today, DateField.DAY_OF_YEAR);
                break;
            default:
                dateRange = new ArrayList<>();
        }
        List<String> dateStrRange = datetimeToDateStr(dateRange);
        map.put("date", dateStrRange);

        List<BorrowRetCountPO> borrowCount = borrowMapper.getCountByTimeRange(timeRange, 1);
        List<BorrowRetCountPO> retCount = borrowMapper.getCountByTimeRange(timeRange, 2);
        map.put("borrow",countList(borrowCount, dateStrRange));
        map.put("ret",countList(retCount, dateStrRange));
        return map;
    }

    private List<Integer> countList(List<BorrowRetCountPO> countPOList, List<String> dateRange) {
        List<Integer> list = CollUtil.newArrayList();
        if(CollUtil.isEmpty(countPOList)){
            return list;
        }
        for(String date : dateRange){
            Integer count = countPOList.stream().filter(countPO -> date.equals(countPO.getDate()))
                    .map(BorrowRetCountPO::getCount).findFirst().orElse(0);
            list.add(count);
        }
        return list;
    }

    //把dateTime 类型 转换成String类型
    private List<String> datetimeToDateStr(List<DateTime> dateTimeList) {
        List<String> list = CollUtil.newArrayList();
        if(CollUtil.isEmpty(dateTimeList)){
            return list;
        }
        for(DateTime dateTime : dateTimeList){
            String date = DateUtil.formatDate(dateTime);
            list.add(date);
        }
        return list;
    }
}
