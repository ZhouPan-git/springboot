package com.yc.bean;


import com.baomidou.mybatisplus.annotation.EnumValue;
import com.yc.mappers.IBaseEnum;

/**
 * @Author zp
 * @Date 2023/8/3 9:25
 * @PackageName:com.yc.bean
 * @ClassName: OpType
 * @Description:
 * @Version 1.0
 */
//enum: 枚举 enumration
public enum OpType implements IBaseEnum {
    WITHDRAW("withdraw",1),DEPOSITE("deposite",2),TRANSFER("transfer",3);
    //@ApiModelProperty(value = "操作名称")
    private String key;

    //@ApiModelProperty(value = "操作id")
    @EnumValue
    private int value;

    OpType(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public Object getKey() {
        return this.key;
    }
}