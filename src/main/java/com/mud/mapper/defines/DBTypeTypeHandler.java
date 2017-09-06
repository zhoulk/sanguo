package com.mud.mapper.defines;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by leeesven on 17/8/20.
 */
public class DBTypeTypeHandler extends BaseTypeHandler<DBType> {

    private Class<DBType> type;
    private final DBType[] enums;

    public DBTypeTypeHandler(Class<DBType> type){
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null)
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, DBType type, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, type.getValue());
    }

    @Override
    public DBType getNullableResult(ResultSet resultSet, String s) throws SQLException {
        int i = resultSet.getInt(s);

        if (resultSet.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位EnumStatus子类
            return locateEnumStatus(i);
        }
    }

    @Override
    public DBType getNullableResult(ResultSet resultSet, int i) throws SQLException {
        int value = resultSet.getInt(i);
        if (resultSet.wasNull()){
            return null;
        }else{
            return locateEnumStatus(value);
        }
    }

    @Override
    public DBType getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        int value = callableStatement.getInt(i);
        if (callableStatement.wasNull()){
            return null;
        }else{
            return locateEnumStatus(value);
        }
    }

    private DBType locateEnumStatus(int code) {
        for(DBType status : enums) {
            if(status.getValue() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + code + ",请核对" + type.getSimpleName());
    }
}