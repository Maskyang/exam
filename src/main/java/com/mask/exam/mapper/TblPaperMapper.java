package com.mask.exam.mapper;

import com.mask.exam.domain.TblPaper;
import com.mask.exam.domain.TblPaperExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TblPaperMapper {
    int countByExample(TblPaperExample example);

    int deleteByExample(TblPaperExample example);

    int deleteByPrimaryKey(Integer paperId);

    int insert(TblPaper record);

    int insertSelective(TblPaper record);

    List<TblPaper> selectByExample(TblPaperExample example);

    TblPaper selectByPrimaryKey(Integer paperId);

    int updateByExampleSelective(@Param("record") TblPaper record, @Param("example") TblPaperExample example);

    int updateByExample(@Param("record") TblPaper record, @Param("example") TblPaperExample example);

    int updateByPrimaryKeySelective(TblPaper record);

    int updateByPrimaryKey(TblPaper record);
}