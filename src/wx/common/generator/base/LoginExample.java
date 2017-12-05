package wx.common.generator.base;

import java.util.ArrayList;
import java.util.List;

public class LoginExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public LoginExample() {
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

        public Criteria andRanidIsNull() {
            addCriterion("ranid is null");
            return (Criteria) this;
        }

        public Criteria andRanidIsNotNull() {
            addCriterion("ranid is not null");
            return (Criteria) this;
        }

        public Criteria andRanidEqualTo(String value) {
            addCriterion("ranid =", value, "ranid");
            return (Criteria) this;
        }

        public Criteria andRanidNotEqualTo(String value) {
            addCriterion("ranid <>", value, "ranid");
            return (Criteria) this;
        }

        public Criteria andRanidGreaterThan(String value) {
            addCriterion("ranid >", value, "ranid");
            return (Criteria) this;
        }

        public Criteria andRanidGreaterThanOrEqualTo(String value) {
            addCriterion("ranid >=", value, "ranid");
            return (Criteria) this;
        }

        public Criteria andRanidLessThan(String value) {
            addCriterion("ranid <", value, "ranid");
            return (Criteria) this;
        }

        public Criteria andRanidLessThanOrEqualTo(String value) {
            addCriterion("ranid <=", value, "ranid");
            return (Criteria) this;
        }

        public Criteria andRanidLike(String value) {
            addCriterion("ranid like", value, "ranid");
            return (Criteria) this;
        }

        public Criteria andRanidNotLike(String value) {
            addCriterion("ranid not like", value, "ranid");
            return (Criteria) this;
        }

        public Criteria andRanidIn(List<String> values) {
            addCriterion("ranid in", values, "ranid");
            return (Criteria) this;
        }

        public Criteria andRanidNotIn(List<String> values) {
            addCriterion("ranid not in", values, "ranid");
            return (Criteria) this;
        }

        public Criteria andRanidBetween(String value1, String value2) {
            addCriterion("ranid between", value1, value2, "ranid");
            return (Criteria) this;
        }

        public Criteria andRanidNotBetween(String value1, String value2) {
            addCriterion("ranid not between", value1, value2, "ranid");
            return (Criteria) this;
        }

        public Criteria andTidIsNull() {
            addCriterion("tid is null");
            return (Criteria) this;
        }

        public Criteria andTidIsNotNull() {
            addCriterion("tid is not null");
            return (Criteria) this;
        }

        public Criteria andTidEqualTo(String value) {
            addCriterion("tid =", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotEqualTo(String value) {
            addCriterion("tid <>", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThan(String value) {
            addCriterion("tid >", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidGreaterThanOrEqualTo(String value) {
            addCriterion("tid >=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThan(String value) {
            addCriterion("tid <", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLessThanOrEqualTo(String value) {
            addCriterion("tid <=", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidLike(String value) {
            addCriterion("tid like", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotLike(String value) {
            addCriterion("tid not like", value, "tid");
            return (Criteria) this;
        }

        public Criteria andTidIn(List<String> values) {
            addCriterion("tid in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotIn(List<String> values) {
            addCriterion("tid not in", values, "tid");
            return (Criteria) this;
        }

        public Criteria andTidBetween(String value1, String value2) {
            addCriterion("tid between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andTidNotBetween(String value1, String value2) {
            addCriterion("tid not between", value1, value2, "tid");
            return (Criteria) this;
        }

        public Criteria andAesIsNull() {
            addCriterion("aes is null");
            return (Criteria) this;
        }

        public Criteria andAesIsNotNull() {
            addCriterion("aes is not null");
            return (Criteria) this;
        }

        public Criteria andAesEqualTo(String value) {
            addCriterion("aes =", value, "aes");
            return (Criteria) this;
        }

        public Criteria andAesNotEqualTo(String value) {
            addCriterion("aes <>", value, "aes");
            return (Criteria) this;
        }

        public Criteria andAesGreaterThan(String value) {
            addCriterion("aes >", value, "aes");
            return (Criteria) this;
        }

        public Criteria andAesGreaterThanOrEqualTo(String value) {
            addCriterion("aes >=", value, "aes");
            return (Criteria) this;
        }

        public Criteria andAesLessThan(String value) {
            addCriterion("aes <", value, "aes");
            return (Criteria) this;
        }

        public Criteria andAesLessThanOrEqualTo(String value) {
            addCriterion("aes <=", value, "aes");
            return (Criteria) this;
        }

        public Criteria andAesLike(String value) {
            addCriterion("aes like", value, "aes");
            return (Criteria) this;
        }

        public Criteria andAesNotLike(String value) {
            addCriterion("aes not like", value, "aes");
            return (Criteria) this;
        }

        public Criteria andAesIn(List<String> values) {
            addCriterion("aes in", values, "aes");
            return (Criteria) this;
        }

        public Criteria andAesNotIn(List<String> values) {
            addCriterion("aes not in", values, "aes");
            return (Criteria) this;
        }

        public Criteria andAesBetween(String value1, String value2) {
            addCriterion("aes between", value1, value2, "aes");
            return (Criteria) this;
        }

        public Criteria andAesNotBetween(String value1, String value2) {
            addCriterion("aes not between", value1, value2, "aes");
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