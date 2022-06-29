package com.JDBC;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ����java����sql�����ַ�ʽ
 */
public class JdbcConn {
    @Test
    //��ʽ1
    public void connect01() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();

        //(1)�̶�Э��jdbc:mysql:����
        String url = "jdbc:mysql://127.0.0.1:3306/query";
        //���û���������ŵ�Properties��
        Properties properties = new Properties();
        //˵�������user��password�ǹ涨�õ�
        properties.setProperty("user", "root"); //�û�
        properties.setProperty("password", "123456"); //����
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    //��ʽ2
    @Test
    public void connect02() throws Exception {
        //ʹ�÷������Driver�࣬��̬�ļ��ظ�������������
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        //(1)�̶�Э��jdbc:mysql:����
        String url = "jdbc:mysql://127.0.0.1:3306/query";
        //���û���������ŵ�Properties��
        Properties properties = new Properties();
        //˵�������user��password�ǹ涨�õ�
        properties.setProperty("user", "root"); //�û�
        properties.setProperty("password", "123456"); //����
        Connection connect = driver.connect(url, properties);
        System.out.println("��ʽ��" + connect);
    }

    //��ʽ3  ʹ��DriverManger���Driver����ͳһ����
    @Test
    public void connect03() throws Exception {

        //ʹ�÷������Driver
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        //����url ��user �� password
        String url = "jdbc:mysql://127.0.0.1:3306/query";
        String user = "root";
        String password = "123456";

        DriverManager.registerDriver(driver);  //ע��Driver����

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("��ʽ��" + connection);
    }

    //����4��ʹ��Class.forName �Զ����ע���������򻯴���
    //���ַ�ʽ��ȡ������ʹ������
    @Test
    public void connect04() throws Exception{
        //ʹ�÷�������� Driver��
        //�����ڼ���Driver��ʱ���������ע��
        /**
         * Դ�룺1.��̬���������������ʱ��ִ��һ��
         * 2.DriverManager.registerDriver(new Driver());
         * 3.��˼���Driverע��Ĺ���
         * static {
         *         try {
         *             DriverManager.registerDriver(new Driver());
         *         } catch (SQLException var1) {
         *             throw new RuntimeException("Can't register driver!");
         *         }
         *     }
         */
        Class.forName("com.mysql.cj.jdbc.Driver");

        //����url ��user �� password
        String url = "jdbc:mysql://127.0.0.1:3306/query";
        String user = "root";
        String password = "123456";

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println("������" + connection);
    }

    //��ʽ5���ڷ����ĵĻ����ϸĽ������������ļ���������MySQL�������
    @Test
    public void connect05() throws Exception {
        //ͨ��properties�����ȡ����ļ���������Ϣ
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\JavaProject\\JDBC\\src\\mysql.properties"));
        //��ȡ��ص�ֵ
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver); //����д��

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println("�����ַ���" + connection);
    }
}
