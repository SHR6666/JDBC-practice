package myjdbc;

/**
 * ģ��Oracle���ݿ�ʵ��jdbc
 */
public class OracleJdbcImpl implements JdbcInterface{
    @Override
    public Object getConnection() {
        System.out.println("�õ� Oracle ������");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("��� Oracle ����ɾ�Ĳ�");
    }

    @Override
    public void close() {
        System.out.println("�ر� Oracle ����");
    }
}
