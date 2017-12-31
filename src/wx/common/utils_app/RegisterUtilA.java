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

}
