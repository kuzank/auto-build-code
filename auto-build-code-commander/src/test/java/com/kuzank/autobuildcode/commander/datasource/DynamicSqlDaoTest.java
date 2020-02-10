package com.kuzank.autobuildcode.commander.datasource;

import com.kuzank.autobuildcode.datasource.dao.DynamicSqlDao;
import com.kuzank.autobuildcode.datasource.dao.po.BookPODao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/7/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicSqlDaoTest {


    @Autowired
    private DynamicSqlDao dynamicSqlDao;

    @Test
    public void dynamicHardDelete() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 1);
        System.out.println(dynamicSqlDao.dynamicHardDelete(BookPODao.collection, map));
    }

    @Test
    public void dynamicSoftDelete() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 2);
        System.out.println(dynamicSqlDao.dynamicSoftDelete(BookPODao.collection, map));
    }

    @Test
    public void dynamicHardDeleteById() {
        System.out.println(dynamicSqlDao.dynamicHardDeleteById(BookPODao.collection, 1));
    }

    @Test
    public void dynamicSoftDeleteById() {
        System.out.println(dynamicSqlDao.dynamicSoftDeleteById(BookPODao.collection, 1));
    }

    @Test
    public void simpleDynamicQuery() {
        System.out.println(dynamicSqlDao.simpleDynamicQuery("select * from " + BookPODao.collection));
    }

    @Test
    public void simpleDynamicUpdate() {
        System.out.println(dynamicSqlDao.simpleDynamicUpdate("update " + BookPODao.collection + " set deleted = 0"));
    }
}
