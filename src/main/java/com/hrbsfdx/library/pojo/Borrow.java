package com.hrbsfdx.library.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;
import java.util.Date;

@Alias("Borrow")
@Data
public class Borrow {
    /**
     * <pre>
     *
     * </pre>
     */
    private Integer	id;

    /**
     * <pre>
     * 图书名称
     * </pre>
     */
    private String	bookName;

    /**
     * <pre>
     * 图书标准码
     * </pre>
     */
    private String	bookNo;

    /**
     * <pre>
     * 用户id
     * </pre>
     */
    private String	userNo;

    /**
     * <pre>
     * 用户名称
     * </pre>
     */
    private String	userName;

    /**
     * <pre>
     * 用户联系方式
     * </pre>
     */
    private String	userPhone;

    /**
     * <pre>
     *
     * </pre>
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT + 8")
    private LocalDate createtime;

    /**
     * <pre>
     *
     * </pre>
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT + 8")
    private LocalDate updatetime;

    /**
     * <pre>
     * 借书积分
     * </pre>
     */
    private Integer	score;
    private Integer nums;
    private String status;
    private Integer days;
    private LocalDate returnDate;

    //提醒状态 即将到期 (-1)    已到期（当天）   已过期（超过归还日期之后）
    private String note;

}
