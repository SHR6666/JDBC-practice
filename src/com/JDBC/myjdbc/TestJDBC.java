package com.JDBC.myjdbc;

public class TestJDBC {
    public static void main(String[] args) {
        //��ɶ�MySQL�Ĳ���
//        JdbcInterface jdbcInterface = new MysqlJdbcImple();
        JdbcInterface jdbcInterface = new OracleJdbcImpl();
        jdbcInterface.getConnection(); //ͨ���ӿڵ���ʵ���ࡾ��̬�󶨡�
        jdbcInterface.crud();
        jdbcInterface.close();
    }
}
