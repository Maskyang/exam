package com.mask.exam.domain;

import java.util.ArrayList;
import java.util.List;

public class TblExamExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TblExamExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andExamIdIsNull() {
            addCriterion("exam_id is null");
            return (Criteria) this;
        }

        public Criteria andExamIdIsNotNull() {
            addCriterion("exam_id is not null");
            return (Criteria) this;
        }

        public Criteria andExamIdEqualTo(Integer value) {
            addCriterion("exam_id =", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotEqualTo(Integer value) {
            addCriterion("exam_id <>", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThan(Integer value) {
            addCriterion("exam_id >", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("exam_id >=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThan(Integer value) {
            addCriterion("exam_id <", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdLessThanOrEqualTo(Integer value) {
            addCriterion("exam_id <=", value, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdIn(List<Integer> values) {
            addCriterion("exam_id in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotIn(List<Integer> values) {
            addCriterion("exam_id not in", values, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdBetween(Integer value1, Integer value2) {
            addCriterion("exam_id between", value1, value2, "examId");
            return (Criteria) this;
        }

        public Criteria andExamIdNotBetween(Integer value1, Integer value2) {
            addCriterion("exam_id not between", value1, value2, "examId");
            return (Criteria) this;
        }

        public Criteria andExamTypeIsNull() {
            addCriterion("exam_type is null");
            return (Criteria) this;
        }

        public Criteria andExamTypeIsNotNull() {
            addCriterion("exam_type is not null");
            return (Criteria) this;
        }

        public Criteria andExamTypeEqualTo(String value) {
            addCriterion("exam_type =", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotEqualTo(String value) {
            addCriterion("exam_type <>", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeGreaterThan(String value) {
            addCriterion("exam_type >", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeGreaterThanOrEqualTo(String value) {
            addCriterion("exam_type >=", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeLessThan(String value) {
            addCriterion("exam_type <", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeLessThanOrEqualTo(String value) {
            addCriterion("exam_type <=", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeLike(String value) {
            addCriterion("exam_type like", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotLike(String value) {
            addCriterion("exam_type not like", value, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeIn(List<String> values) {
            addCriterion("exam_type in", values, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotIn(List<String> values) {
            addCriterion("exam_type not in", values, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeBetween(String value1, String value2) {
            addCriterion("exam_type between", value1, value2, "examType");
            return (Criteria) this;
        }

        public Criteria andExamTypeNotBetween(String value1, String value2) {
            addCriterion("exam_type not between", value1, value2, "examType");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNull() {
            addCriterion("is_use is null");
            return (Criteria) this;
        }

        public Criteria andIsUseIsNotNull() {
            addCriterion("is_use is not null");
            return (Criteria) this;
        }

        public Criteria andIsUseEqualTo(String value) {
            addCriterion("is_use =", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotEqualTo(String value) {
            addCriterion("is_use <>", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThan(String value) {
            addCriterion("is_use >", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseGreaterThanOrEqualTo(String value) {
            addCriterion("is_use >=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThan(String value) {
            addCriterion("is_use <", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLessThanOrEqualTo(String value) {
            addCriterion("is_use <=", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseLike(String value) {
            addCriterion("is_use like", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotLike(String value) {
            addCriterion("is_use not like", value, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseIn(List<String> values) {
            addCriterion("is_use in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotIn(List<String> values) {
            addCriterion("is_use not in", values, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseBetween(String value1, String value2) {
            addCriterion("is_use between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsUseNotBetween(String value1, String value2) {
            addCriterion("is_use not between", value1, value2, "isUse");
            return (Criteria) this;
        }

        public Criteria andIsRadioIsNull() {
            addCriterion("is_radio is null");
            return (Criteria) this;
        }

        public Criteria andIsRadioIsNotNull() {
            addCriterion("is_radio is not null");
            return (Criteria) this;
        }

        public Criteria andIsRadioEqualTo(String value) {
            addCriterion("is_radio =", value, "isRadio");
            return (Criteria) this;
        }

        public Criteria andIsRadioNotEqualTo(String value) {
            addCriterion("is_radio <>", value, "isRadio");
            return (Criteria) this;
        }

        public Criteria andIsRadioGreaterThan(String value) {
            addCriterion("is_radio >", value, "isRadio");
            return (Criteria) this;
        }

        public Criteria andIsRadioGreaterThanOrEqualTo(String value) {
            addCriterion("is_radio >=", value, "isRadio");
            return (Criteria) this;
        }

        public Criteria andIsRadioLessThan(String value) {
            addCriterion("is_radio <", value, "isRadio");
            return (Criteria) this;
        }

        public Criteria andIsRadioLessThanOrEqualTo(String value) {
            addCriterion("is_radio <=", value, "isRadio");
            return (Criteria) this;
        }

        public Criteria andIsRadioLike(String value) {
            addCriterion("is_radio like", value, "isRadio");
            return (Criteria) this;
        }

        public Criteria andIsRadioNotLike(String value) {
            addCriterion("is_radio not like", value, "isRadio");
            return (Criteria) this;
        }

        public Criteria andIsRadioIn(List<String> values) {
            addCriterion("is_radio in", values, "isRadio");
            return (Criteria) this;
        }

        public Criteria andIsRadioNotIn(List<String> values) {
            addCriterion("is_radio not in", values, "isRadio");
            return (Criteria) this;
        }

        public Criteria andIsRadioBetween(String value1, String value2) {
            addCriterion("is_radio between", value1, value2, "isRadio");
            return (Criteria) this;
        }

        public Criteria andIsRadioNotBetween(String value1, String value2) {
            addCriterion("is_radio not between", value1, value2, "isRadio");
            return (Criteria) this;
        }

        public Criteria andExamDetailIsNull() {
            addCriterion("exam_detail is null");
            return (Criteria) this;
        }

        public Criteria andExamDetailIsNotNull() {
            addCriterion("exam_detail is not null");
            return (Criteria) this;
        }

        public Criteria andExamDetailEqualTo(String value) {
            addCriterion("exam_detail =", value, "examDetail");
            return (Criteria) this;
        }

        public Criteria andExamDetailNotEqualTo(String value) {
            addCriterion("exam_detail <>", value, "examDetail");
            return (Criteria) this;
        }

        public Criteria andExamDetailGreaterThan(String value) {
            addCriterion("exam_detail >", value, "examDetail");
            return (Criteria) this;
        }

        public Criteria andExamDetailGreaterThanOrEqualTo(String value) {
            addCriterion("exam_detail >=", value, "examDetail");
            return (Criteria) this;
        }

        public Criteria andExamDetailLessThan(String value) {
            addCriterion("exam_detail <", value, "examDetail");
            return (Criteria) this;
        }

        public Criteria andExamDetailLessThanOrEqualTo(String value) {
            addCriterion("exam_detail <=", value, "examDetail");
            return (Criteria) this;
        }

        public Criteria andExamDetailLike(String value) {
            addCriterion("exam_detail like", value, "examDetail");
            return (Criteria) this;
        }

        public Criteria andExamDetailNotLike(String value) {
            addCriterion("exam_detail not like", value, "examDetail");
            return (Criteria) this;
        }

        public Criteria andExamDetailIn(List<String> values) {
            addCriterion("exam_detail in", values, "examDetail");
            return (Criteria) this;
        }

        public Criteria andExamDetailNotIn(List<String> values) {
            addCriterion("exam_detail not in", values, "examDetail");
            return (Criteria) this;
        }

        public Criteria andExamDetailBetween(String value1, String value2) {
            addCriterion("exam_detail between", value1, value2, "examDetail");
            return (Criteria) this;
        }

        public Criteria andExamDetailNotBetween(String value1, String value2) {
            addCriterion("exam_detail not between", value1, value2, "examDetail");
            return (Criteria) this;
        }

        public Criteria andChoiseAIsNull() {
            addCriterion("choise_a is null");
            return (Criteria) this;
        }

        public Criteria andChoiseAIsNotNull() {
            addCriterion("choise_a is not null");
            return (Criteria) this;
        }

        public Criteria andChoiseAEqualTo(String value) {
            addCriterion("choise_a =", value, "choiseA");
            return (Criteria) this;
        }

        public Criteria andChoiseANotEqualTo(String value) {
            addCriterion("choise_a <>", value, "choiseA");
            return (Criteria) this;
        }

        public Criteria andChoiseAGreaterThan(String value) {
            addCriterion("choise_a >", value, "choiseA");
            return (Criteria) this;
        }

        public Criteria andChoiseAGreaterThanOrEqualTo(String value) {
            addCriterion("choise_a >=", value, "choiseA");
            return (Criteria) this;
        }

        public Criteria andChoiseALessThan(String value) {
            addCriterion("choise_a <", value, "choiseA");
            return (Criteria) this;
        }

        public Criteria andChoiseALessThanOrEqualTo(String value) {
            addCriterion("choise_a <=", value, "choiseA");
            return (Criteria) this;
        }

        public Criteria andChoiseALike(String value) {
            addCriterion("choise_a like", value, "choiseA");
            return (Criteria) this;
        }

        public Criteria andChoiseANotLike(String value) {
            addCriterion("choise_a not like", value, "choiseA");
            return (Criteria) this;
        }

        public Criteria andChoiseAIn(List<String> values) {
            addCriterion("choise_a in", values, "choiseA");
            return (Criteria) this;
        }

        public Criteria andChoiseANotIn(List<String> values) {
            addCriterion("choise_a not in", values, "choiseA");
            return (Criteria) this;
        }

        public Criteria andChoiseABetween(String value1, String value2) {
            addCriterion("choise_a between", value1, value2, "choiseA");
            return (Criteria) this;
        }

        public Criteria andChoiseANotBetween(String value1, String value2) {
            addCriterion("choise_a not between", value1, value2, "choiseA");
            return (Criteria) this;
        }

        public Criteria andChoiseBIsNull() {
            addCriterion("choise_b is null");
            return (Criteria) this;
        }

        public Criteria andChoiseBIsNotNull() {
            addCriterion("choise_b is not null");
            return (Criteria) this;
        }

        public Criteria andChoiseBEqualTo(String value) {
            addCriterion("choise_b =", value, "choiseB");
            return (Criteria) this;
        }

        public Criteria andChoiseBNotEqualTo(String value) {
            addCriterion("choise_b <>", value, "choiseB");
            return (Criteria) this;
        }

        public Criteria andChoiseBGreaterThan(String value) {
            addCriterion("choise_b >", value, "choiseB");
            return (Criteria) this;
        }

        public Criteria andChoiseBGreaterThanOrEqualTo(String value) {
            addCriterion("choise_b >=", value, "choiseB");
            return (Criteria) this;
        }

        public Criteria andChoiseBLessThan(String value) {
            addCriterion("choise_b <", value, "choiseB");
            return (Criteria) this;
        }

        public Criteria andChoiseBLessThanOrEqualTo(String value) {
            addCriterion("choise_b <=", value, "choiseB");
            return (Criteria) this;
        }

        public Criteria andChoiseBLike(String value) {
            addCriterion("choise_b like", value, "choiseB");
            return (Criteria) this;
        }

        public Criteria andChoiseBNotLike(String value) {
            addCriterion("choise_b not like", value, "choiseB");
            return (Criteria) this;
        }

        public Criteria andChoiseBIn(List<String> values) {
            addCriterion("choise_b in", values, "choiseB");
            return (Criteria) this;
        }

        public Criteria andChoiseBNotIn(List<String> values) {
            addCriterion("choise_b not in", values, "choiseB");
            return (Criteria) this;
        }

        public Criteria andChoiseBBetween(String value1, String value2) {
            addCriterion("choise_b between", value1, value2, "choiseB");
            return (Criteria) this;
        }

        public Criteria andChoiseBNotBetween(String value1, String value2) {
            addCriterion("choise_b not between", value1, value2, "choiseB");
            return (Criteria) this;
        }

        public Criteria andChoiseCIsNull() {
            addCriterion("choise_c is null");
            return (Criteria) this;
        }

        public Criteria andChoiseCIsNotNull() {
            addCriterion("choise_c is not null");
            return (Criteria) this;
        }

        public Criteria andChoiseCEqualTo(String value) {
            addCriterion("choise_c =", value, "choiseC");
            return (Criteria) this;
        }

        public Criteria andChoiseCNotEqualTo(String value) {
            addCriterion("choise_c <>", value, "choiseC");
            return (Criteria) this;
        }

        public Criteria andChoiseCGreaterThan(String value) {
            addCriterion("choise_c >", value, "choiseC");
            return (Criteria) this;
        }

        public Criteria andChoiseCGreaterThanOrEqualTo(String value) {
            addCriterion("choise_c >=", value, "choiseC");
            return (Criteria) this;
        }

        public Criteria andChoiseCLessThan(String value) {
            addCriterion("choise_c <", value, "choiseC");
            return (Criteria) this;
        }

        public Criteria andChoiseCLessThanOrEqualTo(String value) {
            addCriterion("choise_c <=", value, "choiseC");
            return (Criteria) this;
        }

        public Criteria andChoiseCLike(String value) {
            addCriterion("choise_c like", value, "choiseC");
            return (Criteria) this;
        }

        public Criteria andChoiseCNotLike(String value) {
            addCriterion("choise_c not like", value, "choiseC");
            return (Criteria) this;
        }

        public Criteria andChoiseCIn(List<String> values) {
            addCriterion("choise_c in", values, "choiseC");
            return (Criteria) this;
        }

        public Criteria andChoiseCNotIn(List<String> values) {
            addCriterion("choise_c not in", values, "choiseC");
            return (Criteria) this;
        }

        public Criteria andChoiseCBetween(String value1, String value2) {
            addCriterion("choise_c between", value1, value2, "choiseC");
            return (Criteria) this;
        }

        public Criteria andChoiseCNotBetween(String value1, String value2) {
            addCriterion("choise_c not between", value1, value2, "choiseC");
            return (Criteria) this;
        }

        public Criteria andChoiseDIsNull() {
            addCriterion("choise_d is null");
            return (Criteria) this;
        }

        public Criteria andChoiseDIsNotNull() {
            addCriterion("choise_d is not null");
            return (Criteria) this;
        }

        public Criteria andChoiseDEqualTo(String value) {
            addCriterion("choise_d =", value, "choiseD");
            return (Criteria) this;
        }

        public Criteria andChoiseDNotEqualTo(String value) {
            addCriterion("choise_d <>", value, "choiseD");
            return (Criteria) this;
        }

        public Criteria andChoiseDGreaterThan(String value) {
            addCriterion("choise_d >", value, "choiseD");
            return (Criteria) this;
        }

        public Criteria andChoiseDGreaterThanOrEqualTo(String value) {
            addCriterion("choise_d >=", value, "choiseD");
            return (Criteria) this;
        }

        public Criteria andChoiseDLessThan(String value) {
            addCriterion("choise_d <", value, "choiseD");
            return (Criteria) this;
        }

        public Criteria andChoiseDLessThanOrEqualTo(String value) {
            addCriterion("choise_d <=", value, "choiseD");
            return (Criteria) this;
        }

        public Criteria andChoiseDLike(String value) {
            addCriterion("choise_d like", value, "choiseD");
            return (Criteria) this;
        }

        public Criteria andChoiseDNotLike(String value) {
            addCriterion("choise_d not like", value, "choiseD");
            return (Criteria) this;
        }

        public Criteria andChoiseDIn(List<String> values) {
            addCriterion("choise_d in", values, "choiseD");
            return (Criteria) this;
        }

        public Criteria andChoiseDNotIn(List<String> values) {
            addCriterion("choise_d not in", values, "choiseD");
            return (Criteria) this;
        }

        public Criteria andChoiseDBetween(String value1, String value2) {
            addCriterion("choise_d between", value1, value2, "choiseD");
            return (Criteria) this;
        }

        public Criteria andChoiseDNotBetween(String value1, String value2) {
            addCriterion("choise_d not between", value1, value2, "choiseD");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNull() {
            addCriterion("answer is null");
            return (Criteria) this;
        }

        public Criteria andAnswerIsNotNull() {
            addCriterion("answer is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerEqualTo(String value) {
            addCriterion("answer =", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotEqualTo(String value) {
            addCriterion("answer <>", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThan(String value) {
            addCriterion("answer >", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerGreaterThanOrEqualTo(String value) {
            addCriterion("answer >=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThan(String value) {
            addCriterion("answer <", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLessThanOrEqualTo(String value) {
            addCriterion("answer <=", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerLike(String value) {
            addCriterion("answer like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotLike(String value) {
            addCriterion("answer not like", value, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerIn(List<String> values) {
            addCriterion("answer in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotIn(List<String> values) {
            addCriterion("answer not in", values, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerBetween(String value1, String value2) {
            addCriterion("answer between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerNotBetween(String value1, String value2) {
            addCriterion("answer not between", value1, value2, "answer");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailIsNull() {
            addCriterion("answer_detail is null");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailIsNotNull() {
            addCriterion("answer_detail is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailEqualTo(String value) {
            addCriterion("answer_detail =", value, "answerDetail");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailNotEqualTo(String value) {
            addCriterion("answer_detail <>", value, "answerDetail");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailGreaterThan(String value) {
            addCriterion("answer_detail >", value, "answerDetail");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailGreaterThanOrEqualTo(String value) {
            addCriterion("answer_detail >=", value, "answerDetail");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailLessThan(String value) {
            addCriterion("answer_detail <", value, "answerDetail");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailLessThanOrEqualTo(String value) {
            addCriterion("answer_detail <=", value, "answerDetail");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailLike(String value) {
            addCriterion("answer_detail like", value, "answerDetail");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailNotLike(String value) {
            addCriterion("answer_detail not like", value, "answerDetail");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailIn(List<String> values) {
            addCriterion("answer_detail in", values, "answerDetail");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailNotIn(List<String> values) {
            addCriterion("answer_detail not in", values, "answerDetail");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailBetween(String value1, String value2) {
            addCriterion("answer_detail between", value1, value2, "answerDetail");
            return (Criteria) this;
        }

        public Criteria andAnswerDetailNotBetween(String value1, String value2) {
            addCriterion("answer_detail not between", value1, value2, "answerDetail");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}