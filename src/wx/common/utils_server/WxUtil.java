package wx.common.utils_server;

import com.google.gson.JsonObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import wx.common.utils_app.TimUtil;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 公用的存放。。。如果，有专属某个模块，就当如到相应的模块下面。
 * <p>
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
 * <p>
 * <p>
 * <p>
 * 即时通讯，如果，没有及时通知到对方，发送方应该有知情权，知道对方没有收到，否则还在等待对方回复。。。  故以对方服务器实际收到信息为发送成功。
 * <p>
 * 因为好多操作是异步，没办法及时返回结果，所以，当接收者服务收到信息后，返回成功信号。
 */
public class WxUtil {

    /**
     * 访问状态，0成功，1token过期
     */
    public final static String para_r = "r";

    /**
     * 具体url地址
     */
    public final static String para_url = "u1r";

    /**
     * 二维码
     */
    public final static String para_qrcode = "qr3c";

    /**
     * 网络访问时间，8秒。 单位秒
     */
    public final static int tim_eight = 8;

    /**
     * 网络访问时间，11秒。 单位秒
     */
    public final static int tim_eleven = 11;

    /**
     * 网络访问时间，20秒，用于关键操作长时间。 单位秒
     */
    public final static int tim_twenty = 20;

    /**
     * 网络访问时间，30秒，用于异步访问时间。 单位秒
     */
    public final static int tim_thirty = 30;

    /**
     * 积极地，喜欢的）
     * 添加好友:同意
     * 消息通知:不屏蔽消息，不免打扰。控件关闭
     * 我的手机号：有效
     * 接受图片:是
     * 我的设置，声音通知
     */
    public final static int val_positive = 20;

    /**
     * 消极的，厌倦的）
     * 添加好友:拒绝
     * 消息通知:屏蔽消息，免打扰。控件打开
     * 我的手机号：失效
     * 接受图片:否
     * 我的设置，取消声音通知
     */
    public final static int val_nagative = 21;

    /**
     * computer id。
     */
    public final static String para_cid = "C6d";

    /**
     * 是的
     */
    public final static String para_yes = "y";

    /**
     * 否定
     */
    public final static String para_no = "n";

    public final static boolean testPass(String pass) {
        if (pass == null || pass.length() > 32 || pass.length() < 5) {
            return false;
        } else {
            return true;
        }
    }

    public final static boolean testEmail(String email) {
        String format = "\\w{1,}[@]\\w{1,}[.]\\p{Lower}{1,}";
        if (email.matches(format)) {
            return true;// 邮箱名合法，返回true
        } else {
            return false;// 邮箱名不合法，返回false
        }
    }

    public final static String getU32() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public final static long getTim() {
        return TimUtil.getTimReal();
    }

    /**
     * 警告日志（应该使用往java日志文件中写入报错的形式）
     */
    public final static void logw(String uid, String local, String param, long tim) {

    }

    /**
     * 随机一个角标。
     *
     * @return
     */
    public final static int getRandomId(int size) {
        if (size == 1) {
            return 0;
        } else {
            return new Random().nextInt(size);
        }
    }

    /*------- -----     返回信息开始。    start    如果不存在？根据json中的类型做不同处理。----
        ---------- 给APP的皆为回复，给服务器的都是client方式发送 -------        -------------------------------*/

    /**
     * 返回http（仅仅返回给app，服务器间不存在。）
     */
    public final static void retHttp(HttpVersion version, ChannelHandlerContext ctx, JsonObject jout) {

        // http不需要para_r
        if (jout == null) {
            ctx.close();
        } else {
            FullHttpResponse response = null;
            try {
                response = new DefaultFullHttpResponse(version,
                        HttpResponseStatus.OK,
                        Unpooled.wrappedBuffer(jout.toString().getBytes("UTF-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (response == null) {
                ctx.close();
            } else {
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            }
        }
    }

    /**
     * 发送长连接的text
     * <p>
     * 需要判断，url是否存在，否则会变成服务间的无限循环。。。。。。。。危险。。。。。
     * <p>
     * 服务器间通讯时：和SerUtil.ctxCli.get(再发送有什么区别？？？：：：ctxCli是以客户端身份发出信息，记载是否失败等。而此方法中的ChannelHandlerContext是回复给ctxCli的，不记载是否失败从发等信息
     *
     * @param inner 是否内部连接。 true服务器间，false用户连接。
     * @param ctx
     * @param jout  一、如果为null，关闭连接。。。二、如果para_r为null，关闭连接
     */
    public static void retWs(boolean inner, ChannelHandlerContext ctx, JsonObject jout) {
        if (ctx == null) {
        } else {
            if (jout == null || jout.get(WxUtil.para_r) == null) {
                if (inner) {
                    // 不返回(返回啦也不知道怎么接受)，不关闭
                } else {
                    //app，请求必须返回数据。
                    if (jout == null) {  //如果为null，关闭。。。如果没有para_r不返回即可，不需要关闭。
                        ctx.close();
                    }
                }
            } else {
                ctx.writeAndFlush(new TextWebSocketFrame(jout.toString()));
            }
        }
    }

    public static void retWsByuid(boolean inner, String uuid, JsonObject jout) {
        retWs(inner, SerUtil.ctxSer.get(uuid), jout);
    }

    /*----------------- -----------    返回信息开始    end  ----------------- ---------------------------*/

}
