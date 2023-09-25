package com.hrbsfdx.library.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("BorrowRetCountPO")
@Data
public class BorrowRetCountPO {
    private String date;
    private Integer count;
}
