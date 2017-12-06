package wx.common.utils_app;

public class ChatUtil {

    //请求者ID
    public final static String para_req = "r74qm";

    //回复者ID
    public final static String para_res = "s92m";

    // 单聊，数据标示
    public final static int typ_chat_single = 5278;

    // 0 文本 1图片 3附件 4链接
    public final static int typ_txt = 0;
    public final static int typ_img = 1;
    public final static int typ_file = 3;
    public final static int typ_url = 4;

    //聊天信息类型前缀。文本？图片？等
    public final static String para_chat_des_tif = "c9tp";

    //聊天内容
    public final static String para_chat_txt = "3tx5";

    //文件内容
    public final static String para_f_str = "f2s0";

    //撤回，和发送聊天，由url来决定，不适用typ判断。(暂时取消撤回信息操作。。。。)
    //单聊，群聊，也通过url判定。
    //各种请求的发送和返回，url分开。返回以 url_ret_ 开头。

    //app发送信息。。。
    public final static int url_app_addChatsingle = 4956;

    //app请求，查询聊天信息，各种通知等。
    public final static int url_app_findChatsingle = 3087;
    public static long url_app_findChatsingle_tim_old = 0L; // 上次时间 ，每次不管成功失败都修改此时间（时间为本地时间，不使用getRealTime）
    public static int url_app_findChatsingle_tim_interval = 15 * 1000; // 每次间隔，15秒
    public static int url_app_findChatsingle_tim_out = 26 * 1000; // 请求超时，30秒
    public static boolean url_app_findChatsingle_webing = false; // true表示正在请求中，不允许再请求

    // 删除已读信息。
    public final static int url_app_delChatsingleByTims = 3417;

    //接受好友服务器发送过来的聊天信息
    public final static int url_ser_chat_single_fromA = 9216;

    //app发送group聊天。
    public final static int url_app_chat_group = 6492;   // 接受app过来的请求。
    public final static int url_ser_chat_group_getFromReq = 5219;//接受分发。
    public final static int url_ret_chat_group = 16378;

/*
    //撤回单聊。
    public final static int url_ret_back_singlechat = 82912;*/

}
