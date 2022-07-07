package com.JDBC.utils;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 这是一个工具类，它要完成mysql的连接和关闭资源
 */
public class JDBCUtils {
    //定义相关的属性（4个），因为只需要一份，因此要做出static
    private static String user; //用户名
    private static String password; //密码
    private static String url; //url
    private static String driver; //驱动

    //在static代码块去初始化
    static{
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("D:\\JavaProject\\JDBC\\src\\mysql.properties"));
            //读取相关的属性
            user = properties.getProperty("user");
            password = properties.getProperty("password");
            url = properties.getProperty("url");
            driver = properties.getProperty("driver");
        } catch (Exception e) {
            //在实际开发中我们可以这样处理
            //1.将编译异常转成 运行异常
            //2.这是调用者，可以选择捕获该异常，也可以选择默认处理，比较方便
            throw new RuntimeException(e);
        }
    }

    //连接数据库，返回一个Connection
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //关闭相关资源
    /**
     * 1.resultSet 结果集
     * 2.Statement 或者PreparedStatement
     * 3.connection
     * 4.如果需要关闭资源，就传入对象，否则就传入空
     */
    public static void close(ResultSet set, Statement statement, Connection connection) {
        try {
            //判断是否为null
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
