package com.yc.biz;

import com.yc.bean.Account;
import com.yc.bean.OpRecord;

import java.util.List;

/**
 * @Author zp
 * @Date 2023/8/2 14:15
 * @PackageName:com.yc.biz
 * @ClassName: AccountBiz
 * @Description:
 * @Version 1.0
 */
public interface AccountBiz {

    public List<OpRecord> findOpRecord(String date);

    public Account openAccount(double money);

    public Account findAccount(Integer accountId);

    public Account deposite(Integer accountId, double money);

    public Account deposite(Integer accountId, double money, Integer transferId);

    public Account withdraw(Integer accountId, double money);

    public Account withdraw(Integer accountId, double money, Integer transferId);

    public Account transfer(Integer accountId, double money, Integer toAccountId);

    public Double findTotalBalance();
}