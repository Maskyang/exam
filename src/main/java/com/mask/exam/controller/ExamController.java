package com.mask.exam.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mask.exam.domain.*;
import com.mask.exam.service.ExamService;
import com.mask.exam.service.PaperService;
import com.mask.exam.service.TitleService;
import com.mask.exam.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping
@Controller
public class ExamController {
    Logger logger = LoggerFactory.getLogger(ExamController.class);

    @Autowired
    ExamService examService;

    @Autowired
    TitleService titleService;

    @Autowired
    PaperService paperService;

    @Autowired
    UserService userService;

    @RequestMapping("/filter/paper")
    public String paper() {
        return "paper";
    }

    //创建paper
    @RequestMapping("/filter/createPaper")
    public String createPaper(HttpServletRequest request, Map<String, Object> map, TblPaper paper) {
        TblUser sessionUser = (TblUser) request.getSession().getAttribute("user");
        TblPaperExample example = new TblPaperExample();
        TblPaperExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(sessionUser.getUserId());
        List<TblPaper> paperList = paperService.selectByExample(example);
        //如果这个userid考试过则不允许创建paper,直接返回成绩页面
        if(paperList.size()==0){
            paper.setUserid(sessionUser.getUserId());
            paper.setPaperType("isv");
            paper.setStarttime(getTime());
            paper.setMark(0);
            paperService.insert(paper);
            TblPaperExample example1 = new TblPaperExample();
            TblPaperExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andStarttimeEqualTo(paper.getStarttime());
            List<TblPaper> paperList2 = paperService.selectByExample(example1);
            request.getSession().setAttribute("paper", paperList2.get(0));
            return "redirect:/filter/exam";

        }else if(paperList.size()!=0&&paperList.get(0).getRadiotime()==null){

            TblPaperExample example1 = new TblPaperExample();
            TblPaperExample.Criteria criteria1 = example1.createCriteria();
            criteria1.andUseridEqualTo(paperList.get(0).getUserid());
            List<TblPaper> paperList2 = paperService.selectByExample(example1);
            request.getSession().setAttribute("paper", paperList2.get(0));

            return "redirect:/filter/exam";
        }else{
            return "redirect:/filter/toExamResult";

        }

    }


    //跳转成绩查询
    @RequestMapping("/filter/toExamResult")
    public String toExamResult(HttpServletRequest request, Map<String, Object> map, TblPaper paper,Model model,
                               @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                               @RequestParam(defaultValue="10",value="pageSize")Integer pageSize) {
        TblUser sessionUser = (TblUser) request.getSession().getAttribute("user");
        TblPaperExample example = new TblPaperExample();
        TblPaperExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(sessionUser.getUserId());
        List<TblPaper> paperList = paperService.selectByExample(example);
        if(paperList!=null){
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
            logger.info("--------------跳转成绩查询---------------");
            logger.info("当前页是："+pageNum+"显示条数是："+pageSize);//1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
            PageHelper.startPage(pageNum,pageSize);
            //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
            try {
                TblTitleExample example2 = new TblTitleExample();
                TblTitleExample.Criteria criteria2 = example2.createCriteria();
                criteria2.andUserIdEqualTo(sessionUser.getUserId());
                List<TblTitle> titleList = titleService.selectByExample(example2);
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
                System.out.println("排序后：" + titleList.toString());
                map.put("titleList", titleList);
                map.put("paper", paperList.get(0).getMark());
                System.out.println("分页数据："+titleList);

                //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
                PageInfo<TblTitle> pageInfo = new PageInfo<TblTitle>(titleList,pageSize);
                //4.使用model/map/modelandview等带回前端
                model.addAttribute("pageInfo",pageInfo);
            }finally {
                PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
            }
            //5.设置返回的jsp/html等前端页面
            // thymeleaf默认就会拼串classpath:/templates/xxxx.html
            return "examResult";
        }else{
            map.put("ErrMsg","列表为空!");

            return "error";
        }


    }


