package wx.common.utils_ser_comm;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.context.ApplicationContext;
import wx.common.generator.base.Computer;
import wx.common.generator.base.ComputerMapper;
import wx.common.utils_app.MineUtilA;
import wx.common.utils_ser_netty.SerUtilN;

import java.io.UnsupportedEncodingException;
import java.util.Random;
import java.util.UUID;

public class SerUtilC {

    //是否成功启动，（如果true才执行定时任务里面的东西.）
    public static boolean succStart = false;

    /**
     * 每个computer的唯一标示，不可重复
     */
    public final static String curCid = "aaa8315999fe403d806d7d33adadwu6q";
    public static String curBid = ""; //数据库组。 根据curCid来自数据库。

    /**
     * computer id。
     */
    public final static String para_cid = "C6d";

    //同步时使用，json的string
    public final static String para_user_full = "u2f";

    //同步时使用，json的string
    public final static String para_user_unique = "u8e";

    /**
     * spring上下文。
     */
    public static ApplicationContext SPRING = null;


    public final static String getU32() {
        return UUID.randomUUID().toString().replace("-", "");
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

    /**
     * 服务器详情List（主键：无）
     * <p>
     * 包括自己
     * <p>
     * 在服务器期限内，并且正常使用中。
     * <p>
     * 不在使用范围中的服务器，初次使用时，有好多信息就没办法收到，请在正常使用后，找时间点统计重要数据。
     * （错误服务：包括不在使用范围，和，自己挂啦的。）
     * <p>
     * <p>
     * CidRedis
     * <p>
     */
    public final static String redis_computer_list = "MliS" + curCid;

    /**
     * 服务器详情（主键：cid）
     * <p>
     * 忽略是否正常使用信息。
     * <p>
     * Computer
     * <p>
     */
    public final static String redis_computer = "m0C";

    /**
     * 数据库中获取computer。不判断是否可用。
     *
     * @param cid
     * @return
     */
    public final static Computer getComputer(String cid) {
        if (cid == null || RedisUtilC.val_error.equals(cid)) {
            return null;
        } else {
            Computer co = null;
            try {
                co = new Gson().fromJson(RedisUtilC.getR(redis_computer + cid, null), Computer.class);
            } catch (Exception e) {
            }
            if (co == null) {
                co = SerUtilC.SPRING.getBean(ComputerMapper.class).selectByPrimaryKey(cid);
                if (co == null) {
                    //日志
                    return null;
                } else {
                    RedisUtilC.setR(redis_computer + cid, new Gson().toJson(co), RedisUtilC.tim_r_90d);
                    return co;
                }
            } else {
                return co;
            }
        }
    }

}
