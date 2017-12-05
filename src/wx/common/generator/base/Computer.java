package wx.common.generator.base;

/**
 * @author 
 */
public class Computer {
    /**
     * 每台电脑都有一个编号，32,位uuid
     */
    private String cid;

    /**
     * 每台电脑的私网IP地址
     */
    private String pri;

    /**
     * 公网ip具体信息。其实每个服务器还有自己的公网ip
     */
    private String ip;

    /**
     * 端口
     */
    private Integer por;

    /**
     * 负载均衡有多少人和群
     */
    private Integer unum;

    private Long stim;

    private Long etim;

    /**
     * 0是正常使用，，，123是连接错误次数。大于18不再尝试连接。
     */
    private Integer stop;

    private String bid;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPor() {
        return por;
    }

    public void setPor(Integer por) {
        this.por = por;
    }

    public Integer getUnum() {
        return unum;
    }

    public void setUnum(Integer unum) {
        this.unum = unum;
    }

    public Long getStim() {
        return stim;
    }

    public void setStim(Long stim) {
        this.stim = stim;
    }

    public Long getEtim() {
        return etim;
    }

    public void setEtim(Long etim) {
        this.etim = etim;
    }

    public Integer getStop() {
        return stop;
    }

    public void setStop(Integer stop) {
        this.stop = stop;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}