package com.github.sailboat.notes.repository.slave.dao.impl;

import com.github.sailboat.notes.repository.slave.po.TestEntity;
import com.github.sailboat.notes.repository.slave.mapper.ext.TestMapperExt;
import com.github.sailboat.notes.repository.slave.dao.TestDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author spy
 * @since 2023-12-12
 */
@Service
public class TestDaoImpl extends ServiceImpl<TestMapperExt, TestEntity>implements TestDao {

}
