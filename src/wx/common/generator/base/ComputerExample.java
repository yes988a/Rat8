package wx.common.generator.base;

import java.util.ArrayList;
import java.util.List;

public class ComputerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public ComputerExample() {
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

        public Criteria andCidIsNull() {
            addCriterion("cid is null");
            return (Criteria) this;
        }

        public Criteria andCidIsNotNull() {
            addCriterion("cid is not null");
            return (Criteria) this;
        }

        public Criteria andCidEqualTo(String value) {
            addCriterion("cid =", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotEqualTo(String value) {
            addCriterion("cid <>", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThan(String value) {
            addCriterion("cid >", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidGreaterThanOrEqualTo(String value) {
            addCriterion("cid >=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThan(String value) {
            addCriterion("cid <", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLessThanOrEqualTo(String value) {
            addCriterion("cid <=", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidLike(String value) {
            addCriterion("cid like", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotLike(String value) {
            addCriterion("cid not like", value, "cid");
            return (Criteria) this;
        }

        public Criteria andCidIn(List<String> values) {
            addCriterion("cid in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotIn(List<String> values) {
            addCriterion("cid not in", values, "cid");
            return (Criteria) this;
        }

        public Criteria andCidBetween(String value1, String value2) {
            addCriterion("cid between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andCidNotBetween(String value1, String value2) {
            addCriterion("cid not between", value1, value2, "cid");
            return (Criteria) this;
        }

        public Criteria andPriIsNull() {
            addCriterion("pri is null");
            return (Criteria) this;
        }

        public Criteria andPriIsNotNull() {
            addCriterion("pri is not null");
            return (Criteria) this;
        }

        public Criteria andPriEqualTo(String value) {
            addCriterion("pri =", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriNotEqualTo(String value) {
            addCriterion("pri <>", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriGreaterThan(String value) {
            addCriterion("pri >", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriGreaterThanOrEqualTo(String value) {
            addCriterion("pri >=", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriLessThan(String value) {
            addCriterion("pri <", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriLessThanOrEqualTo(String value) {
            addCriterion("pri <=", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriLike(String value) {
            addCriterion("pri like", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriNotLike(String value) {
            addCriterion("pri not like", value, "pri");
            return (Criteria) this;
        }

        public Criteria andPriIn(List<String> values) {
            addCriterion("pri in", values, "pri");
            return (Criteria) this;
        }

        public Criteria andPriNotIn(List<String> values) {
            addCriterion("pri not in", values, "pri");
            return (Criteria) this;
        }

        public Criteria andPriBetween(String value1, String value2) {
            addCriterion("pri between", value1, value2, "pri");
            return (Criteria) this;
        }

        public Criteria andPriNotBetween(String value1, String value2) {
            addCriterion("pri not between", value1, value2, "pri");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andPorIsNull() {
            addCriterion("por is null");
            return (Criteria) this;
        }

        public Criteria andPorIsNotNull() {
            addCriterion("por is not null");
            return (Criteria) this;
        }

        public Criteria andPorEqualTo(Integer value) {
            addCriterion("por =", value, "por");
            return (Criteria) this;
        }

        public Criteria andPorNotEqualTo(Integer value) {
            addCriterion("por <>", value, "por");
            return (Criteria) this;
        }

        public Criteria andPorGreaterThan(Integer value) {
            addCriterion("por >", value, "por");
            return (Criteria) this;
        }

        public Criteria andPorGreaterThanOrEqualTo(Integer value) {
            addCriterion("por >=", value, "por");
            return (Criteria) this;
        }

        public Criteria andPorLessThan(Integer value) {
            addCriterion("por <", value, "por");
            return (Criteria) this;
        }

        public Criteria andPorLessThanOrEqualTo(Integer value) {
            addCriterion("por <=", value, "por");
            return (Criteria) this;
        }

        public Criteria andPorIn(List<Integer> values) {
            addCriterion("por in", values, "por");
            return (Criteria) this;
        }

        public Criteria andPorNotIn(List<Integer> values) {
            addCriterion("por not in", values, "por");
            return (Criteria) this;
        }

        public Criteria andPorBetween(Integer value1, Integer value2) {
            addCriterion("por between", value1, value2, "por");
            return (Criteria) this;
        }

        public Criteria andPorNotBetween(Integer value1, Integer value2) {
            addCriterion("por not between", value1, value2, "por");
            return (Criteria) this;
        }

        public Criteria andUnumIsNull() {
            addCriterion("unum is null");
            return (Criteria) this;
        }

        public Criteria andUnumIsNotNull() {
            addCriterion("unum is not null");
            return (Criteria) this;
        }

        public Criteria andUnumEqualTo(Integer value) {
            addCriterion("unum =", value, "unum");
            return (Criteria) this;
        }

        public Criteria andUnumNotEqualTo(Integer value) {
            addCriterion("unum <>", value, "unum");
            return (Criteria) this;
        }

        public Criteria andUnumGreaterThan(Integer value) {
            addCriterion("unum >", value, "unum");
            return (Criteria) this;
        }

        public Criteria andUnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("unum >=", value, "unum");
            return (Criteria) this;
        }

        public Criteria andUnumLessThan(Integer value) {
            addCriterion("unum <", value, "unum");
            return (Criteria) this;
        }

        public Criteria andUnumLessThanOrEqualTo(Integer value) {
            addCriterion("unum <=", value, "unum");
            return (Criteria) this;
        }

        public Criteria andUnumIn(List<Integer> values) {
            addCriterion("unum in", values, "unum");
            return (Criteria) this;
        }

        public Criteria andUnumNotIn(List<Integer> values) {
            addCriterion("unum not in", values, "unum");
            return (Criteria) this;
        }

        public Criteria andUnumBetween(Integer value1, Integer value2) {
            addCriterion("unum between", value1, value2, "unum");
            return (Criteria) this;
        }

        public Criteria andUnumNotBetween(Integer value1, Integer value2) {
            addCriterion("unum not between", value1, value2, "unum");
            return (Criteria) this;
        }

        public Criteria andStimIsNull() {
            addCriterion("stim is null");
            return (Criteria) this;
        }

        public Criteria andStimIsNotNull() {
            addCriterion("stim is not null");
            return (Criteria) this;
        }

        public Criteria andStimEqualTo(Long value) {
            addCriterion("stim =", value, "stim");
            return (Criteria) this;
        }

        public Criteria andStimNotEqualTo(Long value) {
            addCriterion("stim <>", value, "stim");
            return (Criteria) this;
        }

        public Criteria andStimGreaterThan(Long value) {
            addCriterion("stim >", value, "stim");
            return (Criteria) this;
        }

        public Criteria andStimGreaterThanOrEqualTo(Long value) {
            addCriterion("stim >=", value, "stim");
            return (Criteria) this;
        }

        public Criteria andStimLessThan(Long value) {
            addCriterion("stim <", value, "stim");
            return (Criteria) this;
        }

        public Criteria andStimLessThanOrEqualTo(Long value) {
            addCriterion("stim <=", value, "stim");
            return (Criteria) this;
        }

        public Criteria andStimIn(List<Long> values) {
            addCriterion("stim in", values, "stim");
            return (Criteria) this;
        }

        public Criteria andStimNotIn(List<Long> values) {
            addCriterion("stim not in", values, "stim");
            return (Criteria) this;
        }

        public Criteria andStimBetween(Long value1, Long value2) {
            addCriterion("stim between", value1, value2, "stim");
            return (Criteria) this;
        }

        public Criteria andStimNotBetween(Long value1, Long value2) {
            addCriterion("stim not between", value1, value2, "stim");
            return (Criteria) this;
        }

        public Criteria andEtimIsNull() {
            addCriterion("etim is null");
            return (Criteria) this;
        }

        public Criteria andEtimIsNotNull() {
            addCriterion("etim is not null");
            return (Criteria) this;
        }

        public Criteria andEtimEqualTo(Long value) {
            addCriterion("etim =", value, "etim");
            return (Criteria) this;
        }

        public Criteria andEtimNotEqualTo(Long value) {
            addCriterion("etim <>", value, "etim");
            return (Criteria) this;
        }

        public Criteria andEtimGreaterThan(Long value) {
            addCriterion("etim >", value, "etim");
            return (Criteria) this;
        }

        public Criteria andEtimGreaterThanOrEqualTo(Long value) {
            addCriterion("etim >=", value, "etim");
            return (Criteria) this;
        }

        public Criteria andEtimLessThan(Long value) {
            addCriterion("etim <", value, "etim");
            return (Criteria) this;
        }

        public Criteria andEtimLessThanOrEqualTo(Long value) {
            addCriterion("etim <=", value, "etim");
            return (Criteria) this;
        }

        public Criteria andEtimIn(List<Long> values) {
            addCriterion("etim in", values, "etim");
            return (Criteria) this;
        }

        public Criteria andEtimNotIn(List<Long> values) {
            addCriterion("etim not in", values, "etim");
            return (Criteria) this;
        }

        public Criteria andEtimBetween(Long value1, Long value2) {
            addCriterion("etim between", value1, value2, "etim");
            return (Criteria) this;
        }

        public Criteria andEtimNotBetween(Long value1, Long value2) {
            addCriterion("etim not between", value1, value2, "etim");
            return (Criteria) this;
        }

        public Criteria andStopIsNull() {
            addCriterion("stop is null");
            return (Criteria) this;
        }

        public Criteria andStopIsNotNull() {
            addCriterion("stop is not null");
            return (Criteria) this;
        }

        public Criteria andStopEqualTo(Integer value) {
            addCriterion("stop =", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopNotEqualTo(Integer value) {
            addCriterion("stop <>", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopGreaterThan(Integer value) {
            addCriterion("stop >", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopGreaterThanOrEqualTo(Integer value) {
            addCriterion("stop >=", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopLessThan(Integer value) {
            addCriterion("stop <", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopLessThanOrEqualTo(Integer value) {
            addCriterion("stop <=", value, "stop");
            return (Criteria) this;
        }

        public Criteria andStopIn(List<Integer> values) {
            addCriterion("stop in", values, "stop");
            return (Criteria) this;
        }

        public Criteria andStopNotIn(List<Integer> values) {
            addCriterion("stop not in", values, "stop");
            return (Criteria) this;
        }

        public Criteria andStopBetween(Integer value1, Integer value2) {
            addCriterion("stop between", value1, value2, "stop");
            return (Criteria) this;
        }

        public Criteria andStopNotBetween(Integer value1, Integer value2) {
            addCriterion("stop not between", value1, value2, "stop");
            return (Criteria) this;
        }

        public Criteria andBidIsNull() {
            addCriterion("bid is null");
            return (Criteria) this;
        }

        public Criteria andBidIsNotNull() {
            addCriterion("bid is not null");
            return (Criteria) this;
        }

        public Criteria andBidEqualTo(String value) {
            addCriterion("bid =", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotEqualTo(String value) {
            addCriterion("bid <>", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThan(String value) {
            addCriterion("bid >", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidGreaterThanOrEqualTo(String value) {
            addCriterion("bid >=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThan(String value) {
            addCriterion("bid <", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLessThanOrEqualTo(String value) {
            addCriterion("bid <=", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidLike(String value) {
            addCriterion("bid like", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotLike(String value) {
            addCriterion("bid not like", value, "bid");
            return (Criteria) this;
        }

        public Criteria andBidIn(List<String> values) {
            addCriterion("bid in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotIn(List<String> values) {
            addCriterion("bid not in", values, "bid");
            return (Criteria) this;
        }

        public Criteria andBidBetween(String value1, String value2) {
            addCriterion("bid between", value1, value2, "bid");
            return (Criteria) this;
        }

        public Criteria andBidNotBetween(String value1, String value2) {
            addCriterion("bid not between", value1, value2, "bid");
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