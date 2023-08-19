package com.yc.controller;

import com.yc.bean.Account;
import com.yc.bean.OpRecord;
import com.yc.biz.AccountBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zp
 * @Date 2023/8/10 21:09
 * @PackageName:com.yc.controller
 * @ClassName: BankController
 * @Description:
 * @Version 1.0
 */
@RestController
@Api(tags = "银行存取款操作类")
@Slf4j
public class BankController {
    @Autowired
    private AccountBiz accountBiz;

    @GetMapping("/findOpRecord")
    @ApiOperation(value = "查询指定日期的日志")
    public Map findOpRecord(String date) {
        Map result=new HashMap();
        try{
            List<OpRecord> list=this.accountBiz.findOpRecord(date);
            result.put("code",1);
            result.put("data",list);
        }catch (RuntimeException e){
            result.put("code",0);
            result.put("errMsg",e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(value = "开户")
    //@ApiImplicitParam(name = "pid", value = "产品id", paramType = "String")
    @PostMapping("/openAccount")
    public Account openAccount(
            @RequestParam(name="money") double money){
        return accountBiz.openAccount(money);
    }

    @ApiOperation(value = "存")
    @PostMapping("/deposite")
    public Account deposite(
            @RequestParam(name = "accountId") Integer accountId,@RequestParam(name="money") double money){
        return accountBiz.deposite(accountId,money);
    }

    @ApiOperation(value = "取")
    @PostMapping("/withdraw")
    public Account withdraw(
            @RequestParam(name = "accountId") Integer accountId,@RequestParam(name="money") double money){
        return accountBiz.withdraw(accountId,money);
    }

    @ApiOperation(value = "转账")
    @ApiImplicitParams({
            @ApiImplicitParam(name="accountId",value = "转出账户",required = true),
            @ApiImplicitParam(name="money",value = "操作金额",required = true),
            @ApiImplicitParam(name="toAccountId",value = "转入账户",required = true)
    })
    @PostMapping("/transfer")
    public Account transfer( Integer accountId,double money, Integer toAccountId){
        return accountBiz.transfer(accountId,money,toAccountId);
    }

    @ApiOperation(value = "查")
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST},value = "/findAccount")
    public Account findAccount(@RequestParam(name = "accountId") Integer accountId){
        return accountBiz.findAccount(accountId);
    }
}