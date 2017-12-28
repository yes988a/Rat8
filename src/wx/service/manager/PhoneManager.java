package wx.service.manager;

import com.google.gson.JsonObject;
import wx.common.generator.base.*;
import wx.common.utils_app.MineUtilA;
import wx.common.utils_app.PhoneUtilA;
import wx.common.utils_app.TestnumUtilA;
import wx.common.utils_server.RetNumUtil;
import wx.common.utils_server.SerUtil;
import wx.common.utils_server.WxUtil;

import javax.annotation.Resource;

/**
 * 手机号，因为唯一性。只在cat中绑定和解绑。  而且还关系到手机号发送验证码和次数等。
 * <p>
 * 发送验证码为http，   使用验证码，为http， 绑定账号， 解除和任何账号的绑定。
 * <p>
 * rat服务器仅仅接受主服务器cat发送的通知即可。（手机号，以cat为准）
 * <p>
 * cat:手机号的验证码操作在cat服务器。
 * <p>
 * rat:中只接受手机号的改变。
 * <p>
 * 用户绑定需要传送账号密码，正确后才可以获取验证码。
 * <p>
 * 仅仅解绑，可以不登录。每个手机每天只能发送3次验证码，使用后才失效。  设计次数限制sql表和redis： 验证码、个人设置修改、好友删除、添加好友个数等。
 * <p>
 * 手机号修改只修改phone_tim，和tim没有关系。
 * <p>
 * 手机号修改，以发送到服务器就算成功。所以，只有通知，和接收，不需要返回。
 */
public class PhoneManager {

    @Resource
    private UserUniqueMapper userUniqueMapper;
    @Resource
    private UserFullMapper userFullMapper;

    // 解除手机绑定。暂放。app未实现
    public JsonObject app_gettestnum_rp_http(JsonObject into) {
        JsonObject jout = new JsonObject();
        jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);

        String phone = into.get(PhoneUtilA.para_phone).getAsString().trim();

