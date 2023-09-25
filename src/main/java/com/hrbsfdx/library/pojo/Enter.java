package com.hrbsfdx.library.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@Alias("Enter")
@Data
public class Enter {
    private Integer id;
    private String name;
    private String author;
    private Double salary;
    private Integer count;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT + 8")
    private Date createtime;
}
