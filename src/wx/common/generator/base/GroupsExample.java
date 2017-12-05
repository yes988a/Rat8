package wx.common.generator.base;

import java.util.ArrayList;
import java.util.List;

public class GroupsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public GroupsExample() {
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

        public Criteria andGnameIsNull() {
            addCriterion("gname is null");
            return (Criteria) this;
        }

        public Criteria andGnameIsNotNull() {
            addCriterion("gname is not null");
            return (Criteria) this;
        }

        public Criteria andGnameEqualTo(String value) {
            addCriterion("gname =", value, "gname");
            return (Criteria) this;
        }

        public Criteria andGnameNotEqualTo(String value) {
            addCriterion("gname <>", value, "gname");
            return (Criteria) this;
        }

        public Criteria andGnameGreaterThan(String value) {
            addCriterion("gname >", value, "gname");
            return (Criteria) this;
        }

        public Criteria andGnameGreaterThanOrEqualTo(String value) {
            addCriterion("gname >=", value, "gname");
            return (Criteria) this;
        }

        public Criteria andGnameLessThan(String value) {
            addCriterion("gname <", value, "gname");
            return (Criteria) this;
        }

        public Criteria andGnameLessThanOrEqualTo(String value) {
            addCriterion("gname <=", value, "gname");
            return (Criteria) this;
        }

        public Criteria andGnameLike(String value) {
            addCriterion("gname like", value, "gname");
            return (Criteria) this;
        }

        public Criteria andGnameNotLike(String value) {
            addCriterion("gname not like", value, "gname");
            return (Criteria) this;
        }

        public Criteria andGnameIn(List<String> values) {
            addCriterion("gname in", values, "gname");
            return (Criteria) this;
        }

        public Criteria andGnameNotIn(List<String> values) {
            addCriterion("gname not in", values, "gname");
            return (Criteria) this;
        }

        public Criteria andGnameBetween(String value1, String value2) {
            addCriterion("gname between", value1, value2, "gname");
            return (Criteria) this;
        }

        public Criteria andGnameNotBetween(String value1, String value2) {
            addCriterion("gname not between", value1, value2, "gname");
            return (Criteria) this;
        }

        public Criteria andGoticeIsNull() {
            addCriterion("gotice is null");
            return (Criteria) this;
        }

        public Criteria andGoticeIsNotNull() {
            addCriterion("gotice is not null");
            return (Criteria) this;
        }

        public Criteria andGoticeEqualTo(String value) {
            addCriterion("gotice =", value, "gotice");
            return (Criteria) this;
        }

        public Criteria andGoticeNotEqualTo(String value) {
            addCriterion("gotice <>", value, "gotice");
            return (Criteria) this;
        }

        public Criteria andGoticeGreaterThan(String value) {
            addCriterion("gotice >", value, "gotice");
            return (Criteria) this;
        }

        public Criteria andGoticeGreaterThanOrEqualTo(String value) {
            addCriterion("gotice >=", value, "gotice");
            return (Criteria) this;
        }

        public Criteria andGoticeLessThan(String value) {
            addCriterion("gotice <", value, "gotice");
            return (Criteria) this;
        }

        public Criteria andGoticeLessThanOrEqualTo(String value) {
            addCriterion("gotice <=", value, "gotice");
            return (Criteria) this;
        }

        public Criteria andGoticeLike(String value) {
            addCriterion("gotice like", value, "gotice");
            return (Criteria) this;
        }

        public Criteria andGoticeNotLike(String value) {
            addCriterion("gotice not like", value, "gotice");
            return (Criteria) this;
        }

        public Criteria andGoticeIn(List<String> values) {
            addCriterion("gotice in", values, "gotice");
            return (Criteria) this;
        }

        public Criteria andGoticeNotIn(List<String> values) {
            addCriterion("gotice not in", values, "gotice");
            return (Criteria) this;
        }

        public Criteria andGoticeBetween(String value1, String value2) {
            addCriterion("gotice between", value1, value2, "gotice");
            return (Criteria) this;
        }

        public Criteria andGoticeNotBetween(String value1, String value2) {
            addCriterion("gotice not between", value1, value2, "gotice");
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

        public Criteria andUsersMd5IsNull() {
            addCriterion("users_md5 is null");
            return (Criteria) this;
        }

        public Criteria andUsersMd5IsNotNull() {
            addCriterion("users_md5 is not null");
            return (Criteria) this;
        }

        public Criteria andUsersMd5EqualTo(String value) {
            addCriterion("users_md5 =", value, "usersMd5");
            return (Criteria) this;
        }

        public Criteria andUsersMd5NotEqualTo(String value) {
            addCriterion("users_md5 <>", value, "usersMd5");
            return (Criteria) this;
        }

        public Criteria andUsersMd5GreaterThan(String value) {
            addCriterion("users_md5 >", value, "usersMd5");
            return (Criteria) this;
        }

        public Criteria andUsersMd5GreaterThanOrEqualTo(String value) {
            addCriterion("users_md5 >=", value, "usersMd5");
            return (Criteria) this;
        }

        public Criteria andUsersMd5LessThan(String value) {
            addCriterion("users_md5 <", value, "usersMd5");
            return (Criteria) this;
        }

        public Criteria andUsersMd5LessThanOrEqualTo(String value) {
            addCriterion("users_md5 <=", value, "usersMd5");
            return (Criteria) this;
        }

        public Criteria andUsersMd5Like(String value) {
            addCriterion("users_md5 like", value, "usersMd5");
            return (Criteria) this;
        }

        public Criteria andUsersMd5NotLike(String value) {
            addCriterion("users_md5 not like", value, "usersMd5");
            return (Criteria) this;
        }

        public Criteria andUsersMd5In(List<String> values) {
            addCriterion("users_md5 in", values, "usersMd5");
            return (Criteria) this;
        }

        public Criteria andUsersMd5NotIn(List<String> values) {
            addCriterion("users_md5 not in", values, "usersMd5");
            return (Criteria) this;
        }

        public Criteria andUsersMd5Between(String value1, String value2) {
            addCriterion("users_md5 between", value1, value2, "usersMd5");
            return (Criteria) this;
        }

        public Criteria andUsersMd5NotBetween(String value1, String value2) {
            addCriterion("users_md5 not between", value1, value2, "usersMd5");
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