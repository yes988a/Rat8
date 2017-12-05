package wx.common.entity;

/**
 * Created by hp on 2017/6/29.
 */
public class MySimple {

    private String nickname;// 昵称
    private String phone;// 电话
    private Integer sound;// 声音通知
    private String autograph;// 签名

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSound() {
        return sound;
    }

    public void setSound(Integer sound) {
        this.sound = sound;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

}
