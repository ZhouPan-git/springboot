package com.yc.dao;

import com.yc.bean.Account;

import java.util.List;

/**
 * @Author zp
 * @Date 2023/8/3 9:30
 * @PackageName:com.yc.dao
 * @ClassName: AccountDao
 * @Description:
 * @Version 1.0
 */
public interface AccountDao {

    public Integer insert(double money);

    //都是加法 正数表示存，负数表示取
    public void update(Integer accountid,double money);

    public void delete(Integer accountid);

    public Integer findCount();

    public List<Account> findAll();

    public Account findById(Integer accountid);
}