package com.JDBC.resultset_;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * ��ʾselect��䷵�� ResultSet, ��ȡ�����
 */
@SuppressWarnings("all")
public class ResultSet {
    public static void main(String[] args) throws Exception {
        //ͨ��properties�����ȡ����ļ���������Ϣ
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\JavaProject\\JDBC\\src\\mysql.properties"));
        //��ȡ��ص�ֵ
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        //1.ע������
        Class.forName(driver); //����д��
        //�õ�����
        Connection connection = DriverManager.getConnection(url, user, password);
        //3.�õ�statement
        Statement statement = connection.createStatement();//����ִ�о�̬sql��䲢���������ɵĽ������
        //4.��֯sql���
        String sql = "select id, NAME, sex, borndate from actor";
        //ִ�и�����sql��䣬����䷵�ص���ResultSet����
        /**
         * +----+--------+-----+---------------------+-------+
         * | id | NAME   | sex | borndate            | phone |
         * +----+--------+-----+---------------------+-------+
         * |  3 | С��   | ��  | 2000-10-30 00:00:00 | 155   |
         * |  5 | С��   | Ů  | 2000-10-30 00:00:00 | 155   |
         * |  6 | ����   | ��  | 2000-10-30 00:00:00 | 155   |
         * |  7 | ���»� | ��  | 1970-12-12 00:00:00 | 110   |
         * |  8 | jack   | ��  | 1990-11-11 00:00:00 | 112   |
         * +----+--------+-----+---------------------+-------+
         */
        java.sql.ResultSet resultSet = statement.executeQuery(sql);

        //5.ʹ��whileȡ������
        while (resultSet.next()) {  //�ù������ƶ�
            int id = resultSet.getInt(1); //��ȡ���еĵ�һ������
            //int id1 = resultSet.getInt("id"); //ͨ����������ȡֵ
            String name = resultSet.getString(2);  //��ȡ���еĵڶ���
            String sex = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            System.out.println(id + "\t" + name + "\t"+ sex + "\t" + date);
        }

        //6.�ر�����
        resultSet.close();
        statement.close();
        connection.close();
    }
}
