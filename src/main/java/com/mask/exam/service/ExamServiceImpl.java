package com.mask.exam.service;

import com.mask.exam.Des3util.ReadExcel;
import com.mask.exam.domain.TblExam;
import com.mask.exam.domain.TblExamExample;
import com.mask.exam.mapper.TblExamMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    @Override
    public String readExcelFile(MultipartFile file, int userid) {
        String result="";
        //创建处理EXCEL的类
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单
        List<TblExam> examList=readExcel.getExcelInfo(file);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,
        //和你具体业务有关,这里不做具体的示范
        if (examList!=null&&!examList.isEmpty()){
            for(TblExam exam: examList){
                System.out.println(exam.toString());
                exam.setUserId(userid);
                tblExamMapper.insert(exam);
            }
            result="上传成功";
        }else{
            result="上传失败";
        }
        return result;
    }
}
