package com.yc.mappers;

/**
 * @Author zp
 * @Date 2023/8/17 14:56
 * @PackageName:com.yc.mappers
 * @ClassName: IBaseEnum
 * @Description:
 * @Version 1.0
 */

public interface IBaseEnum<K, V, T extends Enum<?>> {

    K getKey();

    V getValue();
}
