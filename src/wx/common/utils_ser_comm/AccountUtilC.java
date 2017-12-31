package wx.common.utils_ser_comm;

import wx.common.generator.base.*;
import wx.common.utils_app.AccountUtilA;


import java.util.List;

public class AccountUtilC {

    // 注册，验证账号是否占用。
    public final static int url_ser_use_acc = 9062;

    // 账号占用。。
    public final static int url_ser_use_acc_exist = 3062;

    // acc暂时占用标识.  （怎么判断？是一次操作，如果网络断了，用uuid也不行，）
    public final static String redis_acc_tem = "_at)";
    //redis_acc_tem准确应该叫：被当前服务器占用。

    /**
     * 账号是否存在（完整的注册成功的账号。）
     * 缺少redis快速查询，不能每次都查base的sql库。
     *
     * @param acc
     * @return 返回null标识不存在。
     */
    public final static UserUnique existAcc(String acc) {
        if (AccountUtilA.testAcc(acc)) {
            UserUniqueExample uniExample = new UserUniqueExample();
            uniExample.createCriteria().andAccEqualTo(acc);
            List<UserUnique> uniss = SerUtilC.SPRING.getBean(UserUniqueMapper.class).selectByExample(uniExample);
            if (uniss.size() == 1) {
                return uniss.get(0);
            } else if (uniss.size() == 0) {

                UserFullExample fullExample = new UserFullExample();
                fullExample.createCriteria().andAccEqualTo(acc);
                long ufff = SerUtilC.SPRING.getBean(UserFullMapper.class).countByExample(fullExample);
                if (ufff > 0) {
                    //日志
                    //仅仅用于纠错判断。。。。，以UserUnique为准。
                }
                return null;
            } else {
                //日志
                return null;
            }
        } else {
            return null;
        }
    }

}
