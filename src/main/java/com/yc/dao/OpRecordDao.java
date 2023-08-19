package com.yc.dao;

import com.yc.bean.OpRecord;

import java.util.List;

/**
 * @Author zp
 * @Date 2023/8/3 9:30
 * @PackageName:com.yc.dao
 * @ClassName: OpRecordDao
 * @Description:
 * @Version 1.0
 */
public interface OpRecordDao {
    //设计日志的添加接口方法：TODO:参数
    public void insertOpRecord(OpRecord opRecord);

    //查询一个用户所有的日志，根据时间排序
    public List<OpRecord> findOpRecord(Integer accountid);

    //查询accountid用户 opType类型的操作 根据时间排序
    public List<OpRecord> findOpRecord(Integer accountid, String opRecord);

    //TODO:待开发，其他特殊查询
    public List<OpRecord> findOpRecord(OpRecord opRecord);
}