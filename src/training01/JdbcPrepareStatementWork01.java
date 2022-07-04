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

        System.out.println("请输入用户名：");
        String admin_name = scanner.nextLine();
        System.out.println("请输入密码：");
        String admin_pwd = scanner.nextLine();
//        System.out.println("请输入新名字：");
//        String new_name = scanner.nextLine();

        Properties properties = new Properties();
        properties.load(new FileInputStream("D:\\JavaProject\\JDBC\\src\\mysql.properties"));
        //获取相关的值
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        //1.注册驱动
        Class.forName(driver); //建议写上
        //得到连接
        Connection connection = DriverManager.getConnection(url, user, password);


        //preparedStatement对象实现了PreparedStatement接口的实现类对象
        PreparedStatement preparedStatement = connection.prepareStatement(deleteValue());  //在括号中修改
        preparedStatement.setString(1, admin_name);
        preparedStatement.setString(2, admin_pwd);

        //这里执行dml语句executeUpdate不用再写sql
        int rows = preparedStatement.executeUpdate();
        System.out.println(rows > 0? "执行成功" : "执行失败");
        //关闭mysql
        preparedStatement.close();
        connection.close();
    }

    /**
     * 执行增加操作
     * @return
     */
    private static String insert(){
        String sql = "insert into admin VALUES (?, ?)";
        return sql;
    }

    /**
     * 执行修改的sql语句
     * @return
     */
    private static String update(){
        //通过对比原先修改的名字修改成新的名字
        String sql = "update admin set name = ? WHERE pwd = ?";
        return sql;
    }

    /**
     * 执行删除操作
     * @return
     */
    private static String deleteValue(){
        String sql = "delete from admin WHERE name = ?";
        return sql;
    }

}
