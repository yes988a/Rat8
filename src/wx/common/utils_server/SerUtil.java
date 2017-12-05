package wx.common.utils_server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sun.istack.internal.NotNull;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.context.ApplicationContext;
import wx.common.WxCliHandler;
import wx.common.generator.active.StorageTask;
import wx.common.generator.active.StorageTaskMapper;
import wx.common.generator.base.Computer;
import wx.common.generator.base.ComputerExample;
import wx.common.generator.base.ComputerMapper;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 服务器间的通讯
 */
public class SerUtil {

    //是否成功启动，（如果true才执行定时任务里面的东西.）
    public static boolean succStart = false;

    /**
     * 每个computer的唯一标示，不可重复
     */
    public final static String curCid = "aaa8315999fe403d806d7d33adadwu6q";
    public static String curBid = ""; //数据库组。 根据curCid来自数据库。

    /**
     * spring上下文。
     */
    public static ApplicationContext SPRING = null;

    //作为服务器 （应该有前缀，否则，app随机一个真实用户uid，用户会下线。攻击）
    public final static Map<String, ChannelHandlerContext> ctxSer = new ConcurrentHashMap<String, ChannelHandlerContext>();

    //作为客户端
    public final static Map<String, ChannelHandlerContext> ctxCli = new ConcurrentHashMap<String, ChannelHandlerContext>();

    //web_socket，url前缀。
    public final static String ws_suffix = "x";
    //http前缀
    public final static String ht_suffix = "t";

    /**
     * 数据库中所有服务器的cid（无论是否启动，只要在数据库中就缓存到这里）。。。当数据库改变时?
     */
    public final static Set<String> servers = new HashSet<String>();

    /**
     * 客户端和service端使用同一个。
     */
    public final static EventLoopGroup eventLoopGroup = new NioEventLoopGroup();//这里的1，是CPU个数。???? 现在不写呢？

    /**
     * 错误有错误吗，严重错误，直接关闭。
     */
    public final static String send_heart = "*";//发送心跳内容 ，一个  *（8上面的那个）
    public final static String res_heart = "^";//回应心跳内容 ，一个  ^（6上面的那个）

    //0:存--发送--不管是否成功（定时任务检测表）---接受者返回专业的业务信息来删除已经成功的sql
    //1：发送----返回事变或者成功（不存储，不存储）（这种形式为了满足真正的得到啦消息，可以app只用长连接，等服务b返回给A，再给app最终结果。）
    public final static int level_0 = 0;

    //storage的主键id，ser之间传送。（发出时传送para_storage_id，返回需要删除的数据时传送uuid）
    public final static String para_storage_id = "s7i";

    //同步时使用，json的string
    public final static String para_user_full = "u2f";

    //同步时使用，json的string
    public final static String para_user_unique = "u8e";

