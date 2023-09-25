package com.hrbsfdx.library.config;

import lombok.Data;

//接口响应信息
@Data
public class Result {
    private static final String SUCCESS_CODE = "200";
    private static final String ERROR_CODE = "-1";

    private String code;
    private Object data;
    private String msg;

    public static Result success(){
        Result result = new Result();
        result.setCode("200");
        return result;
    }
    public static Result success(Object data){
        Result result = new Result();
        result.setCode("200");
        result.setData(data);
        return result;
    }
    public static Result error(String msg){
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        return result;
    }
    public static Result error(String code,String msg){
        Result result = new Result();
        result.setCode(ERROR_CODE);
        result.setMsg(msg);
        return result;
    }
}
