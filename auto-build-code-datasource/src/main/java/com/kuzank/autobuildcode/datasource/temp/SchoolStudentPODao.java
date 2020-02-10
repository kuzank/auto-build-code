package com.kuzank.autobuildcode.datasource.temp;

import com.kuzank.autobuildcode.datasource.temp.SchoolStudentPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p> @author auto-build-code
 * <p> @date   2018-09-25T20:04:58.923
 */
@Mapper
public interface SchoolStudentPODao {

    int insert(@Param("schoolStudentPO") SchoolStudentPO schoolStudentPO);

    int insertSelective(@Param("schoolStudentPO") SchoolStudentPO schoolStudentPO);

    int insertList(@Param("schoolStudentPO") List<SchoolStudentPO> schoolStudentPOs);

    SchoolStudentPO selectById(@Param("id") Integer id);

    SchoolStudentPO selectByUUID(@Param("uuid") String uuid);

    int deleteById(@Param("id") Integer id);

    int deleteByUUID(@Param("uuid") String uuid);

    int updateById(@Param("studentPO") SchoolStudentPO schoolStudentPO);

    int updateByUUID(@Param("studentPO") SchoolStudentPO schoolStudentPO);

    List<SchoolStudentPO> findAll();
}