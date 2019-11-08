package com.mask.exam.service;

import com.mask.exam.domain.TblTitle;
import com.mask.exam.domain.TblTitleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TitleService {
    int countByExample(TblTitleExample example);

    int deleteByExample(TblTitleExample example);

    int deleteByPrimaryKey(Integer titleId);

    int insert(TblTitle record);

    int insertSelective(TblTitle record);

    List<TblTitle> selectByExample(TblTitleExample example);

    TblTitle selectByPrimaryKey(Integer titleId);

    int updateByExampleSelective(@Param("record") TblTitle record, @Param("example") TblTitleExample example);

    int updateByExample(@Param("record") TblTitle record, @Param("example") TblTitleExample example);

    int updateByPrimaryKeySelective(TblTitle record);

    int updateByPrimaryKey(TblTitle record);
}