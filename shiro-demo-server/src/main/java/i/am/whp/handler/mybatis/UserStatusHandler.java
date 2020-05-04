package i.am.whp.handler.mybatis;

import i.am.whp.enums.UserStatusEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserStatusHandler extends BaseTypeHandler<UserStatusEnum> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, UserStatusEnum UserStatusEnum, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, UserStatusEnum.getStatus());
    }

    @Override
    public UserStatusEnum getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return UserStatusEnum.valueOf(resultSet.getInt(s));
    }

    @Override
    public UserStatusEnum getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return UserStatusEnum.valueOf(resultSet.getInt(i));
    }

    @Override
    public UserStatusEnum getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return UserStatusEnum.valueOf(callableStatement.getInt(i));
    }
}
