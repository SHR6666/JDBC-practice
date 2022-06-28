package com.JDBC;


import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 这是第一个jdbc程序，完成简单操作
 */
public class Jdbc01 {
    public static void main(String[] args) throws SQLException{
        //1.注册驱动
        Driver driver = new com.mysql.cj.jdbc.Driver();

        //2.得到连接
        //解读：
        //(1)固定协议jdbc:mysql:，勿动
        String url = "jdbc:mysql://127.0.0.1:3306/query";
        //将用户名和密码放到Properties中
        Properties properties = new Properties();
        //说明，这个user和password是规定好的
        properties.setProperty("user", "root"); //用户
        properties.setProperty("password", "123456"); //密码

        Connection connect = driver.connect(url, properties);
        //3.执行sql语句
//        String sql = "insert into actor value(null, '刘德华', '男', '1970-11-11', '110')";
        //修改
//        String sql = "update actor set NAME ='周星驰' where id = '1'";
        //删除
        String sql = "delete FROM actor WHERE id = '1'";

        Statement statement = connect.createStatement(); //用于执行静态sql语句并返回其生成的结果对象
        int rows = statement.executeUpdate(sql); //如果是dml语句，返回的就是影响的行数

        System.out.println(rows > 0 ? "成功": "失败");
        //4.关闭连接资源
        statement.close();
        connect.close();
    }
}
