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
 * 演示statement 的注入问题
 */
@SuppressWarnings("all")
public class Statement_ {
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
        //3.得到statement
        Statement statement = connection.createStatement();//用于执行静态sql语句并返回其生成的结果对象
        //4.组织sql语句
        String sql = "select name, pwd from admin where name = '"+admin_name+"' and pwd = '"+admin_pwd+"'";
        ResultSet resultSet = statement.executeQuery(sql);
        if (resultSet.next()){ //如果查询到一条记录，则说明该管理员存在，则登录成功
            System.out.println("成功");
        }else{
            System.out.println("false");
        }

        //关闭mysql
        resultSet.close();
        statement.close();
        connection.close();
    }
}
