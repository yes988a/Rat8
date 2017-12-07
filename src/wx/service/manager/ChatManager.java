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
        jout.addProperty(WxUtil.para_json, new Gson().toJson(list));

        return jout;
    }

    /**
     * 清除已经得到的信息。不管是http还是ws都不返回para_r等信息。没有返回失败就是成功
     */
    public JsonObject app_delChatsingleByTims_http(JsonObject into) {

        JsonObject jout = new JsonObject();

        if (into.has(MineUtil.para_uid) && into.has(WxUtil.para_tim)) {
            String uid = into.get(MineUtil.para_uid).getAsString();
            String tims = into.get(WxUtil.para_tim).getAsString();

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
     */
    public JsonObject app_addChatsingle_http(JsonObject into) {

        String uid = into.get(MineUtil.para_uid).getAsString();

        JsonObject jout = new JsonObject();
        //校验数据格式。长度
        if (uid.length() != 32 || into.get(ChatUtil.para_chat_des_tif) == null || into.get(ChatUtil.para_chat_txt) == null
                || into.get(ChatUtil.para_res) == null) {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
            return jout;
        } else {
            //需要快速知道，who 、where、是不是同一个，所在服务器的简单信息。。。优化
            String cid = FriendUtil.getUsimpleCid(uid, into.get(ChatUtil.para_res).getAsString());
            if (cid == null) {
                //不是friend
                jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
                return jout;
            } else {
                long tim = WxUtil.getTim();
                into.addProperty(WxUtil.para_tim, tim);
                if (SerUtil.curCid.equals(cid)) {
                    insertChat(into);
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
                        SerUtil.sendOne(computer, into, null, ChatUtil.url_ser_chat_single_fromA, tim);
                        jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
                        return jout;
                    }
                }
            }
        }
    }

    /**
     * 服务器间使用，（接受好友服务器发送过来的聊天信息）
     */
    public JsonObject ser_getChatFromA(JsonObject into) {
        //判断，好友是否存在此服务器。?????
        boolean bb = insertChat(into);
        JsonObject jout = new JsonObject();
        if (bb) {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
            return jout;
        } else {
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
            return jout;
        }
    }

    //插入数据库。  jo中，uid即接收者ID，fid即发送者ID
    //发送给好友.
    private boolean insertChat(JsonObject into) {
        //校验，user权限。

        //同一个服务器，直接插入。
        //不同服务器，转发。

        int des_typ = into.get(ChatUtil.para_chat_des_tif).getAsInt();
        String txt = into.get(ChatUtil.para_chat_txt).getAsString();
        String req = into.get(ChatUtil.para_req).getAsString();
        String res = into.get(ChatUtil.para_res).getAsString();

        boolean filesucc = false;// 防止图片上传失败

        Chat chat = new Chat();

        chat.setUid(res);
        chat.setTim(into.get(WxUtil.para_tim).getAsLong());

        chat.setBtyp(ChatUtil.typ_chat_single);

        chat.setDtyp(des_typ);
        chat.setReqid(req);
        chat.setDes(txt);

        if (ChatUtil.typ_img == des_typ) {
            if (into.get(ChatUtil.para_f_str) == null) {
                filesucc = false;
            } else {
                Calendar instance = Calendar.getInstance();
                String yearday = instance.get(Calendar.YEAR)
                        + ""
                        + instance.get(Calendar.DAY_OF_YEAR);
                String path = File.separator
                        + FilesUtil.file_tempimg + res
                        + File.separator + yearday
                        + File.separator + txt;
                File file = new File(path);
                byte[] filebyte = Base64.decodeBase64(into.get(ChatUtil.para_f_str).getAsString());
                try {
                    FileUtils.writeByteArrayToFile(file,
                            filebyte);
                    chat.setDes(path);
                    filesucc = true;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {// 不是图片和附件上传，直接跳过啦文件操作
            filesucc = true;
        }
        if (filesucc) {
            //发送给friend
            chatMapper.insert(chat);
        }
        return filesucc;
    }
}