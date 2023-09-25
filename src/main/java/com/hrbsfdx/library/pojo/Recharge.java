package com.hrbsfdx.library.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Recharge")
@Data
public class Recharge {
    private Integer id;
    private String username;
    private Double previousBalance;
    private Double afterBalance;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT + 8")
    private Date createtime;
}
