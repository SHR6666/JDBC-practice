package com.dao_.test;

import com.dao_.dao.ActorDAO;
import com.dao_.domain.Actor;
import org.junit.Test;

import java.util.List;

public class TestDAO {
    //测试ActorDAO 对 Actor表crud操作
    @Test
    public void testActorDAO(){
        ActorDAO actorDAO = new ActorDAO();
        //1.查询
        List<Actor> actors = actorDAO.queryMulti("select * from actor where id >= ?", Actor.class, 1);
        System.out.println("查询结果");
        for (Actor actor : actors) {
            System.out.println(actor);
        }

        //2.查询单行记录
        Actor actor = actorDAO.querySingle("select * from actor where id = ?", Actor.class, 9);
        System.out.println("====查询单行结果====");
        System.out.println(actor);

        //3.查询单行单列
        Object o = actorDAO.queryScalar("select name from actor where id = ?", 9);
        System.out.println("===查询单行单列值===");
        System.out.println(o);

        //4.dml操作
        int update = actorDAO.update("insert into actor values(null, ?, ?, ?, ?)", "张无忌", "男", "1443-11-11", "999");

        System.out.println(update > 0 ? "执行成功" : "没有影响表");
    }
}
