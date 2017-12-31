package wx.common.utils_ser_comm;

import com.google.gson.JsonObject;
import wx.common.utils_app.MineUtilA;
import wx.common.utils_app.RetNumUtilA;

public class MineUtilC {

    //更新我的昵称。 （关于时间冲突问题：
    // 如果这次，带时间过期，当更新成功后，时间改成最新，但其实，以前的一个其他修改，没有成功同步到其他服务器。
    // 所有，要么不依赖时间，要么，每个更新都对应一个时间，但是如果每个都对应时间，又何必要时间，直接对比值即可
    // 方法二、时间不一样，每次不仅仅更新如昵称，要更新，同级别的所有属性如sound、autograph、nickname
    // 三、pas应该为“”，因为不是自己的服务器。也可以其他
    // 四、手机号更改单独有个时间，因为，需要同时更改其他人员的手机操作。
    // 所以，不需要有url_ser_updateMyNickname这样的url，而是url_ser_update_mine）
    public final static int url_ser_update_mine = 7642;


    /**
     * 统一定义返回信息
     * <p>
     * 修改我的设置的时候。
     *
     * @param update
     */
    public final static JsonObject retMyset(int update) {
        JsonObject jout = new JsonObject();
        if (update == 1) {
            jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_0);
            return jout;
        } else {
            jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_b1);
            return jout;
        }
    }

}
