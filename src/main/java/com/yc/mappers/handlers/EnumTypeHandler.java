package com.yc.mappers.handlers;

import com.yc.bean.OpType;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(OpType.class)
public class EnumTypeHandler extends BaseTypeHandler<OpType> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, OpType parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, (String) parameter.getKey());
    }

    @Override
    public OpType getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String value = rs.getString(columnName);
        return OpType.valueOf(value.toUpperCase()); // Convert to uppercase before mapping
    }

    @Override
    public OpType getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String value = rs.getString(columnIndex);
        return OpType.valueOf(value.toUpperCase()); // Convert to uppercase before mapping
    }

    @Override
    public OpType getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String value = cs.getString(columnIndex);
        return OpType.valueOf(value.toUpperCase()); // Convert to uppercase before mapping
    }
}
