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

    //同步验证码发送次数到其他服务器。
    public final static int url_ser_syn_testSfe = 9412;

    /**
     * 手机验证码次数限定，（主键：手机号）
     * <p>
     * value： 时间戳&发送次数。
     */
    public final static String redis_test_num = "n3M";
    public final static String redis_reg_temp = "T7R";//验证码和手机号匹配临时存储。

    public final static String redis_remove_phone_temp = "w*j";//移除手机号账号关联，临时存储。

    /**
     * 验证码插入。所有地方可用。
     *
     * @param url_redis    使用范围。:如：redis_reg_temp
     * @param phoneOrEmail 手机号，邮箱
     * @param uuid_remove_phone         随机ID，防止用户验证码在其他地方使用。   也防止在其他业务使用。
     * @return 可能为null。true成功，false发送失败，null次数太多或者格式错误。
     */
    public final synchronized static Boolean insertVerification(String url_redis, String phoneOrEmail, String uuid_remove_phone) {
        Integer num = 0;
        try {
            String numTim = RedisUtil.getR(redis_test_num + phoneOrEmail, null);
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
                    jser.addProperty(TestnumUtilA.para_tim_ser_create,tim_create);
                    jser.addProperty(TestnumUtilA.para_ser_phone_email, phoneOrEmail);//其实是，phoneOrEmail
                    SerUtil.sendMore(null, jser, null, url_ser_syn_testSfe);
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

    //配合insertVerification使用，处理insertVerification的结果为json
    public final static void retinsertVerification(Boolean succ, JsonObject jout) {
        if (succ == null) {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_17);
        } else if (succ) {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
        } else {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_27);
        }
    }

    /**
     * 验证二维码是否正确，如果正确(删除，表示已经使用过。)
     *
     * @param url_redis    使用范围。:如：redis_reg_temp
     * @param phoneOrEmail 手机号，邮箱
     * @return 可能为null，标识已经过期。
     */
    public final static boolean testVerification(String url_redis, String phoneOrEmail, String uuidNumMd5) {
        if (uuidNumMd5 == null || uuidNumMd5.length() < 6) {
            return false;
        } else if (uuidNumMd5.equals(RedisUtil.getR(url_redis + phoneOrEmail, null))) {//格式：DigestUtils.md5Hex(uuid + testnum)
            RedisUtil.delR(url_redis + phoneOrEmail);
            return true;
        } else {
            return false;
        }
    }

    //更新发送验证码的次数，（安全验证使用。）各个服务器间要同是更新。。
    public final static void updateVerificationNum(String phoneOrEmail, long tim_create, Integer num) {
        String numTim = RedisUtil.getR(redis_test_num + phoneOrEmail, null);
        if (RedisUtil.val_error.equals(numTim)) {

        } else if (numTim == null) {
            RedisUtil.setR(redis_test_num + phoneOrEmail,
                    1 + "&" + tim_create, RedisUtil.tim_r_15m);
            inner_update_sql(phoneOrEmail, tim_create);
        } else if (!numTim.startsWith(tim_create + "")) {
            if (num == null) {
                try {
                    num = 0;
                    num = Integer.valueOf(numTim.substring(numTim.lastIndexOf("&")));
                } catch (Exception e) {
                }
            }
            RedisUtil.setR(redis_test_num + phoneOrEmail,
                    (num + 1) + "&" + tim_create, RedisUtil.tim_r_15m);
            inner_update_sql(phoneOrEmail, tim_create);
        }
    }

    private final static void inner_update_sql(String phoneOrEmail, long tim) {

        //用什么方法快速获取呢？sql库怎么不受攻击。
        //一天内不能超过的次数。一天黑名单，
        ErrNumMapper mapper = SerUtil.SPRING.getBean(ErrNumMapper.class);
        ErrNum errNum = mapper.selectByPrimaryKey(phoneOrEmail);
        if (errNum == null) {
            errNum = new ErrNum();
            errNum.setUniqueId(phoneOrEmail);
            errNum.setNum(1);
            errNum.setTim(tim);
            mapper.insert(errNum);
        } else {
            if (tim == errNum.getTim()) {
                //已经更新过啦。
            } else {
                errNum.setNum(errNum.getNum() + 1);
                errNum.setTim(tim);
                mapper.updateByPrimaryKeySelective(errNum);
            }
        }
    }
}