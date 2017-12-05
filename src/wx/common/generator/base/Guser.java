package wx.common.generator.base;

/**
 * @author 
 */
public class Guser extends GuserKey {
    /**
     * 用户对群设置。用户在群中的名字
     */
    private String uremark;

    /**
     * 用户对群设置。群名称群备注.
     */
    private String gremark;

    /**
     * 用户对群设置。是否屏蔽群消息提示
     */
    private Integer shie;

    /**
     * 入群时间，没啥用暂时。
     */
    private Long tim;

    /**
     * 人员是否还保留着发送权。
 1永远保存，0在线，-1已经删除
定时任务检查时：如果所有群成员都不再永久保存，那么就删除群。
     */
    private Integer save;

    public String getUremark() {
        return uremark;
    }

    public void setUremark(String uremark) {
        this.uremark = uremark;
    }

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

    public Long getTim() {
        return tim;
    }

    public void setTim(Long tim) {
        this.tim = tim;
    }

    public Integer getSave() {
        return save;
    }

    public void setSave(Integer save) {
        this.save = save;
    }
}