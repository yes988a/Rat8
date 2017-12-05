package wx.service.manager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import wx.common.generator.base.UserFull;
import wx.common.generator.base.UserFullMapper;
import wx.common.utils_app.MineUtil;
import wx.common.utils_server.RetNumUtil;
import wx.common.utils_server.WxUtil;
import wx.common.entity.MySimple;
import wx.service.dao.ExtUserFullMapper;

import javax.annotation.Resource;

public class MineManager {

    @Resource
    private ExtUserFullMapper extUserFullMapper;
    @Resource
    private UserFullMapper userFullMapper;

    //查询我的个人设置
    public JsonObject app_findMysetting_http(JsonObject into) {
        String uid = into.get(MineUtil.para_uid).getAsString();
        MySimple mySimple = extUserFullMapper.findMySimple(uid);
        JsonObject jout = new JsonObject();
        if (mySimple == null) {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
            return jout;
        } else {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
            jout.addProperty(WxUtil.para_json, new Gson().toJson(mySimple));
            return jout;
        }
    }

    // 修改我的昵称，对应的通知其他用户更新   @return rt_8用户不存在或者其他错误
    public JsonObject app_updateMyNickname_http(JsonObject into) {
        String uid = into.get(MineUtil.para_uid).getAsString();
        JsonObject jout = new JsonObject();
        if (into.get(MineUtil.para_nickname) == null) {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
            return jout;
        } else {
            UserFull userFull = new UserFull();
            userFull.setUid(uid);
            userFull.setNickname(into.get(MineUtil.para_nickname).getAsString());
            int update = userFullMapper.updateByPrimaryKeySelective(userFull);
            return MineUtil.retMyset(update);
        }
    }

    //修改，声音通知
    public JsonObject app_updateMysound_http(JsonObject into) {
        String uid = into.get(MineUtil.para_uid).getAsString();
        JsonObject jout = new JsonObject();
        if (into.get(MineUtil.para_sound) == null) {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
            return jout;
        } else {
            int sound = into.get(MineUtil.para_sound).getAsInt();
            if (WxUtil.val_positive != sound && WxUtil.val_nagative != sound) {
                return jout;
            } else {

                UserFull userFull = new UserFull();
                userFull.setUid(uid);
                userFull.setSound(sound);
                int update = userFullMapper.updateByPrimaryKeySelective(userFull);
                return MineUtil.retMyset(update);
            }
        }
    }

    //修改，签名
    public JsonObject app_updateMyautograph_http(JsonObject into) {
        String uid = into.get(MineUtil.para_uid).getAsString();
        JsonObject jout = new JsonObject();
        if (into.get(MineUtil.para_autograph) == null) {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
            return jout;
        } else {

            UserFull userFull = new UserFull();
            userFull.setUid(uid);
            userFull.setAutograph(into.get(MineUtil.para_autograph).getAsString());
            int update = userFullMapper.updateByPrimaryKeySelective(userFull);
            return MineUtil.retMyset(update);
        }
    }
}
