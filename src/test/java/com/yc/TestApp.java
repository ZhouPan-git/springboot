package com.yc;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author zp
 * @Date 2023/8/10 19:04
 * @PackageName:com.yc
 * @ClassName: TestApp
 * @Description:
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
@Slf4j
@ActiveProfiles("init")
public class TestApp {
    @Autowired
    private DataSource ds;

    @Test
    public void test() throws SQLException {
        Assert.assertNotNull(ds.getConnection());
        log.info(ds.getConnection().toString());
    }
}