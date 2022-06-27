package com.JDBC.myjdbc;

public class TestJDBC {
    public static void main(String[] args) {
        //完成对MySQL的操作
//        JdbcInterface jdbcInterface = new MysqlJdbcImple();
        JdbcInterface jdbcInterface = new OracleJdbcImpl();
        jdbcInterface.getConnection(); //通过接口调用实现类【动态绑定】
        jdbcInterface.crud();
        jdbcInterface.close();
    }
}
