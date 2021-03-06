package cn.wolfcode.edu.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@ToString
public class ReceiptBill {
    private Long id;

    //学员
    private Client client;

    //收款金额
    private BigDecimal receiptmoney;

    //课程
    private CourseName courseName;

    //支付方式
    private PayMent payment;

    //单号
    private String billnum;

    //班级
    private Grade grade;

    //备注
    private String remark;

    //收款人
    private Employee receipter;

    //营销人员
    private Employee auditor;

    //审核人
    private Employee marketer;

    //总学费
    private BigDecimal totalmoney;

    //状态
    private int state;

    //校区
    private Campus campus;

    //收款时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date receipttime;

    //待付金额
    private BigDecimal unpaidmoney;
}