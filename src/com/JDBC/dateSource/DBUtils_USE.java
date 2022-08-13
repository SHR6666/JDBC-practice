package com.JDBC.dateSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class DBUtils_USE {
    //ʹ��apache-DBUtils  ������ + druid ��ɶԱ��crud����

    @Test
    public void testQueryMany() throws Exception {  //���ؽ���Ƕ��е����
        //1.�õ����ӣ�druid��
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.ʹ��DBUtils ��ͽӿڣ�������DBUtils ��ص� jar
        //���� QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //4. �Ϳ���ִ����صķ���������ArrayList �����
        String sql = "select * FROM  actor where id = ?";
        List<Actor> list = queryRunner.query(connection, sql, new BeanListHandler<Actor>(Actor.class), 3);
        System.out.println("���������Ϣ��");
        for(Actor actor : list){
            System.out.println(actor);
        }
//      System.out.println("actor: " + actor);

        //�ͷ���Դ
        JDBCUtilsByDruid.close(null,null, connection);
    }

    @Test
    public void testQuerySingle() throws Exception {
        //1.�õ����ӣ�druid��
        Connection connection = JDBCUtilsByDruid.getConnection();
        //���� QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * FROM  actor where id = ?";
        //��Ϊ���Ƿ��صĵ��м�¼
        Actor actor = queryRunner.query(connection, sql, new BeanHandler<>(Actor.class), 5);
        System.out.println(actor);

        JDBCUtilsByDruid.close(null, null, connection);
    }
}
