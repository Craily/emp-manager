package com.example.craily.po;

import java.util.ArrayList;
import java.util.List;

public class JobMenuExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JobMenuExample() {
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

        public Criteria andJobNoIsNull() {
            addCriterion("job_no is null");
            return (Criteria) this;
        }

        public Criteria andJobNoIsNotNull() {
            addCriterion("job_no is not null");
            return (Criteria) this;
        }

        public Criteria andJobNoEqualTo(String value) {
            addCriterion("job_no =", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoNotEqualTo(String value) {
            addCriterion("job_no <>", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoGreaterThan(String value) {
            addCriterion("job_no >", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoGreaterThanOrEqualTo(String value) {
            addCriterion("job_no >=", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoLessThan(String value) {
            addCriterion("job_no <", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoLessThanOrEqualTo(String value) {
            addCriterion("job_no <=", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoLike(String value) {
            addCriterion("job_no like", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoNotLike(String value) {
            addCriterion("job_no not like", value, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoIn(List<String> values) {
            addCriterion("job_no in", values, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoNotIn(List<String> values) {
            addCriterion("job_no not in", values, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoBetween(String value1, String value2) {
            addCriterion("job_no between", value1, value2, "jobNo");
            return (Criteria) this;
        }

        public Criteria andJobNoNotBetween(String value1, String value2) {
            addCriterion("job_no not between", value1, value2, "jobNo");
            return (Criteria) this;
        }

        public Criteria andMenuNoIsNull() {
            addCriterion("menu_no is null");
            return (Criteria) this;
        }

        public Criteria andMenuNoIsNotNull() {
            addCriterion("menu_no is not null");
            return (Criteria) this;
        }

        public Criteria andMenuNoEqualTo(String value) {
            addCriterion("menu_no =", value, "menuNo");
            return (Criteria) this;
        }

        public Criteria andMenuNoNotEqualTo(String value) {
            addCriterion("menu_no <>", value, "menuNo");
            return (Criteria) this;
        }

        public Criteria andMenuNoGreaterThan(String value) {
            addCriterion("menu_no >", value, "menuNo");
            return (Criteria) this;
        }

        public Criteria andMenuNoGreaterThanOrEqualTo(String value) {
            addCriterion("menu_no >=", value, "menuNo");
            return (Criteria) this;
        }

        public Criteria andMenuNoLessThan(String value) {
            addCriterion("menu_no <", value, "menuNo");
            return (Criteria) this;
        }

        public Criteria andMenuNoLessThanOrEqualTo(String value) {
            addCriterion("menu_no <=", value, "menuNo");
            return (Criteria) this;
        }

        public Criteria andMenuNoLike(String value) {
            addCriterion("menu_no like", value, "menuNo");
            return (Criteria) this;
        }

        public Criteria andMenuNoNotLike(String value) {
            addCriterion("menu_no not like", value, "menuNo");
            return (Criteria) this;
        }

        public Criteria andMenuNoIn(List<String> values) {
            addCriterion("menu_no in", values, "menuNo");
            return (Criteria) this;
        }

        public Criteria andMenuNoNotIn(List<String> values) {
            addCriterion("menu_no not in", values, "menuNo");
            return (Criteria) this;
        }

        public Criteria andMenuNoBetween(String value1, String value2) {
            addCriterion("menu_no between", value1, value2, "menuNo");
            return (Criteria) this;
        }

        public Criteria andMenuNoNotBetween(String value1, String value2) {
            addCriterion("menu_no not between", value1, value2, "menuNo");
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