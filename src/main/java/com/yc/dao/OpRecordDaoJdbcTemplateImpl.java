package com.yc.dao;

import com.yc.bean.OpRecord;
import com.yc.bean.OpType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author zp
 * @Date 2023/8/3 10:09
 * @PackageName:com.yc.bean
 * @ClassName: OpRecordDaoJdbcImpl
 * @Description:
 * @Version 1.0
 */
@Repository
public class OpRecordDaoJdbcTemplateImpl implements OpRecordDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insertOpRecord(OpRecord opRecord) {
        String sql="insert into oprecord (accountid,opmoney,optime,optype,transferid) values (?,?,now(),?,?)";
        this.jdbcTemplate.update(sql, opRecord.getAccountid(), opRecord.getOpmoney(),
                                      opRecord.getOptype().getKey(), opRecord.getTransferid());
    }

    @Override
    public List<OpRecord> findOpRecord(Integer accountid) {
        List<OpRecord> list = this.jdbcTemplate.query(
                "select * from oprecord where accountid = ? order by optime desc",
                (resultSet, rowNum) -> {
                    OpRecord o = new OpRecord();
                    o.setId(resultSet.getInt(1));
                    o.setAccountid(resultSet.getInt(2));
                    o.setOpmoney(resultSet.getDouble(3));
                    o.setOptime(resultSet.getString(4));
                    String optype=resultSet.getString(5);
                    if (optype.equalsIgnoreCase("withdraw")){
                        o.setOptype(OpType.WITHDRAW);
                    }else if (optype.equalsIgnoreCase("deposite")){
                        o.setOptype(OpType.DEPOSITE);
                    }else {
                        o.setOptype(OpType.TRANSFER);
                    }
                    o.setTransferid(resultSet.getInt(6));
                    return o;
                },accountid);
        return list;
    }

    @Override
    public List<OpRecord> findOpRecord(Integer accountid, String opType) {
        List<OpRecord> list = this.jdbcTemplate.query(
                "select * from oprecord where accountid = ? and optype=? order by optime desc",
                (resultSet, rowNum) -> {
                    OpRecord o = new OpRecord();
                    o.setId(resultSet.getInt(1));
                    o.setAccountid(resultSet.getInt(2));
                    o.setOpmoney(resultSet.getDouble(3));
                    o.setOptime(resultSet.getString(4));
                    String optype=resultSet.getString(5);
                    if (optype.equalsIgnoreCase("withdraw")){
                        o.setOptype(OpType.WITHDRAW);
                    }else if (optype.equalsIgnoreCase("deposite")){
                        o.setOptype(OpType.DEPOSITE);
                    }else {
                        o.setOptype(OpType.TRANSFER);
                    }
                    o.setTransferid(resultSet.getInt(6));
                    return o;
                },accountid,opType);
        return list;
    }

    @Override
    public List<OpRecord> findOpRecord(OpRecord opRecord) {
        return null;
    }
}