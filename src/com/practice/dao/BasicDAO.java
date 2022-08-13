package com.practice.dao;

import com.dao_.utils.JDBCUtilsByDruid;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * ����BasicDAO��������DAO�ĸ���
 */
public class BasicDAO<T> { //����ָ�����������


    private QueryRunner qr = new QueryRunner();

    //����ͨ�õ���ɾ�Ĳ鷽�����������ı�
    public int update(String sql, Object... parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            int update = qr.update(connection, sql, parameters);
            return update;
        } catch (SQLException e) {
            throw new RuntimeException(e);  //��һ�������쳣ת��һ�������쳣
        } finally {
            JDBCUtilsByDruid.close(null,null, connection);
        }

    }

    //���ض��������������
    /**
     *
     * @param sql sql��䣬�����У�
     * @param clazz  ����һ�����Class����
     * @param parameters ���룿�����ֵ�������Ƕ��
     * @return ����Actor.class ���ض�Ӧ��ArrayList����
     */
    public List<T> queryMulti(String sql, Class<T> clazz, Object... parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            List<T> query = qr.query(connection, sql, new BeanListHandler<>(clazz), parameters);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);  //��һ�������쳣ת��һ�������쳣
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }

    }

    //��ѯ���н����ͨ�÷���
    public T querySingle(String sql, Class<T> clazz, Object... parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            T query = qr.query(connection,sql, new BeanHandler<T>(clazz), parameters);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);  //��һ�������쳣ת��һ�������쳣
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }

    //��ѯ���е��еķ����������ص�ֵ�ķ���
    public Object queryScalar(String sql, Object... parameters){
        Connection connection = null;
        try {
            connection = JDBCUtilsByDruid.getConnection();
            Object query = qr.query(connection, sql, new ScalarHandler(), parameters);
            return query;
        } catch (SQLException e) {
            throw new RuntimeException(e);  //��һ�������쳣ת��һ�������쳣
        } finally {
            JDBCUtilsByDruid.close(null, null, connection);
        }
    }
}
