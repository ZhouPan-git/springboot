package com.yc.bean;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author zp
 * @Date 2023/8/3 9:25
 * @PackageName:com.yc.bean
 * @ClassName: OpRecord
 * @Description:
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("oprecord")
public class OpRecord implements Serializable {
    @TableId(type= IdType.AUTO)
    //@ApiModelProperty(value = "操作id")
    private int id;;

    //@ApiModelProperty(value = "账户id")
    private Integer accountid;

    //@ApiModelProperty(value = "操作金额")
    private double opmoney;

    @TableField(fill = FieldFill.INSERT)
    //@ApiModelProperty(value = "操作时间")
    private String optime;  //数据库是datetime 在java中转为string

    //@ApiModelProperty(value = "操作类型")
    private OpType optype;//Optype 类型

    //@ApiModelProperty(value = "转账id")
    private Integer transferid;
}