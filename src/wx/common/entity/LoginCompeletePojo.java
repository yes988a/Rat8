package wx.common.entity;

/**
 * 用户登录成功，信息。
 */
public class LoginCompeletePojo {

    private String uid;

    private String nickname;

    private Integer sound;

    private Long create_time;//用作app数据库加密。

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSound() {
        return sound;
    }

    public void setSound(Integer sound) {
        this.sound = sound;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
}
