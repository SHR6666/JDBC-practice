package com.JDBC.utils;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * ����һ�������࣬��Ҫ���mysql�����Ӻ͹ر���Դ
 */
public class JDBCUtils {
    //������ص����ԣ�4��������Ϊֻ��Ҫһ�ݣ����Ҫ����static
    private static String user; //�û���
    private static String password; //����
    private static String url; //url
    private static String driver; //����

    //��static�����ȥ��ʼ��
    static{
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\JavaProject\\JDBC\\src\\mysql.properties"));
            //��ȡ��ص�����
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
        } catch (Exception e) {
            //��ʵ�ʿ��������ǿ�����������
            //1.�������쳣ת�� �����쳣
            //2.���ǵ����ߣ�����ѡ�񲶻���쳣��Ҳ����ѡ��Ĭ�ϴ����ȽϷ���
            throw new RuntimeException(e);
        }
    }

    //�������ݿ⣬����һ��Connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //�ر������Դ
    /**
     * 1.resultSet �����
     * 2.Statement ����PreparedStatement
     * 3.connection
     * 4.�����Ҫ�ر���Դ���ʹ�����󣬷���ʹ����
     */
    public static void close(ResultSet set, Statement statement, Connection connection) {
        try {
            //�ж��Ƿ�Ϊnull
            if (set != null){
                set.close();
            }
            if (statement != null){
                statement.close();
            }
            if (connection != null){
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
