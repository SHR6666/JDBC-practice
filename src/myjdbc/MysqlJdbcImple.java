package myjdbc;

/**
 * mysqkʵ����jdbc�ӿڣ�ģ�⣩
 */
public class MysqlJdbcImple implements JdbcInterface{
    @Override
    public Object getConnection() {
        System.out.println("�õ���mysql������");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("���MySQL����ɾ�Ĳ�");
    }

    @Override
    public void close() {
        System.out.println("�ر�����");
    }
}
