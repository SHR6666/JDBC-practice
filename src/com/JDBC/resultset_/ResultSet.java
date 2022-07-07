package com.JDBC.resultset_;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * 演示select语句返回 ResultSet, 并取出结果
 */
@SuppressWarnings("all")
public class ResultSet {
    public static void main(String[] args) throws Exception {
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
        String sql = "select id, NAME, sex, borndate from actor";
        //执行给定的sql语句，该语句返回单个ResultSet对象
        /**
         * +----+--------+-----+---------------------+-------+
         * | id | NAME   | sex | borndate            | phone |
         * +----+--------+-----+---------------------+-------+
         * |  3 | 小王   | 男  | 2000-10-30 00:00:00 | 155   |
         * |  5 | 小红   | 女  | 2000-10-30 00:00:00 | 155   |
         * |  6 | 张三   | 男  | 2000-10-30 00:00:00 | 155   |
         * |  7 | 刘德华 | 男  | 1970-12-12 00:00:00 | 110   |
         * |  8 | jack   | 男  | 1990-11-11 00:00:00 | 112   |
         * +----+--------+-----+---------------------+-------+
         */
        java.sql.ResultSet resultSet = statement.executeQuery(sql);

        //5.使用while取出数据
        while (resultSet.next()) {  //让光标向后移动
            int id = resultSet.getInt(1); //获取该行的第一列数据
            //int id1 = resultSet.getInt("id"); //通过列名来获取值
            String name = resultSet.getString(2);  //获取该行的第二列
            String sex = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            System.out.println(id + "\t" + name + "\t"+ sex + "\t" + date);
        }

        //6.关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
