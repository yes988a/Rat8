package wx.common.utils_app;

/**
 * 账号是否存在。唯一性。
 */
public class AccountUtilA {

    /* 账号 */
    public final static String para_acc = "a75c";

    /**
     * 注册时，判断账号存是否在时，判断操作的唯一性。
     */
    public final static String para_uuid_existAccForReg = "oU74L";

    // acc暂时占用标识.  （怎么判断？是一次操作，如果网络断了，用uuid也不行，）
    public final static String redis_acc_tem = "_at)";
    //redis_acc_tem准确应该叫：被当前服务器占用。

    /**
     * 登录唯一标识，用户名验证
     */
    public static boolean testAcc(String acc) {
        if (acc == null || "".equals(acc)) {
            return false;
        } else {
            String uNameReg = "^[a-zA-Z][a-zA-Z0-9_.]{4,63}$";
            return acc.matches(uNameReg);
        }
    }

}
