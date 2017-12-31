package wx.common.utils_ser_netty;

import com.google.gson.JsonObject;
import org.apache.commons.codec.digest.DigestUtils;
import wx.common.utils_app.LoginUtilA;
import wx.common.utils_app.PhoneUtilA;
import wx.common.utils_app.RetNumUtilA;
import wx.common.utils_ser_comm.SerUtilC;
import wx.common.utils_ser_comm.TestnumUtilC;
import wx.common.utils_ser_comm.RedisUtilC;
import wx.common.utils_ser_comm.TimUtilC;


import static wx.common.utils_app.RetNumUtilA.num_1000;

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
            String numTim = RedisUtilC.getR(TestnumUtilC.redis_test_num + phoneOrEmail, null);
            num = Integer.valueOf(numTim.substring(numTim.lastIndexOf("&")));
        } catch (Exception e) {
        }

        long tim_create = TimUtilC.getTimReal();

        if (num != null && num > RetNumUtilA.n_3) {
            return null;
            //全服务器通知，手机，次数过多，如果，其他服务也很多次，拉入黑名单。
        } else {

            if (PhoneUtilA.testPhone(phoneOrEmail)) {

                String[] labc = {"W", "E", "R", "Y", "U", "P", "A", "S",
                        "D", "F", "G", "H", "K", "Z", "X", "V", "B", "N", "M"};
                String testnum = labc[SerUtilC.getRandomId(labc.length)] + SerUtilC.getRandomId(num_1000);
                boolean sendSucc = true;//sample(phone, numTest);

                testnum = "12345";
                //testnum = DigestUtils.md5Hex(testnum);

                if (sendSucc) { //发送成功。
                    RedisUtilC.setR(url_redis + phoneOrEmail,
                            DigestUtils.md5Hex(uuid_remove_phone + testnum), RedisUtilC.tim_r_15m);
                    updateVerificationNum(phoneOrEmail, tim_create, num);
                    JsonObject jser = new JsonObject();
                    jser.addProperty(TestnumUtilC.para_tim_ser_create, tim_create);
                    jser.addProperty(TestnumUtilC.para_ser_phone_email, phoneOrEmail);//其实是，phoneOrEmail
                    SerUtilN.sendMore(null, jser, null, TestnumUtilC.url_ser_syn_testSfe);
                    return true;
                } else {
                    return false;
                }
            } else if (LoginUtilA.testEmail(phoneOrEmail)) {
                return true;
            } else {
                return false;
            }
        }
    }


    //更新发送验证码的次数，（安全验证使用。）各个服务器间要同是更新。。
    public final static void updateVerificationNum(String phoneOrEmail, long tim_create, Integer num) {
        String numTim = RedisUtilC.getR(TestnumUtilC.redis_test_num + phoneOrEmail, null);
        if (RedisUtilC.val_error.equals(numTim)) {

        } else if (numTim == null) {
            RedisUtilC.setR(TestnumUtilC.redis_test_num + phoneOrEmail,
                    1 + "&" + tim_create, RedisUtilC.tim_r_15m);
            TestnumUtilC.inner_update_sql(phoneOrEmail, tim_create);
        } else if (!numTim.startsWith(tim_create + "")) {
            if (num == null) {
                try {
                    num = 0;
                    num = Integer.valueOf(numTim.substring(numTim.lastIndexOf("&")));
                } catch (Exception e) {
                }
            }
            RedisUtilC.setR(TestnumUtilC.redis_test_num + phoneOrEmail,
                    (num + 1) + "&" + tim_create, RedisUtilC.tim_r_15m);
            TestnumUtilC.inner_update_sql(phoneOrEmail, tim_create);
        }
    }

}
