
package com.hrbsfdx.library.controller.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PenaltyPageRequest extends BaseRequest {
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