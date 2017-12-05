package wx.common.entity;

/**
 * 没有确定的需求，通用先,暂放
 *
 * @author syf
 */
public class UsimplePojo {

    private String uid;

    private String acc;

    private String nickname;

    private String cid;

    /**
     * 仅仅同意添加好友时使用
     */
    private String phone;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
