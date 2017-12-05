package wx.common.generator.base;

/**
 * @author 
 */
public class Urelation extends UrelationKey {
    /**
     * 好友cid，每次都要验证是否我的好友，而且要知道好友的私网ip，存redis中吧
     */
    private String cid;

    /**
     * 好友账号
     */
    private String acc;

    /**
     * 我对好友的备注 64
     */
    private String remark;

    /**
     * 完全屏蔽val_positive  val_nagative
     */
    private Integer shie;

    /**
     * 更新时间
     */
    private Long tim;

    /**
     * 是否已经被好友删除。0是正常，负数是已经被删除-1
     */
    private Integer del;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getShie() {
        return shie;
    }

    public void setShie(Integer shie) {
        this.shie = shie;
    }

    public Long getTim() {
        return tim;
    }

    public void setTim(Long tim) {
        this.tim = tim;
    }

    public Integer getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}