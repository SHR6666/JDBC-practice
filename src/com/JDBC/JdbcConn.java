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
 * 分析java连接sql的五种方式
 */
public class JdbcConn {
    @Test
    //方式1
    public void connect01() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();

        //(1)固定协议jdbc:mysql:，勿动
        String url = "jdbc:mysql://127.0.0.1:3306/query";
        //将用户名和密码放到Properties中
        Properties properties = new Properties();
        //说明，这个user和password是规定好的
        properties.setProperty("user", "root"); //用户
        properties.setProperty("password", "123456"); //密码
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    //方式2
    @Test
    public void connect02() throws Exception {
        //使用反射加载Driver类，动态的加载更加灵活，减少依赖
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        //(1)固定协议jdbc:mysql:，勿动
        String url = "jdbc:mysql://127.0.0.1:3306/query";
        //将用户名和密码放到Properties中
        Properties properties = new Properties();
        //说明，这个user和password是规定好的
        properties.setProperty("user", "root"); //用户
        properties.setProperty("password", "123456"); //密码
        Connection connect = driver.connect(url, properties);
        System.out.println("方式二" + connect);
    }

    //方式3  使用DriverManger替代Driver进行统一管理
    @Test
    public void connect03() throws Exception {

        //使用反射加载Driver
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        //创建url 和user 和 password
        String url = "jdbc:mysql://127.0.0.1:3306/query";
        String user = "root";
        String password = "123456";

        DriverManager.registerDriver(driver);  //注册Driver驱动

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("方式三" + connection);
    }

    //方法4：使用Class.forName 自动完成注册驱动，简化代码
    //这种方式获取连接是使用最多的
    @Test
    public void connect04() throws Exception{
        //使用反射加载了 Driver类
        //这里在加载Driver类时，完成驱动注册
        /**
         * 源码：1.静态代码块在这个类加载时会执行一次
         * 2.DriverManager.registerDriver(new Driver());
         * 3.因此加载Driver注册的工作
         * static {
         *         try {
         *             DriverManager.registerDriver(new Driver());
         *         } catch (SQLException var1) {
         *             throw new RuntimeException("Can't register driver!");
         *         }
         *     }
         */
        Class.forName("com.mysql.cj.jdbc.Driver");

        //创建url 和user 和 password
        String url = "jdbc:mysql://127.0.0.1:3306/query";
        String user = "root";
        String password = "123456";

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println("第四种" + connection);
    }

    //方式5：在方法四的基础上改进，增加配置文件，让连接MySQL更加灵活
    @Test
    public void connect05() throws Exception {
        //通过properties对象获取相关文件的配置信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\JavaProject\\JDBC\\src\\mysql.properties"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver); //建议写上

        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println("第五种方法" + connection);
    }
}
