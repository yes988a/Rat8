package wx.common.utils_app;

import wx.common.generator.base.*;
import wx.common.utils_server.RedisUtil;
import wx.common.utils_server.SerUtil;
import wx.service.dao.ExtComputerMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 各参数前加固定前缀。
 * <p>
 * 网络访问时间：tim_web_
 * <p>
 * redis存储时间：tim_redis_
 * <p>
 * redis存储前缀： redis_
 * <p>
 * 路径前缀： file_
 * <p>
 * url业务标识前缀：url_login  作为para_url的value存在。    ，，，URL是A发送给B的路上(线)上的标识（线标识。）
 * <p>
 * 参数前缀： para_
 * <p>
 * 固定值标识：val_
 * <p>
 * type类型区分：typ_
 */
public class RegisterUtilA {

    //register 手机验证码。
    public final static int url_app_testnum = 2588;

    //register 完成
    public final static int url_app_complete = 7844;

    //把userFull同步到所在服务器。（必然同时插入userunique）
    public final static int url_ser_userfull_syn = 92402;

    //同步uni。到全部服务器。
    public final static int url_ser_userUni_syn = 95602;

    //负载最小的compter。（没有后缀）：好处，当注册时，某次请求重复时，获取到的compter是同一个。减少错误。
    public final static String redis_lessComputer = "w@l!" + SerUtil.curCid;

    /**
     * （可用的computer。客户端）获取负载最小的computer（可以是自己）
     * 当前服务器，可以联系上的服务器。
     */
    public final static Computer getLessComputer(long tim, List<String> noIds) {//count为零或者null即可

        if (noIds == null) {
            noIds = new ArrayList<String>();
        }
        int count = noIds.size();
        if (count < 6) {//防止死循环
            Computer computer = null;
            if (count == 0) {
                computer = SerUtil.getComputer(RedisUtil.getR(redis_lessComputer, null));
            }
            if (computer == null) {
                ExtComputerMapper extComputerMapper = SerUtil.SPRING.getBean(ExtComputerMapper.class);
                computer = extComputerMapper.selectLessOne(tim, noIds);
            }
            if (computer == null) {
                return null;
                //日志，不存在
            } else {
                boolean bb = SerUtil.ctxCli.containsKey(computer.getCid());
                if (SerUtil.curCid.equals(computer.getCid()) || bb) {
                    if (count != 0) {
                        RedisUtil.setR(redis_lessComputer, computer.getCid(), RedisUtil.tim_r_20m);
                    }
                    return computer;
                } else {
                    //尝试建立连接，，，，
                    SerUtil.connectServer(computer,null);
                    noIds.add(computer.getCid());
                    return getLessComputer(tim, noIds);
                }
            }
        } else {
            return null;
        }
    }
}
