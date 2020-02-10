package com.kuzank.autobuildcode.datasource.dao.po;

import com.kuzank.autobuildcode.datasource.entity.po.BookPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author kuzank
 */
@Mapper
public interface BookPODao {

    String collection = "book";

    int insert(@Param("bookPO") BookPO bookPO);

    int insertSelective(@Param("bookPO") BookPO bookPO);

    int insertList(@Param("bookPOs") List<BookPO> bookPOs);

    int update(@Param("bookPO") BookPO bookPO);

    List<BookPO> findAll(@Param("collection") String collection);

    BookPO selectById(@Param("id") Integer id);

    BookPO selectByUUID(@Param("uuid") String uuid);

    int deleteById(@Param("id") Integer id);

    int deleteByUUID(@Param("uuid") String uuid);
}
