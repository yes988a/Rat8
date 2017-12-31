package wx.common.utils_ser_comm;

import com.google.gson.JsonObject;
import wx.common.generator.active.ErrNum;
import wx.common.generator.active.ErrNumMapper;
import wx.common.utils_server.RedisUtil;
import wx.common.utils_server.RetNumUtil;
import wx.common.utils_server.SerUtil;
import wx.common.utils_server.WxUtil;

public class TestnumUtilC {


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

    /**
     * 验证码插入数据库
     *
     * @param phoneOrEmail 主题，手机或者邮箱
     * @param tim          插入时间
     */
    public final static void inner_update_sql(String phoneOrEmail, long tim) {

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

}
