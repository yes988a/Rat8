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

/*
    //包含自己在内.
    //获取group的cids----cid和它上面的额人数
    public final static List<GroupCidsNum> getCids(String gid) {

        String ss = RedisUtilC.getR(redis_group_cid + gid);

        if (RedisUtilC.val_error.equals(ss)) {
            return SerUtilC.SPRING.getBean(ExtGuserDao.class).findCids(gid);
        } else {
            List<GroupCidsNum> list = null;
            try {
                list = new Gson().fromJson(ss, new TypeToken<List<GroupCidsNum>>() {
                }.getType());
            } catch (Exception e) {
            }
            if (list == null) {
                list = SerUtilC.SPRING.getBean(ExtGuserDao.class).findCids(gid);
                RedisUtilC.setR(redis_group_cid + gid, new Gson().toJson(list), tim_redis_g);
            }
            return list;
        }
    }

    //获取group的当前服务器uids
    public final static List<String> getCidsUids(String gid) {

        String ss = RedisUtilC.getR(redis_group_curr_uid + gid);

        if (RedisUtilC.val_error.equals(ss)) {
            return SerUtilC.SPRING.getBean(ExtGuserDao.class).findCidsUids(gid, SerUtilC.curCid);
        } else {
            List<String> list = null;
            try {
                list = new Gson().fromJson(ss, new TypeToken<List<String>>() {
                }.getType());
            } catch (Exception e) {
            }
            if (list == null) {
                list = SerUtilC.SPRING.getBean(ExtGuserDao.class).findCidsUids(gid, SerUtilC.curCid);
                RedisUtilC.setR(redis_group_curr_uid + gid, new Gson().toJson(list), tim_redis_g);
            }
            return list;
        }
    }

    public final static void sendMsg(String gid, JsonObject jo, long tim) {

        List<GroupCidsNum> list = getCids(gid);

        for (int x = 0; x < list.size(); x++) {
            GroupCidsNum gcn = list.get(x);
            jo.addProperty(para_cid_usernum, gcn.getNum());
            WxUtil.sendOneByCid(gcn.getCid(), jo, tim);
        }
    }
    */

}
