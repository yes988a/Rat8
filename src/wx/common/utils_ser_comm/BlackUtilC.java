package wx.common.utils_ser_comm;

import wx.common.utils_ser_comm.RedisUtilC;
import wx.common.utils_ser_netty.SerUtilN;


/**
 * 黑名单
 */
public class BlackUtilC {

    /**
     * 判断，或，添加黑名单。（如果是服务器，一定返回true）
     *
     * @param address 黑名单地址，可以是ip，可以是uid，可以是手机号，可以是一切。
     * @param bnum    如果非0，表示出错，应该添加错误次数。
     *                //黑名单更改时间和次数。  //7次为基数、6分钟为时间基数，大于5时间升一级。格式错误+2，没有权限+1，不在线+1。大于7禁止访问
     * @return -1不合格，0没在黑名单中，1服务器间访问
     * null 不合格，false 没在黑名单中，true服务器间访问
     */
    public static Boolean safe(String address, int bnum) {
        if (SerUtilN.servers.contains(address)) {
            //白名单
            return true;
        } else {
            String bb = RedisUtilC.getR(address,null);
            if (RedisUtilC.val_error.equals(bb)) {
//统计。通知管理员。
                return false;
            } else {
                if (bb == null) {//黑名单中不存在。
                    if (bnum > 0) {
                        RedisUtilC.setR(address, bnum + "", RedisUtilC.tim_r_6m);
                    }
                    return false;
                } else {
                    Integer num = null;
                    try {
                        num = Integer.valueOf(bb);
                    } catch (Exception e) {
                    }
                    if (num == null) {
                        RedisUtilC.setR(address, 1 + "", RedisUtilC.tim_r_6m);
                        return false;
                    } else {
                        if (bnum > 0) {
                            num = num + bnum;
                            if (num > 18) {
                                RedisUtilC.setR(address, num + "", RedisUtilC.tim_r_2h);
                                //后续应该增加，是否把手机号等更真实的信息放入黑名单，甚至数据库中。
                                return null;
                            } else if (num > 7) {
                                RedisUtilC.setR(address, num + "", RedisUtilC.tim_r_10m);
                                return null;
                            } else {
                                if (num > 5) {
                                    RedisUtilC.setR(address, num + "", RedisUtilC.tim_r_8m);
                                }
                                return false;
                            }
                        } else {
                            if (num > 7) {
                                return null;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        }
    }
}
