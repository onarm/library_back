package com.hrbsfdx.library.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Penalty")
@Data
public class Penalty {
    private Integer id;
    private String name;
    private String username;
    private String phone;
    private Double previousBalance;
    private Double penalty;
    private Double afterBalance;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT + 8")
    private Date createtime;
}
