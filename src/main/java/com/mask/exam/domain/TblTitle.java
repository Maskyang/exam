package com.mask.exam.domain;

public class TblTitle {
    private Integer titleId;

    private String titleResult;

    private Integer istrue;

    private Integer userId;

    private Integer examId;

    private Integer paperId;

    public Integer getTitleId() {
        return titleId;
    }

    public void setTitleId(Integer titleId) {
        this.titleId = titleId;
    }

    public String getTitleResult() {
        return titleResult;
    }

    public void setTitleResult(String titleResult) {
        this.titleResult = titleResult == null ? null : titleResult.trim();
    }

    public Integer getIstrue() {
        return istrue;
    }

    public void setIstrue(Integer istrue) {
        this.istrue = istrue;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public Integer getPaperId() {
        return paperId;
    }

    public void setPaperId(Integer paperId) {
        this.paperId = paperId;
    }
}