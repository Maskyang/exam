package com.mask.exam.service;

import com.mask.exam.domain.TblExam;
import com.mask.exam.domain.TblExamExample;
import com.mask.exam.mapper.TblExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    TblExamMapper tblExamMapper;

    @Override
    public int countByExample(TblExamExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(TblExamExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer examId) {
        return tblExamMapper.deleteByPrimaryKey(examId);
    }

    @Override
    public int insert(TblExam record) {
        return tblExamMapper.insert(record);
    }

    @Override
    public int insertSelective(TblExam record) {
        return 0;
    }

    @Override
    public List<TblExam> selectByExample(TblExamExample example) {
        return tblExamMapper.selectByExample(example);
    }

    @Override
    public TblExam selectByPrimaryKey(Integer examId) {
        return tblExamMapper.selectByPrimaryKey(examId);
    }

    @Override
    public int updateByExampleSelective(TblExam record, TblExamExample example) {
        return 0;
    }

    @Override
    public int updateByExample(TblExam record, TblExamExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(TblExam record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TblExam record) {
        return tblExamMapper.updateByPrimaryKey(record);
    }
}