        if (PhoneUtilA.testPhone(phone)) {

            UserUniqueExample uniExample = new UserUniqueExample();
            uniExample.createCriteria().andPhoneEqualTo(phone);
            long count = userUniqueMapper.countByExample(uniExample);

            if (count == RetNumUtil.n_0) {
                //不需要，做此操作？？？
                // 此方法有个隐私隐患，如果，我随意输入一个好友的手机号发送验证码怎么办？
                // 不应该透漏，好友信息才对。
                return jout;
            } else {
                String uuid_remove_phone = WxUtil.getU32();
                jout.addProperty(PhoneUtilA.para_re_phone_random_uuid, uuid_remove_phone);
                Boolean succ = TestnumUtilA.insertVerification(TestnumUtilA.redis_remove_phone_temp, phone, uuid_remove_phone);
                TestnumUtilA.retinsertVerification(succ, jout);
                return jout;
            }
        } else {
            //日志。ip加入黑名单一段时间。唯一验证也加入黑名单一段时间。
            return null;
        }
    }

    //完成，手机解除绑定操作。暂放，app未实现。
    public JsonObject app_del_phone_complete_http(JsonObject into) {

        JsonObject jout = new JsonObject();
        jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);

        //// 缺少安全字符串验证，统一工具方法吧。
        if (into == null || into.get(PhoneUtilA.para_phone) == null || into.get(TestnumUtilA.para_testnum) == null) {
            return jout;
        } else {
            String phone = into.get(PhoneUtilA.para_phone).getAsString().trim(); // 手机号
            if (!PhoneUtilA.testPhone(phone)) {
                //攻击日志
                jout.addProperty(WxUtil.para_r, RetNumUtil.n_7);// 手机号格式不正确
                return jout;
            } else {
                String userId = null;
                String testnum = into.get(TestnumUtilA.para_testnum).getAsString().trim(); // 验证码
                if (TestnumUtilA.testVerification(TestnumUtilA.redis_reg_temp, phone, testnum)) {
                    long tim = WxUtil.getTim();
                    PhoneUtilA.delPhone(phone, tim);
                    JsonObject jser = new JsonObject();
                    jser.addProperty(PhoneUtilA.para_phone, phone);
                    jser.addProperty(PhoneUtilA.para_tim_ser_del, tim);
                    SerUtil.sendMore(null, jser, null, PhoneUtilA.url_ser_del_phone);

                    jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
                    return jout;
                } else {
                    return jout;
                }
            }
        }
    }

    //修改phone请求。  权限验证放到上一层。（登录后使用。）
    public JsonObject app_updateMyphone_http(JsonObject into) {
        JsonObject jout = new JsonObject();
        jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);

        //para_phone新手机号。
        if (into.get(PhoneUtilA.para_phone) == null || into.get(MineUtilA.para_uid) == null) {
            return jout;
        } else {
            String phone = into.get(PhoneUtilA.para_phone).getAsString();
            if (PhoneUtilA.testPhone(phone)) {
                String uid = into.get(MineUtilA.para_uid).getAsString();
                UserFull userFull = userFullMapper.selectByPrimaryKey(uid);
                if (userFull == null) {
                    //日志，安全。。。
                    jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
                    return jout;
                } else {
                    long tim = WxUtil.getTim();
                    PhoneUtilA.delPhone(phone, tim);
                    int update = updatePhone(uid, phone, tim);
                    if (update == 1) {
                        JsonObject jup = new JsonObject();
                        jup.addProperty(PhoneUtilA.para_phone, phone);
                        jup.addProperty(MineUtilA.para_uid, uid);
                        jup.addProperty(PhoneUtilA.para_tim_ser_update, tim);

                        //通知所有其他服务器。
                        SerUtil.sendMore(null, jup, SerUtil.level_0, PhoneUtilA.url_ser_upda_phone);

                        return MineUtilA.retMyset(update);
                    } else {
                        //  日志，不应该出现。
                        return jout;
                    }
                }
            } else {
                jout.addProperty(WxUtil.para_r, RetNumUtil.n_7);
                return jout;
            }
        }
    }

    //--------------                                    服务器端。                                --------------

    /**
     * 单独的解除手机号。功能： 注册，
     */
    public void ser_del_phone(JsonObject into) {
        if (into.get(PhoneUtilA.para_phone) == null || into.get(PhoneUtilA.para_tim_ser_del) == null) {
            // 日志，攻击？
        } else {
            String phone = into.get(PhoneUtilA.para_phone).getAsString().trim();
            long tim = into.get(PhoneUtilA.para_phone).getAsLong();
            PhoneUtilA.delPhone(phone, tim);
        }
    }

    /**
     * 手机号，切换用户。
     */
    public void ser_upda_phone(JsonObject into) {
        if (into.get(PhoneUtilA.para_phone) == null
                || into.get(MineUtilA.para_uid) == null
                || into.get(PhoneUtilA.para_tim_ser_update) == null) {
//日志。人工分析。
            //服务器间的交互，不能关闭。
        } else {
            String phone = into.get(PhoneUtilA.para_phone).getAsString();
            String uid = into.get(MineUtilA.para_uid).getAsString();
            long tim = into.get(PhoneUtilA.para_tim_ser_update).getAsLong();

            PhoneUtilA.delPhone(phone, tim);

            //插入信息数据。。
            int up = updatePhone(uid, phone, tim);

            if (up == 1) {
                //成功。
                //可能需要返回给原服务器。
            } else {
                //记录错误。
            }
        }
    }

    private int updatePhone(String uid, String phone, long tim) {

        //先废弃自己的手机号。再通知其服务器更新手机号。
        //如果，当手机号发生冲出。以PhoneTim修改时间为准。

        UserUnique UserUniqueNew = new UserUnique();
        UserUniqueNew.setUid(uid);
        UserUniqueNew.setPhoneTim(tim);
        UserUniqueNew.setPhone(phone);
        return userUniqueMapper.updateByPrimaryKeySelective(UserUniqueNew);
    }
}
