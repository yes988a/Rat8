package wx.common.entity;

/**
 * group成员简单信息
 */
public class GuserSimple {

    //用户ID。
    private String uid;

    //用户账号。
    private String uac;

    //用户备注。
    private String uremark;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUac() {
        return uac;
    }

    public void setUac(String uac) {
        this.uac = uac;
    }

    public String getUremark() {
        return uremark;
    }

    public void setUremark(String uremark) {
        this.uremark = uremark;
    }
}
