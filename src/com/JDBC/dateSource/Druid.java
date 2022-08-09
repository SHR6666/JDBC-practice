package com.JDBC.dateSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * 测试德鲁伊的使用
 */
public class Druid {
    @Test
    public void testDruid() throws Exception{
        //1.加入Druid jar包
        //2. 加入配置文件, 将该文件放置到项目src即可
        //3. 创建properties对象
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\JavaProject\\JDBC\\src\\druid.properties"));

        //创建一个指定参数的数据库连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++){
            Connection connection = dataSource.getConnection();
//            System.out.println("连接成功");
            connection.close();
        }
        long end = System.currentTimeMillis();
        //Druid 连接池操作5000次耗时：860
        //Druid 连接池操作500000次耗时：815
        System.out.println("Druid 连接池操作500000次耗时：" + (end - start));
    }
}
