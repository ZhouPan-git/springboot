package com.yc;

import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import com.yc.dao.OpRecordDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author zp
 * @Date 2023/8/3 11:44
 * @PackageName:com.yc
 * @ClassName: OpRecordDaoJdbcImplTest
 * @Description:
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AppMain.class})
@Slf4j
public class OpRecordDaoJdbcTemplateImplTest {
    @Autowired
    private OpRecordDao opRecordDao;

    @Test
    public void insertOpRecord(){
        OpRecord opRecord=new OpRecord();
        opRecord.setAccountid(6);
        opRecord.setOpmoney(5);
        opRecord.setOptype(OpType.DEPOSITE);
        this.opRecordDao.insertOpRecord(opRecord);
    }

    @Test
    public void findOpRecord(){
        List<OpRecord> list=this.opRecordDao.findOpRecord(9);
        System.out.println(list);
    }

    @Test
    public void testFindOpRecord(){
        List<OpRecord> list=this.opRecordDao.findOpRecord(6,"DEPOSITE");
        System.out.println(list);
    }

    @Test
    public void testFindOpRecord1(){
    }
}