package com.mask.exam.service;

import com.mask.exam.domain.TblTitle;
import com.mask.exam.domain.TblTitleExample;
import com.mask.exam.mapper.TblTitleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TitleServiceImpl implements TitleService {

    @Autowired
    TblTitleMapper titleMapper;

    @Override
    public int countByExample(TblTitleExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(TblTitleExample example) {
        return titleMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer titleId) {
        return 0;
    }

    @Override
    public int insert(TblTitle record) {
        return titleMapper.insert(record);
    }

    @Override
    public int insertSelective(TblTitle record) {
        return 0;
    }

    @Override
    public List<TblTitle> selectByExample(TblTitleExample example) {
        return titleMapper.selectByExample(example);
    }

    @Override
    public TblTitle selectByPrimaryKey(Integer titleId) {
        return null;
    }

    @Override
    public int updateByExampleSelective(TblTitle record, TblTitleExample example) {
        return 0;
    }

    @Override
    public int updateByExample(TblTitle record, TblTitleExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(TblTitle record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TblTitle record) {
        return 0;
    }
}
