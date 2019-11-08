package com.mask.exam.service;

import com.mask.exam.domain.TblPaper;
import com.mask.exam.domain.TblPaperExample;
import com.mask.exam.mapper.TblPaperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperServiceImpl implements PaperService{
    @Autowired
    TblPaperMapper paperMapper;

    @Override
    public int countByExample(TblPaperExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(TblPaperExample example) {
        return paperMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Integer paperId) {
        return 0;
    }

    @Override
    public int insert(TblPaper record) {
        return paperMapper.insert(record);
    }

    @Override
    public int insertSelective(TblPaper record) {
        return 0;
    }

    @Override
    public List<TblPaper> selectByExample(TblPaperExample example) {
        return paperMapper.selectByExample(example);
    }

    @Override
    public TblPaper selectByPrimaryKey(Integer paperId) {
        return paperMapper.selectByPrimaryKey(paperId);
    }

    @Override
    public int updateByExampleSelective(TblPaper record, TblPaperExample example) {
        return 0;
    }

    @Override
    public int updateByExample(TblPaper record, TblPaperExample example) {
        return paperMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(TblPaper record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TblPaper record) {
        return paperMapper.updateByPrimaryKey(record);
    }
}
