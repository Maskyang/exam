package com.mask.exam.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mask.exam.domain.*;
import com.mask.exam.service.ExamService;
import com.mask.exam.service.PaperService;
import com.mask.exam.service.TitleService;
import com.mask.exam.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping
@Controller
public class PaperController {
    Logger logger = LoggerFactory.getLogger(PaperController.class);

    @Autowired
    ExamService examService;

    @Autowired
    TitleService titleService;

    @Autowired
    PaperService paperService;

    @Autowired
    UserService userService;



    //试卷管理页面加分页查询
    @RequestMapping("/filter/paperResult")
    public String paperResult(Map<String, Object> map, Model model,
                             @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                             @RequestParam(defaultValue="10",value="pageSize")Integer pageSize) {
        //为了程序的严谨性，判断非空：
        if(pageNum == null){
            pageNum = 1;   //设置默认当前页
        }
        if(pageNum <= 0){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 10;    //设置默认每页显示的数据数
        }
        logger.info("当前页是："+pageNum+"显示条数是："+pageSize);//1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum,pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            List<TblPaper> paperList = paperService.selectByExample(null);
            System.out.println("分页数据："+paperList);
            map.put("papers",paperList);

            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<TblPaper> pageInfo = new PageInfo<TblPaper>(paperList,pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //5.设置返回的jsp/html等前端页面
        // thymeleaf默认就会拼串classpath:/templates/xxxx.html
        return "paperResult";
    }


    @RequestMapping("/filter/toPaperDetail")
    public String toPaperDetail(int id, Map<String, Object> map,HttpServletRequest request) {
        TblUser sessionUser = (TblUser) request.getSession().getAttribute("user");
        TblTitleExample example = new TblTitleExample();
        TblTitleExample.Criteria criteria = example.createCriteria();
        criteria.andPaperIdEqualTo(id);
        List<TblTitle> titleList = titleService.selectByExample(example);
        if(titleList.size()!=0){
            Collections.sort(titleList, new Comparator<TblTitle>() {
                @Override
                public int compare(TblTitle o1, TblTitle o2) {
                    //按照学生的年龄进行升序排列
                    if(o1.getExamId() > o2.getExamId()){
                        return 1;
                    }
                    if(o1.getExamId() == o2.getExamId()){
                        return 0;
                    }
                    return -1;
                }

            });
            //排序后
            System.out.println("排序后：" + titleList);
            map.put("titles", titleList);
            int userid = titleList.get(0).getUserId();
            TblUser user = userService.selectByPrimaryKey(userid);
            map.put("user", user);
            return "paperDetail";
        }
        map.put("ErrMsg","该试卷未完成答题");
        return "error";

    }


    public String getTime() {
        // 获得当前时间
        Date getDate = Calendar.getInstance().getTime();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getDate);
        System.out.println("日期加时间:" + dateStr);
        return dateStr;
    }

    //导出配件列表
    @RequestMapping(value = "/exportExcel", method={RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public void exportReportStaticsData( HttpServletRequest request, HttpServletResponse response) {
        String mether =request.getMethod();
        //获取查询数据，在service层实现
        List<TblPaper> list = paperService.selectByExample(null);

        HSSFWorkbook wb = new HSSFWorkbook();//声明工
        Sheet sheet = wb.createSheet("试卷列表");//新建表
        sheet.setDefaultColumnWidth(15);//设置表宽
        HSSFCellStyle style = wb.createCellStyle();
        org.apache.poi.hssf.usermodel.HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 12);
        HSSFCellStyle headerStyle = wb.createCellStyle();
        org.apache.poi.hssf.usermodel.HSSFFont headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        headerStyle.setFont(headerFont);
        CellRangeAddress cra0 = new CellRangeAddress(0, 1,0,9);
        sheet.addMergedRegion(cra0);
        sheet.setDefaultColumnWidth((short) 15);
        Row row = sheet.createRow(0);
        Cell cell1 = row.createCell(0);

        cell1.setCellValue("试卷列表");
        cell1.setCellStyle(headerStyle);
        //设置字体样式
        org.apache.poi.hssf.usermodel.HSSFFont titleFont = wb.createFont();
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(titleFont);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        Row row1 = sheet.createRow(2);
        Cell cell = row1.createCell(0);
        cell.setCellValue("试卷ID");
        cell.setCellStyle(style);
        cell = row1.createCell(1);
        cell.setCellValue("试卷类型");
        cell.setCellStyle(style);
        cell = row1.createCell(2);
        cell.setCellValue("开始时间");
        cell.setCellStyle(style);
        cell = row1.createCell(3);
        cell.setCellValue("结束时间");
        cell.setCellStyle(style);
        cell = row1.createCell(4);
        cell.setCellValue("分数");
        cell.setCellStyle(style);
        cell = row1.createCell(5);
        cell.setCellValue("用户ID");
        cell.setCellStyle(style);

//      cell = row1.createCell(8);
//      cell.setCellValue("最小值");
//      cell = row1.createCell(9);
//      cell.setCellValue("最小值时间");
//      cell.setCellStyle(style);
        //时间转字符串的格式
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0, imax = list.size(); i < imax; i++) {
            row1 = sheet.createRow(i + 3);
            if (list.get(i).getPaperId()== null||"".equals(list.get(i).getPaperId())) {
                row1.createCell(0).setCellValue("-");
            } else {
                row1.createCell(0).setCellValue(list.get(i).getPaperId());
            }
            if (list.get(i).getPaperType() == null ||"".equals(list.get(i).getPaperType())) {
                row1.createCell(1).setCellValue("-");
            } else {
                row1.createCell(1).setCellValue(list.get(i).getPaperType());
            }
            if (list.get(i).getStarttime() == null ||"".equals(list.get(i).getStarttime())) {
                row1.createCell(2).setCellValue("-");
            } else {
                row1.createCell(2).setCellValue(list.get(i).getStarttime());
            }
            if (list.get(i).getEndtime() == null||"".equals(list.get(i).getEndtime())) {
                row1.createCell(3).setCellValue("-");
            } else {
                row1.createCell(3).setCellValue(list.get(i).getEndtime());
            }
            if (list.get(i).getMark() == null||"".equals(list.get(i).getMark())) {
                row1.createCell(4).setCellValue("-");
            } else {
                row1.createCell(4).setCellValue(list.get(i).getMark());
            }
            if (list.get(i).getUserid() == null||"".equals(list.get(i).getUserid())) {
                row1.createCell(5).setCellValue("-");
            } else {
                row1.createCell(5).setCellValue(list.get(i).getUserid());
            }


        }
        response.reset();
        response.setContentType("application/msexcel;charset=UTF-8");
        try {
            SimpleDateFormat newsdf=new SimpleDateFormat("yyyyMMddHHmmss");
            String date = newsdf.format(new Date());
            response.addHeader("Content-Disposition", "attachment;filename=\""
                    + new String(("数据权限申请审批表" + date + ".xls").getBytes("GBK"),
                    "ISO8859_1") + "\"");
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "导出失败!");
            e.printStackTrace();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "导出失败!");
            e.printStackTrace();
        }
    }


}
