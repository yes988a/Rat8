package wx.common.generator.active;

/**
 * @author 
 */
public class StorageTask {
    /**
     * 随机主键ID,为了用于删除.
     */
    private String uuid;

    /**
     * 哪个服务器接收。必填一个服务器ID
没有多个服务器情况。因为存储 为每个发送失败后存。
     */
    private String cid;

    /**
     * 业务类，用于确定查询UUID所在表。
     */
    private Integer url;

    private Long tim;

    /**
     * 具体内容，jsonobject(包含时间,接收用户或群,内容,发起人员或群.等)
     */
    private String jo;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getUrl() {
        return url;
    }

    public void setUrl(Integer url) {
        this.url = url;
    }

    public Long getTim() {
        return tim;
    }

    public void setTim(Long tim) {
        this.tim = tim;
    }

    public String getJo() {
        return jo;
    }

    public void setJo(String jo) {
        this.jo = jo;
    }
}