package com.kuzank.autobuildcode.datasource.temp;

import com.github.pagehelper.PageInfo;
import com.kuzank.autobuildcode.datasource.temp.SchoolStudentPO;

import java.util.List;

/**
 * <p> @author auto-build-code
 * <p> @date   2018-09-25T20:04:58.935
 */
public interface SchoolStudentPOService {

    int insert(SchoolStudentPO schoolStudentPO);

    int insertSelective(SchoolStudentPO schoolStudentPO);

    int insertList(List<SchoolStudentPO> schoolStudentPOs);

    SchoolStudentPO selectById(Integer id);

    SchoolStudentPO selectByUUID(String uuid);

    int deleteById(Integer id);

    int deleteByUUID(String uuid);

    int updateById(SchoolStudentPO schoolStudentPO);

    int updateByUUID(SchoolStudentPO schoolStudentPO);

    PageInfo<SchoolStudentPO> findAll(int pageNum, int pageSize);
}