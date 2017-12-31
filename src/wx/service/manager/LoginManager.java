package wx.service.manager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.apache.commons.codec.digest.DigestUtils;
import wx.common.entity.LoginCompeletePojo;
import wx.common.entity.UserrelationSimple;
import wx.common.generator.base.*;
import wx.common.utils_app.AccountUtilA;
import wx.common.utils_app.LoginUtilA;
import wx.common.utils_app.MineUtilA;
import wx.common.utils_app.RetNumUtilA;
import wx.common.utils_ser_comm.*;

import wx.service.dao.ExtUrelationMapper;
import wx.service.dao.ExtUserFullMapper;

import javax.annotation.Resource;
import java.util.List;

public class LoginManager {

    @Resource
    private ExtUserFullMapper extUserFullMapper;

    @Resource
    private LoginMapper loginMapper;

    @Resource
    private ExtUrelationMapper extUrelationMapper;

    /**
     * 验证码，好像，都有破解方法。最好的方式，莫过于，验证真正的唯一拥有者。
     *
     * 安全验证，暂放。
     */

    /**
     * 账号和密码是否匹配。
     * <p>
     * 一、随机选择的服务器到这里来，只知道userUnique，不知道userFull
     * <p>
     * 二、全当做情况一处理即可。。。
     *
     * @param into
     */
    public synchronized JsonObject app_testAcc_http(JsonObject into) {

        JsonObject jout = new JsonObject();

        String acc = into.get(AccountUtilA.para_acc).getAsString().trim();
        UserUnique unique = AccountUtilC.existAcc(acc);
        if (unique == null) {
            jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_23);//账号错误。
            return jout;
        } else {
            LoginUtilC.returnApp(jout, SerUtilC.getComputer(unique.getCid()));
            return jout;
        }
    }

    //   ------------                  app请求。                     ----------------------

    /**
     * app，登录完成。
     */
    public synchronized JsonObject app_LoginCompelete_http(JsonObject into) {
        JsonObject jout = new JsonObject();
        jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_b1);
        long tim = TimUtilC.getTimReal();

        if (into.get(AccountUtilA.para_acc) == null || into.get(MineUtilA.para_pas) == null) { //还应该有一些其他信息，安全信息。后续增加.
            return jout;
        } else {
            String acc = into.get(AccountUtilA.para_acc).getAsString();
            String pas = into.get(MineUtilA.para_pas).getAsString();
            if (AccountUtilA.testAcc(acc) && LoginUtilA.testPass(pas)) {

                LoginCompeletePojo lup = extUserFullMapper.loginCompelete(acc, pas);

                if (lup == null) {
                    jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_12);// 用户名密码不正确
                    return jout;
                } else {

                    String aesuuid = SerUtilC.getU32();  //第一次登录，加密信息
                    String token = SerUtilC.getU32();    //第一次登录，token
                    String randomid = DigestUtils.md5Hex(aesuuid + token); //每次登录唯一随机id

                    String rawKeyPC = AesUtilC.getRawKey(aesuuid + Long.toString(lup.getCreate_time()));
                    //第一次默认的加密秘钥，。。。。  优化

                    Login le = new Login();
                    le.setRanid(randomid);
                    le.setUid(lup.getUid());
                    le.setTid(token);
                    le.setAes(rawKeyPC);
                    le.setTim(tim);

                    // 二、在登录表和用户表中插入数据，如果登录表中有就更新登录表
                    int upnum = loginMapper.updateByPrimaryKey(le);
                    //安全验证，暂时放下：如，有nickename必须有登录记录，有好友关系必须有nickename，cid和当前服务器cid一致，
                    if (upnum == RetNumUtilA.n_0) {
                        loginMapper.insert(le);
                    }

                    List<UserrelationSimple> list = extUrelationMapper.findUserrelations(lup.getUid());

                    jout.addProperty(LoginUtilA.para_fri_list, new Gson().toJson(list));

                    //不需要返回urlIP地址，因为，它已经在访问
                    jout.addProperty(LoginUtilA.para_login_aes, aesuuid);
                    jout.addProperty(LoginUtilA.para_login_tid, token);
                    jout.addProperty(LoginUtilA.para_login_ctim, lup.getCreate_time());//应该配合ranid加密使用。
                    jout.addProperty(MineUtilA.para_uid, lup.getUid());
                    jout.addProperty(MineUtilA.para_nickname, lup.getNickname());
                    jout.addProperty(MineUtilA.para_sound, lup.getSound());
                    jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_0);
                    // app需要自己合成两个参数：randomid是aes和token的Md5，aes是给app的aes加createTime生成的密钥
                    return jout;
                }
            } else {
                jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_12);// 用户名密码不正确
                return jout;
            }
        }
    }
}
