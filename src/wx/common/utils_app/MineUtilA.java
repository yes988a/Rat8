package wx.common.utils_app;

import com.google.gson.JsonObject;
import wx.common.utils_server.RetNumUtil;
import wx.common.utils_server.WxUtil;

/**
 * rat有app发送请求等。端应该有相应的所有ser更新。
 */
public class MineUtilA {

    //查询我的个人设置
    public final static int url_app_findMysetting = 4063;

    //修改我的昵称，对应的通知其他用户更新   @return rt_8用户不存在或者其他错误
    public final static int url_app_updateMyNickname = 6792;

    //修改，声音通知
    public final static int url_app_updateMysound = 2633;

    //修改，签名
    public final static int url_app_updateMyautograph = 3202;

    //声音
    public final static String para_sound = "2D3d";

    //昵称
    public final static String para_nickname = "1n9i";

    //手机号是否可用。0可用，-1不可用
    public final static String para_isphone = "iEo";

    //签名
    public final static String para_autograph = "a3h7";

    /**
     * 密码
     */
    public final static String para_pas = "p9a";

    /**
     * 用户的ID
     */
    public final static String para_uid = "U0D";

    /**
     * 查询个人设置，返回的我的简单信息。
     */
    public final static String para_m_simple_json = "usR1D";

    /**
     * 验证我的昵称，至少一位，最多18
     */
    public final static boolean testUserNickname(String name) {
        if (name == null) {
            return false;
        } else {
            String ss = name.trim();
            if (ss == null || ss.length() < 2 || ss.length() > 36) {
                return false;
            } else {
                return true;
            }
        }
    }

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
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
            return jout;
        } else {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
            return jout;
        }
    }
}
