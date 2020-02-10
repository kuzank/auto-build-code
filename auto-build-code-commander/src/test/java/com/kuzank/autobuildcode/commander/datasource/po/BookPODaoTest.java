package com.kuzank.autobuildcode.commander.datasource.po;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuzank.autobuildcode.datasource.dao.po.BookPODao;
import com.kuzank.autobuildcode.datasource.entity.po.BookPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/7/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookPODaoTest {

    @Autowired
    private BookPODao bookPODao;

    @Test
    public void findAll() {
        System.out.println(bookPODao.findAll(BookPODao.collection));
    }

    @Test
    public void pageHelperTest() {

        PageHelper.startPage(5, 20);

        List<BookPO> bookPOS = bookPODao.findAll(BookPODao.collection);

        if (bookPOS != null) {
            for (BookPO bookPO : bookPOS) {
                System.out.println(bookPO);
            }
        }
    }

    @Test
    public void pageInfoTest() {

        PageHelper.startPage(5, 20);

        List<BookPO> bookPOS = bookPODao.findAll(BookPODao.collection);

        PageInfo<BookPO> pageInfo = new PageInfo<BookPO>(bookPOS);

        System.out.println(pageInfo);
    }

    @Test
    public void insertDate() {

        int num = 100;

        for (int i = 0; i < num; i++) {

            BookPO bookPO = new BookPO();
            bookPO
                    .setName(String.valueOf(i))
                    .setSortname(i)
                    .setColor(String.valueOf(i))
                    .setNoteId(String.valueOf(i));

            bookPODao.insertSelective(bookPO);

            System.out.println(i);
        }

    }

}
