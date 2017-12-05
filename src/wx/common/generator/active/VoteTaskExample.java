package wx.common.generator.active;

import java.util.ArrayList;
import java.util.List;

public class VoteTaskExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public VoteTaskExample() {
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

        public Criteria andUuidIsNull() {
            addCriterion("uuid is null");
            return (Criteria) this;
        }

        public Criteria andUuidIsNotNull() {
            addCriterion("uuid is not null");
            return (Criteria) this;
        }

        public Criteria andUuidEqualTo(String value) {
            addCriterion("uuid =", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotEqualTo(String value) {
            addCriterion("uuid <>", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThan(String value) {
            addCriterion("uuid >", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidGreaterThanOrEqualTo(String value) {
            addCriterion("uuid >=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThan(String value) {
            addCriterion("uuid <", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLessThanOrEqualTo(String value) {
            addCriterion("uuid <=", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidLike(String value) {
            addCriterion("uuid like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotLike(String value) {
            addCriterion("uuid not like", value, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidIn(List<String> values) {
            addCriterion("uuid in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotIn(List<String> values) {
            addCriterion("uuid not in", values, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidBetween(String value1, String value2) {
            addCriterion("uuid between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andUuidNotBetween(String value1, String value2) {
            addCriterion("uuid not between", value1, value2, "uuid");
            return (Criteria) this;
        }

        public Criteria andRunTimIsNull() {
            addCriterion("run_tim is null");
            return (Criteria) this;
        }

        public Criteria andRunTimIsNotNull() {
            addCriterion("run_tim is not null");
            return (Criteria) this;
        }

        public Criteria andRunTimEqualTo(Long value) {
            addCriterion("run_tim =", value, "runTim");
            return (Criteria) this;
        }

        public Criteria andRunTimNotEqualTo(Long value) {
            addCriterion("run_tim <>", value, "runTim");
            return (Criteria) this;
        }

        public Criteria andRunTimGreaterThan(Long value) {
            addCriterion("run_tim >", value, "runTim");
            return (Criteria) this;
        }

        public Criteria andRunTimGreaterThanOrEqualTo(Long value) {
            addCriterion("run_tim >=", value, "runTim");
            return (Criteria) this;
        }

        public Criteria andRunTimLessThan(Long value) {
            addCriterion("run_tim <", value, "runTim");
            return (Criteria) this;
        }

        public Criteria andRunTimLessThanOrEqualTo(Long value) {
            addCriterion("run_tim <=", value, "runTim");
            return (Criteria) this;
        }

        public Criteria andRunTimIn(List<Long> values) {
            addCriterion("run_tim in", values, "runTim");
            return (Criteria) this;
        }

        public Criteria andRunTimNotIn(List<Long> values) {
            addCriterion("run_tim not in", values, "runTim");
            return (Criteria) this;
        }

        public Criteria andRunTimBetween(Long value1, Long value2) {
            addCriterion("run_tim between", value1, value2, "runTim");
            return (Criteria) this;
        }

        public Criteria andRunTimNotBetween(Long value1, Long value2) {
            addCriterion("run_tim not between", value1, value2, "runTim");
            return (Criteria) this;
        }

        public Criteria andAgreeIsNull() {
            addCriterion("agree is null");
            return (Criteria) this;
        }

        public Criteria andAgreeIsNotNull() {
            addCriterion("agree is not null");
            return (Criteria) this;
        }

        public Criteria andAgreeEqualTo(Integer value) {
            addCriterion("agree =", value, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeNotEqualTo(Integer value) {
            addCriterion("agree <>", value, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeGreaterThan(Integer value) {
            addCriterion("agree >", value, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("agree >=", value, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeLessThan(Integer value) {
            addCriterion("agree <", value, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeLessThanOrEqualTo(Integer value) {
            addCriterion("agree <=", value, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeIn(List<Integer> values) {
            addCriterion("agree in", values, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeNotIn(List<Integer> values) {
            addCriterion("agree not in", values, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeBetween(Integer value1, Integer value2) {
            addCriterion("agree between", value1, value2, "agree");
            return (Criteria) this;
        }

        public Criteria andAgreeNotBetween(Integer value1, Integer value2) {
            addCriterion("agree not between", value1, value2, "agree");
            return (Criteria) this;
        }

        public Criteria andOpposeIsNull() {
            addCriterion("oppose is null");
            return (Criteria) this;
        }

        public Criteria andOpposeIsNotNull() {
            addCriterion("oppose is not null");
            return (Criteria) this;
        }

        public Criteria andOpposeEqualTo(Integer value) {
            addCriterion("oppose =", value, "oppose");
            return (Criteria) this;
        }

        public Criteria andOpposeNotEqualTo(Integer value) {
            addCriterion("oppose <>", value, "oppose");
            return (Criteria) this;
        }

        public Criteria andOpposeGreaterThan(Integer value) {
            addCriterion("oppose >", value, "oppose");
            return (Criteria) this;
        }

        public Criteria andOpposeGreaterThanOrEqualTo(Integer value) {
            addCriterion("oppose >=", value, "oppose");
            return (Criteria) this;
        }

        public Criteria andOpposeLessThan(Integer value) {
            addCriterion("oppose <", value, "oppose");
            return (Criteria) this;
        }

        public Criteria andOpposeLessThanOrEqualTo(Integer value) {
            addCriterion("oppose <=", value, "oppose");
            return (Criteria) this;
        }

        public Criteria andOpposeIn(List<Integer> values) {
            addCriterion("oppose in", values, "oppose");
            return (Criteria) this;
        }

        public Criteria andOpposeNotIn(List<Integer> values) {
            addCriterion("oppose not in", values, "oppose");
            return (Criteria) this;
        }

        public Criteria andOpposeBetween(Integer value1, Integer value2) {
            addCriterion("oppose between", value1, value2, "oppose");
            return (Criteria) this;
        }

        public Criteria andOpposeNotBetween(Integer value1, Integer value2) {
            addCriterion("oppose not between", value1, value2, "oppose");
            return (Criteria) this;
        }

        public Criteria andTotalIsNull() {
            addCriterion("total is null");
            return (Criteria) this;
        }

        public Criteria andTotalIsNotNull() {
            addCriterion("total is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEqualTo(Integer value) {
            addCriterion("total =", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotEqualTo(Integer value) {
            addCriterion("total <>", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThan(Integer value) {
            addCriterion("total >", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("total >=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThan(Integer value) {
            addCriterion("total <", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalLessThanOrEqualTo(Integer value) {
            addCriterion("total <=", value, "total");
            return (Criteria) this;
        }

        public Criteria andTotalIn(List<Integer> values) {
            addCriterion("total in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotIn(List<Integer> values) {
            addCriterion("total not in", values, "total");
            return (Criteria) this;
        }

        public Criteria andTotalBetween(Integer value1, Integer value2) {
            addCriterion("total between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("total not between", value1, value2, "total");
            return (Criteria) this;
        }

        public Criteria andTypIsNull() {
            addCriterion("typ is null");
            return (Criteria) this;
        }

        public Criteria andTypIsNotNull() {
            addCriterion("typ is not null");
            return (Criteria) this;
        }

        public Criteria andTypEqualTo(Integer value) {
            addCriterion("typ =", value, "typ");
            return (Criteria) this;
        }

        public Criteria andTypNotEqualTo(Integer value) {
            addCriterion("typ <>", value, "typ");
            return (Criteria) this;
        }

        public Criteria andTypGreaterThan(Integer value) {
            addCriterion("typ >", value, "typ");
            return (Criteria) this;
        }

        public Criteria andTypGreaterThanOrEqualTo(Integer value) {
            addCriterion("typ >=", value, "typ");
            return (Criteria) this;
        }

        public Criteria andTypLessThan(Integer value) {
            addCriterion("typ <", value, "typ");
            return (Criteria) this;
        }

        public Criteria andTypLessThanOrEqualTo(Integer value) {
            addCriterion("typ <=", value, "typ");
            return (Criteria) this;
        }

        public Criteria andTypIn(List<Integer> values) {
            addCriterion("typ in", values, "typ");
            return (Criteria) this;
        }

        public Criteria andTypNotIn(List<Integer> values) {
            addCriterion("typ not in", values, "typ");
            return (Criteria) this;
        }

        public Criteria andTypBetween(Integer value1, Integer value2) {
            addCriterion("typ between", value1, value2, "typ");
            return (Criteria) this;
        }

        public Criteria andTypNotBetween(Integer value1, Integer value2) {
            addCriterion("typ not between", value1, value2, "typ");
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