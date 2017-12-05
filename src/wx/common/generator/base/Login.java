package wx.common.generator.base;

/**
 * @author 
 */
public class Login {
    /**
     * 用户真实id
     */
    private String uid;

    /**
     * 随机id，来自person表里面的。什么作用，？现在没用到？标识是同一次login中的操作
     */
    private String ranid;

    /**
     * 随机token
     */
    private String tid;

    /**
     * 加密秘钥 key
     */
    private String aes;

    /**
     * 更新时间
     */
    private Long tim;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRanid() {
        return ranid;
    }

    public void setRanid(String ranid) {
        this.ranid = ranid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getAes() {
        return aes;
    }

    public void setAes(String aes) {
        this.aes = aes;
    }

    public Long getTim() {
        return tim;
    }

    public void setTim(Long tim) {
        this.tim = tim;
    }
}