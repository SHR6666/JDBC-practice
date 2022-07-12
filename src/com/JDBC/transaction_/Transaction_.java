package com.JDBC.transaction_;

import com.JDBC.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * ��ʾjdbc�����ʹ������
 */
@SuppressWarnings("all")
public class Transaction_ {
    public void noTransaction(){
        //����ת�˵�ҵ��
        //�õ�����
        Connection connection = null;  //Ĭ���Զ��ύ
        //2.��֯һ��sql���
        String sql = "update account set balance = balance - 100 where id = 1";
        String sql2 = "update account set balance = balance + 100 where id = 2";
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();  //��Ĭ������£�connection��Ĭ���Զ��ύ
            preparedStatement = connection.prepareStatement(sql);  //ִ�е�һ��
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(sql2);  //ִ�еڶ���
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //�ر���Դ
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }

    @Test
    //���������
    public void useTransaction(){
        //����ת�˵�ҵ��
        //�õ�����
        Connection connection = null;  //Ĭ���Զ��ύ
        //2.��֯һ��sql���
        String sql = "update account set balance = balance - 100 where id = 1";
        String sql2 = "update account set balance = balance + 100 where id = 2";
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();  //��Ĭ������£�connection��Ĭ���Զ��ύ
            //��connection����Ϊ���Զ��ύ
            connection.setAutoCommit(false); //���Զ��ύ
            preparedStatement = connection.prepareStatement(sql);  //ִ�е�һ��
            preparedStatement.executeUpdate();
//            int i = 1/0; //�׳��쳣
            preparedStatement = connection.prepareStatement(sql2);  //ִ�еڶ���
            preparedStatement.executeUpdate();

            //�����ύ����
            connection.commit();
        } catch (SQLException e) {
            //�����ִ�лع�������ִ�е�SQL
            System.out.println("ִ�з������쳣������ִ�е�SQL");
            try {
                //Ĭ�ϻع�������ʼ��״̬
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            //�ر���Դ
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }
}
