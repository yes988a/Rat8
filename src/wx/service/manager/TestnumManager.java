package wx.service.manager;

import com.google.gson.JsonObject;
import wx.common.utils_app.TestnumUtil;
import wx.common.utils_server.WxUtil;

/**
 * 验证码
 */
public class TestnumManager {

    //rat2接受删除请求。
    public void ser_syn_testSfe(JsonObject into) {

        String phoneOrEmail = into.get(WxUtil.para_uuid).getAsString();
        long tim = into.get(WxUtil.para_tim).getAsLong();

        TestnumUtil.updateVerificationNum(phoneOrEmail, tim, null);
    }
}
