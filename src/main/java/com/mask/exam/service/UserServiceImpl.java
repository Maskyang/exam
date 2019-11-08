package com.mask.exam.service;

import com.mask.exam.domain.TblUser;
import com.mask.exam.domain.TblUserExample;
import com.mask.exam.mapper.TblUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    TblUserMapper userMapper;

    @Override
    public int countByExample(TblUserExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(TblUserExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int insert(TblUser record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(TblUser record) {
        return 0;
    }

    @Override
    public List<TblUser> selectByExample(TblUserExample example) {
        return userMapper.selectByExample(example);
    }

    @Override
    public TblUser selectByPrimaryKey(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByExampleSelective(TblUser record, TblUserExample example) {
        return 0;
    }

    @Override
    public int updateByExample(TblUser record, TblUserExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(TblUser record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TblUser record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public TblUser login(String name) {
        return userMapper.login(name);
    }
}