    //查看答案
    @RequestMapping("/filter/ckDA")
    public String ckDA(HttpServletRequest request, Map<String, Object> map, Model model,
                       @RequestParam(required = false,defaultValue="1",value="pageNum")Integer pageNum,
                       @RequestParam(defaultValue="10",value="pageSize")Integer pageSize) {
        TblUser sessionUser = (TblUser) request.getSession().getAttribute("user");
        TblPaperExample example = new TblPaperExample();
        TblPaperExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(sessionUser.getUserId());
        List<TblPaper> paperList = paperService.selectByExample(example);
        if(paperList!=null){
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
                List<TblExam> examList = examService.selectByExample(null);
                System.out.println("分页数据："+examList);
                map.put("exams",examList);

                //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
                PageInfo<TblExam> pageInfo = new PageInfo<TblExam>(examList,pageSize);
                //4.使用model/map/modelandview等带回前端
                model.addAttribute("pageInfo",pageInfo);
            }finally {
                PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
            }
            //5.设置返回的jsp/html等前端页面
            // thymeleaf默认就会拼串classpath:/templates/xxxx.html
            return "ckDA";
        }else{
            map.put("ErrMsg","列表为空!");

            return "error";
        }
    }


    //跳转单选题
    @RequestMapping("/filter/exam")
    public String exam(HttpServletRequest request, Map<String, Object> map) {
//        TblUser sessionUser = (TblUser) request.getSession().getAttribute("user");
        TblExamExample example = new TblExamExample();
        TblExamExample.Criteria criteria = example.createCriteria();
        criteria.andIsRadioEqualTo("单选");
        criteria.andIsUseEqualTo("可用");
        List<TblExam> examList = examService.selectByExample(example);
        //需求：定时，乱序（随机抽取然后正序），修改
        Collections.shuffle(examList);

        map.put("examList", examList);
        return "exam";
    }


    //提交单选题
    @RequestMapping("/filter/submitRadio")
    public String submitRadio(HttpServletRequest request, Map<String, Object> map, TblExam exam, TblTitle title) throws Exception{
        TblUser sessionUser = (TblUser) request.getSession().getAttribute("user");
        TblPaper sessionPaper = (TblPaper) request.getSession().getAttribute("paper");
        int score = 0;
        if(sessionPaper.getRadiotime()==null) {

            for (int i = 1; i <= 500; i++) {
                //获取examid对应的值,即获取用户当前一题填的答案
                String answer = request.getParameter("exam_" + i);
                //查询id对应的真实answer
                exam = examService.selectByPrimaryKey(i);
                if (exam == null) {
                    System.out.println("第" + i + "题不存在");

                } else if (exam.getIsRadio().equals("单选")&&exam.getIsUse().equals("可用")) {
                    if (exam.getAnswer().equals(answer)) {
                        //试卷+4分
                        score = score + 1;
                        System.out.println("试卷得分:" + score);
                        //并且实际需要记录第几题,得分多少(或者设置当前题目id为1)
                        title.setExamId(i);
                        title.setUserId(sessionUser.getUserId());
                        title.setIstrue(1);
                        title.setTitleResult(answer);
                        title.setPaperId(sessionPaper.getPaperId());
                        titleService.insert(title);

                    } else {
                        //不加分
                        System.out.println("试卷得分:" + score);
                        //并且实际需要记录第几题,得分多少(或者设置当前题目id为0)
                        title.setExamId(i);
                        title.setUserId(sessionUser.getUserId());
                        title.setIstrue(0);
                        title.setTitleResult(answer);
                        title.setPaperId(sessionPaper.getPaperId());
                        titleService.insert(title);
                        System.out.println("第" + i + "题答错了");
                    }
                }
            }
        }else if(sessionPaper.getRadiotime()!=null&&sessionPaper.getEndtime()==null){
            return "redirect:/filter/checkExam";
        }else{
            return "examOver";

        }
        System.out.println("总分:"+score);
        sessionPaper.setMark(score);
        sessionPaper.setRadiotime(getTime());
        TblPaper paper = paperService.selectByPrimaryKey(sessionPaper.getPaperId());
        paper.setRadiotime(getTime());
        paperService.updateByPrimaryKey(paper);
        map.put("mark", score);
        return "redirect:/filter/checkExam";
    }


    //跳转多选题
    @RequestMapping("/filter/checkExam")
    public String checkExam(HttpServletRequest request, Map<String, Object> map) {
        TblExamExample example = new TblExamExample();
        TblExamExample.Criteria criteria = example.createCriteria();
        criteria.andIsRadioEqualTo("多选");
        criteria.andIsUseEqualTo("可用");
        List<TblExam> examList = examService.selectByExample(example);
        Collections.shuffle(examList);

        map.put("examList", examList);
        return "checkExam";
    }


    //交卷
    @RequestMapping("/filter/submitPaper")
    public String submitPaper(HttpServletRequest request, Map<String, Object> map, TblExam exam, TblTitle title,TblPaper paper) throws Exception{
        TblUser sessionUser = (TblUser) request.getSession().getAttribute("user");
        TblPaper sessionPaper = (TblPaper) request.getSession().getAttribute("paper");

        double score = 0;
        //先判断是否有完成时间，有直接跳转成绩页面
        if(sessionPaper.getEndtime()==null) {

            for (int i = 1; i <= 500; i++) {
                //获取examid对应的值,即获取用户当前一题填的答案
                String[] answers = request.getParameterValues("exam_" + i);
                String answer = "";

                //查询id对应的真实answer
                exam = examService.selectByPrimaryKey(i);
                if (exam == null) {
                    System.out.println("第" + i + "题不存在");

                } else if (exam != null&&answers!=null) {
                    for (int j = 0; j < answers.length; j++) {
                        answer += answers[j];
                    }

                    if (exam.getIsRadio().equals("多选")) {
                        if (exam.getAnswer().equals(answer)) {
                            //试卷+4分
                            score = score + 1;
                            System.out.println("试卷得分:" + score);
                            //并且实际需要记录第几题,得分多少(或者设置当前题目id为1)
                            title.setExamId(i);
                            title.setUserId(sessionUser.getUserId());
                            title.setIstrue(1);
                            title.setTitleResult(answer);
                            title.setPaperId(sessionPaper.getPaperId());
                            titleService.insert(title);
                            System.out.println("第" + i + "题答对了");

                        } else {
                            //不加分
                            System.out.println("试卷得分:" + score);
                            //并且实际需要记录第几题,得分多少(或者设置当前题目id为1)
                            title.setExamId(i);
                            title.setUserId(sessionUser.getUserId());
                            title.setIstrue(0);
                            title.setTitleResult(answer);
                            title.setPaperId(sessionPaper.getPaperId());
                            titleService.insert(title);
                            System.out.println("第" + i + "题答错了");
                        }

                    }

                }
            }
        }else{
            return "examOver";
        }
        score+=sessionPaper.getMark();
        TblExamExample example = new TblExamExample();
        TblExamExample.Criteria criteria = example.createCriteria();
        criteria.andIsUseEqualTo("可用");
        List<TblExam> examList = examService.selectByExample(example);
        double allScore = examList.size();
        score = score/allScore*100;
        new BigDecimal(score).setScale(0, BigDecimal.ROUND_HALF_UP);
        int mark = (int)score;
        paper = paperService.selectByPrimaryKey(sessionPaper.getPaperId());
        sessionPaper.setEndtime(getTime());
        sessionPaper.setMark(mark);
        paper.setEndtime(getTime());
        paper.setMark(mark);
        paperService.updateByPrimaryKey(paper);
        map.put("mark", mark);

        return "result";
    }



    //试卷管理页面加分页查询
    @RequestMapping("/filter/examManage")
    public String examManage(Map<String, Object> map, Model model,
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
            List<TblExam> examList = examService.selectByExample(null);
            System.out.println("分页数据："+examList);
            map.put("exams",examList);

            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<TblExam> pageInfo = new PageInfo<TblExam>(examList,pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo",pageInfo);
        }finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //5.设置返回的jsp/html等前端页面
        // thymeleaf默认就会拼串classpath:/templates/xxxx.html
        return "examManage";
    }


    //添加试题
    @RequestMapping("/filter/addExam")
    public String addExam(Map<String, Object> map,TblExam tblExam,HttpServletRequest request) {
        String[] answers =request.getParameterValues("answer");
        String answer = "";
        for(int i=0;i<answers.length;i++){
            answer+=answers[i];
        }
        tblExam.setAnswer(answer);
        TblUser sessionUser = (TblUser) request.getSession().getAttribute("user");
        if(sessionUser.getUserCode().equals("m02")||sessionUser.getUserCode().equals("m01")){
            tblExam.setUserId(sessionUser.getUserId());
            examService.insert(tblExam);
            return "redirect:/filter/examManage";
        }else{
            map.put("ErrMsg","答案不能为空!");

            return "error";
        }

    }


    //删除试题
    @RequestMapping(value = "/filter/deleteExam")
    @ResponseBody
    public Map<String,String> deleteExam(Integer id,HttpServletRequest request) {
        Map<String,String> map = new HashMap<String, String>();
        TblExam exam = examService.selectByPrimaryKey(id);
        TblUser sessionUser = (TblUser) request.getSession().getAttribute("user");
        if(sessionUser.getUserCode().equals("m02")||exam.getUserId().equals(sessionUser.getUserId())){
            TblTitleExample example = new TblTitleExample();
            TblTitleExample.Criteria exampleCriteria = example.createCriteria();
            exampleCriteria.andExamIdEqualTo(id);
            if(titleService.selectByExample(example).size()>0){
                titleService.deleteByExample(example);
            }
            examService.deleteByPrimaryKey(id);
            map.put("result","提交成功");
        }else{
            map.put("result","提交失败");
        }
        return map;
    }


    //修改试题
    @RequestMapping("/filter/editExam")
    public String editExam(TblExam exam, HttpServletRequest request,Map<String, Object> map) {
        TblUser sessionUser = (TblUser) request.getSession().getAttribute("user");

        if(sessionUser.getUserCode().equals("m02")){
            if(exam.getAnswer()!=null){
                String answer=exam.getAnswer().replace(",", "");
                exam.setAnswer(answer);
                exam.setUserId(sessionUser.getUserId());
                examService.updateByPrimaryKey(exam);
                return "redirect:/filter/examManage";
            }else{
                map.put("ErrMsg", "答案不能为空!");

                return "error";

            }

        }else{
            return "error";
        }
    }

    //根据信息查询exam
    @RequestMapping("/filter/searchExam")
    public String searchExam(Map<String, Object> map, Model model, String aq,
                             @RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                             @RequestParam(defaultValue = "10", value = "pageSize") Integer pageSize) {
        //为了程序的严谨性，判断非空：
        if (pageNum == null) {
            pageNum = 1;   //设置默认当前页
        }
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 10;    //设置默认每页显示的数据数
        }
        System.out.println("当前页是：" + pageNum + "显示条数是：" + pageSize);

        //1.引入分页插件,pageNum是第几页，pageSize是每页显示多少条,默认查询总数count
        PageHelper.startPage(pageNum, pageSize);
        //2.紧跟的查询就是一个分页查询-必须紧跟.后面的其他查询不会被分页，除非再次调用PageHelper.startPage
        try {
            TblExamExample example = new TblExamExample();
            TblExamExample.Criteria criteria = example.createCriteria();
            criteria.andExamDetailLike("%" + aq + "%");
            List<TblExam> examList = examService.selectByExample(example);
            System.out.println("分页数据：" + examList);
            map.put("exams", examList);

            //3.使用PageInfo包装查询后的结果,5是连续显示的条数,结果list类型是Page<E>
            PageInfo<TblExam> pageInfo = new PageInfo<TblExam>(examList, pageSize);
            //4.使用model/map/modelandview等带回前端
            model.addAttribute("pageInfo", pageInfo);
        } finally {
            PageHelper.clearPage(); //清理 ThreadLocal 存储的分页参数,保证线程安全
        }
        //5.设置返回的jsp/html等前端页面
        // thymeleaf默认就会拼串classpath:/templates/xxxx.html
        return "examManage";
    }


    public String getTime() {
        // 获得当前时间
        Date getDate = Calendar.getInstance().getTime();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(getDate);
        System.out.println("日期加时间:" + dateStr);
        return dateStr;
    }



}
