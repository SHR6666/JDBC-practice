package com.JDBC.batch_;

import com.JDBC.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ��ʾjava��������
 */
public class Batch_ {
    @Test
    //��ͳ���������5000�����ݵ�admin2
    public void noBatch() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values (null,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("��ʼִ��");
        long start = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            preparedStatement.setString(1,"jack");
            preparedStatement.setString(2,"666");
            preparedStatement.executeUpdate();
        }
        long end = System.currentTimeMillis();
        System.out.println("��ͳ�ķ�����ʱ��"+ (end - start));
        //�ر�����
        JDBCUtils.close(null, preparedStatement, connection);
    }

    /**
     * ʹ��������ʽ�������
     */
    @Test
    public void batch() throws Exception {
        Connection connection = JDBCUtils.getConnection();
        String sql = "insert into admin2 values (null,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        System.out.println("��ʼִ��");
        long start = System.currentTimeMillis();
        for(int i = 0; i < 5000; i++){
            preparedStatement.setString(1,"jack");
            preparedStatement.setString(2,"666");
            //��sql�����뵽�����������
            preparedStatement.addBatch();
            //����1000����¼ʱ����ִ��
            if ((i + 1) % 1000 == 0){
                preparedStatement.executeBatch();
                //���һ��
                preparedStatement.clearBatch();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("������ʽ ��ʱ��"+ (end - start));
        //�ر�����
        JDBCUtils.close(null, preparedStatement, connection);
    }
}
