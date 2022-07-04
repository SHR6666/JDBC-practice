package com.JDBC.preparedstatement_;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

/**
 * 演示PreparedStatement的DML
 */
@SuppressWarnings({"all"})
public class PreparedStatementDML_ {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        //让用户输入管理员账号和密码
        System.out.println("输入删除的管理员的名字：");
        String admin_name = scanner.nextLine();  //如果希望看到sql注入这里需要用nextLine()
//        System.out.println("请输入管理员的密码：");
//        String admin_pwd = scanner.nextLine();

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
        //组织sql语句的 ？ 相当于占位符
        //添加记录
//        String sql = "insert into admin values(?, ?)";
//        String sql = "update admin set pwd = ? WHERE name = ?";
        String sql = "delete from admin WHERE name = ?";

        //preparedStatement对象实现了PreparedStatement接口的实现类对象
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //给？赋值
//        preparedStatement.setString(1,admin_pwd);
        preparedStatement.setString(1,admin_name);

        //这里执行dml语句executeUpdate不用再写sql
        int rows = preparedStatement.executeUpdate();
        System.out.println(rows > 0? "执行成功" : "执行失败");

        //关闭mysql
        preparedStatement.close();
        connection.close();
    }
}
