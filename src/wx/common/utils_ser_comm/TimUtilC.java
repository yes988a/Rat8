package wx.common.utils_ser_comm;

public class TimUtilC {

    /**
     * 获取真正的时间，服务器获取真实时间和app不一样。。。
     *
     * @return
     */
    public static long getTimReal() {
        return System.currentTimeMillis();
    }

}
