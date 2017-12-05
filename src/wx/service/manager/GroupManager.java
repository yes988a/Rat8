package wx.service.manager;

public class GroupManager {
/*

    @Resource
    private ExtGuserDao extGuserDao;

    @Resource
    private ExtUsimpleDao extUsimpleDao;

    @Resource
    private UsimpleDao usimpleDao;

    @Resource
    private GuserDao guserDao;

    // --------   --------   --------     查询   信息      ------------------------------------------------      ---     -----------      ---     -----------

    //查询我的群列表简单信息.。
    public void app_findGroupSimples(String uid) {
        if (uid == null) {
            WxUtil.appClose(uid);
        } else {
            List<GroupSimple> groupSimple = extGuserDao.findGroupSimples(uid);
            JsonObject jout = new JsonObject();
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
            jout.addProperty(WxUtil.para_json, new Gson().toJson(groupSimple));
            WxUtil.appSendMsg(uid, jout);
        }
    }

    //查询群成员简单信息。
    public void app_findGroupUsersSimple(String uid, JsonObject jo) {
        if (jo.get(GroupUtil.para_gid) == null) {
            WxUtil.appClose(uid);
        } else {
            JsonObject jout = new JsonObject();
            GuserExample example = new GuserExample();
            example.createCriteria().andGidEqualTo(jo.get(GroupUtil.para_gid).getAsString()).andUidEqualTo(uid);
            long ll = guserDao.countByExample(example);
            if (ll == 0) {
                //不是群成员，可能已经别删除，但是客户端还没有更新。n_21
                jout.addProperty(WxUtil.para_r, RetNumUtil.n_21);
            } else {
                List<GuserSimple> list = extGuserDao.findGroupUsersSimple(jo.get(GroupUtil.para_gid).getAsString());
                jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
                jout.addProperty(WxUtil.para_json, new Gson().toJson(list));
            }
            WxUtil.appSendMsg(uid, jout);
        }
    }

    // --------   --------   --------     修改   设置       ------------------------------------------------      ---     -----------      ---     -----------

    //不需要通知其他sql
    //修改，群备注。
    public void app_updateGroupRemark(String uid, JsonObject jo) {
        if (uid == null || jo.get(GroupUtil.para_gid) == null || jo.get(GroupUtil.para_gropremark) == null) {
            WxUtil.appClose(uid);
        } else {
            String gid = jo.get(GroupUtil.para_gid).getAsString();

            Guser guser = new Guser();
            setGuserKey(guser, uid, gid);
            guser.setGremark(jo.get(GroupUtil.para_gropremark).getAsString());
            guserDao.updateByPrimaryKeySelective(guser);
            JsonObject jout = new JsonObject();
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
            jout.addProperty(WxUtil.para_url, GroupUtil.url_app_updateGroupRemark);
            jout.addProperty(GroupUtil.para_gid, gid);
            WxUtil.appSendMsg(uid, jout);
        }
    }

    //需要通知，其他服务器sql
    //修改，我在群中的备注。
    public void app_updateUserRemark(String uid, JsonObject jo) {
        if (uid == null || jo.get(GroupUtil.para_gid) == null || jo.get(GroupUtil.para_userremark) == null) {
            WxUtil.appClose(uid);
        } else {
            String gid = jo.get(GroupUtil.para_gid).getAsString();

            Guser guser = new Guser();
            setGuserKey(guser, uid, gid);
            guser.setUremark(jo.get(GroupUtil.para_userremark).getAsString());
            guserDao.updateByPrimaryKeySelective(guser);

            //群的发送，封装。
            jo.addProperty(WxUtil.para_url, GroupUtil.url_ser_updateUserRemark);
            GroupUtil.sendMsg(gid, jo, WxUtil.getTim());

            JsonObject jout = new JsonObject();
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
            jout.addProperty(WxUtil.para_url, GroupUtil.url_app_updateUserRemark);
            jout.addProperty(GroupUtil.para_gid, gid);
            WxUtil.appSendMsg(uid, jout);
        }
    }

    //修改，群通知。
    public void app_updateShie(String uid, JsonObject jo) {
        if (uid == null || jo.get(GroupUtil.para_gid) == null || jo.get(GroupUtil.para_groupshie) == null) {
            WxUtil.appClose(uid);
        } else {
            String gid = jo.get(GroupUtil.para_gid).getAsString();
            Guser guser = new Guser();
            setGuserKey(guser, uid, gid);
            guser.setShie(jo.get(GroupUtil.para_groupshie).getAsInt());
            guserDao.updateByPrimaryKeySelective(guser);
            JsonObject jout = new JsonObject();
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
            jout.addProperty(WxUtil.para_url, GroupUtil.url_app_updateShie);
            jout.addProperty(GroupUtil.para_gid, gid);
            WxUtil.appSendMsg(uid, jout);
        }
    }

    // --------   --------   --------     新增和邀请         ------------------------------------------------      ---     -----------      ---     -----------      ---     -----------

    //(新增群)仅仅添加一条，我对群的设置信息。
    public void app_addGroup(String uid, JsonObject jo) {

        //暂时缺少去重复判断。  用redis？每次sql校验？

        UsimpleTostrange usStrange = extUsimpleDao.findSimpleByUid(SerUtil.curCid, uid);

        if (usStrange == null) {
            //日志.
            WxUtil.appClose(uid);
        } else {
            String gid = WxUtil.getU32();

            Guser guser = new Guser();
            guser.setGid(gid);
            guser.setUid(uid);
            guser.setUremark(usStrange.getNickname());
            guser.setGremark("");
            guser.setShie(WxUtil.val_positive);
            guser.setTim(WxUtil.getTim());
            guser.setSave(0);
            guserDao.insert(guser);

            JsonObject jout = new JsonObject();
            jout.addProperty(WxUtil.para_r, RetNumUtil.n_0);
            jout.addProperty(GroupUtil.para_gid, gid);
            WxUtil.appSendMsg(uid, jout);
        }
    }

    //邀请
    // 邀请好友加入群（通知其他服务器。发送信息时每次都带群成员数目，来验证群成员是否正确，如果不正确？对比缺少谁？然后到相应人员服务器为准。（入校验系统。）。）
    public void app_invitation(String uid, JsonObject jo) {

        if (jo.get(GroupUtil.para_gid) == null || jo.get(FriendUtil.para_fids) == null) {
            //日志
        } else {
            Set<String> fids = null;
            try {
                fids = new Gson().fromJson(jo.get(FriendUtil.para_fids).getAsString(), HashSet.class);
            } catch (Exception e) {
            }

            if (fids == null) {
                //日志
            } else {
                long tim = WxUtil.getTim();
                String gid = jo.get(FriendUtil.para_fids).getAsString();
                //我在群中。
                GuserKey key = new GuserKey();
                key.setGid(gid);
                key.setUid(uid);
                Guser gu = guserDao.selectByPrimaryKey(key);
                if (gu == null) {
                    //不是群成员不能邀请其他人加入.
                } else if (!SerUtil.curCid.equals(gu.getCid())) {
//用户信息错误，人工处理
                    System.out.println("群，用户信息错误，人工处理");
                } else {
                    List<Guser> end = new ArrayList<Guser>();
                    Iterator<String> it = fids.iterator();

                    //服务器集合
                    List<String> allCid = extGuserDao.findCidsForUpdate(gid);
                    Set<String> allCidMore = new HashSet<String>();

                    //cid集合
                    List<String> allUid = extGuserDao.findUidsForUpdate(gid);

                    while (it.hasNext()) {// 人员服务器归类。
                        Usimple usimple = usimpleDao.selectByPrimaryKey(it.next());

                        if (!allCid.contains(usimple.getCid())) {
                            allCidMore.add(usimple.getCid());
                        }

                        if (!allUid.contains(usimple.getUid())) {

                            Guser guser = new Guser();
                            guser.setGid(gid);
                            guser.setUid(usimple.getUid());
                            guser.setCid(usimple.getCid());
                            guser.setUaccount(usimple.getAccount());
                            guser.setUremark(usimple.getNickname());
                            guser.setGremark("");
                            guser.setShie(WxUtil.val_positive);
                            guser.setGname("");
                            guser.setGotice("");
                            guser.setTim(WxUtil.getTim());
                            guser.setSave(0);

                            end.add(guser);

                        }
                    }
                    JsonObject joooo = new JsonObject();
                    joooo.addProperty(WxUtil.para_url, GroupUtil.url_ser_insertGusers);
                    joooo.addProperty(WxUtil.para_json, new Gson().toJson(end));

                    allCidMore.addAll(allCid);
                    Iterator<String> itttt = allCidMore.iterator();
                    while (itttt.hasNext()) {
                        //发送guser到其他服务器。
                        WxUtil.sendOneByCid(itttt.next(), joooo, tim);
                    }
                    //完成
                }
            }
        }
    }

    //自己退出群，没有被群主删除使用此方法 (删除群某条信息)
    public void app_quitGroup(String uid, JsonObject jo) {
        if (jo.get(GroupUtil.para_gid) == null) {
            //日志
        } else {
            GuserKey key = new GuserKey();
            key.setUid(uid);
            key.setGid(jo.get(GroupUtil.para_gid).getAsString());

            int del = guserDao.deleteByPrimaryKey(key);
            if (del == 1) {
                jo.addProperty(WxUtil.para_url, GroupUtil.url_ser_delGuserOne);
                GroupUtil.sendMsg(jo.get(GroupUtil.para_gid).getAsString(), jo, WxUtil.getTim());
            } else {
                //日志
                System.out.println("发起，，退群失败。" + uid + "" + jo.get(GroupUtil.para_gid).getAsString() + ".....");
            }
        }
    }

    //(新增群某条信息)
    public void ser_insertGusers(String uid, JsonObject jo) {

        if (jo.get(WxUtil.para_json) == null) {
            //日志
            System.out.println("新增加人员报错,参数错误");
        } else {
            List<Guser> end = null;
            try {
                end = new Gson().fromJson(jo.get(WxUtil.para_json).getAsString(), new TypeToken<List<Guser>>() {
                }.getType());
            } catch (Exception e) {
            }
            if (end != null && end.size() > 0) {
                for (int i = 0; i < end.size(); i++) {
                    Guser eee = end.get(i);

                    GuserKey key = new GuserKey();
                    key.setUid(eee.getUid());
                    key.setGid(eee.getGid());
                    Guser ggg = guserDao.selectByPrimaryKey(key);
                    if (ggg == null) {
                        guserDao.insert(eee);
                    } else {
//日志；；；；而且，是不是做其他处理。
                        System.out.println("插入用户是信息系不匹配。");
                    }
                }
            }
        }
    }

    //修改我在群中的备注(修改更新群某条信息)
    public void ser_updateUserRemark(String uid, JsonObject jo) {
        if (jo.get(GroupUtil.para_gid) == null || jo.get(GroupUtil.para_userremark) == null) {
            //日志，错误。攻击？
        } else {
            Guser guser = new Guser();
            setGuserKey(guser, uid, jo.get(GroupUtil.para_gid).getAsString());
            guser.setUremark(jo.get(GroupUtil.para_userremark).getAsString());
            guserDao.updateByPrimaryKeySelective(guser);
        }
    }

    //(删除群某条信息)
    public void ser_delGuserOne(String uid, JsonObject jo) {
        if (jo.get(GroupUtil.para_gid) == null) {
            //日志
        } else {
            GuserKey key = new GuserKey();
            key.setUid(uid);
            key.setGid(jo.get(GroupUtil.para_gid).getAsString());
            int del = guserDao.deleteByPrimaryKey(key);
            if (del != 1) {
                //日志
                System.out.println("同步，，退群失败。" + uid + "" + jo.get(GroupUtil.para_gid).getAsString() + ".....");
            }
        }
    }

    //---     -----------         ---      投票     -----------         ---     -----------      ---     -----------      ---     -----------      ---     -----------

    //投票定时。
    //修改，群名称。（群主权限）（等同于发送啦一条特殊聊天记录。具体更新由定时任务来做）
    public void app_updateGname(String uid, JsonObject jo) {
        if (uid == null || jo.get(GroupUtil.para_gid) == null || jo.get(GroupUtil.para_groupname) == null) {
            WxUtil.appClose(uid);
        } else {
            Guser guser = new Guser();
            setGuserKey(guser, uid, jo.get(GroupUtil.para_gid).getAsString());
            guser.setGname(jo.get(GroupUtil.para_groupname).getAsString());
            guserDao.updateByPrimaryKeySelective(guser);
            //通知其他
        }
    }

    //投票定时。
    //保存群公告。（群主权限）（等同于发送啦一条特殊聊天记录。具体更新由定时任务来做）
    public void app_updateGotice(String uid, JsonObject jo) {
        if (uid == null || jo.get(GroupUtil.para_gid) == null || jo.get(GroupUtil.para_groupnotice) == null) {
            WxUtil.appClose(uid);
        } else {
            Guser guser = new Guser();
            setGuserKey(guser, uid, jo.get(GroupUtil.para_gid).getAsString());
            guser.setGotice(jo.get(GroupUtil.para_groupnotice).getAsString());
            guserDao.updateByPrimaryKeySelective(guser);
            //通知其他
        }
    }

    //设置群主键
    private void setGuserKey(Guser guser, String uid, String gid) {
        guser.setGid(gid);
        guser.setUid(uid);
    }
*/


}
