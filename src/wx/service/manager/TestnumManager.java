package wx.service.manager;

import com.google.gson.JsonObject;
import wx.common.utils_app.TestnumUtilA;
import wx.common.utils_ser_netty.TestnumUtilN;

/**
 * 验证码
 */
public class TestnumManager {

    //rat2接受删除请求。
    public void ser_syn_testSfe(JsonObject into) {

        String phoneOrEmail = into.get(TestnumUtilA.para_ser_phone_email).getAsString();
        long tim_create = into.get(TestnumUtilA.para_tim_ser_create).getAsLong();

        TestnumUtilN.updateVerificationNum(phoneOrEmail, tim_create, null);
    }
}
