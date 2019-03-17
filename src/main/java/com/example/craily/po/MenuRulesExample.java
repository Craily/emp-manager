package com.example.craily.po;

import java.util.ArrayList;
import java.util.List;

public class MenuRulesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MenuRulesExample() {
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

        public Criteria andJobnoIsNull() {
            addCriterion("jobno is null");
            return (Criteria) this;
        }

        public Criteria andJobnoIsNotNull() {
            addCriterion("jobno is not null");
            return (Criteria) this;
        }

        public Criteria andJobnoEqualTo(String value) {
            addCriterion("jobno =", value, "jobno");
            return (Criteria) this;
        }

        public Criteria andJobnoNotEqualTo(String value) {
            addCriterion("jobno <>", value, "jobno");
            return (Criteria) this;
        }

        public Criteria andJobnoGreaterThan(String value) {
            addCriterion("jobno >", value, "jobno");
            return (Criteria) this;
        }

        public Criteria andJobnoGreaterThanOrEqualTo(String value) {
            addCriterion("jobno >=", value, "jobno");
            return (Criteria) this;
        }

        public Criteria andJobnoLessThan(String value) {
            addCriterion("jobno <", value, "jobno");
            return (Criteria) this;
        }

        public Criteria andJobnoLessThanOrEqualTo(String value) {
            addCriterion("jobno <=", value, "jobno");
            return (Criteria) this;
        }

        public Criteria andJobnoLike(String value) {
            addCriterion("jobno like", value, "jobno");
            return (Criteria) this;
        }

        public Criteria andJobnoNotLike(String value) {
            addCriterion("jobno not like", value, "jobno");
            return (Criteria) this;
        }

        public Criteria andJobnoIn(List<String> values) {
            addCriterion("jobno in", values, "jobno");
            return (Criteria) this;
        }

        public Criteria andJobnoNotIn(List<String> values) {
            addCriterion("jobno not in", values, "jobno");
            return (Criteria) this;
        }

        public Criteria andJobnoBetween(String value1, String value2) {
            addCriterion("jobno between", value1, value2, "jobno");
            return (Criteria) this;
        }

        public Criteria andJobnoNotBetween(String value1, String value2) {
            addCriterion("jobno not between", value1, value2, "jobno");
            return (Criteria) this;
        }

        public Criteria andMenunoIsNull() {
            addCriterion("menuno is null");
            return (Criteria) this;
        }

        public Criteria andMenunoIsNotNull() {
            addCriterion("menuno is not null");
            return (Criteria) this;
        }

        public Criteria andMenunoEqualTo(String value) {
            addCriterion("menuno =", value, "menuno");
            return (Criteria) this;
        }

        public Criteria andMenunoNotEqualTo(String value) {
            addCriterion("menuno <>", value, "menuno");
            return (Criteria) this;
        }

        public Criteria andMenunoGreaterThan(String value) {
            addCriterion("menuno >", value, "menuno");
            return (Criteria) this;
        }

        public Criteria andMenunoGreaterThanOrEqualTo(String value) {
            addCriterion("menuno >=", value, "menuno");
            return (Criteria) this;
        }

        public Criteria andMenunoLessThan(String value) {
            addCriterion("menuno <", value, "menuno");
            return (Criteria) this;
        }

        public Criteria andMenunoLessThanOrEqualTo(String value) {
            addCriterion("menuno <=", value, "menuno");
            return (Criteria) this;
        }

        public Criteria andMenunoLike(String value) {
            addCriterion("menuno like", value, "menuno");
            return (Criteria) this;
        }

        public Criteria andMenunoNotLike(String value) {
            addCriterion("menuno not like", value, "menuno");
            return (Criteria) this;
        }

        public Criteria andMenunoIn(List<String> values) {
            addCriterion("menuno in", values, "menuno");
            return (Criteria) this;
        }

        public Criteria andMenunoNotIn(List<String> values) {
            addCriterion("menuno not in", values, "menuno");
            return (Criteria) this;
        }

        public Criteria andMenunoBetween(String value1, String value2) {
            addCriterion("menuno between", value1, value2, "menuno");
            return (Criteria) this;
        }

        public Criteria andMenunoNotBetween(String value1, String value2) {
            addCriterion("menuno not between", value1, value2, "menuno");
            return (Criteria) this;
        }

        public Criteria andOpenrulesIsNull() {
            addCriterion("openrules is null");
            return (Criteria) this;
        }

        public Criteria andOpenrulesIsNotNull() {
            addCriterion("openrules is not null");
            return (Criteria) this;
        }

        public Criteria andOpenrulesEqualTo(String value) {
            addCriterion("openrules =", value, "openrules");
            return (Criteria) this;
        }

        public Criteria andOpenrulesNotEqualTo(String value) {
            addCriterion("openrules <>", value, "openrules");
            return (Criteria) this;
        }

        public Criteria andOpenrulesGreaterThan(String value) {
            addCriterion("openrules >", value, "openrules");
            return (Criteria) this;
        }

        public Criteria andOpenrulesGreaterThanOrEqualTo(String value) {
            addCriterion("openrules >=", value, "openrules");
            return (Criteria) this;
        }

        public Criteria andOpenrulesLessThan(String value) {
            addCriterion("openrules <", value, "openrules");
            return (Criteria) this;
        }

        public Criteria andOpenrulesLessThanOrEqualTo(String value) {
            addCriterion("openrules <=", value, "openrules");
            return (Criteria) this;
        }

        public Criteria andOpenrulesLike(String value) {
            addCriterion("openrules like", value, "openrules");
            return (Criteria) this;
        }

        public Criteria andOpenrulesNotLike(String value) {
            addCriterion("openrules not like", value, "openrules");
            return (Criteria) this;
        }

        public Criteria andOpenrulesIn(List<String> values) {
            addCriterion("openrules in", values, "openrules");
            return (Criteria) this;
        }

        public Criteria andOpenrulesNotIn(List<String> values) {
            addCriterion("openrules not in", values, "openrules");
            return (Criteria) this;
        }

        public Criteria andOpenrulesBetween(String value1, String value2) {
            addCriterion("openrules between", value1, value2, "openrules");
            return (Criteria) this;
        }

        public Criteria andOpenrulesNotBetween(String value1, String value2) {
            addCriterion("openrules not between", value1, value2, "openrules");
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