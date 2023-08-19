package com.yc.biz;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yc.bean.Account;
import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.mappers.AccountMapper;
import com.yc.mappers.OpRecordMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author zp
 * @Date 2023/8/2 14:15
 * @PackageName:com.yc.biz
 * @ClassName: AccountBizImpl
 * @Description:
 * @Version 1.0
 */
@Service
@Log4j2
@Transactional
public class AccountBizImpl implements AccountBiz {
//    @Autowired
//    private accountMapper accountMapper;
//    @Autowired
//    private opRecordMapper opRecordMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private OpRecordMapper opRecordMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<OpRecord> findOpRecord(String date) {
        List<OpRecord> list=new ArrayList<OpRecord>();
        //验证date格式是否正确
        DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Date d=null;
        try{
            d=df.parse(date);
        }catch (ParseException e){
            e.printStackTrace();
            log.error("日期格式不正确，原格式："+date+",要求的格式为：yyyy-MM-dd");
            throw new RuntimeException(e);
        }
        if (redisTemplate.hasKey(date)){
            list=redisTemplate.opsForList().range(date,0,redisTemplate.opsForList().size(date));
            log.info("从缓存的键："+date+",取出的list为："+list);
            return list;
        }
        QueryWrapper wrapper=new QueryWrapper();
        Calendar cal=Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DATE,1);
        Date nextDate=cal.getTime();
        String nextDateString=df.format(nextDate);
        wrapper.between("optime",date,nextDateString);
        list=opRecordMapper.selectList(wrapper);
        if (list!=null&&list.size()>0){
            redisTemplate.delete(date);
            redisTemplate.opsForList().leftPush(date,list);
            redisTemplate.expire(date,15, TimeUnit.DAYS);
        }
        return list;
    }

    @Override
    public Account openAccount(double money) {
        Account account = new Account();
        account.setBalance(money);
        this.accountMapper.insert(account);

        OpRecord opRecord = new OpRecord();
        opRecord.setAccountid(account.getAccountid());
        opRecord.setOpmoney(money);
        opRecord.setOptype(OpType.DEPOSITE);
        this.opRecordMapper.insert(opRecord);
        return account;
    }

    @Override
    public Account findAccount(Integer accountId) {
//        return this.accountMapper.findById(accountId);
        return this.accountMapper.selectById(accountId);
    }

    @Override
    public Account deposite(Integer accountId, double money) {
        return this.deposite(accountId,money,null);
    }

    @Override
    public Account deposite(Integer accountId, double money, Integer transferId) {
        Account a=null;
        try {
            //a = this.accountMapper.findById(accountId);
            a=this.accountMapper.selectById(accountId);
        }catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new RuntimeException("查无此账户："+accountId+"无法完成存款操作");
        }
        if (a==null) {
            throw new RuntimeException("查无此账户");
        }
        a.setBalance(a.getBalance()+money);
        //this.accountMapper.update(accountId,a.getBalance());
        this.accountMapper.updateById(a);
        OpRecord opRecord=new OpRecord();
        opRecord.setAccountid(accountId);
        opRecord.setOpmoney(money);
        if (transferId != null) {
            opRecord.setOptype(OpType.TRANSFER);
            opRecord.setTransferid(transferId);
        }else {
            opRecord.setOptype(OpType.WITHDRAW);
        }
        //this.opRecordMapper.insertOpRecord(opRecord);
        this.opRecordMapper.insert(opRecord);
        //this.accountMapper.update(accountId,a.getBalance());
        this.accountMapper.updateById(a);
        return a;
    }

    @Override
    public Account withdraw(Integer accountId, double money) {
        return this.withdraw(accountId, money,null);
    }

    @Override
    public Account withdraw(Integer accountId, double money, Integer transferId) {
        Account a=null;
        try {
            //a = this.accountMapper.findById(accountId);
            a=this.accountMapper.selectById(accountId);
        }catch (RuntimeException e) {
            log.error(e.getMessage());
            throw new RuntimeException("查无此账户："+accountId+"无法完成取款操作");
        }
        if (a==null) {
            throw new RuntimeException("查无此账户");
        }
        a.setBalance(a.getBalance()-money);
        //this.accountMapper.update(accountId,a.getBalance());
        this.accountMapper.updateById(a);
        OpRecord opRecord=new OpRecord();
        opRecord.setAccountid(accountId);
        opRecord.setOpmoney(money);
        if (transferId != null) {
            opRecord.setOptype(OpType.TRANSFER);
            opRecord.setTransferid(transferId);
        }else {
            opRecord.setOptype(OpType.WITHDRAW);
        }
//        this.opRecordMapper.insertOpRecord(opRecord);
//        this.accountMapper.update(accountId,a.getBalance());
        this.opRecordMapper.insert(opRecord);
        this.accountMapper.updateById(a);
        return a;
    }

    @Override
    public Account transfer(Integer accountId, double money, Integer toAccountId) {
        this.deposite(toAccountId, money, accountId);
        Account a = this.withdraw(accountId, money, toAccountId);
        return a;
    }

    @Override
    public Double findTotalBalance() {
        QueryWrapper<Account> wrapper = new QueryWrapper<>();
        wrapper.select("sum(balance) as total");
        List<Map<String, Object>> list = accountMapper.selectMaps(wrapper);
        if (list != null && list.size() > 0) {
            Map<String, Object> map = list.get(0);
            if (map.containsKey("total")) {
                Double totalBalance = Double.parseDouble(map.get("total").toString());
                return totalBalance;
            }
        }
        return 0.0;
    }

}