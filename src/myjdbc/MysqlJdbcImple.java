package myjdbc;

/**
 * mysqk实现了jdbc接口（模拟）
 */
public class MysqlJdbcImple implements JdbcInterface{
    @Override
    public Object getConnection() {
        System.out.println("得到了mysql的连接");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("完成MySQL的增删改查");
    }

    @Override
    public void close() {
        System.out.println("关闭连接");
    }
}
