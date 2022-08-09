package com.JDBC.dateSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * ���Ե�³����ʹ��
 */
public class Druid {
    @Test
    public void testDruid() throws Exception{
        //1.����Druid jar��
        //2. ���������ļ�, �����ļ����õ���Ŀsrc����
        //3. ����properties����
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\JavaProject\\JDBC\\src\\druid.properties"));

        //����һ��ָ�����������ݿ����ӳ�
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++){
            Connection connection = dataSource.getConnection();
//            System.out.println("���ӳɹ�");
            connection.close();
        }
        long end = System.currentTimeMillis();
        //Druid ���ӳز���5000�κ�ʱ��860
        //Druid ���ӳز���500000�κ�ʱ��815
        System.out.println("Druid ���ӳز���500000�κ�ʱ��" + (end - start));
    }
}
