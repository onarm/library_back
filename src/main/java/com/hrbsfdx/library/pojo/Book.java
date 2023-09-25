package com.hrbsfdx.library.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;
import java.util.List;

@Data
@Alias("Book")
public class Book {
    private Integer	id;
    private String	name;
    private String	description;
    private String	publishDate;
    private String	author;
    private String	publisher;
    private String	category;
    private String	bookNo;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT + 8")
    private LocalDate createtime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT + 8")
    private LocalDate updatetime;
    private String	cover;
    private Integer nums;

    private List<String> categories;
    private Integer score;
}
