package wx.service.manager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import wx.common.generator.active.Chat;
import wx.common.generator.active.ChatExample;
import wx.common.generator.active.ChatMapper;
import wx.common.generator.base.*;
import wx.common.utils_app.ChatUtil;
import wx.common.utils_app.FriendUtil;
import wx.common.utils_app.MineUtil;
import wx.common.utils_server.RetNumUtil;
import wx.common.utils_server.SerUtil;
import wx.common.utils_server.WxUtil;
import wx.common.entity.UserrelationSimple;
import wx.common.entity.UsimpleTostrange;
import wx.service.dao.ExtUrelationMapper;
import wx.service.dao.ExtUserFullMapper;

import javax.annotation.Resource;
import java.util.List;

//好友关系，仅仅在rat中存在。
//关于备份：？？？？？ 暂时，没有备份。（理论应该，有专门的备份服务器？所有重要的操作，如删除好友，修改，加入群，等，备份。）
//备份的作用是什么？
public class FriendManager {

    @Resource
    private ExtUrelationMapper extUrelationMapper;

    @Resource
    private UrelationMapper urelationMapper;

    @Resource
    private ChatMapper chatMapper;

    @Resource
    private UserUniqueMapper userUniqueMapper;

    @Resource
    private UserFullMapper userFullMapper;

    @Resource
    private ExtUserFullMapper extUserFullMapper;


    //------------------------------------        查询和修改设置        --------------------------------------------

    //查询单个好友设置
    public JsonObject app_findUserrelation_http(JsonObject into) {
        JsonObject jout = new JsonObject();
        jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);

