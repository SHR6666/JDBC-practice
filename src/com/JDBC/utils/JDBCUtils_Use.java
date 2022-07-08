package com.JDBC.utils;

import org.junit.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * 演示如何使用JDBCUtils工具类，完成dml和select
 */
public class JDBCUtils_Use {
    public static void main(String[] args) {
        testSelect();
    }
    @SuppressWarnings("all")
    public static void testSelect(){
        //得到连接
        Connection connection = null;
        //2.组织一个sql语句
        String sql = "select * FROM  actor where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,5);
            set = preparedStatement.executeQuery();
            //遍历该结果集
            while(set.next()){
                int id = set.getInt("id");
                String name = set.getString("NAME");
                String sex = set.getString("sex");
                Date borndate = set.getDate("borndate");
                String phone = set.getString("phone");
                System.out.println(id + "\t" + name + "\t" + sex + "\t" + borndate + "\t" + phone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(set, preparedStatement, connection);
        }
    }

//    @Test
    public void testDML() { //insert update,delete
        //得到连接
        Connection connection = null;
        //2.组织一个sql语句
        String sql = "update actor set name = ? where id = ?";
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            //给占位符赋值
            preparedStatement.setString(1,"六小龄童");
            preparedStatement.setInt(2, 5);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }

    /**
     * 插入数据
     */
//    @Test
    public void insertTest(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql =  "insert into actor (NAME, sex, borndate, phone) VALUES (?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "苍井空");
            preparedStatement.setString(2,"女");
            preparedStatement.setString(3,"1983-11-11");
            preparedStatement.setString(4,"1000000");
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }

    /**
     * PreparedStatement删除操作
     */
//    @Test
    public void deleteTest(){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "delete FROM actor WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,8);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtils.close(null, preparedStatement, connection);
        }
    }
}
