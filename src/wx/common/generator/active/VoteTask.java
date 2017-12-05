package wx.common.generator.active;

/**
 * @author 
 */
public class VoteTask {
    /**
     * 主键ID
     */
    private String uuid;

    /**
     * 任务执行时间
     */
    private Long runTim;

    /**
     * 赞同人数
     */
    private Integer agree;

    /**
     * 反对人数
     */
    private Integer oppose;

    /**
     * 总共多少人。理论上（大于同意人数和反对人数的和）
     */
    private Integer total;

    /**
     * 什么投票？ 群聊？等，做好基础人员聊天再说。
     */
    private Integer typ;

    /**
     * 创建时间
     */
    private Long tim;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getRunTim() {
        return runTim;
    }

    public void setRunTim(Long runTim) {
        this.runTim = runTim;
    }

    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public Integer getOppose() {
        return oppose;
    }

    public void setOppose(Integer oppose) {
        this.oppose = oppose;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getTyp() {
        return typ;
    }

    public void setTyp(Integer typ) {
        this.typ = typ;
    }

    public Long getTim() {
        return tim;
    }

    public void setTim(Long tim) {
        this.tim = tim;
    }
}