package com.JDBC.dateSource;

import com.JDBC.utils.JDBCUtils;

import java.sql.Connection;

public class ConQuestion {
    //代码，连接mysql  5000次
    public void testCon(){
        for (int i = 0; i < 5000; i++){
            //使用传统的JDBC来连接
            Connection connection = JDBCUtils.getConnection();
            //得到PreparedStatement，发送sql

            //关闭
            JDBCUtils.close(null,null, connection);
        }
    }
}
