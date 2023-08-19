package com.yc;

import com.yc.bean.Account;
import com.yc.biz.AccountBiz;
import com.yc.config.Configs;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author zp
 * @Date 2023/8/3 19:52
 * @PackageName:com.yc.biz
 * @ClassName: AccountBizImplTest
 * @Description:
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Configs.class})
@Slf4j
@ActiveProfiles("init")
public class AccountBizImplTest extends TestCase {
    @Autowired
    private AccountBiz accountBiz;

    @Test
    public void openAccount() {
        Account a=accountBiz.openAccount(100);
        Assert.assertNotNull(a);
        log.info(a.toString());
    }

    @Test
    public void findAccount() {
        Account a=accountBiz.findAccount(5);
        Assert.assertNotNull(a);
        log.info(a.toString());
    }

    @Test
    public void deposite() {
        Account a=accountBiz.deposite(5,7);
        Assert.assertNotNull(a);
        log.info(a.toString());
    }

    @Test
    public void withdraw() {
        Account a=accountBiz.withdraw(1,1);
        Assert.assertNotNull(a);
        log.info(a.toString());
    }

    @Test
    public void transfer() {
        Account a=accountBiz.transfer(7,5,6);
        Assert.assertNotNull(a);
        log.info(a.toString());
    }
}