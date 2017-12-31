package wx.common.utils_app;

import com.google.gson.JsonObject;
import org.apache.commons.codec.digest.DigestUtils;
import wx.common.generator.active.ErrNum;
import wx.common.generator.active.ErrNumMapper;
import wx.common.utils_server.RedisUtil;
import wx.common.utils_server.RetNumUtil;
import wx.common.utils_server.SerUtil;
import wx.common.utils_server.WxUtil;

import static wx.common.utils_server.RetNumUtil.num_1000;

/**
 * 验证码
 */
public class TestnumUtilA {

    /**
     * 验证码，类型：注册时，手机或者邮箱验证码。
     */
    public final static String para_testnum = "T04n";

    /**
     * 服务器间传送的主键。手机号？邮箱？等。
     */
    public final static String para_ser_phone_email = "U295E";

    /**
     * 验证码创建时间。。
     */
    public final static String para_tim_ser_create = "C5st";

    //随机一个UUID，获取验证码操作返回给app
    public final static String para_testnum_random = "roc5n";

}
