package com.JDBC.dateSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

public class DBUtils_USE {
    //使用apache-DBUtils  工具类 + druid 完成对表的crud操作

    @Test
    public void testQueryMany() throws Exception {  //返回结果是多行的情况
        //1.得到连接（druid）
        Connection connection = JDBCUtilsByDruid.getConnection();
        //2.使用DBUtils 类和接口，先引入DBUtils 相关的 jar
        //创建 QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        //4. 就可以执行相关的方法，返回ArrayList 结果集
        String sql = "select * FROM  actor where id = ?";
        List<Actor> list = queryRunner.query(connection, sql, new BeanListHandler<Actor>(Actor.class), 3);
        System.out.println("输出集合信息：");
        for(Actor actor : list){
            System.out.println(actor);
        }
//      System.out.println("actor: " + actor);

        //释放资源
        JDBCUtilsByDruid.close(null,null, connection);
    }

    @Test
    public void testQuerySingle() throws Exception {
        //1.得到连接（druid）
        Connection connection = JDBCUtilsByDruid.getConnection();
        //创建 QueryRunner
        QueryRunner queryRunner = new QueryRunner();
        String sql = "select * FROM  actor where id = ?";
        //因为我们返回的单行记录
        Actor actor = queryRunner.query(connection, sql, new BeanHandler<>(Actor.class), 5);
        System.out.println(actor);

        JDBCUtilsByDruid.close(null, null, connection);
    }
}
