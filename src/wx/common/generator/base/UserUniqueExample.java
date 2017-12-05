package wx.common.generator.base;

import java.util.ArrayList;
import java.util.List;

public class UserUniqueExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public UserUniqueExample() {
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

        public Criteria andAccIsNull() {
            addCriterion("acc is null");
            return (Criteria) this;
        }

        public Criteria andAccIsNotNull() {
            addCriterion("acc is not null");
            return (Criteria) this;
        }

        public Criteria andAccEqualTo(String value) {
            addCriterion("acc =", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccNotEqualTo(String value) {
            addCriterion("acc <>", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccGreaterThan(String value) {
            addCriterion("acc >", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccGreaterThanOrEqualTo(String value) {
            addCriterion("acc >=", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccLessThan(String value) {
            addCriterion("acc <", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccLessThanOrEqualTo(String value) {
            addCriterion("acc <=", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccLike(String value) {
            addCriterion("acc like", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccNotLike(String value) {
            addCriterion("acc not like", value, "acc");
            return (Criteria) this;
        }

        public Criteria andAccIn(List<String> values) {
            addCriterion("acc in", values, "acc");
            return (Criteria) this;
        }

        public Criteria andAccNotIn(List<String> values) {
            addCriterion("acc not in", values, "acc");
            return (Criteria) this;
        }

        public Criteria andAccBetween(String value1, String value2) {
            addCriterion("acc between", value1, value2, "acc");
            return (Criteria) this;
        }

        public Criteria andAccNotBetween(String value1, String value2) {
            addCriterion("acc not between", value1, value2, "acc");
            return (Criteria) this;
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

        public Criteria andQrcodeIsNull() {
            addCriterion("qrcode is null");
            return (Criteria) this;
        }

        public Criteria andQrcodeIsNotNull() {
            addCriterion("qrcode is not null");
            return (Criteria) this;
        }

        public Criteria andQrcodeEqualTo(String value) {
            addCriterion("qrcode =", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotEqualTo(String value) {
            addCriterion("qrcode <>", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeGreaterThan(String value) {
            addCriterion("qrcode >", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeGreaterThanOrEqualTo(String value) {
            addCriterion("qrcode >=", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeLessThan(String value) {
            addCriterion("qrcode <", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeLessThanOrEqualTo(String value) {
            addCriterion("qrcode <=", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeLike(String value) {
            addCriterion("qrcode like", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotLike(String value) {
            addCriterion("qrcode not like", value, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeIn(List<String> values) {
            addCriterion("qrcode in", values, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotIn(List<String> values) {
            addCriterion("qrcode not in", values, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeBetween(String value1, String value2) {
            addCriterion("qrcode between", value1, value2, "qrcode");
            return (Criteria) this;
        }

        public Criteria andQrcodeNotBetween(String value1, String value2) {
            addCriterion("qrcode not between", value1, value2, "qrcode");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneTimIsNull() {
            addCriterion("phone_tim is null");
            return (Criteria) this;
        }

        public Criteria andPhoneTimIsNotNull() {
            addCriterion("phone_tim is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneTimEqualTo(Long value) {
            addCriterion("phone_tim =", value, "phoneTim");
            return (Criteria) this;
        }

        public Criteria andPhoneTimNotEqualTo(Long value) {
            addCriterion("phone_tim <>", value, "phoneTim");
            return (Criteria) this;
        }

        public Criteria andPhoneTimGreaterThan(Long value) {
            addCriterion("phone_tim >", value, "phoneTim");
            return (Criteria) this;
        }

        public Criteria andPhoneTimGreaterThanOrEqualTo(Long value) {
            addCriterion("phone_tim >=", value, "phoneTim");
            return (Criteria) this;
        }

        public Criteria andPhoneTimLessThan(Long value) {
            addCriterion("phone_tim <", value, "phoneTim");
            return (Criteria) this;
        }

        public Criteria andPhoneTimLessThanOrEqualTo(Long value) {
            addCriterion("phone_tim <=", value, "phoneTim");
            return (Criteria) this;
        }

        public Criteria andPhoneTimIn(List<Long> values) {
            addCriterion("phone_tim in", values, "phoneTim");
            return (Criteria) this;
        }

        public Criteria andPhoneTimNotIn(List<Long> values) {
            addCriterion("phone_tim not in", values, "phoneTim");
            return (Criteria) this;
        }

        public Criteria andPhoneTimBetween(Long value1, Long value2) {
            addCriterion("phone_tim between", value1, value2, "phoneTim");
            return (Criteria) this;
        }

        public Criteria andPhoneTimNotBetween(Long value1, Long value2) {
            addCriterion("phone_tim not between", value1, value2, "phoneTim");
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