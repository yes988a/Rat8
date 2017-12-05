package wx.common.generator.base;

/**
 * @author 
 */
public class UserUnique {
    /**
     * 用户的真实id，应该是用户名注册时间md5。  用户真实id仅仅使用在服务期间，任何用户客户端不可以获取到，包括自己获取自己
     */
    private String uid;

    /**
     * 账号，如yes988a。
唯一搜索。
为什么默认值不是-1，以为，登录时，如果没有匹配到此用户，需要知道，是不存在，还是，恶意攻击。
     */
    private String acc;

    /**
     * 用户所在服务器。(当有用户id想要获取用户id时使用此)
     */
    private String cid;

    /**
     * 二维码。
唯一搜索。
     */
    private String qrcode;

    /**
     * 如果，废弃，手机号又是不可重复，怎么办？  正常（17346513333），废弃（:时间戳+&+手机号）长度够用。
唯一搜索。
并修改phone_tim

     */
    private String phone;

    /**
     * 用户拥有手机号de确认时间.以用户实际操作确认验证码为准.

     */
    private Long phoneTim;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getPhoneTim() {
        return phoneTim;
    }

    public void setPhoneTim(Long phoneTim) {
        this.phoneTim = phoneTim;
    }
}