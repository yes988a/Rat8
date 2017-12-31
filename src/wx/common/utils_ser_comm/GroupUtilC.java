package wx.common.utils_ser_comm;

public class GroupUtilC {


    //(新增群某条信息)
    public final static int url_ser_insertGusers = 4634;

    //修改我在群中的备注(修改更新群某条信息)
    public final static int url_ser_updateUserRemark = 6693;

    //(删除群某条信息)
    public final static int url_ser_delGuserOne = 4172;


    //group的cid分布（主键，gid）
    public final static String redis_group_cid = "g1c9";

    //group在当前服务器人员集合。（主键，gid）
    public final static String redis_group_curr_uid = "gU3s7";

    //20分钟
    public final static int tim_redis_g = 1201;

}
