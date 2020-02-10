package com.kuzank.autobuildcode.datasource.temp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuzank.autobuildcode.datasource.temp.SchoolStudentPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p> @author auto-build-code
 * <p> @date   2018-09-25T20:04:58.939
 */
@Service
public class SchoolStudentPOServiceImpl implements SchoolStudentPOService {

    @Autowired
    private SchoolStudentPODao schoolStudentPODao;

    @Override
    public int insert(SchoolStudentPO schoolStudentPO) {
        return schoolStudentPODao.insert(schoolStudentPO);
    }

    @Override
    public int insertSelective(SchoolStudentPO schoolStudentPO) {
        return schoolStudentPODao.insertSelective(schoolStudentPO);
    }

    /**
     * 事务注解说明：
     * propagation = Propagation.REQUIRED 支持当前事务，如果当前没有事务，就新建一个事务
     * isolation = Isolation.DEFAULT      使用数据库默认的事务隔离级别
     * timeout = 36000                    事务超时时间，单位毫秒
     * rollbackFor = Exception.class      回滚条件，程序抛出 Exception 级别的异常
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    public int insertList(List<SchoolStudentPO> schoolStudentPOs) {
        return schoolStudentPODao.insertList(schoolStudentPOs);
    }

    @Override
    public SchoolStudentPO selectById(Integer id) {
        return schoolStudentPODao.selectById(id);
    }

    @Override
    public SchoolStudentPO selectByUUID(String uuid) {
        return schoolStudentPODao.selectByUUID(uuid);
    }

    @Override
    public int deleteById(Integer id) {
        return schoolStudentPODao.deleteById(id);
    }

    @Override
    public int deleteByUUID(String uuid) {
        return schoolStudentPODao.deleteByUUID(uuid);
    }

    @Override
    public int updateById(SchoolStudentPO schoolStudentPO) {
        return schoolStudentPODao.updateById(schoolStudentPO);
    }

    @Override
    public int updateByUUID(SchoolStudentPO schoolStudentPO) {
        return schoolStudentPODao.updateByUUID(schoolStudentPO);
    }

    @Override
    public PageInfo<SchoolStudentPO> findAll(int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize > 100 ? 100 : pageSize);

        List<SchoolStudentPO> schoolStudentPOs = schoolStudentPODao.findAll();

        return new PageInfo<SchoolStudentPO>(schoolStudentPOs);
    }
}