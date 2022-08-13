package com.dao_.test;

import com.dao_.dao.ActorDAO;
import com.dao_.domain.Actor;
import org.junit.Test;

import java.util.List;

public class TestDAO {
    //����ActorDAO �� Actor��crud����
    @Test
    public void testActorDAO(){
        ActorDAO actorDAO = new ActorDAO();
        //1.��ѯ
        List<Actor> actors = actorDAO.queryMulti("select * from actor where id >= ?", Actor.class, 1);
        System.out.println("��ѯ���");
        for (Actor actor : actors) {
            System.out.println(actor);
        }

        //2.��ѯ���м�¼
        Actor actor = actorDAO.querySingle("select * from actor where id = ?", Actor.class, 9);
        System.out.println("====��ѯ���н��====");
        System.out.println(actor);

        //3.��ѯ���е���
        Object o = actorDAO.queryScalar("select name from actor where id = ?", 9);
        System.out.println("===��ѯ���е���ֵ===");
        System.out.println(o);

        //4.dml����
        int update = actorDAO.update("insert into actor values(null, ?, ?, ?, ?)", "���޼�", "��", "1443-11-11", "999");

        System.out.println(update > 0 ? "ִ�гɹ�" : "û��Ӱ���");
    }
}
