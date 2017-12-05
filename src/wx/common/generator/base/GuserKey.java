package wx.common.generator.base;

/**
 * @author 
 */
public class GuserKey {
    /**
     * 用户主键
     */
    private String uid;

    /**
     * 群主键，随机产生。发送消息使用，修改群设置使用      （根据gid查询时,效率不怎么样，应该）
     */
    private String gid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }
}