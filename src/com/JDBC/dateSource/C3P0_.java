package com.JDBC.dateSource;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 演示C3P0的使用
 */
public class C3P0_ {
    @Test
    //方式一：相关参数在程序中指定user，url，password等
    public void testC3P0_01() throws Exception {
        //创建数据源对象
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        //通过配置文件MySQL.properties获取相关连接信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\JavaProject\\JDBC\\src\\mysql.properties"));
        //读取相关的属性
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        //给数据源 comboPooledDataSource 设置相关参数
        //注意：连接的管理是由 comboPooledDataSource 来管理
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        //设置连接数初始化
        comboPooledDataSource.setInitialPoolSize(10);
        //最大连接数
        comboPooledDataSource.setMaxPoolSize(50);
        //测试连接池效率，测试对MySQL的5000次操作
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++){
            Connection connection = comboPooledDataSource.getConnection(); //这个方法就是从DateSource 接口实现的
//            System.out.println("连接成功");
            connection.close();
        }
        long end = System.currentTimeMillis();

        System.out.println("c3o0 5000次连接MySQL 耗时：" + (end - start));
    }

    //第二种方式：使用配置文件模板来完成
    //将c3p0提供的 c3p0.xml放置到src目录下
    //该文件指定了连接数据库和连接池的相关参数
    @Test
    public void testC3P0_02() throws Exception{
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("jdbcUrl");
        //测试5000次
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++){
            Connection connection = comboPooledDataSource.getConnection();
//            System.out.println("连接成功");
            connection.close();
        }
        long end = System.currentTimeMillis();
        //c3p0 第二种操作5000次方式耗时：526
        //c3p0 第二种操作500000次方式耗时：1724
        System.out.println("c3p0 第二种操作500000次方式耗时：" + (end - start));
    }
}
