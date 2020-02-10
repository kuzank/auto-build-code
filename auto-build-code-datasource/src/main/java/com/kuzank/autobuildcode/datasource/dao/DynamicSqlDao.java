package com.kuzank.autobuildcode.datasource.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>Description: </p>
 *
 * @author kuzank  2018/7/28
 */
public interface DynamicSqlDao {

    /**
     * 简单条件搜索
     *
     * @param collection 表名
     * @param conditions 条件，${key}=#{value} and ${key}=#{value}
     * @param sort       排序字段
     * @return
     */
    List<Map<String, Object>> dynamicConditionQuery(@Param("collection") String collection,
                                                    @Param("conditions") Map<String, Object> conditions,
                                                    @Param("sort") String sort);

    /**
     * 插入动态数据
     *
     * @param collection 表名
     * @param fields     键值对，以键为字段名，其值为字段值
     * @return
     */
    int dynamicInsert(@Param("collection") String collection, @Param("fields") Map<String, Object> fields);

    /**
     * 更新动态数据
     *
     * @param collection 表名
     * @param mainKey    主键名
     * @param keyValue   主键值
     * @param fields     键值对，以键为字段名，其值为字段值
     * @return
     */
    int dynamicUpdate(@Param("collection") String collection,
                      @Param("mainKey") String mainKey,
                      @Param("keyValue") String keyValue,
                      @Param("fields") Map<String, Object> fields);


    /**
     * 查询
     *
     * @param sql sql语句
     * @return
     */
    List<Map<String, Object>> simpleDynamicQuery(@Param("sql") String sql);


    /**
     * 更新
     *
     * @param sql sql语句
     * @return
     */
    int simpleDynamicUpdate(@Param("sql") String sql);


    /**
     * 动态硬删除
     *
     * @param collection
     * @param fields
     * @return
     */
    int dynamicHardDelete(@Param("collection") String collection,
                          @Param("fields") Map<String, Object> fields);

    /**
     * 动态软删除
     *
     * @param collection
     * @param fields
     * @return
     */
    int dynamicSoftDelete(@Param("collection") String collection,
                          @Param("fields") Map<String, Object> fields);

    /**
     * 动态软删除 --> 根据ID
     *
     * @param collection
     * @param id
     * @return
     */
    int dynamicHardDeleteById(@Param("collection") String collection, @Param("id") Integer id);

    /**
     * 动态软删除 --> 根据ID
     *
     * @param collection
     * @param id
     * @return
     */
    int dynamicSoftDeleteById(@Param("collection") String collection, @Param("id") Integer id);

    /**
     * 复杂条件搜索
     *
     * @param args 条件参数。通过ivring-common:com.dxtech.ivring.common.builders.sql.DynamicQueryBuilder进行构建
     * @return
     */
    List<Map<String, Object>> dynamicQuery(@Param("doc") Map<String, Object> args);

}
