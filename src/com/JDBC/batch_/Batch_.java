package com.JDBC.batch_;

import com.JDBC.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 演示java的批处理
 */
public class Batch_ {
    @Test
    //传统方法，添加5000条数据到admin2
    public void noBatch() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values (null,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("开始执行");
        long start = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            preparedStatement.setString(1,"jack");
            preparedStatement.setString(2,"666");
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("传统的方法耗时："+ (end - start));
        //关闭连接
        JDBCUtils.close(null, preparedStatement, connection);
    }

    /**
     * 使用批量方式添加数据
     */
    @Test
    public void batch() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values (null,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("开始执行");
        long start = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            preparedStatement.setString(1,"jack");
            preparedStatement.setString(2,"666");
            //将sql语句加入到批量处理包中
            preparedStatement.addBatch();
            //当有1000条记录时批量执行
            if ((i + 1) % 1000 == 0){
                preparedStatement.executeBatch();
                //清空一把
                preparedStatement.clearBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("批量方式 耗时："+ (end - start));
        //关闭连接
        JDBCUtils.close(null, preparedStatement, connection);
    }
}
