package wx.service.manager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import wx.common.generator.active.Chat;
import wx.common.generator.active.ChatExample;
import wx.common.generator.active.ChatMapper;
import wx.common.generator.base.Computer;
import wx.common.utils_app.ChatUtil;
import wx.common.utils_app.FilesUtil;
import wx.common.utils_app.FriendUtil;
import wx.common.utils_app.MineUtil;
import wx.common.utils_server.RetNumUtil;
import wx.common.utils_server.SerUtil;
import wx.common.utils_server.WxUtil;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * 聊天记录。不需要查询操作，因为安全验证通过后直接查询chat表即可。
 */
public class ChatManager {

    @Resource
    private ChatMapper chatMapper;

    /**
     * 查询，chat表信息，理论上包含所有通知信息。
     */
    public JsonObject app_findChatsingle_http(JsonObject into) {

        String uid = into.get(MineUtil.para_uid).getAsString();

        ChatExample exampleChat = new ChatExample();
        exampleChat.createCriteria().andUidEqualTo(uid);

        List<Chat> list = chatMapper.selectByExample(exampleChat);

        JsonObject jout = new JsonObject();

        jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
        jout.addProperty(ChatUtil.para_list_msg_json, new Gson().toJson(list));

        return jout;
    }

    /**
     * 清除已经得到的信息。不管是http还是ws都不返回para_r等信息。没有返回失败就是成功
     */
    public JsonObject app_delChatsingleByTims_http(JsonObject into) {

        JsonObject jout = new JsonObject();

        if (into.has(MineUtil.para_uid) && into.has(ChatUtil.para_del_tims_json)) {
            String uid = into.get(MineUtil.para_uid).getAsString();
            String tims = into.get(ChatUtil.para_del_tims_json).getAsString();

            if (tims.equals("[]")) { //为null，数据错误，加入黑名单。
                jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
            } else {
                List<Long> timList = new Gson()
                        .fromJson(tims,
                                new TypeToken<List<Long>>() {
                                }.getType());

                ChatExample exampleChat = new ChatExample();
                exampleChat.createCriteria().andUidEqualTo(uid).andTimIn(timList);
                chatMapper.deleteByExample(exampleChat);
                jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
            }
        } else {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
        }
        return jout;
    }

    /**
     * 插入到chat内容。仅仅是单聊。
     * <p>
     * 图片等，应该先链接图片服务器，上传成功后才能调用此方法。
     */
    public JsonObject app_addChatsingle_http(JsonObject into) {
        JsonObject jout = new JsonObject();
        jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);

        String uid = into.get(MineUtil.para_uid).getAsString();

        //校验数据格式。长度
        if (!into.has(ChatUtil.para_chat_des_tif)
                || !into.has(ChatUtil.para_chat_txt)
                || !into.has(ChatUtil.para_res)) {
            return jout;
        } else {
            Integer des_typ = into.get(ChatUtil.para_chat_des_tif).getAsInt();
            if (ChatUtil.testDesTyp(des_typ)) { // 不符合要求
                return jout;
            } else {
                long tim_chat = WxUtil.getTim();
                String txt = into.get(ChatUtil.para_chat_txt).getAsString();
                String res = into.get(ChatUtil.para_res).getAsString();
                // 数据格式正确性判断。
                Chat chat = new Chat();
                chat.setUid(res);
                chat.setTim(tim_chat);
                chat.setBtyp(ChatUtil.url_app_addChatsingle);
                chat.setDtyp(des_typ);
                chat.setReqid(uid);
                chat.setDes(txt);

                //需要快速知道，who 、where、是不是同一个，所在服务器的简单信息。。。优化
                String cid = FriendUtil.getUsimpleCid(uid, into.get(ChatUtil.para_res).getAsString());
                if (cid == null) {
                    //不是friend
                    jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
                    return jout;
                } else {
                    if (SerUtil.curCid.equals(cid)) {
                        chatMapper.insert(chat);
                        jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
                        return jout;
                    } else {
                        //发送到好友服务器。
                        Computer computer = SerUtil.getComputer(cid);
                        if (computer == null) {
                            //日志
                            jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
                            return jout;
                        } else {
                            JsonObject ser_single_toB = new JsonObject();
                            ser_single_toB.addProperty(ChatUtil.para_tim_to_res_json, new Gson().toJson(chat));
                            SerUtil.sendOne(computer, ser_single_toB, null, ChatUtil.url_ser_chat_single_fromA);
                            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
                            return jout;
                        }
                    }
                }
            }
        }
    }

    /**
     * 服务器间使用，（接受好友服务器发送过来的聊天信息）
     */
    public JsonObject ser_getChatFromA(JsonObject into) {
        JsonObject jout = new JsonObject();
        if (into.has(ChatUtil.para_tim_to_res_json)) {
            String string = into.get(ChatUtil.para_tim_to_res_json).getAsString();
            Chat chat = new Gson().fromJson(string, Chat.class);

            //判断，好友是否存在此服务器。?????

            chatMapper.insert(chat);
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
            return jout;
        } else {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
            return jout;
        }
    }
}