package myjdbc;

/**
 * ���ǹ涨��JDBC�ӿڣ�������
 */
public interface JdbcInterface {
    //����
    public Object getConnection();
    //crud
    public void crud();

    //�ر�����
    public void close();
}
