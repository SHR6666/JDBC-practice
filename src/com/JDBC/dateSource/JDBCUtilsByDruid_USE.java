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
        System.out.println("ʹ�� druid ��ʽ���");
        //�õ�����
        Connection connection = null;
        //2.��֯һ��sql���
        String sql = "select * FROM  actor where id = ?";
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            System.out.println(connection.getClass());  //��������
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 5);
            set = preparedStatement.executeQuery();
            //�����ý����
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
            //�ر���Դ
            JDBCUtilsByDruid.close(set, preparedStatement, connection);
        }

    }

    @Test
    //ʹ��������
    public void testSelectArrayList() {
        System.out.println("ʹ�� druid ��ʽ���");
        //�õ�����
        Connection connection = null;
        //2.��֯һ��sql���
        String sql = "select * FROM  actor where id >= ?";
        PreparedStatement preparedStatement = null;
        ResultSet set = null;
        ArrayList<Actor> list = new ArrayList<>(); //����ArrayList����
        try {
            connection = JDBCUtilsByDruid.getConnection();
            System.out.println(connection.getClass());  //��������
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 1);
            set = preparedStatement.executeQuery();
            //�����ý����
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("NAME");
                String sex = set.getString("sex");
                Date borndate = set.getDate("borndate");
                String phone = set.getString("phone");
                //�ѵõ���ResultSet�ļ�¼����װ��Actor��������뵽list����
                list.add(new Actor(id, name, sex, borndate, phone));
            }
//            System.out.println("list�������ݣ�" + list);
            for (Actor actor : list){
                System.out.println("id = " + actor.getId() + "\t" + actor.getName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //�ر���Դ
            JDBCUtilsByDruid.close(set, preparedStatement, connection);
        }
        //��ΪArrayList �� connectionû�й��������Կ����ٴ�ʹ��
//        return list;
    }
}
