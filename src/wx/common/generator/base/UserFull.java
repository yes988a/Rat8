package wx.common.generator.base;

/**
 * @author 
 */
public class UserFull {
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
     * 密码。原始密码md5两次，第一次是appmd5，第二次是pc接受后md5.

如果不在当前服务器，为-1，不需要全服务器同步。
     */
    private String pas;

    /**
     * 用户名称，如飞。长度18，不可以为null，不可以为“”。。。。   逻辑关系中不凭借此做判断。

如果不在当前服务器，为-1，不需要全服务器同步。好友想要获取，查询用户所在服务器

     */
    private String nickname;

    /**
     * 消息通知：声音通知 

如果不在当前服务器，为-1，不需要全服务器同步。
     */
    private Integer sound;

    /**
     * 个人签名

如果不在当前服务器，为-1，不需要全服务器同步。好友想要获取，查询用户所在服务器

     */
    private String autograph;

    /**
     * 创建时间，也用作app数据库加密密钥

永远不变。

如果不在当前服务器，为-1，不需要全服务器同步。
     */
    private Long createTime;

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

    public String getPas() {
        return pas;
    }

    public void setPas(String pas) {
        this.pas = pas;
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

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
}