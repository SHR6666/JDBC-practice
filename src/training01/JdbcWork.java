package training01;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * �÷���������ҵ
 * ����actor��
 * ʹ��jdbc������������
 * �޸�id = 1�ļ�¼����name�ĳ��Լ�������
 * ɾ��id = 3�ļ�¼
 */
public class JdbcWork {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\JavaProject\\JDBC\\src\\training01\\actor.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);

        //3.ִ����ɾ�Ĳ�
//        String sql = "insert into actor value(null, 'С��', '��', '2000-10-30', '155')";

        Statement statement = connection.createStatement();
//        int rows = statement.executeUpdate(InsertDate()); //��������
//        int rows = statement.executeUpdate(updateDate()); //�޸�����
        int rows = statement.executeUpdate(deleteDate());  //ɾ������

        System.out.println(rows > 0 ? "�ɹ�": "ʧ��");

        statement.close();
        connection.close();
    }

    /**
     * �����ֶ�
     * @return
     */
    private static String InsertDate(){
        String date =
       "insert into actor(NAME,sex,borndate,phone) " +
               "value('С��', '��', '2000-10-30', '155')," +
               "('С��', '��', '2000-10-30', '155')," +
               "('С��', '��', '2000-10-30', '155')," +
               "('С��', 'Ů', '2000-10-30', '155')," +
               "('����', '��', '2000-10-30', '155');";
        return date;
    }

    /**
     * �޸�id = 1;
     */
    private static String updateDate(){
        String date = "update actor set NAME ='�����' where id = '2'";
        return date;
    }

    /**
     * ɾ��id = 3�ļ�¼
     */
    private static String deleteDate(){
        String date = "delete from actor where id = '4'";
        return date;
    }
}
