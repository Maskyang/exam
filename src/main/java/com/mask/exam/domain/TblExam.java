package com.mask.exam.domain;

public class TblExam {
    private Integer examId;

    private String examType;

    private String isUse;

    private String isRadio;

    private String examDetail;

    private String choiseA;

    private String choiseB;

    private String choiseC;

    private String choiseD;

    private String answer;

    private String answerDetail;

    private Integer userId;

    public Integer getExamId() {
        return examId;
    }

    public void setExamId(Integer examId) {
        this.examId = examId;
    }

    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType == null ? null : examType.trim();
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse == null ? null : isUse.trim();
    }

    public String getIsRadio() {
        return isRadio;
    }

    public void setIsRadio(String isRadio) {
        this.isRadio = isRadio == null ? null : isRadio.trim();
    }

    public String getExamDetail() {
        return examDetail;
    }

    public void setExamDetail(String examDetail) {
        this.examDetail = examDetail == null ? null : examDetail.trim();
    }

    public String getChoiseA() {
        return choiseA;
    }

    public void setChoiseA(String choiseA) {
        this.choiseA = choiseA == null ? null : choiseA.trim();
    }

    public String getChoiseB() {
        return choiseB;
    }

    public void setChoiseB(String choiseB) {
        this.choiseB = choiseB == null ? null : choiseB.trim();
    }

    public String getChoiseC() {
        return choiseC;
    }

    public void setChoiseC(String choiseC) {
        this.choiseC = choiseC == null ? null : choiseC.trim();
    }

    public String getChoiseD() {
        return choiseD;
    }

    public void setChoiseD(String choiseD) {
        this.choiseD = choiseD == null ? null : choiseD.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getAnswerDetail() {
        return answerDetail;
    }

    public void setAnswerDetail(String answerDetail) {
        this.answerDetail = answerDetail == null ? null : answerDetail.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}