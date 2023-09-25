
package com.hrbsfdx.library.controller.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class EnterPageRequest extends BaseRequest {
    private Integer id;
    private String name;
    private String author;
    private Double salary;
    private Integer count;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT + 8")
    private Date createtime;
}