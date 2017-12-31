package wx.service.manager;

import com.google.gson.JsonObject;
import wx.common.generator.base.Computer;
import wx.common.generator.base.UserUnique;
import wx.common.generator.base.UserUniqueExample;
import wx.common.generator.base.UserUniqueMapper;
import wx.common.utils_app.*;
import wx.common.utils_app.RetNumUtilA;

import wx.common.entity.UsimpleTostrange;
import wx.common.utils_ser_comm.SerUtilC;
import wx.service.dao.ExtUserFullMapper;

import javax.annotation.Resource;
import java.util.List;

/**
 * 将来需要优化。
 */
public class SearchManager {

    @Resource
    private ExtUserFullMapper extUserFullMapper;

    @Resource
    private UserUniqueMapper userUniqueMapper;

    /**
     * 根据二维码主键查询用户详情。
     * rt_8没有结果
     */
    public JsonObject app_findUserByQr_http(JsonObject into) {
        JsonObject jout = new JsonObject();
        jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_b1);

        if (into.get(MineUtilA.para_qrcode) == null || into.get(MineUtilA.para_qrcode).getAsString().length() != 32) {
            //严重错误。攻击？
            return jout;
        } else {
            String qrc = into.get(MineUtilA.para_qrcode).getAsString();// qrcode 标示
            UsimpleTostrange uts = extUserFullMapper.findSimpleByQrcode(qrc);
            if (uts == null) {
                UserUniqueExample example = new UserUniqueExample();
                example.createCriteria().andQrcodeEqualTo(qrc);
                ipp(example, jout);
                return jout;
            } else {
                sendSimple(uts, jout);
                return jout;
            }
        }
    }

    /**
     * 用户，或者电话号码是否存在（如果有信息直接返回，如果没有，返回ipp，App去相应的服务器查询。暂时这么弄吧。）
     * rt_8没有结果
     */
    public JsonObject app_findUserByuu_http(JsonObject into) {
        JsonObject jout = new JsonObject();
        jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_b1);

        if (!into.has(SearchUtilA.para_acc_or_phone)) {
            //严重错误。攻击？
            return jout;
        } else {
            String accORphone = into.get(SearchUtilA.para_acc_or_phone).getAsString();

            UsimpleTostrange uts = null;
            UserUniqueExample example = new UserUniqueExample();
            if (AccountUtilA.testAcc(accORphone)) {
                uts = extUserFullMapper.findSimpleByacc(accORphone);
                example.createCriteria().andAccEqualTo(accORphone);
            } else if (PhoneUtilA.testPhone(accORphone)) {
                uts = extUserFullMapper.findSimpleByphone(accORphone);
                example.createCriteria().andPhoneEqualTo(accORphone);
            }
            if (uts == null) {
                ipp(example, jout);
                return jout;
            } else {
                sendSimple(uts, jout);
                return jout;
            }
        }
    }

    //返回ipp，暂时，所有的 人员昵称和头像可公开？
    private void ipp(UserUniqueExample example, JsonObject jout) {

        List<UserUnique> list = userUniqueMapper.selectByExample(example);
        if (list.size() == 0) {
            jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_8);
        } else {
            Computer computer = SerUtilC.getComputer(list.get(0).getCid());
            jout.addProperty(SearchUtilA.para_ipp, computer.getIp());
            jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_0);
        }
    }

    //发送simple
    private void sendSimple(UsimpleTostrange uts, JsonObject jout) {
        if (uts == null) {
            //信息不正确，不存在信息
            jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_8);

        } else {
            jout.addProperty(MineUtilA.para_uid, uts.getUid());
            jout.addProperty(MineUtilA.para_nickname, uts.getNickname());
            jout.addProperty(MineUtilA.para_autograph, uts.getAutograph());
            jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_0);
        }
    }
}
