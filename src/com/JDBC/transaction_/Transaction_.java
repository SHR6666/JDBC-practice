package com.JDBC.transaction_;

import com.JDBC.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 演示jdbc中如何使用事务
 */
@SuppressWarnings("all")
public class Transaction_ {
    public void noTransaction(){
        //操作转账的业务
        //得到连接
        Connection connection = null;  //默认自动提交
        //2.组织一个sql语句
        String sql = "update account set balance = balance - 100 where id = 1";
        String sql2 = "update account set balance = balance + 100 where id = 2";
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();  //在默认情况下，connection是默认自动提交
            preparedStatement = connection.prepareStatement(sql);  //执行第一条
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sql2);  //执行第二条
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }

    @Test
    //事务来解决
    public void useTransaction(){
        //操作转账的业务
        //得到连接
        Connection connection = null;  //默认自动提交
        //2.组织一个sql语句
        String sql = "update account set balance = balance - 100 where id = 1";
        String sql2 = "update account set balance = balance + 100 where id = 2";
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();  //在默认情况下，connection是默认自动提交
            //将connection设置为不自动提交
            connection.setAutoCommit(false); //不自动提交
            preparedStatement = connection.prepareStatement(sql);  //执行第一条
            preparedStatement.executeUpdate();
//            int i = 1/0; //抛出异常
            preparedStatement = connection.prepareStatement(sql2);  //执行第二条
            preparedStatement.executeUpdate();

            //这里提交事务
            connection.commit();
        } catch (SQLException e) {
            //这里可执行回滚，撤销执行的SQL
            System.out.println("执行发生了异常，撤销执行的SQL");
            try {
                //默认回滚到事务开始的状态
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }
}
