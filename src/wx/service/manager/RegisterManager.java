package wx.service.manager;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import wx.common.generator.base.*;
import wx.common.utils_app.*;
import wx.common.utils_ser_comm.*;
import wx.common.utils_ser_netty.RegisterUtilN;
import wx.common.utils_ser_netty.SerUtilN;
import wx.common.utils_ser_netty.TestnumUtilN;
import wx.common.utils_ser_comm.RedisUtilC;
import wx.common.utils_app.RetNumUtilA;

import wx.service.dao.ExtComputerMapper;

import javax.annotation.Resource;
import java.util.List;

public class RegisterManager {

    @Resource
    private UserUniqueMapper userUniqueMapper;

    @Resource
    private UserFullMapper userFullMapper;

    @Resource
    private ExtComputerMapper extComputerMapper;

    /**
     * 步骤：随机一个pc------>获取。同一台服务器上完成reg（验证唯一性，去其他服务器上验证，）
     *
     * @param into
     */
    public synchronized JsonObject app_testnum_http(JsonObject into) {

        JsonObject jout = new JsonObject();
        jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_b1);

        if (into.get(PhoneUtilA.para_phone) == null || into.get(AccountUtilA.para_acc) == null) {
            return jout;
        } else {

            String know = ""; // 是否已经知道要解除其他账号的绑定。为""标识没有阅读，其他标识为阅读过啦
            String phone = into.get(PhoneUtilA.para_phone).getAsString().trim();
            String acc = into.get(AccountUtilA.para_acc).getAsString().trim(); // 账号

            if (into.get(PhoneUtilA.para_know_del_phone) != null) {
                know = into.get(PhoneUtilA.para_know_del_phone).getAsString();
            }
            if (!PhoneUtilA.testPhone(phone)) {
                jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_7);// 手机号不正确
                return jout;
            } else if (!AccountUtilA.testAcc(acc)) {
                jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_4);// 用户名格式不正确
                return jout;
            } else {

                boolean kPhone = false; //手机号是否可以继续注册。
                if (know.startsWith(MineUtilA.para_yes) && know.contains(phone)) {
                    kPhone = true;
                } else {
                    UserUniqueExample uniExample = new UserUniqueExample();
                    uniExample.createCriteria().andPhoneEqualTo(phone);
                    long count = userUniqueMapper.countByExample(uniExample);
                    if (count == RetNumUtilA.n_0) {
                        kPhone = true;
                    } else {
                        kPhone = false;
                    }
                }
                if (kPhone) {
                    String u32 = SerUtilC.getU32();
                    long tim = TimUtilC.getTimReal();
                    Boolean bb = existAccForReg(phone, u32, tim, acc);
                    if (bb == null) {
                        //redis错误。暂停注册业务。
                        return jout;
                    } else if (bb) {
                        //如果，服务器间更新时发生错误，怎么办？纠错系统吧。。。。。。。。。。。。。。。。
                        jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_5);//账号重复。
                        return jout;
                    } else {
                        // 手机号发送，返回结果，和存储到pc端数据库，调用网络接口并且记录日志。
                        Boolean succ = TestnumUtilN.insertVerification(TestnumUtilC.redis_reg_temp, phone, u32);
                        jout.addProperty(TestnumUtilA.para_testnum_random, u32);
                        TestnumUtilC.retinsertVerification(succ, jout);
                        return jout;
                    }
                } else {
                    jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_18);// 手机号已经注册，是否解除其他注册
                    return jout;
                }
            }
        }
    }

    /**
     * 完成注册，分配服务器，插入人员信息。
     */
    public JsonObject app_complete_http(JsonObject into) {

        JsonObject jout = new JsonObject();
        jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_b1);
        long tim = TimUtilC.getTimReal();

        //// 缺少安全字符串验证，统一工具方法吧。
        if (into == null || into.get(PhoneUtilA.para_phone) == null || into.get(TestnumUtilA.para_testnum) == null
                || into.get(AccountUtilA.para_acc) == null
                || into.get(MineUtilA.para_pas) == null) {
            return jout;
        } else {
            String phone = into.get(PhoneUtilA.para_phone).getAsString().trim(); // 手机号
            String pass = into.get(MineUtilA.para_pas).getAsString().trim(); // 密码
            String acc = into.get(AccountUtilA.para_acc).getAsString().trim(); // 账号
            if (!PhoneUtilA.testPhone(phone)) {
                //攻击日志
                jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_7);// 手机号格式不正确
                return jout;
            } else if (!AccountUtilA.testAcc(acc)) {
                jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_4);//攻击日志// 用户名格式不正确
                return jout;
            } else if (!LoginUtilA.testPass(pass)) {
                jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_26);//密码格式不正确
                return jout;
            } else {
                String userId = null;
                String testnum = into.get(TestnumUtilA.para_testnum).getAsString().trim(); // 验证码

                if (TestnumUtilC.testVerification(TestnumUtilC.redis_reg_temp, phone, testnum)) {
                    userId = SerUtilC.getU32();//生成用户的随机id。
                } else {
                    userId = null; //app请求才会发生。
                }
                if (userId == null) {
                    jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_6);// 手机号验证码过期或者不匹配
                    return jout;
                } else {
                    UserUniqueExample uniExample = new UserUniqueExample();
                    uniExample.createCriteria().andAccEqualTo(acc);
                    List<UserUnique> list = userUniqueMapper.selectByExample(uniExample);

                    if (list.size() > 0) {
                        if (list.get(0).getPhone().equals(phone)) { // 注册成功，重复请求。
                            LoginUtilC.returnApp(jout, SerUtilC.getComputer(list.get(0).getCid()));
                            return jout;
                        } else {
                            jout.addProperty(MineUtilA.para_r, RetNumUtilA.n_5);//账号重复。
                            return jout;
                        }
                    } else {
                        //注册，进行。

                        // 注册成功.  分配computer然后返回url。
                        // 相当于提前执行登录，中，的分配服务器。
                        Computer computer = RegisterUtilN.getLessComputer(TimUtilC.getTimReal(), null);
                        if (computer == null) {  //服务器错误。
                            return jout;
                        } else {
                            UserUnique unique = new UserUnique();
                            unique.setCid(computer.getCid());
                            unique.setUid(SerUtilC.getU32());
                            unique.setAcc(acc);
                            unique.setPhone(phone);
                            unique.setPhoneTim(tim);
                            unique.setQrcode(SerUtilC.getU32());

                            //纠错：如果，所在服务器只有userUnique，没有userFull。用户登录不上而已，空闲时间定时纠错即可。
                            UserFull userFull = new UserFull();
                            userFull.setUid(unique.getUid());
                            userFull.setAcc(unique.getAcc());
                            userFull.setAutograph("");
                            userFull.setCreateTime(TimUtilC.getTimReal());
                            userFull.setNickname("");
                            userFull.setPas(pass);
                            userFull.setSound(MineUtilA.val_positive);

                            boolean synBoo = false;
                            if (SerUtilC.curBid.equals(computer.getBid())) {
                                inner_ser_userfull_syn(userFull);
                                inner_ser_userunique_syn(unique);
                                synBoo = true;
                            } else {
                                JsonObject jo = new JsonObject();
                                jo.addProperty(SerUtilC.para_user_full, new Gson().toJson(userFull));
                                jo.addProperty(SerUtilC.para_user_unique, new Gson().toJson(unique));
                                synBoo = SerUtilN.sendOne(computer, jo, SerUtilN.level_0, RegisterUtilC.url_ser_userfull_syn);
                            }
                            if (synBoo) {
                                LoginUtilC.returnApp(jout, computer);
                                return jout;
                            } else {
                                return jout;
                            }
                        }
                    }
                }
            }
        }
    }

    //插入userfull时，一定同时插入userunique
    public void ser_userfull_syn(JsonObject into) {
        UserFull full = null;
        UserUnique unique = null;
        try {
            full = new Gson().fromJson(into.get(SerUtilC.para_user_full).getAsString(), UserFull.class);
            unique = new Gson().fromJson(into.get(SerUtilC.para_user_unique).getAsString(), UserUnique.class);
        } catch (Exception e) {
        }
        inner_ser_userfull_syn(full);
        inner_ser_userunique_syn(unique);

        JsonObject jo = new JsonObject();
        jo.addProperty(SerUtilC.para_user_unique, new Gson().toJson(unique));
        SerUtilN.sendMore(null, jo, SerUtilN.level_0, RegisterUtilC.url_ser_userUni_syn);
    }

    //同步uni。到全部服务器。
    public void ser_userUni_syn(JsonObject into) {
        UserUnique uNew = null;
        try {
            uNew = new Gson().fromJson(into.get(SerUtilC.para_user_unique).getAsString(), UserUnique.class);
        } catch (Exception e) {
        }

        if (uNew == null) {
            //日志
        } else {
            inner_ser_userunique_syn(uNew);
        }
    }

    //插入UserFull
    private void inner_ser_userfull_syn(UserFull full) {
        if (full == null) {
            //日志
        } else {
            UserFull userFullOld = userFullMapper.selectByPrimaryKey(full.getUid());
            if (userFullOld == null) {
                userFullMapper.insert(full);
            } else {
                if (userFullOld.getAcc().equals(full.getAcc()) && userFullOld.getUid().equals(full.getUid())) {
                    userFullMapper.updateByPrimaryKeySelective(full);
                } else {
                    //纠错系统。
                }
            }
        }
    }

    //插入UserUnique
    private void inner_ser_userunique_syn(UserUnique unique) {
        if (unique == null) {
            //日志
        } else {
            // 如果账号一样，纠错：判断账号创建时间。
            UserUniqueExample example = new UserUniqueExample();
            example.createCriteria().andAccEqualTo(unique.getAcc());
            List<UserUnique> unsss = userUniqueMapper.selectByExample(example);

            if (unsss.size() > 0) {//存在，不需要更改，可以判断是否需要进入纠错系统。。。
                UserUnique uniOld = unsss.get(0);

                if (uniOld.getCid().equals(unique.getCid()) && uniOld.getUid().equals(unique.getUid())
                        && uniOld.getQrcode().equals(unique.getQrcode())) {
                    //正确
                } else {
                    //纠错系统。包括验证cid在哪里。和多余的信息删除等。
                }
            } else {
                PhoneUtilC.delPhone(unique.getPhone(), unique.getPhoneTim());
                userUniqueMapper.insert(unique);
                extComputerMapper.updateUnum(unique.getCid());
            }
        }
    }

    /**
     * 账号是否占用。（注册第一步使用。）
     */
    public JsonObject ser_use_acc(JsonObject into) {

        String uuid_exist = into.get(AccountUtilA.para_uuid_existAccForReg).getAsString().trim();
        String acc = into.get(AccountUtilA.para_acc).getAsString().trim();

        UserUniqueExample uniExample = new UserUniqueExample();
        uniExample.createCriteria().andAccEqualTo(acc);
        long count = SerUtilC.SPRING.getBean(UserUniqueMapper.class).countByExample(uniExample);

        JsonObject jout = new JsonObject();
        if (count > 0) {
            //因为时间不是同一个服务器获取，所以，比较时间大小有点行不通。
            //每一个注册时都去对比，如果，其他服务器返回已经使用，直接取消注册（再次注册竞争即可。）

            jout.addProperty(AccountUtilA.para_uuid_existAccForReg, uuid_exist);
            jout.addProperty(AccountUtilA.para_acc, acc);
            jout.addProperty(MineUtilA.para_r, AccountUtilC.url_ser_use_acc_exist);
            return jout;
        } else {
            RedisUtilC.setR(AccountUtilC.redis_acc_tem + acc, uuid_exist, RedisUtilC.tim_r_10m);
            return null;
        }
    }

    /**
     * 账号占用啦啦啦。（服务器）
     */
    public void ser_use_acc_exist(JsonObject into) {

        String acc = into.get(AccountUtilA.para_acc).getAsString().trim();
        String uuid_exist = into.get(AccountUtilA.para_uuid_existAccForReg).getAsString().trim();

        RedisUtilC.delR(TestnumUtilC.redis_reg_temp + acc);

        RedisUtilC.setR(AccountUtilC.redis_acc_tem + acc, uuid_exist, RedisUtilC.tim_r_10m);
    }

    /**
     * 仅仅，注册第一步使用（账号是否被临时占用。）
     *
     * @param phone 用于判断是否同一次操作。
     * @param u32   随机一次UUID，判断是否用在同一个客户端。,应该是app传过来的，因为如果是服务器随机生成，连接重连出现问题。
     * @param acc
     * @param tim
     * @return
     */
    private final Boolean existAccForReg(String phone, String u32, long tim, String acc) {

        //本服务器，内容不为null，其他服务器，内容为""。
        //当前服务器中是否被其他人员占用？：比较phone是否一样。
        //验证码只能用在一个操作中：u32来确定同一操作。

        String str = RedisUtilC.getR(AccountUtilC.redis_acc_tem + acc, null);
        if (str == null || str.startsWith(phone)) { // 已经发送过一次验证码了。

            if (str == null) {
                UserUniqueExample uniExample = new UserUniqueExample();
                uniExample.createCriteria().andAccEqualTo(acc);
                long count = SerUtilC.SPRING.getBean(UserUniqueMapper.class).countByExample(uniExample);
                if (count > 0) {
                    return true;
                } else {
                    //发送给其他服务器，如果其他服务器返回已经存在，那么，删除redis中的注册信息，如果不存在，那么标记账号暂时占用（8分钟）。
                    //如果其他服务存在，对比时间，谁小，谁保留。
                    //这一步，并没有确定要使用此acc，所以，不能占用太长时间。
                    JsonObject jo = new JsonObject();
                    jo.addProperty(AccountUtilA.para_acc, acc);  // 如果，其他服务发现注册过了，那么通知其他所有服务设置成""
                    jo.addProperty(AccountUtilA.para_uuid_existAccForReg, u32);  // 随机的一个id，用于判断操作的唯一性。
                    SerUtilN.sendMore(null, jo, null, AccountUtilC.url_ser_use_acc);
                    return false;
                }
            } else {
                return false; // 插入验证码时，当前服务器的账号信息和安全信息会更新。
            }
        } else if ("".equals(str)) { //被占用啦。
            return true;
        } else {
            //服务器错误。。。暂停注册业务。
            return null;
        }
    }
}
