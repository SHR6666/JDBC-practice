package training01;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;

/**
 * 用方法五做作业
 * 创建actor表
 * 使用jdbc增加五条数据
 * 修改id = 1的记录，将name改成自己的名字
 * 删除id = 3的记录
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

        //3.执行增删改查
//        String sql = "insert into actor value(null, '小杨', '男', '2000-10-30', '155')";

        Statement statement = connection.createStatement();
//        int rows = statement.executeUpdate(InsertDate()); //插入数据
//        int rows = statement.executeUpdate(updateDate()); //修改数据
        int rows = statement.executeUpdate(deleteDate());  //删除数据

        System.out.println(rows > 0 ? "成功": "失败");

        statement.close();
        connection.close();
    }

    /**
     * 增量字段
     * @return
     */
    private static String InsertDate(){
        String date =
       "insert into actor(NAME,sex,borndate,phone) " +
               "value('小虎', '男', '2000-10-30', '155')," +
               "('小王', '男', '2000-10-30', '155')," +
               "('小明', '男', '2000-10-30', '155')," +
               "('小红', '女', '2000-10-30', '155')," +
               "('张三', '男', '2000-10-30', '155');";
        return date;
    }

    /**
     * 修改id = 1;
     */
    private static String updateDate(){
        String date = "update actor set NAME ='孙浩人' where id = '2'";
        return date;
    }

    /**
     * 删除id = 3的记录
     */
    private static String deleteDate(){
        String date = "delete from actor where id = '4'";
        return date;
    }
}
