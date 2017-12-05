package wx.common.generator.base;

import java.util.ArrayList;
import java.util.List;

public class GuserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public GuserExample() {
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

        public Criteria andGidIsNull() {
            addCriterion("gid is null");
            return (Criteria) this;
        }

        public Criteria andGidIsNotNull() {
            addCriterion("gid is not null");
            return (Criteria) this;
        }

        public Criteria andGidEqualTo(String value) {
            addCriterion("gid =", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotEqualTo(String value) {
            addCriterion("gid <>", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThan(String value) {
            addCriterion("gid >", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidGreaterThanOrEqualTo(String value) {
            addCriterion("gid >=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThan(String value) {
            addCriterion("gid <", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLessThanOrEqualTo(String value) {
            addCriterion("gid <=", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidLike(String value) {
            addCriterion("gid like", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotLike(String value) {
            addCriterion("gid not like", value, "gid");
            return (Criteria) this;
        }

        public Criteria andGidIn(List<String> values) {
            addCriterion("gid in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotIn(List<String> values) {
            addCriterion("gid not in", values, "gid");
            return (Criteria) this;
        }

        public Criteria andGidBetween(String value1, String value2) {
            addCriterion("gid between", value1, value2, "gid");
            return (Criteria) this;
        }

        public Criteria andGidNotBetween(String value1, String value2) {
            addCriterion("gid not between", value1, value2, "gid");
            return (Criteria) this;
        }

        public Criteria andUremarkIsNull() {
            addCriterion("uremark is null");
            return (Criteria) this;
        }

        public Criteria andUremarkIsNotNull() {
            addCriterion("uremark is not null");
            return (Criteria) this;
        }

        public Criteria andUremarkEqualTo(String value) {
            addCriterion("uremark =", value, "uremark");
            return (Criteria) this;
        }

        public Criteria andUremarkNotEqualTo(String value) {
            addCriterion("uremark <>", value, "uremark");
            return (Criteria) this;
        }

        public Criteria andUremarkGreaterThan(String value) {
            addCriterion("uremark >", value, "uremark");
            return (Criteria) this;
        }

        public Criteria andUremarkGreaterThanOrEqualTo(String value) {
            addCriterion("uremark >=", value, "uremark");
            return (Criteria) this;
        }

        public Criteria andUremarkLessThan(String value) {
            addCriterion("uremark <", value, "uremark");
            return (Criteria) this;
        }

        public Criteria andUremarkLessThanOrEqualTo(String value) {
            addCriterion("uremark <=", value, "uremark");
            return (Criteria) this;
        }

        public Criteria andUremarkLike(String value) {
            addCriterion("uremark like", value, "uremark");
            return (Criteria) this;
        }

        public Criteria andUremarkNotLike(String value) {
            addCriterion("uremark not like", value, "uremark");
            return (Criteria) this;
        }

        public Criteria andUremarkIn(List<String> values) {
            addCriterion("uremark in", values, "uremark");
            return (Criteria) this;
        }

        public Criteria andUremarkNotIn(List<String> values) {
            addCriterion("uremark not in", values, "uremark");
            return (Criteria) this;
        }

        public Criteria andUremarkBetween(String value1, String value2) {
            addCriterion("uremark between", value1, value2, "uremark");
            return (Criteria) this;
        }

        public Criteria andUremarkNotBetween(String value1, String value2) {
            addCriterion("uremark not between", value1, value2, "uremark");
            return (Criteria) this;
        }

        public Criteria andGremarkIsNull() {
            addCriterion("gremark is null");
            return (Criteria) this;
        }

        public Criteria andGremarkIsNotNull() {
            addCriterion("gremark is not null");
            return (Criteria) this;
        }

        public Criteria andGremarkEqualTo(String value) {
            addCriterion("gremark =", value, "gremark");
            return (Criteria) this;
        }

        public Criteria andGremarkNotEqualTo(String value) {
            addCriterion("gremark <>", value, "gremark");
            return (Criteria) this;
        }

        public Criteria andGremarkGreaterThan(String value) {
            addCriterion("gremark >", value, "gremark");
            return (Criteria) this;
        }

        public Criteria andGremarkGreaterThanOrEqualTo(String value) {
            addCriterion("gremark >=", value, "gremark");
            return (Criteria) this;
        }

        public Criteria andGremarkLessThan(String value) {
            addCriterion("gremark <", value, "gremark");
            return (Criteria) this;
        }

        public Criteria andGremarkLessThanOrEqualTo(String value) {
            addCriterion("gremark <=", value, "gremark");
            return (Criteria) this;
        }

        public Criteria andGremarkLike(String value) {
            addCriterion("gremark like", value, "gremark");
            return (Criteria) this;
        }

        public Criteria andGremarkNotLike(String value) {
            addCriterion("gremark not like", value, "gremark");
            return (Criteria) this;
        }

        public Criteria andGremarkIn(List<String> values) {
            addCriterion("gremark in", values, "gremark");
            return (Criteria) this;
        }

        public Criteria andGremarkNotIn(List<String> values) {
            addCriterion("gremark not in", values, "gremark");
            return (Criteria) this;
        }

        public Criteria andGremarkBetween(String value1, String value2) {
            addCriterion("gremark between", value1, value2, "gremark");
            return (Criteria) this;
        }

        public Criteria andGremarkNotBetween(String value1, String value2) {
            addCriterion("gremark not between", value1, value2, "gremark");
            return (Criteria) this;
        }

        public Criteria andShieIsNull() {
            addCriterion("shie is null");
            return (Criteria) this;
        }

        public Criteria andShieIsNotNull() {
            addCriterion("shie is not null");
            return (Criteria) this;
        }

        public Criteria andShieEqualTo(Integer value) {
            addCriterion("shie =", value, "shie");
            return (Criteria) this;
        }

        public Criteria andShieNotEqualTo(Integer value) {
            addCriterion("shie <>", value, "shie");
            return (Criteria) this;
        }

        public Criteria andShieGreaterThan(Integer value) {
            addCriterion("shie >", value, "shie");
            return (Criteria) this;
        }

        public Criteria andShieGreaterThanOrEqualTo(Integer value) {
            addCriterion("shie >=", value, "shie");
            return (Criteria) this;
        }

        public Criteria andShieLessThan(Integer value) {
            addCriterion("shie <", value, "shie");
            return (Criteria) this;
        }

        public Criteria andShieLessThanOrEqualTo(Integer value) {
            addCriterion("shie <=", value, "shie");
            return (Criteria) this;
        }

        public Criteria andShieIn(List<Integer> values) {
            addCriterion("shie in", values, "shie");
            return (Criteria) this;
        }

        public Criteria andShieNotIn(List<Integer> values) {
            addCriterion("shie not in", values, "shie");
            return (Criteria) this;
        }

        public Criteria andShieBetween(Integer value1, Integer value2) {
            addCriterion("shie between", value1, value2, "shie");
            return (Criteria) this;
        }

        public Criteria andShieNotBetween(Integer value1, Integer value2) {
            addCriterion("shie not between", value1, value2, "shie");
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

        public Criteria andSaveIsNull() {
            addCriterion("save is null");
            return (Criteria) this;
        }

        public Criteria andSaveIsNotNull() {
            addCriterion("save is not null");
            return (Criteria) this;
        }

        public Criteria andSaveEqualTo(Integer value) {
            addCriterion("save =", value, "save");
            return (Criteria) this;
        }

        public Criteria andSaveNotEqualTo(Integer value) {
            addCriterion("save <>", value, "save");
            return (Criteria) this;
        }

        public Criteria andSaveGreaterThan(Integer value) {
            addCriterion("save >", value, "save");
            return (Criteria) this;
        }

        public Criteria andSaveGreaterThanOrEqualTo(Integer value) {
            addCriterion("save >=", value, "save");
            return (Criteria) this;
        }

        public Criteria andSaveLessThan(Integer value) {
            addCriterion("save <", value, "save");
            return (Criteria) this;
        }

        public Criteria andSaveLessThanOrEqualTo(Integer value) {
            addCriterion("save <=", value, "save");
            return (Criteria) this;
        }

        public Criteria andSaveIn(List<Integer> values) {
            addCriterion("save in", values, "save");
            return (Criteria) this;
        }

        public Criteria andSaveNotIn(List<Integer> values) {
            addCriterion("save not in", values, "save");
            return (Criteria) this;
        }

        public Criteria andSaveBetween(Integer value1, Integer value2) {
            addCriterion("save between", value1, value2, "save");
            return (Criteria) this;
        }

        public Criteria andSaveNotBetween(Integer value1, Integer value2) {
            addCriterion("save not between", value1, value2, "save");
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