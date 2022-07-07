package com.JDBC.utils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ��ʾ���ʹ��JDBCUtils�����࣬���dml��select
 */
public class JDBCUtils_Use {
    public static void main(String[] args) {

    }
    @Test
    public void testDML() { //insert update,delete
        //�õ�����
        Connection connection = null;
        //2.��֯һ��sql���
        String sql = "update actor set name = ? where id = ?";
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //��ռλ����ֵ
            preparedStatement.setString(1,"��С��ͯ");
            preparedStatement.setInt(2, 5);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //�ر���Դ
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }

    /**
     * ��������
     */
    @Test
    public void insertTest(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql =  "insert into actor (NAME, sex, borndate, phone) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "�Ծ���");
            preparedStatement.setString(2,"Ů");
            preparedStatement.setString(3,"1983-11-11");
            preparedStatement.setString(4,"1000000");
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //�ر���Դ
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }

    /**
     * PreparedStatementɾ������
     */
    @Test
    public void deleteTest(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete FROM actor WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,8);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //�ر���Դ
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }
}
