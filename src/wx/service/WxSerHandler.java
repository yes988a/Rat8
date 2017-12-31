package wx.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpVersion;
import wx.common.AbsSerHandler;
import wx.common.utils_app.*;
import wx.common.utils_ser_comm.*;
import wx.common.utils_server.SerUtil;
import wx.common.utils_server.WxUtil;
import wx.service.manager.*;

/**
 * url中转站
 * <p>
 * 关于：服务器间，可能出现无限循环：如果不需要返回，请在manager中不要接受jout，
 * 统一返回方法中也要判断jout是否有url，如果没有，不做操作。
 */
public class WxSerHandler extends AbsSerHandler {

    //manager业务逻辑处理中不进行，ctx的关闭和发送消息处理，所以，进来不使用WxUtil.appClose(uid);，直接使用ctx

    @Override
    public void userHttp(ChannelHandlerContext ctx, HttpVersion httpVersion, JsonObject into) {

        Integer url = null;
        try {
            url = into.get(WxUtil.para_url).getAsInt();
        } catch (Exception e) {
        }
        if (into == null || url == null) {
            ctx.close();
        } else {
            JsonObject jout = null;
            switch (url) {
                case RegisterUtilA.url_app_testnum:
                    jout = SerUtil.SPRING.getBean(RegisterManager.class).app_testnum_http(into);
                    break;
                case RegisterUtilA.url_app_complete:
                    jout = SerUtil.SPRING.getBean(RegisterManager.class).app_complete_http(into);
                    break;
                case LoginUtilA.url_app_testAcc:
                    jout = SerUtil.SPRING.getBean(LoginManager.class).app_testAcc_http(into);
                    break;
                case LoginUtilA.url_app_LoginCompelete:
                    jout = SerUtil.SPRING.getBean(LoginManager.class).app_LoginCompelete_http(into);
                    break;
                // --------------------     --------------         ------------          -----------
                case MineUtilA.url_app_findMysetting:
                    jout = SerUtil.SPRING.getBean(MineManager.class).app_findMysetting_http(into);
                    break;
                case MineUtilA.url_app_updateMyNickname:
                    jout = SerUtil.SPRING.getBean(MineManager.class).app_updateMyNickname_http(into);
                    break;
                case MineUtilA.url_app_updateMysound:
                    jout = SerUtil.SPRING.getBean(MineManager.class).app_updateMysound_http(into);
                    break;
                case MineUtilA.url_app_updateMyautograph:
                    jout = SerUtil.SPRING.getBean(MineManager.class).app_updateMyautograph_http(into);
                    break;
                // --------------------         ------------          --------------         ------------          -----------
                case SearchUtilA.url_app_findUserByQr:
                    jout = SerUtil.SPRING.getBean(SearchManager.class).app_findUserByQr_http(into);
                    break;
                case SearchUtilA.url_app_findUserByuu:
                    jout = SerUtil.SPRING.getBean(SearchManager.class).app_findUserByuu_http(into);
                    break;
                // --------------------         ------------          --------------         ------------          -----------
                case FriendUtilA.url_app_findUserrelation:
                    jout = SerUtil.SPRING.getBean(FriendManager.class).app_findUserrelation_http(into);
                    break;
                case FriendUtilA.url_app_updateRemark:
                    jout = SerUtil.SPRING.getBean(FriendManager.class).app_updateRemark_http(into);
                    break;
                case FriendUtilA.url_app_updateShie:
                    jout = SerUtil.SPRING.getBean(FriendManager.class).app_updateShie_http(into);
                    break;
                case FriendUtilA.url_app_delFriFrom:
                    jout = SerUtil.SPRING.getBean(FriendManager.class).app_delFriFrom_http(into);
                    break;
                case FriendUtilA.url_app_requestFri:
                    jout = SerUtil.SPRING.getBean(FriendManager.class).app_requestFri_http(into);
                    break;
                case FriendUtilA.url_app_agreeFri:
                    jout = SerUtil.SPRING.getBean(FriendManager.class).app_agreeFri_http(into);
                    break;
                // --------------------         ------------          --------------         ------------          -----------
                case ChatUtilA.url_app_addChatsingle:
                    jout = SerUtil.SPRING.getBean(ChatManager.class).app_addChatsingle_http(into);
                    break;
                case ChatUtilA.url_app_findChatsingle:
                    jout = SerUtil.SPRING.getBean(ChatManager.class).app_findChatsingle_http(into);
                    break;
                case ChatUtilA.url_app_delChatsingleByTims:
                    jout = SerUtil.SPRING.getBean(ChatManager.class).app_delChatsingleByTims_http(into);
                    break;
                // --------------------         ------------          --------------         ------------          -----------
                case PhoneUtilA.url_app_updateMyphone:
                    jout = SerUtil.SPRING.getBean(PhoneManager.class).app_updateMyphone_http(into);
                    break;
                // --------------------         ------------          --------------         ------------          -----------
                default:
                    System.out.println(url + "没有");
            }
            WxUtil.retHttp(httpVersion, ctx, jout);
        }
    }