    //删除StorageTask，统一，需要UUID即可。
    public final static int url_ser_del_storage = 8531;

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
        if (cid == null || RedisUtil.val_error.equals(cid)) {
            return null;
        } else {
            Computer co = null;
            try {
                co = new Gson().fromJson(RedisUtil.getR(redis_computer + cid, null), Computer.class);
            } catch (Exception e) {
            }
            if (co == null) {
                co = SerUtil.SPRING.getBean(ComputerMapper.class).selectByPrimaryKey(cid);
                if (co == null) {
                    //日志
                    return null;
                } else {
                    RedisUtil.setR(redis_computer + cid, new Gson().toJson(co), RedisUtil.tim_r_90d);
                    return co;
                }
            } else {
                return co;
            }
        }
    }

    /**
     * 发送给其它rat服务器（当选择群发时，没有返回结果是必然的。）
     * <p>
     * cat服务器如何处理？  全局通知很多时候包含cat，但是有些通知cat又不需要处理。（cat不处理的做判断，直接省略即可？还是rat中判断是否发送给cat）
     *
     * @param cs 如果cids为null则发送给所有
     */
    public final static void sendMore(List<Computer> cs, @NotNull JsonObject jo, Integer level, int url, Long tim) {
        if (tim == null) {
            tim = WxUtil.getTim();
        }
        if (cs == null || cs.size() == 0) {
            List<Computer> list = null;
            try {
                //所有在服务器使用时间范围内的。即使没有连接成功。
                list = new Gson().fromJson(RedisUtil.getR(redis_computer_list, null), new TypeToken<List<Computer>>() {
                }.getType());
            } catch (Exception e) {
            }
            if (list == null) {
                list = SerUtil.SPRING.getBean(ComputerMapper.class)
                        .selectByExample(new ComputerExample());// 查询所有，因为 serverStr 存储的所有computer

                for (int x = list.size() - 1; x >= 0; x--) {// 缓存数据处理。（去除自己和cat）
                    Computer c33 = list.get(x);
                    if (SerUtil.curCid.equals(c33.getCid())) {
                        list.remove(x); // 去除自己
                    } else {
                        SerUtil.servers.add(c33.getCid());
                        SerUtil.servers.add(c33.getPri());

                        if (isGoodComputer(c33, tim)) {
                        } else {
                            //不符合要求
                            list.remove(x);// redis中去除不在时间范围内的。
                        }
                    }
                }
                RedisUtil.setR(redis_computer_list, new Gson().toJson(list), RedisUtil.tim_r_30d);
            }
            moreToOne(list,jo,level,url,tim);
        } else {
            moreToOne(cs,jo,level,url,tim);
        }
    }
    //群发变单发
    private static void moreToOne(List<Computer> list,JsonObject jo,Integer level,int url,long tim){
        //群发处理。  待优化：关于不同业务处理，如群聊不能因为单个发送失败就返回给用户错误。如同步用户信息，某个服务器失败应该怎么处理。
        for (int y = list.size() - 1; y >= 0; y--) {
            boolean num = sendOne(list.get(y), jo, level, url, tim);
            if (!num) {
                RedisUtil.delR(redis_computer_list);
            }
        }
        //      群发，需要处理：当对方服务器停用时，作何操作。
    }

    //okhttp的websocket发送信息好像皆为  阻塞型的  ，不需要再有记录等。直接返回false即可。可以直接做到返回给app结果。

    /**
     * 发送给一个computer（检查是不是当前computer，如果是当前返回false。）
     * <p>
     * 区别：ChannelHandlerContext,见：retWs方法。
     *
     * @param computer
     * @param jo       具体消息内容，不存储时间和业务等信息。
     * @param level    0：存--发送--不管是否成功（定时任务检测表）---接受者返回专业的业务信息来删除已经成功的sql
     *                 1：发送----返回事变或者成功（不存储，不存储）（这种形式为了满足真正的得到啦消息，可以app只用长连接，等服务b返回给A，再给app最终结果。）
     * @param url      业务 ，不可以为null
     * @param tim      时间
     * @return, , ,-false发送失败(服务器错误、未知错误等)，ture成功(包括跳过自己)。
     * 成功，代表服务器是可用的，不一定是已经发送成功，如果没成功会自动再次发送
     * 模棱两可时，应该偏向与false.
     */
    public final static boolean sendOne(@NotNull Computer computer, @NotNull JsonObject jo, Integer level, int url, Long tim) {

        // 接受到信息后如何回执成功状态？ 主键UUID，和，url_ser_succ。待完善。

        if (SerUtil.curCid.equals(computer.getCid())) {
            return false;//跳过自己，对应返回结果，只要是1才是正确，0也是错误。
        } else {
            if (tim == null) {
                tim = WxUtil.getTim();
            }
            if (isGoodComputer(computer, tim)) {

                String uuid = null; // 随机标识。FailsTask主键。
                if (level != null && level_0 == level) {
                    uuid = WxUtil.getU32();
                    jo.addProperty(SerUtil.para_storage_id, uuid);
                }

                jo.addProperty(WxUtil.para_tim, tim);
                jo.addProperty(WxUtil.para_url, url);

                ChannelHandlerContext cf = SerUtil.ctxCli.get(computer.getCid());
                if (cf != null && cf.channel().isActive()) {
                    if (uuid != null) {
                        insertFails(uuid, computer.getCid(), url, tim, jo);
                    }
                    cf.writeAndFlush(new TextWebSocketFrame(jo.toString()));
                    return true;
                } else {
                    //纠错，处理
                    //有可能是从redis中取得数据，时间问题，也可能是服务器时间更新存在时间差。
                    boolean bb = connectServer(computer, tim);
                    if (bb) {
                        if (uuid != null) {
                            insertFails(uuid, computer.getCid(), url, tim, jo);
                        }
                        cf.writeAndFlush(new TextWebSocketFrame(jo.toString()));
                        return true;
                    } else {
                        return false;
                    }
                }
            } else {
                //不在服务期
                return false;
            }
        }
    }

    // 插入,失败表
    private final static void insertFails(@NotNull String uuid, @NotNull String cid, int url, long tim, @NotNull JsonObject jo) {

        StorageTask fails = new StorageTask();
        fails.setUuid(uuid);
        fails.setCid(cid);
        fails.setUrl(url);
        fails.setTim(tim);
        fails.setJo(jo.toString());

        SerUtil.SPRING.getBean(StorageTaskMapper.class).insert(fails);

    }

    //能不能发起服务器间的请求？正在发生请求的存储到这里面，则不可以再次请求。
    public static Set<String> conning = new HashSet<String>();

    /**
     * 连接服务器。 在服务时间内，而且可以连接上的，如果连接不上，会在当前服务器标识成不可连接。
     * <p>
     * 万一有一次失败？？？？？如果不标，那么每次都等待连接。。。。。。如何手动恢复？还是。。。。
     *
     * @param computer 返回true表示服务器时可用的并发送请求成功，不一定是已经连接成功。
     *                 模糊不清时应该尽量返回false，此处控制服务器没有连接情况下应该怎么返回给用户。
     *                 <p>
     *                 不能使用synchronized，因为很多在用此方法，如果没有连接成功应该快速的返回false
     *                 不能使用阻塞方法awaitUninterruptibly，也是因为要快速返回。
     *                 怎么避免重复？请求。。。添加标识  Set<String> conning
     */
    public final static boolean connectServer(@NotNull Computer computer, Long tim) {

        if (SerUtil.curCid.equals(computer.getCid())) {
//            System.out.println("---跳过自己---服务器-" + new Gson().toJson(computer));
            return false;
        } else {
            String cid = computer.getCid();
            if (SerUtil.ctxCli.containsKey(cid)) {
                return true;
            } else {

                if (tim == null) {
                    tim = TimUtil.getTimReal();
                }

                if (isGoodComputer(computer, tim) && !conning.contains(cid)) {
                    conning.add(cid);

                    try {
                        System.out.println("---申请连接----服务器--" + computer.getCid() + ":" + computer.getPor() + "  ......tim:  " + TimUtil.formatTimeToStr(WxUtil.getTim()) + "---------");

                        JsonObject jtest = new JsonObject();
                        jtest.addProperty(WxUtil.para_cid, SerUtil.curCid);

                        //  /?ws_suffix={"":""}
                        URI uri = new URI("ws", null, computer.getPri(), computer.getPor(), "/", SerUtil.ws_suffix + "=" + jtest.toString(), null);
                        Bootstrap bootstrap = new Bootstrap();
                        bootstrap.group(SerUtil.eventLoopGroup)
                                .channel(NioSocketChannel.class)
                                .option(ChannelOption.TCP_NODELAY, true)
                                .handler(new ChannelInitializer<SocketChannel>() {
                                    @Override
                                    protected void initChannel(SocketChannel ch) throws Exception {
                                        ChannelPipeline pipeline = ch.pipeline();
                                        pipeline.addLast(new HttpClientCodec());
                                        pipeline.addLast(new ChunkedWriteHandler());
                                        pipeline.addLast(new HttpObjectAggregator(512));
                                        pipeline.addLast(new WebSocketClientProtocolHandler(WebSocketClientHandshakerFactory.newHandshaker(
                                                uri, WebSocketVersion.V13, null, false, new DefaultHttpHeaders())));
                                        ch.pipeline().addLast(new WxCliHandler(computer.getCid()));
                                    }
                                });
                        bootstrap.connect(computer.getPri(), computer.getPor()).addListener(new ChannelFutureListener() {
                            @Override
                            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                                conning.remove(cid);
                                if (channelFuture.isSuccess()) {
                                    ComputerMapper computerMapper = SerUtil.SPRING.getBean(ComputerMapper.class);
                                    computer.setStop(RetNumUtil.n_0);//是否可用，每个服务器自己标记。也可批量管理员更新
                                    computerMapper.updateByPrimaryKeySelective(computer);
                                }
                            }
                        });

                        return false; // 当前没有连接时返回false（偏向与及时通知用户。那么群发怎么办？待完善）
                    } catch (Exception e) {
                        System.out.println("严重报错---连接服务器--" + computer.getCid() + ":" + computer.getPor() + "  ......tim:  " + TimUtil.formatTimeToStr(WxUtil.getTim()));
                        System.out.println(e.toString());
                        return false;
                    }
                } else {
                    System.out.println("-----不在服务时间----" + new Gson().toJson(computer) + TimUtil.formatTimeToStr(WxUtil.getTim()) + "---------");
                    return false;
                }
            }
        }
    }

    /**
     * 是否可以继续尝试连接的服务器。   如果是可以尝试连接的服务器那么，发送错误要记录，并定时去执行没有发送成功的。
     * <p>
     * 如果不是可以连接的服务器，那么，能返回给app的就报错误信息，不能返回的，即？？？？   怎么处理。。。。。。
     * <p>
     * 此方法过滤啦是否自己服务器。
     *
     * @param computer
     * @param tim      系统时间，可以null
     * @return 可以使用true，不可以使用false
     */
    private static boolean isGoodComputer(Computer computer, Long tim) {
        if (curCid.equals(computer.getCid())) {
            return false;
        } else {
            if (tim == null) {
                tim = TimUtil.getTimReal();
            }
            if (26 < computer.getStop() || tim < computer.getStim() || tim > computer.getEtim()) {
                return false;
            } else {
                return true;
            }
        }
    }
}
