package com.JDBC.dateSource;

import com.JDBC.utils.JDBCUtils;

import java.sql.Connection;

public class ConQuestion {
    //���룬����mysql  5000��
    public void testCon(){
        for (int i = 0; i < 5000; i++){
            //ʹ�ô�ͳ��JDBC������
            Connection connection = JDBCUtils.getConnection();
            //�õ�PreparedStatement������sql

            //�ر�
            JDBCUtils.close(null,null, connection);
        }
    }
}
