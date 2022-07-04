package com.JDBC.preparedstatement_;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * ��ʾPreparedStatementʹ��
 */
@SuppressWarnings({"all"})
public class PreparedStatement_ {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        //���û��������Ա�˺ź�����
        System.out.println("���������Ա�����֣�");
        String admin_name = scanner.nextLine();  //���ϣ������sqlע��������Ҫ��nextLine()
        System.out.println("���������Ա�����룺");
        String admin_pwd = scanner.nextLine();

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

        //3.�õ�prepareStatement
        //��֯sql���
        String sql = "select name, pwd from admin where name = ? and pwd = ?";  //sql���ģ��൱��ռλ��
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //������ֵ
        preparedStatement.setString(1,admin_name);
        preparedStatement.setString(2,admin_pwd);

        //����ִ��executeQuery������дsql
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){ //�����ѯ��һ����¼����˵���ù���Ա���ڣ����¼�ɹ�
            System.out.println("�ɹ�");
        }else{
            System.out.println("false");
        }

        //�ر�mysql
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
