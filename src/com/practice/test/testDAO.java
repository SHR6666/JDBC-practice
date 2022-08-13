package com.practice.test;

import com.practice.dao.GoodsDAO;
import org.junit.Test;

public class testDAO {
    @Test
    public void testGoods(){
        //对表执行dml操作
        GoodsDAO goods = new GoodsDAO();
        int update = goods.update("insert into " +
                "goods(goods_name,price) " +
                "values('小米手机','1999'), ('华为手机','5999'), ('iphone手机','5499')");

        System.out.println(update > 0 ? "执行成功" : "没有影响表");
    }
}
