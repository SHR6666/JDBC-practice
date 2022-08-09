package com.JDBC.dateSource;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * ��ʾC3P0��ʹ��
 */
public class C3P0_ {
    @Test
    //��ʽһ����ز����ڳ�����ָ��user��url��password��
    public void testC3P0_01() throws Exception {
        //��������Դ����
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        //ͨ�������ļ�MySQL.properties��ȡ���������Ϣ
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\JavaProject\\JDBC\\src\\mysql.properties"));
        //��ȡ��ص�����
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        //������Դ comboPooledDataSource ������ز���
        //ע�⣺���ӵĹ������� comboPooledDataSource ������
        comboPooledDataSource.setDriverClass(driver);
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        //������������ʼ��
        comboPooledDataSource.setInitialPoolSize(10);
        //���������
        comboPooledDataSource.setMaxPoolSize(50);
        //�������ӳ�Ч�ʣ����Զ�MySQL��5000�β���
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000; i++){
            Connection connection = comboPooledDataSource.getConnection(); //����������Ǵ�DateSource �ӿ�ʵ�ֵ�
//            System.out.println("���ӳɹ�");
            connection.close();
        }
        long end = System.currentTimeMillis();

        System.out.println("c3o0 5000������MySQL ��ʱ��" + (end - start));
    }

    //�ڶ��ַ�ʽ��ʹ�������ļ�ģ�������
    //��c3p0�ṩ�� c3p0.xml���õ�srcĿ¼��
    //���ļ�ָ�����������ݿ�����ӳص���ز���
    @Test
    public void testC3P0_02() throws Exception{
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("jdbcUrl");
        //����5000��
        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++){
            Connection connection = comboPooledDataSource.getConnection();
//            System.out.println("���ӳɹ�");
            connection.close();
        }
        long end = System.currentTimeMillis();
        //c3p0 �ڶ��ֲ���5000�η�ʽ��ʱ��526
        //c3p0 �ڶ��ֲ���500000�η�ʽ��ʱ��1724
        System.out.println("c3p0 �ڶ��ֲ���500000�η�ʽ��ʱ��" + (end - start));
    }
}
