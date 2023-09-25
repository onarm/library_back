package com.hrbsfdx.library.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;
import java.util.List;

@Alias("Category")
@Data
public class Category {
    private Integer id;
    private String name;
    private String remark;
    private Integer pid;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT + 8")
    private LocalDate createtime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT + 8")
    private LocalDate updatetime;

    private List<Category> children;
}