    @Override
    public void serverWs(ChannelHandlerContext ctx, String msg) {

        JsonObject into = null;
        Integer url = null;
        try {
            into = new JsonParser().parse(msg).getAsJsonObject();
            url = into.get(WxUtil.para_url).getAsInt();
        } catch (Exception e) {
        }
        if (into == null || url == null) {
            //服务器间通信，不应该关闭，
            //日志
            //人，分析原因。
        } else {
            JsonObject jout = null;
            switch (url) {

                // --------------------         ------------          --------------         ------------          -----------
                case FriendUtilC.url_ser_delFriToRat2:
                    SerUtil.SPRING.getBean(FriendManager.class).ser_delFriToRat2(into);
                    break;
// --------------------         ------------          --------------         ------------          -----------
                case FriendUtilC.url_ser_ratToFriend:
                    SerUtil.SPRING.getBean(FriendManager.class).ser_ratToFriend(into);
                    break;
// --------------------         ------------          --------------         ------------          -----------
                case FriendUtilC.url_ser_resToreq:
                    SerUtil.SPRING.getBean(FriendManager.class).ser_resToreq(into);
                    break;

                // --------------------         ------------          --------------         ------------          -----------

/*
                    case GroupUtilA.url_ser_insertGusers:
                        SerUtil.SPRING.getBean(GroupManager.class).ser_insertGusers(into, jout);
                        break;

                    case GroupUtilA.url_ser_updateUserRemark:
                        SerUtil.SPRING.getBean(GroupManager.class).ser_updateUserRemark(into, jout);
                        break;

                    case GroupUtilA.url_ser_delGuserOne:
                        SerUtil.SPRING.getBean(GroupManager.class).ser_delGuserOne(into, jout);
                        break;
*/

                // --------------------         ------------          --------------         ------------          -----------
                case ChatUtilC.url_ser_chat_single_fromA:
                    jout = SerUtil.SPRING.getBean(ChatManager.class).ser_getChatFromA(into);
                    break;
                // --------------------         ------------          --------------         ------------          -----------
                case PhoneUtilC.url_ser_del_phone:
                    SerUtil.SPRING.getBean(PhoneManager.class).ser_del_phone(into);
                    break;
                case PhoneUtilC.url_ser_upda_phone:
                    SerUtil.SPRING.getBean(PhoneManager.class).ser_upda_phone(into);
                    break;

                // --------------------         ------------          --------------         ------------          -----------
                case TestnumUtilC.url_ser_syn_testSfe:
                    SerUtil.SPRING.getBean(TestnumManager.class).ser_syn_testSfe(into);
                    break;

                // --------------------         ------------          --------------         ------------          -----------
                case AccountUtilC.url_ser_use_acc:
                    jout = SerUtil.SPRING.getBean(RegisterManager.class).ser_use_acc(into);
                    break;
                case AccountUtilC.url_ser_use_acc_exist:
                    SerUtil.SPRING.getBean(RegisterManager.class).ser_use_acc_exist(into);
                    break;
                case RegisterUtilA.url_ser_userfull_syn:
                    SerUtil.SPRING.getBean(RegisterManager.class).ser_userfull_syn(into);
                    break;
                case RegisterUtilC.url_ser_userUni_syn:
                    SerUtil.SPRING.getBean(RegisterManager.class).ser_userUni_syn(into);
                    break;

                // --------------------         ------------          --------------         ------------          -----------

                case SerUtil.url_ser_del_storage:
                    SerUtil.SPRING.getBean(SerManager.class).ser_del_storage(into);
                    break;

                default:
                    System.out.println(url + "没有");
            }
            if (into.get(SerUtil.para_storage_need_return_id) != null) {
                JsonObject jdel = new JsonObject();
                jdel.addProperty(SerUtil.para_storage_return_id, into.get(SerUtil.para_storage_need_return_id).getAsString());
                jdel.addProperty(WxUtil.para_url, SerUtil.url_ser_del_storage);
                WxUtil.retWs(inner, ctx, jout);
            }
            WxUtil.retWs(inner, ctx, jout);
        }
    }
}
