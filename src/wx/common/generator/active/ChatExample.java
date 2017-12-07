package wx.common.generator.active;

import java.util.ArrayList;
import java.util.List;

public class ChatExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ChatExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(String value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(String value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(String value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(String value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(String value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(String value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLike(String value) {
            addCriterion("uid like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotLike(String value) {
            addCriterion("uid not like", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<String> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<String> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(String value1, String value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(String value1, String value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andTimIsNull() {
            addCriterion("tim is null");
            return (Criteria) this;
        }

        public Criteria andTimIsNotNull() {
            addCriterion("tim is not null");
            return (Criteria) this;
        }

        public Criteria andTimEqualTo(Long value) {
            addCriterion("tim =", value, "tim");
            return (Criteria) this;
        }

        public Criteria andTimNotEqualTo(Long value) {
            addCriterion("tim <>", value, "tim");
            return (Criteria) this;
        }

        public Criteria andTimGreaterThan(Long value) {
            addCriterion("tim >", value, "tim");
            return (Criteria) this;
        }

        public Criteria andTimGreaterThanOrEqualTo(Long value) {
            addCriterion("tim >=", value, "tim");
            return (Criteria) this;
        }

        public Criteria andTimLessThan(Long value) {
            addCriterion("tim <", value, "tim");
            return (Criteria) this;
        }

        public Criteria andTimLessThanOrEqualTo(Long value) {
            addCriterion("tim <=", value, "tim");
            return (Criteria) this;
        }

        public Criteria andTimIn(List<Long> values) {
            addCriterion("tim in", values, "tim");
            return (Criteria) this;
        }

        public Criteria andTimNotIn(List<Long> values) {
            addCriterion("tim not in", values, "tim");
            return (Criteria) this;
        }

        public Criteria andTimBetween(Long value1, Long value2) {
            addCriterion("tim between", value1, value2, "tim");
            return (Criteria) this;
        }

        public Criteria andTimNotBetween(Long value1, Long value2) {
            addCriterion("tim not between", value1, value2, "tim");
            return (Criteria) this;
        }

        public Criteria andReqidIsNull() {
            addCriterion("reqid is null");
            return (Criteria) this;
        }

        public Criteria andReqidIsNotNull() {
            addCriterion("reqid is not null");
            return (Criteria) this;
        }

        public Criteria andReqidEqualTo(String value) {
            addCriterion("reqid =", value, "reqid");
            return (Criteria) this;
        }

        public Criteria andReqidNotEqualTo(String value) {
            addCriterion("reqid <>", value, "reqid");
            return (Criteria) this;
        }

        public Criteria andReqidGreaterThan(String value) {
            addCriterion("reqid >", value, "reqid");
            return (Criteria) this;
        }

        public Criteria andReqidGreaterThanOrEqualTo(String value) {
            addCriterion("reqid >=", value, "reqid");
            return (Criteria) this;
        }

        public Criteria andReqidLessThan(String value) {
            addCriterion("reqid <", value, "reqid");
            return (Criteria) this;
        }

        public Criteria andReqidLessThanOrEqualTo(String value) {
            addCriterion("reqid <=", value, "reqid");
            return (Criteria) this;
        }

        public Criteria andReqidLike(String value) {
            addCriterion("reqid like", value, "reqid");
            return (Criteria) this;
        }

        public Criteria andReqidNotLike(String value) {
            addCriterion("reqid not like", value, "reqid");
            return (Criteria) this;
        }

        public Criteria andReqidIn(List<String> values) {
            addCriterion("reqid in", values, "reqid");
            return (Criteria) this;
        }

        public Criteria andReqidNotIn(List<String> values) {
            addCriterion("reqid not in", values, "reqid");
            return (Criteria) this;
        }

        public Criteria andReqidBetween(String value1, String value2) {
            addCriterion("reqid between", value1, value2, "reqid");
            return (Criteria) this;
        }

        public Criteria andReqidNotBetween(String value1, String value2) {
            addCriterion("reqid not between", value1, value2, "reqid");
            return (Criteria) this;
        }

        public Criteria andBtypIsNull() {
            addCriterion("btyp is null");
            return (Criteria) this;
        }

        public Criteria andBtypIsNotNull() {
            addCriterion("btyp is not null");
            return (Criteria) this;
        }

        public Criteria andBtypEqualTo(Integer value) {
            addCriterion("btyp =", value, "btyp");
            return (Criteria) this;
        }

        public Criteria andBtypNotEqualTo(Integer value) {
            addCriterion("btyp <>", value, "btyp");
            return (Criteria) this;
        }

        public Criteria andBtypGreaterThan(Integer value) {
            addCriterion("btyp >", value, "btyp");
            return (Criteria) this;
        }

        public Criteria andBtypGreaterThanOrEqualTo(Integer value) {
            addCriterion("btyp >=", value, "btyp");
            return (Criteria) this;
        }

        public Criteria andBtypLessThan(Integer value) {
            addCriterion("btyp <", value, "btyp");
            return (Criteria) this;
        }

        public Criteria andBtypLessThanOrEqualTo(Integer value) {
            addCriterion("btyp <=", value, "btyp");
            return (Criteria) this;
        }

        public Criteria andBtypIn(List<Integer> values) {
            addCriterion("btyp in", values, "btyp");
            return (Criteria) this;
        }

        public Criteria andBtypNotIn(List<Integer> values) {
            addCriterion("btyp not in", values, "btyp");
            return (Criteria) this;
        }

        public Criteria andBtypBetween(Integer value1, Integer value2) {
            addCriterion("btyp between", value1, value2, "btyp");
            return (Criteria) this;
        }

        public Criteria andBtypNotBetween(Integer value1, Integer value2) {
            addCriterion("btyp not between", value1, value2, "btyp");
            return (Criteria) this;
        }

        public Criteria andDtypIsNull() {
            addCriterion("dtyp is null");
            return (Criteria) this;
        }

        public Criteria andDtypIsNotNull() {
            addCriterion("dtyp is not null");
            return (Criteria) this;
        }

        public Criteria andDtypEqualTo(Integer value) {
            addCriterion("dtyp =", value, "dtyp");
            return (Criteria) this;
        }

        public Criteria andDtypNotEqualTo(Integer value) {
            addCriterion("dtyp <>", value, "dtyp");
            return (Criteria) this;
        }

        public Criteria andDtypGreaterThan(Integer value) {
            addCriterion("dtyp >", value, "dtyp");
            return (Criteria) this;
        }

        public Criteria andDtypGreaterThanOrEqualTo(Integer value) {
            addCriterion("dtyp >=", value, "dtyp");
            return (Criteria) this;
        }

        public Criteria andDtypLessThan(Integer value) {
            addCriterion("dtyp <", value, "dtyp");
            return (Criteria) this;
        }

        public Criteria andDtypLessThanOrEqualTo(Integer value) {
            addCriterion("dtyp <=", value, "dtyp");
            return (Criteria) this;
        }

        public Criteria andDtypIn(List<Integer> values) {
            addCriterion("dtyp in", values, "dtyp");
            return (Criteria) this;
        }

        public Criteria andDtypNotIn(List<Integer> values) {
            addCriterion("dtyp not in", values, "dtyp");
            return (Criteria) this;
        }

        public Criteria andDtypBetween(Integer value1, Integer value2) {
            addCriterion("dtyp between", value1, value2, "dtyp");
            return (Criteria) this;
        }

        public Criteria andDtypNotBetween(Integer value1, Integer value2) {
            addCriterion("dtyp not between", value1, value2, "dtyp");
            return (Criteria) this;
        }

        public Criteria andDesIsNull() {
            addCriterion("des is null");
            return (Criteria) this;
        }

        public Criteria andDesIsNotNull() {
            addCriterion("des is not null");
            return (Criteria) this;
        }

        public Criteria andDesEqualTo(String value) {
            addCriterion("des =", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotEqualTo(String value) {
            addCriterion("des <>", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesGreaterThan(String value) {
            addCriterion("des >", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesGreaterThanOrEqualTo(String value) {
            addCriterion("des >=", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLessThan(String value) {
            addCriterion("des <", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLessThanOrEqualTo(String value) {
            addCriterion("des <=", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesLike(String value) {
            addCriterion("des like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotLike(String value) {
            addCriterion("des not like", value, "des");
            return (Criteria) this;
        }

        public Criteria andDesIn(List<String> values) {
            addCriterion("des in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotIn(List<String> values) {
            addCriterion("des not in", values, "des");
            return (Criteria) this;
        }

        public Criteria andDesBetween(String value1, String value2) {
            addCriterion("des between", value1, value2, "des");
            return (Criteria) this;
        }

        public Criteria andDesNotBetween(String value1, String value2) {
            addCriterion("des not between", value1, value2, "des");
            return (Criteria) this;
        }
    }

    /**
     */
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