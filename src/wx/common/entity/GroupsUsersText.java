package wx.common.entity;

public class GroupsUsersText {


    /**
     * 主键
     */
    private String cid;


    /**
     * 人员ID集合（上面cid下的人员集合。）
     */
    private String users;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }
}
