package training01;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.Scanner;
@SuppressWarnings({"all"})
public class JdbcPrepareStatementWork01 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.println("�������û�����");
        String admin_name = scanner.nextLine();
        System.out.println("���������룺");
        String admin_pwd = scanner.nextLine();
//        System.out.println("�����������֣�");
//        String new_name = scanner.nextLine();

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


        //preparedStatement����ʵ����PreparedStatement�ӿڵ�ʵ�������
        PreparedStatement preparedStatement = connection.prepareStatement(deleteValue());  //���������޸�
        preparedStatement.setString(1, admin_name);
        preparedStatement.setString(2, admin_pwd);

        //����ִ��dml���executeUpdate������дsql
        int rows = preparedStatement.executeUpdate();
        System.out.println(rows > 0? "ִ�гɹ�" : "ִ��ʧ��");
        //�ر�mysql
        preparedStatement.close();
        connection.close();
    }

    /**
     * ִ�����Ӳ���
     * @return
     */
    private static String insert(){
        String sql = "insert into admin VALUES (?, ?)";
        return sql;
    }

    /**
     * ִ���޸ĵ�sql���
     * @return
     */
    private static String update(){
        //ͨ���Ա�ԭ���޸ĵ������޸ĳ��µ�����
        String sql = "update admin set name = ? WHERE pwd = ?";
        return sql;
    }

    /**
     * ִ��ɾ������
     * @return
     */
    private static String deleteValue(){
        String sql = "delete from admin WHERE name = ?";
        return sql;
    }

}
