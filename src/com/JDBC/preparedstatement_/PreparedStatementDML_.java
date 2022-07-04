package com.JDBC.preparedstatement_;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;

/**
 * ��ʾPreparedStatement��DML
 */
@SuppressWarnings({"all"})
public class PreparedStatementDML_ {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        //���û��������Ա�˺ź�����
        System.out.println("����ɾ���Ĺ���Ա�����֣�");
        String admin_name = scanner.nextLine();  //���ϣ������sqlע��������Ҫ��nextLine()
//        System.out.println("���������Ա�����룺");
//        String admin_pwd = scanner.nextLine();

        //ͨ��properties�����ȡ����ļ���������Ϣ
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\JavaProject\\JDBC\\src\\mysql.properties"));
        //��ȡ��ص�ֵ
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        //1.ע������
        Class.forName(driver); //����д��
        //�õ�����
        Connection connection = DriverManager.getConnection(url, user, password);

        //3.�õ�prepareStatement
        //��֯sql���� �� �൱��ռλ��
        //��Ӽ�¼
//        String sql = "insert into admin values(?, ?)";
//        String sql = "update admin set pwd = ? WHERE name = ?";
        String sql = "delete from admin WHERE name = ?";

        //preparedStatement����ʵ����PreparedStatement�ӿڵ�ʵ�������
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        //������ֵ
//        preparedStatement.setString(1,admin_pwd);
        preparedStatement.setString(1,admin_name);

        //����ִ��dml���executeUpdate������дsql
        int rows = preparedStatement.executeUpdate();
        System.out.println(rows > 0? "ִ�гɹ�" : "ִ��ʧ��");

        //�ر�mysql
        preparedStatement.close();
        connection.close();
    }
}