        if (into.get(FriendUtil.para_resid) == null || into.get(FriendUtil.para_resid).getAsString().length() != 32
                || into.get(MineUtil.para_uid) == null || into.get(MineUtil.para_uid).getAsString().length() != 32) {
            //错误。关闭连接
            return null;
        } else {
            String uid = into.get(MineUtil.para_uid).getAsString();
            String resid = into.get(FriendUtil.para_resid).getAsString();
            UserrelationSimple simple = extUrelationMapper.findUserrelationByfid(uid, resid);
            if (simple == null) { //我已经删除好友。
                //日志，不应该出现，因为是app发起的删除，怎么可能不知道。
                return jout;
            } else {
                jout.addProperty(WxUtil.para_json, new Gson().toJson(simple));
                jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
                return jout;
            }
        }
    }

    //更新： remark、shie
    //修改friend设置--remark
    public JsonObject app_updateRemark_http(JsonObject into) {
        JsonObject jout = new JsonObject();
        jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);

        if (into.get(FriendUtil.para_resid) == null || into.get(FriendUtil.para_resid).getAsString().length() != 32
                || into.get(MineUtil.para_uid) == null || into.get(MineUtil.para_uid).getAsString().length() != 32) {
            //错误。关闭连接
            return null;
        } else {
            String uid = into.get(MineUtil.para_uid).getAsString();
            String fid = into.get(FriendUtil.para_resid).getAsString();
            String bzname = into.get(FriendUtil.para_remark).getAsString();
            Urelation rure = new Urelation();
            rure.setUid(uid);
            rure.setFid(fid);
            rure.setRemark(bzname);
            int num = urelationMapper.updateByPrimaryKeySelective(rure);
            if (num != 1) {
                //日志，好友不存在，，不应该出现，因为是app发起的删除，怎么可能不知道。
                return jout;
            } else {
                jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
                return jout;
            }
        }
    }

    //更新： remark、shie
    //修改friend设置--shie
    public JsonObject app_updateShie_http(JsonObject into) {
        JsonObject jout = new JsonObject();
        jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);

        if (into.get(FriendUtil.para_resid) == null || into.get(FriendUtil.para_resid).getAsString().length() != 32
                || into.get(MineUtil.para_uid) == null || into.get(MineUtil.para_uid).getAsString().length() != 32) {
            //错误。关闭连接
            return null;
        } else {
            String uid = into.get(MineUtil.para_uid).getAsString();
            int shie = into.get(FriendUtil.para_shie).getAsInt();
            if (shie == WxUtil.val_nagative || shie == WxUtil.val_positive) {
                String fid = into.get(FriendUtil.para_resid).getAsString();
                Urelation rure = new Urelation();
                rure.setUid(uid);
                rure.setFid(fid);
                rure.setShie(into.get(FriendUtil.para_shie).getAsInt());
                int num = urelationMapper.updateByPrimaryKeySelective(rure);
                if (num != 1) {
                    //日志，好友不存在，，不应该出现，因为是app发起的删除，怎么可能不知道。
                    return jout;
                } else {
                    jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
                    return jout;
                }
            } else {
                //并加入黑名单。
                return null;
            }
        }
    }

    //----------------        ----------------   请求  请求  请求  请求   请求  ----------------       --------------

    // 请求者服务器使用
    // 申请 return n_9本来就是好友，，n_8不存在
    // 成功后，请求信息在被请求者的服务器。
    public JsonObject app_requestFri_http(JsonObject into) {

        //问：：：：什么是已经被删除？ 正常好友？  我也删除啦对方？

        //关于是否本来就是friend。
        //请求者不关心，自己这里是否是friend。
        //在接收方，收到时判断，是否friend，情况如下：
        //如果本来就是正常好友：直接校验。
        //如果，对方删除啦，我，但是我没删除对方，直接提示已经添加成功可以chat啦。
        //如果不是Fri，收到请求通知。

        //同意添加判断如下：
        //仅仅校验，请求方，不管是否存在关系，直接改为正常状态即可。

        //缺少长度和格式判断。

        JsonObject jout = new JsonObject();
        jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);

        if (!into.has(FriendUtil.para_met) || !into.has(FriendUtil.para_reqdes)
                || !into.has(FriendUtil.para_reqid) || !into.has(FriendUtil.para_resid)) {
            return jout;
        } else {
            String resid = into.get(FriendUtil.para_resid).getAsString();
            String reqid = into.get(FriendUtil.para_reqid).getAsString();
            String reqdes = into.get(FriendUtil.para_reqdes).getAsString();
            String met = into.get(FriendUtil.para_met).getAsString();

            if ("".equals(resid) || "".equals(reqid) || (!WxUtil.para_yes.equals(met) && !WxUtil.para_no.equals(met))) {
                // 黑名单。
                return jout;
            } else {
                Long tim = WxUtil.getTim();

                UsimpleTostrange reqDes = extUserFullMapper.findSimpleByUid(reqid);//查询自己的详情。
                UserUnique resDes = userUniqueMapper.selectByPrimaryKey(resid);//请求者的详情。

                if (reqDes == null) {//严重问题，只是断开连接应该不行。
                    // 黑名单。
                    return jout;
                } else if (reqid.equals(resid)) {
                    jout.addProperty(WxUtil.para_r, RetNumUtil.n_9);
                    return jout;
                } else if (resDes == null) {
                    //人员不存在。
                    jout.addProperty(WxUtil.para_r, RetNumUtil.n_8);
                    return jout;
                } else {
                    UrelationKey key = new UrelationKey();
                    key.setUid(reqid);
                    key.setFid(resid);
                    Urelation urelation = urelationMapper.selectByPrimaryKey(key);


                    // 请求页面（搜到：昵称，签名）
                    // 被请求页面：（账号，昵称，请求描述，是否认识,from从哪里来）

                    if (urelation == null || urelation.getDel() < 0) {
                        JsonObject des = new JsonObject();

                        des.addProperty(FriendUtil.para_reqacc, reqDes.getAcc());
                        des.addProperty(FriendUtil.para_reqnickname, reqDes.getNickname());
                        des.addProperty(FriendUtil.para_reqdes, reqdes);
                        des.addProperty(FriendUtil.para_met, met);

                        Chat chat = new Chat();

                        chat.setReqid(reqid);
                        chat.setBtyp(FriendUtil.typ_add_fri);
                        chat.setDtyp(ChatUtil.typ_des_txt);
                        chat.setDes(des.toString());
                        chat.setTim(tim);
                        chat.setUid(resid);

                        if (SerUtil.curCid.equals(resDes.getCid())) {
                            inner_friendSucc(chat);
                            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
                            return jout;
                        } else {
                            jout.addProperty(WxUtil.para_json, new Gson().toJson(chat));
                            SerUtil.sendOne(SerUtil.getComputer(resDes.getCid()), jout, SerUtil.level_0, FriendUtil.url_ser_ratToFriend, tim);
                            //不等待，不等待，不等待好友服务器返回信息。
                            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
                            return jout;
                        }
                    } else {
                        jout.addProperty(WxUtil.para_r, RetNumUtil.n_9);
                        return jout;
                    }
                }
            }
        }
    }

    //申请 ，rat2接收rat1消息。。
    //申请 ，接收者服务器收到信息。。
    public void ser_ratToFriend(JsonObject into) {
        Chat chat = null;
        try {
            chat = new Gson().fromJson(into.get(WxUtil.para_json).getAsString(), Chat.class);
        } catch (Exception e) {
        }

        JsonObject jout = new JsonObject();
        jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
        if (chat == null) {
            //严重错误，记录日志
            System.out.println("sendRat1ToFriend验证错误:" + into.get(WxUtil.para_json).getAsString());
        } else {
            inner_friendSucc(chat);
            //应该统一：所有的回复，都是服务器间同一个回复，标识已经成功接受到信息，删除临时记录表fails_task信息。
        }
    }

    /**
     * 通知被请求用户接收信息。。。
     * <p>
     * chat应该统一封装，，，，，，，，，，，，，，，，，
     *
     * @param chat
     */
    private void inner_friendSucc(Chat chat) {
        //用户行为让用户自己控制吧，接受信息烦了，就添加黑名单。   如果时间重复，表示服务器重复发送，app不再提示。

        ChatExample chatExample = new ChatExample();
        chatExample.createCriteria().andUidEqualTo(chat.getUid())//优化。。。。
                .andReqidEqualTo(chat.getReqid()).andBtypEqualTo(chat.getBtyp()); // 接收信息去重，从新设计吧。。。。。。。。。

        List<Chat> list = chatMapper.selectByExample(chatExample);

        JsonObject to_res = new JsonObject();
        to_res.addProperty(WxUtil.para_url, FriendUtil.url_ret_knownFri);
        to_res.addProperty(WxUtil.para_r, RetNumUtil.n_0);
        to_res.addProperty(WxUtil.para_json, new Gson().toJson(chat));

        if (list.size() == 0) {
            //发送给好友。
            chatMapper.insert(chat);
            WxUtil.retWsByuid(false, chat.getUid(), to_res);
        } else {
            if (list.size() > 1) {
                chatMapper.deleteByExample(chatExample);
                chatMapper.insert(chat);
                WxUtil.retWsByuid(false, chat.getUid(), to_res);
            } else {//等于一，对比时间和状态即可。
                /*
                if (WxUtil.stat_ab == list.get(0).getStat()) {//等待发送。（也不能删除，留着判断安全性。）
                    //忽略掉新的，留着旧请求。
                    WxUtil.retWsByuid(false, chat.getUid(), to_res);
                } else if (WxUtil.stat_ab == list.get(0).getStat()) {//已经发送过啦。（不能删除，因为还留着判断安全性。）
                    //判断时间是否相同。
                    if (list.get(0).getTim() == chat.getTim()) {
                        // 忽略新，不做操作。
                    } else {
                        chatMapper.insert(chat); //两次不同的请求。
                        WxUtil.retWsByuid(false, chat.getUid(), to_res);
                        //客户端，接受到相同的请求后，应该发送"忽略旧请求"，以新请求为准。
                    }
                }
                */

            }
        }
    }

    //----------------        ----------------   回复区，进行     ----------------        -------------------------
    //回复者服务器使用。（不再有拒绝，忽略。使用定时清空请求的方式。）
    //如果本来就是好友，也返回成功，好友服务器和user服务器校验更新即可。
    //同意 被请求方服务器接收。
    // 成功后，请求信息在被请求者的服务器。所以，同意后，在被请求者的服务器中插入数据即可。（是否是好友，以任何一个人有信息为准。）
    // n_10 ，已过期，
    public JsonObject app_agreeFri_http(JsonObject into) {

        JsonObject jout = new JsonObject();
        jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);

        if (into.get(WxUtil.para_tim) == null || into.get(FriendUtil.para_reqid) == null
                || into.get(FriendUtil.para_resid) == null || into.get(FriendUtil.para_reqnickname) == null) {

            return jout;
        } else {

            //请求者描述，如果回复者更改啦成为备注也可以。
            String reqNick = into.get(FriendUtil.para_reqnickname).getAsString();

            Long reqTim = into.get(WxUtil.para_tim).getAsLong();
            String reqid = into.get(FriendUtil.para_reqid).getAsString();
            String resid = into.get(FriendUtil.para_resid).getAsString();

            ChatExample ce = new ChatExample();  //优化，，，当用户接收到信息后，就会删除此条信息，需要另存其他地方，安全验证也在其他地方即可。
            ce.createCriteria().andReqidEqualTo(reqid).andTimEqualTo(reqTim)
                    .andUidEqualTo(resid).andBtypEqualTo(FriendUtil.typ_add_fri);

            long count = chatMapper.countByExample(ce);
            if (count == 1) {
                long tim = WxUtil.getTim();

                UserUnique reqUni = userUniqueMapper.selectByPrimaryKey(reqid);//请求者。
                UserFull respFull = userFullMapper.selectByPrimaryKey(resid);//回复者。

                if (reqUni == null || respFull == null) {
                    //过期啦
                    jout.addProperty(WxUtil.para_r, RetNumUtil.n_10);
                    return jout;
                } else {

                    // 回复者(被请求者)，插入好友数据。
                    insertFriend(resid, reqid, reqUni.getCid(), reqUni.getAcc(), reqNick, tim);

                    //发送到请求者的服务器。
                    if (reqUni.getCid().equals(SerUtil.curCid)) {
                        // 是否好友，以自己服务器数据为准（任何人员自己服务器有好友信息即使好友。）。
                        insertFriend(reqid, resid, SerUtil.curCid, respFull.getAcc(), respFull.getNickname(), tim);//请求者服务器插入数据。
                        noticeReq(reqid, resid);

                        jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
                        return jout;
                    } else {
                        // 通知，请求者服务器。
                        jout.addProperty(FriendUtil.para_resid, resid);
                        jout.addProperty(FriendUtil.para_reqid, reqid);
                        jout.addProperty(WxUtil.para_tim, tim);
                        jout.addProperty(FriendUtil.para_resnickname, respFull.getNickname());
                        // 通知请求者服务器。
                        SerUtil.sendOne(SerUtil.getComputer(reqUni.getCid()), jout, SerUtil.level_0, FriendUtil.url_ser_resToreq, tim);

                        jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
                        return jout;
                    }
                }
            } else if (count > 1) {
                // 不应该。。。日志，手动检查.
                jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
                return jout;
            } else {
                //过期啦
                jout.addProperty(WxUtil.para_r, RetNumUtil.n_10);
                return jout;
            }
        }
    }

    //接收到回复者的反馈。（请求者使用。）
    //同意 请求方，接收到同意信息。
    public void ser_resToreq(JsonObject into) {

        if (into.get(FriendUtil.para_resid) == null || into.get(FriendUtil.para_resnickname) == null
                || into.get(FriendUtil.para_reqid) == null || into.get(WxUtil.para_tim) == null) {
            // 日志
        } else {
            String resid = into.get(FriendUtil.para_resid).getAsString();
            UserUnique resUni = userUniqueMapper.selectByPrimaryKey(resid);//回复者。
            if (resUni == null || !SerUtil.curCid.equals(resUni.getCid())) {
                //纠错，回复者服务器已经插入啦，这个地方，有问题。
            } else {
                String resnickname = into.get(FriendUtil.para_resnickname).getAsString();
                String reqid = into.get(FriendUtil.para_reqid).getAsString();
                Long tim = into.get(WxUtil.para_tim).getAsLong();

                insertFriend(reqid, resid, resUni.getCid(), resUni.getAcc(), resnickname, tim);//请求者服务器插入数据。
                noticeReq(reqid, resid);
            }
        }
    }

    // 插入，关系表信息
    private void insertFriend(String uid, String fid, String f_cid, String f_acc, String f_nickname, long tim) {

        Urelation ure = new Urelation();
        ure.setFid(fid);
        ure.setUid(uid);
        ure.setDel(0);
        ure.setTim(tim);
        ure.setCid(f_cid);

        UrelationKey key = new UrelationKey();
        key.setFid(fid);
        key.setUid(uid);
        Urelation urelation = urelationMapper.selectByPrimaryKey(key);

        if (urelation == null) {
            ure.setAcc(f_acc);
            ure.setRemark(f_nickname);
            ure.setShie(WxUtil.val_positive);
            urelationMapper.insert(ure);
        } else {
            urelationMapper.updateByPrimaryKeySelective(ure);
        }

        //是否确实chat插入数据。

    }

    // 告诉请求者，好友已经同意添加（请求者使用.） 然后，app 进行，好友信息从新获取操作
    private void noticeReq(String uid, String resid) {
        JsonObject jout = new JsonObject();

        //告诉请求者，好友已经同意添加
        JsonObject chat = new JsonObject();
        chat.addProperty(WxUtil.para_url, FriendUtil.url_ret_knownFri);
        chat.addProperty(FriendUtil.para_resid, resid);

        WxUtil.retWsByuid(false, uid, jout);//提前判断，没有发送成功选择怎么的处理。使用不同的appSendMsg
        //标记，新信息变化，暂放
    }

    //-------------     -------------  -------------  删除好友   -------------     ---------     -------------

    //删除friend，，，，删除成功后，双方直接关系标记为删除，不删除（定时任务N天后删除。）
    public JsonObject app_delFriFrom_http(JsonObject into) {

        JsonObject jout = new JsonObject();
        jout.addProperty(WxUtil.para_r, RetNumUtil.n_b1);
        jout.addProperty(WxUtil.para_url, FriendUtil.url_app_delFriFrom);

        String uid = null;
        if (into.get(WxUtil.para_tim) == null || into.get(FriendUtil.para_resid) == null || uid == null) {
            return jout;
        } else {
            String resid = into.get(FriendUtil.para_resid).getAsString();

            UrelationKey key = new UrelationKey();
            key.setUid(uid);
            key.setFid(resid);
            Urelation urelation = urelationMapper.selectByPrimaryKey(key);

            if (urelation == null) {
                return jout;
            } else {
                if (SerUtil.curCid.equals(urelation.getCid())) {
                    //标记，被删除者关系
                    delResponse(resid, uid);
                    //删除请求者中的关系
                    urelationMapper.deleteByPrimaryKey(key);
                    //通知，发起者，删除成功。
                    jout.addProperty(FriendUtil.para_resid, resid);
                    jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
                    return jout;
                    //不需要通知被删除者。被删除者点击好友聊天时，检测好友信息。？？？？

                } else {
                    JsonObject toFri = new JsonObject();
                    toFri.addProperty(FriendUtil.para_reqid, uid);
                    toFri.addProperty(FriendUtil.para_resid, resid);
                    SerUtil.sendOne(SerUtil.getComputer(urelation.getCid()), toFri, SerUtil.level_0, FriendUtil.url_ser_delFriToRat2, WxUtil.getTim());
                    return jout;
                }
            }
        }
    }

    //rat2接受删除请求。
    public void ser_delFriToRat2(JsonObject into) {
        String resid = into.get(FriendUtil.para_resid).getAsString();
        String reqid = into.get(FriendUtil.para_reqid).getAsString();
        delResponse(resid, reqid);
    }

    //标记，被删除
    private void delResponse(String uid, String fid) {
        Urelation key = new Urelation();
        key.setFid(fid);
        key.setUid(uid);

        key.setDel(-1);
        urelationMapper.updateByPrimaryKeySelective(key);
    }
}
