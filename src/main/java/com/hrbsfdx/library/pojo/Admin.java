package com.hrbsfdx.library.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Admin")
@Data
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT + 8")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT + 8")
    private Date updatetime;
    private boolean status;
}