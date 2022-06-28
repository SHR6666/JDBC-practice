package com.JDBC;


import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * ���ǵ�һ��jdbc������ɼ򵥲���
 */
public class Jdbc01 {
    public static void main(String[] args) throws SQLException{
        //1.ע������
        Driver driver = new com.mysql.cj.jdbc.Driver();

        //2.�õ�����
        //�����
        //(1)�̶�Э��jdbc:mysql:����
        String url = "jdbc:mysql://127.0.0.1:3306/query";
        //���û���������ŵ�Properties��
        Properties properties = new Properties();
        //˵�������user��password�ǹ涨�õ�
        properties.setProperty("user", "root"); //�û�
        properties.setProperty("password", "123456"); //����

        Connection connect = driver.connect(url, properties);
        //3.ִ��sql���
//        String sql = "insert into actor value(null, '���»�', '��', '1970-11-11', '110')";
        //�޸�
//        String sql = "update actor set NAME ='���ǳ�' where id = '1'";
        //ɾ��
        String sql = "delete FROM actor WHERE id = '1'";

        Statement statement = connect.createStatement(); //����ִ�о�̬sql��䲢���������ɵĽ������
        int rows = statement.executeUpdate(sql); //�����dml��䣬���صľ���Ӱ�������

        System.out.println(rows > 0 ? "�ɹ�": "ʧ��");
        //4.�ر�������Դ
        statement.close();
        connect.close();
    }
}
