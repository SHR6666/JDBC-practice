package com.JDBC.dateSource;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class JDBCUtilsByDruid_USE {

    @Test
    public void testSelect() {
        System.out.println("使用 druid 方式完成");
        //得到连接
        Connection connection = null;
        //2.组织一个sql语句
        String sql = "select * FROM  actor where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            System.out.println(connection.getClass());  //运行类型
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 5);
            set = preparedStatement.executeQuery();
            //遍历该结果集
            while (set.next()) {
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
            JDBCUtilsByDruid.close(set, preparedStatement, connection);
        }

    }

    @Test
    //使用土方法
    public void testSelectArrayList() {
        System.out.println("使用 druid 方式完成");
        //得到连接
        Connection connection = null;
        //2.组织一个sql语句
        String sql = "select * FROM  actor where id >= ?";
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        ArrayList<Actor> list = new ArrayList<>(); //创建ArrayList对象
        try {
            connection = JDBCUtilsByDruid.getConnection();
            System.out.println(connection.getClass());  //运行类型
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            set = preparedStatement.executeQuery();
            //遍历该结果集
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("NAME");
                String sex = set.getString("sex");
                Date borndate = set.getDate("borndate");
                String phone = set.getString("phone");
                //把得到的ResultSet的记录，封装到Actor对象里，放入到list集合
                list.add(new Actor(id, name, sex, borndate, phone));
            }
//            System.out.println("list集合数据：" + list);
            for (Actor actor : list){
                System.out.println("id = " + actor.getId() + "\t" + actor.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭资源
            JDBCUtilsByDruid.close(set, preparedStatement, connection);
        }
        //因为ArrayList 和 connection没有关联，所以可以再次使用
//        return list;
    }
}
