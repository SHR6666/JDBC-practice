package com.practice.test;

import com.practice.dao.GoodsDAO;
import org.junit.Test;

public class testDAO {
    @Test
    public void testGoods(){
        //�Ա�ִ��dml����
        GoodsDAO goods = new GoodsDAO();
        int update = goods.update("insert into " +
                "goods(goods_name,price) " +
                "values('С���ֻ�','1999'), ('��Ϊ�ֻ�','5999'), ('iphone�ֻ�','5499')");

        System.out.println(update > 0 ? "ִ�гɹ�" : "û��Ӱ���");
    }
}
