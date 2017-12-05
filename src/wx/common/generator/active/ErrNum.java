package wx.common.generator.active;

/**
 * @author 
 */
public class ErrNum {
    /**
     * 唯一标识，可以手机号，账号，邮箱，
     */
    private String uniqueId;

    /**
     * 错误次数。
     */
    private Integer num;

    /**
     * 最近更新时间
     */
    private Long tim;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Long getTim() {
        return tim;
    }

    public void setTim(Long tim) {
        this.tim = tim;
    }
}