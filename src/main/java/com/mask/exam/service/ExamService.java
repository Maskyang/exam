package com.mask.exam.service;

import com.mask.exam.domain.TblExam;
import com.mask.exam.domain.TblExamExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExamService {
    int countByExample(TblExamExample example);

    int deleteByExample(TblExamExample example);

    int deleteByPrimaryKey(Integer examId);

    int insert(TblExam record);

    int insertSelective(TblExam record);

    List<TblExam> selectByExample(TblExamExample example);

    TblExam selectByPrimaryKey(Integer examId);

    int updateByExampleSelective(@Param("record") TblExam record, @Param("example") TblExamExample example);

    int updateByExample(@Param("record") TblExam record, @Param("example") TblExamExample example);

    int updateByPrimaryKeySelective(TblExam record);

    int updateByPrimaryKey(TblExam record);

    String readExcelFile( MultipartFile file,int userid);

}