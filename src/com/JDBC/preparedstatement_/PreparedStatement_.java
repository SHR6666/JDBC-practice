package com.JDBC.preparedstatement_;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * 演示PreparedStatement使用
 */
@SuppressWarnings({"all"})
public class PreparedStatement_ {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        //让用户输入管理员账号和密码
        System.out.println("请输入管理员的名字：");
        String admin_name = scanner.nextLine();  //如果希望看到sql注入这里需要用nextLine()
        System.out.println("请输入管理员的密码：");
        String admin_pwd = scanner.nextLine();

        //通过properties对象获取相关文件的配置信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\JavaProject\\JDBC\\src\\mysql.properties"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        //1.注册驱动
        Class.forName(driver); //建议写上
        //得到连接
        Connection connection = DriverManager.getConnection(url, user, password);

        //3.得到prepareStatement
        //组织sql语句
        String sql = "select name, pwd from admin where name = ? and pwd = ?";  //sql语句的？相当于占位符
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //给？赋值
        preparedStatement.setString(1,admin_name);
        preparedStatement.setString(2,admin_pwd);

        //这里执行executeQuery不用再写sql
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()){ //如果查询到一条记录，则说明该管理员存在，则登录成功
            System.out.println("成功");
        }else{
            System.out.println("false");
        }

        //关闭mysql
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
