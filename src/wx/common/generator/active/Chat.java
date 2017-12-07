package wx.common.generator.active;

/**
 * @author 
 */
public class Chat extends ChatKey {
    /**
     * 请求者id：  好友id、群id、其他组织id
     */
    private String reqid;

    /**
     * 请求类型：type_app.md。
     */
    private Integer btyp;

    /**
     * 请求内容类型：type_app.md。
     */
    private Integer dtyp;

    /**
     * 单聊Json：txt长度640，。
（不管是群聊还是单聊，requid是：请求者ID(群id或人id)，长度只能是32位）

群聊Json：群聊消息发起者id，txt长度640，。
（不管是群聊还是单聊，requid是：请求者ID(群id或人id)，长度只能是32位）

delefri:fid，：：：des不需要有内容。

frireq：(des为:reqaccount ，reqnickname 、reqdes、met），
     */
    private String des;

    public String getReqid() {
        return reqid;
    }

    public void setReqid(String reqid) {
        this.reqid = reqid;
    }

    public Integer getBtyp() {
        return btyp;
    }

    public void setBtyp(Integer btyp) {
        this.btyp = btyp;
    }

    public Integer getDtyp() {
        return dtyp;
    }

    public void setDtyp(Integer dtyp) {
        this.dtyp = dtyp;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}