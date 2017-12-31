package wx.common.utils_ser_netty;

import com.google.gson.JsonObject;
import org.apache.commons.codec.digest.DigestUtils;
import wx.common.utils_app.PhoneUtilA;
import wx.common.utils_app.TestnumUtilA;
import wx.common.utils_ser_comm.TestnumUtilC;
import wx.common.utils_server.RedisUtil;
import wx.common.utils_server.RetNumUtil;
import wx.common.utils_server.SerUtil;
import wx.common.utils_server.WxUtil;

import static wx.common.utils_server.RetNumUtil.num_1000;

public class TestnumUtilN {

    /**
     * 验证码插入。所有地方可用。
     *
     * @param url_redis         使用范围。:如：redis_reg_temp
     * @param phoneOrEmail      手机号，邮箱
     * @param uuid_remove_phone 随机ID，防止用户验证码在其他地方使用。   也防止在其他业务使用。
     * @return 可能为null。true成功，false发送失败，null次数太多或者格式错误。
     */
    public final synchronized static Boolean insertVerification(String url_redis, String phoneOrEmail, String uuid_remove_phone) {
        Integer num = 0;
        try {
            String numTim = RedisUtil.getR(TestnumUtilC.redis_test_num + phoneOrEmail, null);
            num = Integer.valueOf(numTim.substring(numTim.lastIndexOf("&")));
        } catch (Exception e) {
        }

        long tim_create = WxUtil.getTim();

        if (num != null && num > RetNumUtil.n_3) {
            return null;
            //全服务器通知，手机，次数过多，如果，其他服务也很多次，拉入黑名单。
        } else {

            if (PhoneUtilA.testPhone(phoneOrEmail)) {

                String[] labc = {"W", "E", "R", "Y", "U", "P", "A", "S",
                        "D", "F", "G", "H", "K", "Z", "X", "V", "B", "N", "M"};
                String testnum = labc[WxUtil.getRandomId(labc.length)] + WxUtil.getRandomId(num_1000);
                boolean sendSucc = true;//sample(phone, numTest);

                testnum = "12345";
                //testnum = DigestUtils.md5Hex(testnum);

                if (sendSucc) { //发送成功。
                    RedisUtil.setR(url_redis + phoneOrEmail,
                            DigestUtils.md5Hex(uuid_remove_phone + testnum), RedisUtil.tim_r_15m);
                    updateVerificationNum(phoneOrEmail, tim_create, num);
                    JsonObject jser = new JsonObject();
                    jser.addProperty(TestnumUtilA.para_tim_ser_create, tim_create);
                    jser.addProperty(TestnumUtilA.para_ser_phone_email, phoneOrEmail);//其实是，phoneOrEmail
                    SerUtil.sendMore(null, jser, null, TestnumUtilC.url_ser_syn_testSfe);
                    return true;
                } else {
                    return false;
                }
            } else if (WxUtil.testEmail(phoneOrEmail)) {
                return true;
            } else {
                return false;
            }
        }
    }


    //更新发送验证码的次数，（安全验证使用。）各个服务器间要同是更新。。
    public final static void updateVerificationNum(String phoneOrEmail, long tim_create, Integer num) {
        String numTim = RedisUtil.getR(TestnumUtilC.redis_test_num + phoneOrEmail, null);
        if (RedisUtil.val_error.equals(numTim)) {

        } else if (numTim == null) {
            RedisUtil.setR(TestnumUtilC.redis_test_num + phoneOrEmail,
                    1 + "&" + tim_create, RedisUtil.tim_r_15m);
            TestnumUtilC.inner_update_sql(phoneOrEmail, tim_create);
        } else if (!numTim.startsWith(tim_create + "")) {
            if (num == null) {
                try {
                    num = 0;
                    num = Integer.valueOf(numTim.substring(numTim.lastIndexOf("&")));
                } catch (Exception e) {
                }
            }
            RedisUtil.setR(TestnumUtilC.redis_test_num + phoneOrEmail,
                    (num + 1) + "&" + tim_create, RedisUtil.tim_r_15m);
            TestnumUtilC.inner_update_sql(phoneOrEmail, tim_create);
        }
    }

}
