package wq.handler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class TestHandler extends BaseTypeHandler<Date> {

    @Override
    /*
    将java的数据类型转换为数据库字段所需的类型
     */
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Date date, JdbcType jdbcType) throws SQLException {
        //将Date类型转成数据库里的bigint
        Long time=date.getTime();
        preparedStatement.setLong(i,time);
//        Long.valueOf(time);
        //将Date 类型转换成数据库里的varchar
//        String time2=String.valueOf(date.getTime());
//        preparedStatement.setString(i,time2);
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, String s) throws SQLException {
        long aLong=resultSet.getLong(s);
        Date date=new Date(aLong);
        return date;
    }

    @Override
    public Date getNullableResult(ResultSet resultSet, int i) throws SQLException {
        long aLong=resultSet.getLong(i);
        Date date=new Date(aLong);
        return date;
    }

    @Override
    public Date getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        long aLong=callableStatement.getLong(i);
        Date date=new Date(aLong);
        return date;
    }
}
