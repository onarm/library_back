
package com.hrbsfdx.library.controller.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RechargePageRequest extends BaseRequest {
    private Integer id;
    private String username;
    private Double previousBalance;
    private Double afterBalance;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT + 8")
    private Date createtime;
}