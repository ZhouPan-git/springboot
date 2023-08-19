package com.yc.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author zp
 * @Date 2023/8/3 9:24
 * @PackageName:com.yc.bean
 * @ClassName: Account
 * @Description:
 * @Version 1.0
 */
//Serializable: 此类可以
@Data
@AllArgsConstructor  //带所有参
@NoArgsConstructor  //无参数
@ToString  //tostring（）
@TableName("accounts")
//@ApiModel("账户类")
public class Account implements Serializable {
    @TableId(type = IdType.AUTO)
    //@ApiModelProperty(value = "账户id")
    private Integer accountid;

    //@ApiModelProperty(value = "余额")
    private double balance;
}