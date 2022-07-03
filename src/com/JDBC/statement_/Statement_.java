package com.JDBC.statement_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

/**
 * ��ʾstatement ��ע������
 */
@SuppressWarnings("all")
public class Statement_ {
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
        //3.�õ�statement
        Statement statement = connection.createStatement();//����ִ�о�̬sql��䲢���������ɵĽ������
        //4.��֯sql���
        String sql = "select name, pwd from admin where name = '"+admin_name+"' and pwd = '"+admin_pwd+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()){ //�����ѯ��һ����¼����˵���ù���Ա���ڣ����¼�ɹ�
            System.out.println("�ɹ�");
        }else{
            System.out.println("false");
        }

        //�ر�mysql
        resultSet.close();
        statement.close();
        connection.close();
    }
}
