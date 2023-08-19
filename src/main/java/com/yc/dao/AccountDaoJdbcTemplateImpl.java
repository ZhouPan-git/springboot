package com.yc.dao;

import com.yc.bean.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * @Author zp
 * @Date 2023/8/3 10:07
 * @PackageName:com.yc.bean
 * @ClassName: AccountDaoJdbcImpl
 * @Description:
 * @Version 1.0
 */
@Repository
public class AccountDaoJdbcTemplateImpl implements AccountDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Integer insert(double money) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into accounts (balance) values (?)", new String[] { "accountid" });
            ps.setString(1, money+"");
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public void update(Integer accountid, double money) {
        this.jdbcTemplate.update(
                "update accounts set balance=? where accountid = ?",
                money, accountid);
    }

    @Override
    public void delete(Integer accountid) {
        this.jdbcTemplate.update(
                "delete from accounts where accountid = ?",
                Integer.valueOf(accountid));
    }

    @Override
    public Integer findCount() {
        Integer rowCount = this.jdbcTemplate.queryForObject("select count(*) from accounts ", Integer.class);
        return rowCount;
    }

    @Override
    public List<Account> findAll() {
        List<Account> list = this.jdbcTemplate.query(
                "select * from accounts",
                (resultSet, rowNum) -> {
                    Account a = new Account();
                    a.setAccountid(resultSet.getInt(1));
                    a.setBalance(resultSet.getDouble(2));
                    return a;
                });
        return list;
    }

    @Override
    public Account findById(Integer accountid) {
        //模版模式
        Account account = jdbcTemplate.queryForObject(
                "select * from accounts where accountid = ?",
                (resultSet, rowNum) -> {
                    Account a = new Account();
                    a.setAccountid(resultSet.getInt(1));
                    a.setBalance(resultSet.getInt(2));
                    return a;
                },
                accountid);
        return account;
    }

    public Integer calculateSumOfColumn(String columnName, String tableName) {
        String sql = "SELECT SUM(" + columnName + ") FROM " + tableName;
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }
}