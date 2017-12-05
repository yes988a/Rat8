package wx.common.entity;

/**
 * 群简单信息
 */
public class GroupSimple {

    private String gremark;//群备注。

    private Integer shie;// 通知设置。

    private String gname;// 群名称。

    private String gotice;// 群公告。

    private Integer save;// 我是否保存群到群列表。

    public String getGremark() {
        return gremark;
    }

    public void setGremark(String gremark) {
        this.gremark = gremark;
    }

    public Integer getShie() {
        return shie;
    }

    public void setShie(Integer shie) {
        this.shie = shie;
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

    public Integer getSave() {
        return save;
    }

    public void setSave(Integer save) {
        this.save = save;
    }
}
