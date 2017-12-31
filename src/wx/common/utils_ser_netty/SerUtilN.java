package wx.common.utils_ser_netty;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.sun.istack.internal.NotNull;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import io.netty.handler.stream.ChunkedWriteHandler;
import wx.common.WxCliHandler;
import wx.common.generator.active.StorageTask;
import wx.common.generator.active.StorageTaskMapper;
import wx.common.generator.base.Computer;
import wx.common.generator.base.ComputerExample;
import wx.common.generator.base.ComputerMapper;
import wx.common.utils_app.MineUtilA;
import wx.common.utils_app.RetNumUtilA;
import wx.common.utils_app.TimUtilA;
import wx.common.utils_ser_comm.MineUtilC;
import wx.common.utils_ser_comm.RedisUtilC;
import wx.common.utils_ser_comm.SerUtilC;
import wx.common.utils_ser_comm.TimUtilC;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SerUtilN {

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

    //storage的主键id，ser之间传送。需要被返回的主键id
    public final static String para_storage_need_return_id = "ri3n1";

    //storage的主键id，ser之间传送。返回时的主键，表示已经读取到这条数据。
    public final static String para_storage_return_id = "9krK";

    // ser之间传递，需要的时间标识。
    public final static String para_storage_tim = "t2nke";

    //删除StorageTask，统一，需要UUID即可。
    public final static int url_ser_del_storage = 8531;

    /**
     * 发送给其它rat服务器（当选择群发时，没有返回结果是必然的。）
     * <p>
     * cat服务器如何处理？  全局通知很多时候包含cat，但是有些通知cat又不需要处理。（cat不处理的做判断，直接省略即可？还是rat中判断是否发送给cat）
     *
     * @param cs 如果cids为null则发送给所有
     */
    public final static void sendMore(List<Computer> cs, @NotNull JsonObject jo, Integer level, int url) {
        long sys_time = TimUtilC.getTimReal();
        if (cs == null || cs.size() == 0) {
            List<Computer> list = null;
            try {
                //所有在服务器使用时间范围内的。即使没有连接成功。
                list = new Gson().fromJson(RedisUtilC.getR(SerUtilC.redis_computer_list, null), new TypeToken<List<Computer>>() {
                }.getType());
            } catch (Exception e) {
            }
            if (list == null) {
                list = SerUtilC.SPRING.getBean(ComputerMapper.class)
                        .selectByExample(new ComputerExample());// 查询所有，因为 serverStr 存储的所有computer

                for (int x = list.size() - 1; x >= 0; x--) {// 缓存数据处理。（去除自己和cat）
                    Computer c33 = list.get(x);
                    if (SerUtilC.curCid.equals(c33.getCid())) {
                        list.remove(x); // 去除自己
                    } else {
                        SerUtilN.servers.add(c33.getCid());
                        SerUtilN.servers.add(c33.getPri());

                        if (isGoodComputer(c33, sys_time)) {
                        } else {
                            //不符合要求
                            list.remove(x);// redis中去除不在时间范围内的。
                        }
                    }
                }
                RedisUtilC.setR(SerUtilC.redis_computer_list, new Gson().toJson(list), RedisUtilC.tim_r_30d);
            }
            moreToOne(list,jo,level,url);
        } else {
            moreToOne(cs,jo,level,url);
        }
    }

    //群发变单发，jo存储自己的业务内容和时间。
    private static void moreToOne(List<Computer> list,JsonObject jo,Integer level,int url){
        //群发处理。  待优化：关于不同业务处理，如群聊不能因为单个发送失败就返回给用户错误。如同步用户信息，某个服务器失败应该怎么处理。
        for (int y = list.size() - 1; y >= 0; y--) {
            boolean num = sendOne(list.get(y), jo, level, url);
            if (!num) {
                RedisUtilC.delR(SerUtilC.redis_computer_list);
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
     * @param jo       具体消息内容，存储自己的业务时间和业务等信息。
     * @param level    0：存--发送--不管是否成功（定时任务检测表）---接受者返回专业的业务信息来删除已经成功的sql
     *                 1：发送----返回事变或者成功（不存储，不存储）（这种形式为了满足真正的得到啦消息，可以app只用长连接，等服务b返回给A，再给app最终结果。）
     * @param url      业务 ，不可以为null
     * @return, , ,-false发送失败(服务器错误、未知错误等)，ture成功(包括跳过自己)。
     * 成功，代表服务器是可用的，不一定是已经发送成功，如果没成功会自动再次发送
     * 模棱两可时，应该偏向与false.
     */
    public final static boolean sendOne(@NotNull Computer computer, @NotNull JsonObject jo, Integer level, int url) {

        // 接受到信息后如何回执成功状态？ 主键UUID，和，url_ser_succ。待完善。

        if (SerUtilC.curCid.equals(computer.getCid())) {
            return false;//跳过自己，对应返回结果，只要是1才是正确，0也是错误。
        } else {

            long sys_time =  TimUtilC.getTimReal();  // 不变更业务的时间，服务器间传递时间单独存储在para_storage_tim中。
            if (isGoodComputer(computer, sys_time)) {
                String uuid = null; // 随机标识。FailsTask主键。
                if (level != null && level_0 == level) {
                    uuid = SerUtilC.getU32();
                    jo.addProperty(para_storage_need_return_id, uuid);
                }
                jo.addProperty(para_storage_tim, sys_time);
                jo.addProperty(MineUtilA.para_url, url);
                ChannelHandlerContext cf = SerUtilN.ctxCli.get(computer.getCid());
                if (cf != null && cf.channel().isActive()) {
                    if (uuid != null) {
                        insertFails(uuid, computer.getCid(), url, sys_time, jo);
                    }
                    cf.writeAndFlush(new TextWebSocketFrame(jo.toString()));
                    return true;
                } else {
                    //纠错，处理
                    //有可能是从redis中取得数据，时间问题，也可能是服务器时间更新存在时间差。
                    boolean bb = connectServer(computer, sys_time);
                    if (bb) {
                        if (uuid != null) {
                            insertFails(uuid, computer.getCid(), url, sys_time, jo);
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

        SerUtilC.SPRING.getBean(StorageTaskMapper.class).insert(fails);

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

        if (SerUtilC.curCid.equals(computer.getCid())) {
//            System.out.println("---跳过自己---服务器-" + new Gson().toJson(computer));
            return false;
        } else {
            String cid = computer.getCid();
            if (SerUtilN.ctxCli.containsKey(cid)) {
                return true;
            } else {

                if (tim == null) {
                    tim = TimUtilC.getTimReal();
                }

                if (isGoodComputer(computer, tim) && !conning.contains(cid)) {
                    conning.add(cid);

                    try {
                        System.out.println("---申请连接----服务器--" + computer.getCid() + ":" + computer.getPor() + "  ......tim:  " + TimUtilA.formatTimeToStr(TimUtilC.getTimReal()) + "---------");

                        JsonObject jtest = new JsonObject();
                        jtest.addProperty(SerUtilC.para_cid, SerUtilC.curCid);

                        //  /?ws_suffix={"":""}
                        URI uri = new URI("ws", null, computer.getPri(), computer.getPor(), "/", SerUtilN.ws_suffix + "=" + jtest.toString(), null);
                        Bootstrap bootstrap = new Bootstrap();
                        bootstrap.group(SerUtilN.eventLoopGroup)
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
                                    ComputerMapper computerMapper = SerUtilC.SPRING.getBean(ComputerMapper.class);
                                    computer.setStop(RetNumUtilA.n_0);//是否可用，每个服务器自己标记。也可批量管理员更新
                                    computerMapper.updateByPrimaryKeySelective(computer);
                                }
                            }
                        });

                        return false; // 当前没有连接时返回false（偏向与及时通知用户。那么群发怎么办？待完善）
                    } catch (Exception e) {
                        System.out.println("严重报错---连接服务器--" + computer.getCid() + ":" + computer.getPor() + "  ......tim:  " + TimUtilA.formatTimeToStr(TimUtilC.getTimReal()));
                        System.out.println(e.toString());
                        return false;
                    }
                } else {
                    System.out.println("-----不在服务时间----" + new Gson().toJson(computer) + TimUtilA.formatTimeToStr(TimUtilC.getTimReal()) + "---------");
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
        if (SerUtilC.curCid.equals(computer.getCid())) {
            return false;
        } else {
            if (tim == null) {
                tim = TimUtilC.getTimReal();
            }
            if (26 < computer.getStop() || tim < computer.getStim() || tim > computer.getEtim()) {
                return false;
            } else {
                return true;
            }
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
            if (jout == null || jout.get(MineUtilA.para_r) == null) {
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
        retWs(inner, SerUtilN.ctxSer.get(uuid), jout);
    }

    /*----------------- -----------    返回信息开始    end  ----------------- ---------------------------*/

}
