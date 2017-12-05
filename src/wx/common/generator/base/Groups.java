package wx.common.generator.base;

/**
 * @author 
 */
public class Groups {
    private String gid;

    private String gname;

    private String gotice;

    /**
     * 更新时间，如果app时间和此时间不一致，那么做更新操作。
     */
    private Long tim;

    /**
     * 用户ID存入hashset后变成json，tostring后，md5
     */
    private String usersMd5;

    /**
     * cid人数分组形式的json...GroupsUsersText
     */
    private String gusers;

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    public String getGotice() {
        return gotice;
    }

    public void setGotice(String gotice) {
        this.gotice = gotice;
    }

    public Long getTim() {
        return tim;
    }

    public void setTim(Long tim) {
        this.tim = tim;
    }

    public String getUsersMd5() {
        return usersMd5;
    }

    public void setUsersMd5(String usersMd5) {
        this.usersMd5 = usersMd5;
    }

    public String getGusers() {
        return gusers;
    }

    public void setGusers(String gusers) {
        this.gusers = gusers;
    }
}