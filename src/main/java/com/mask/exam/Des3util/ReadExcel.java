package com.mask.exam.Des3util;

import com.mask.exam.domain.TblExam;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liuchj
 * @version 1.0
 */
public class ReadExcel {
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;
    //构造方法
    public ReadExcel(){}
    //获取总行数
    public int getTotalRows()  { return totalRows;}
    //获取总列数
    public int getTotalCells() {  return totalCells;}
    //获取错误信息
    public String getErrorInfo() { return errorMsg; }

    /**
     * 读EXCEL文件，获取信息集合
     * @param mFile
     * @return
     */
    public List<TblExam> getExcelInfo(MultipartFile mFile) {
        List<TblExam> examList = new ArrayList<>();
        String fileName = mFile.getOriginalFilename();//获取文件名
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            examList = createExcel(mFile.getInputStream(), isExcel2003);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return examList;
    }

    /**
     * 根据excel里面的内容读取客户信息
     * @param is 输入流
     * @param isExcel2003 excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public List<TblExam> createExcel(InputStream is, boolean isExcel2003) {
        List<TblExam> examList = new ArrayList<>();
        try{
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }
            examList = readExcelValue(wb);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return examList;
    }

    /**
     * 读取Excel里面客户的信息
     * @param wb
     * @return
     */
    private List<TblExam> readExcelValue(Workbook wb) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<TblExam> examList = new ArrayList<TblExam>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }
            TblExam exam = new TblExam();
            // 循环Excel的列
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        //如果是纯数字,比如你写的是25,cell.getNumericCellValue()获得是25.0,通过截取字符串去掉.0获得25
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String examType = String.valueOf(cell.getNumericCellValue());
                            exam.setExamType(examType.substring(0, examType.length()-2>0?examType.length()-2:1));//名称
                        }else{
                            exam.setExamType(cell.getStringCellValue());//名称
                        }
                    } else if (c == 1) {
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String isUse = String.valueOf(cell.getNumericCellValue());
                            exam.setIsUse(isUse.substring(0, isUse.length()-2>0?isUse.length()-2:1));//性别
                        }else{
                            exam.setIsUse(cell.getStringCellValue());//性别
                        }
                    } else if (c == 2){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String isRadio = String.valueOf(cell.getNumericCellValue());
                            exam.setIsRadio(isRadio.substring(0, isRadio.length()-2>0?isRadio.length()-2:1));//年龄
                        }else{
                            exam.setIsRadio(cell.getStringCellValue());//年龄
                        }
                    }else if (c == 3){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String examDetail = String.valueOf(cell.getNumericCellValue());
                            exam.setExamDetail(examDetail.substring(0, examDetail.length()-2>0?examDetail.length()-2:1));//年龄
                        }else{
                            exam.setExamDetail(cell.getStringCellValue());//年龄
                        }
                    }else if (c == 4){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String choiseA = String.valueOf(cell.getNumericCellValue());
                            exam.setChoiseA(choiseA.substring(0, choiseA.length()-2>0?choiseA.length()-2:1));//年龄
                        }else{
                            exam.setChoiseA(cell.getStringCellValue());//年龄
                        }
                    }else if (c == 5){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String choiseB = String.valueOf(cell.getNumericCellValue());
                            exam.setChoiseB(choiseB.substring(0, choiseB.length()-2>0?choiseB.length()-2:1));//年龄
                        }else{
                            exam.setChoiseB(cell.getStringCellValue());//年龄
                        }
                    }else if (c == 6){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String choiseC = String.valueOf(cell.getNumericCellValue());
                            exam.setChoiseC(choiseC.substring(0, choiseC.length()-2>0?choiseC.length()-2:1));//年龄
                        }else{
                            exam.setChoiseC(cell.getStringCellValue());//年龄
                        }
                    }else if (c == 7){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String choiseD = String.valueOf(cell.getNumericCellValue());
                            exam.setChoiseD(choiseD.substring(0, choiseD.length()-2>0?choiseD.length()-2:1));//年龄
                        }else{
                            exam.setChoiseD(cell.getStringCellValue());//年龄
                        }
                    }else if (c == 8){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String answer = String.valueOf(cell.getNumericCellValue());
                            exam.setAnswer(answer.substring(0, answer.length()-2>0?answer.length()-2:1));//年龄
                        }else{
                            exam.setAnswer(cell.getStringCellValue());//年龄
                        }
                    }else if (c == 9){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String answerDetail = String.valueOf(cell.getNumericCellValue());
                            exam.setAnswerDetail(answerDetail.substring(0, answerDetail.length()-2>0?answerDetail.length()-2:1));//年龄
                        }else{
                            exam.setAnswerDetail(cell.getStringCellValue());//年龄
                        }
                    }else if (c == 10){
                        if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
                            String eLevel = String.valueOf(cell.getNumericCellValue());
                            exam.seteLevel(eLevel.substring(0, eLevel.length()-2>0?eLevel.length()-2:1));//年龄
                        }else{
                            exam.seteLevel(cell.getStringCellValue());//年龄
                        }
                    }
                }
            }
            // 添加到list
            examList.add(exam);
        }
        return examList;
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    //@描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath)  {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
}
