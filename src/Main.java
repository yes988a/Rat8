import com.google.gson.Gson;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import wx.common.WxSafeHandler;
import wx.common.generator.base.Computer;
import wx.common.generator.base.ComputerExample;
import wx.common.generator.base.ComputerMapper;
import wx.common.utils_server.*;
import wx.service.WxSerHandler;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    private static EventLoopGroup workerGroup = null;
    private static ComputerMapper computerMapper = null;

    public static void main(String[] args) {
        System.out.println("...Hello WeiXiao World 启动中..." + TimUtil.formatTimeToStr(WxUtil.getTim()) + ".....................");
        Main.start();
//        System.out.println(WxUtil.getU32());
        SerUtil.succStart = true;
    }

    public static void start() {
        SerUtil.SPRING = new ClassPathXmlApplicationContext("wx-servlet.xml");
        computerMapper = SerUtil.SPRING.getBean(ComputerMapper.class);
        Computer computer = computerMapper.selectByPrimaryKey(SerUtil.curCid);

        SerUtil.curBid = computer.getBid();
        int port = computer.getPor();

        Boolean red = RedisUtil.setR(Main.class.getSimpleName(),
                "启动成功" + new Gson().toJson(computer), RedisUtil.tim_r_3d);

        if (red) {
            System.out.println("redis 正常！！！！！！！！");
        }

        workerGroup = new NioEventLoopGroup(Runtime.getRuntime().availableProcessors());
        try {
            ServerBootstrap sbs = new ServerBootstrap()
                    .group(SerUtil.eventLoopGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            pipeline.addLast(new ChunkedWriteHandler());
                            pipeline.addLast(new HttpObjectAggregator(512));

                            //WebSocketServerProtocolHandler，匹配一“”开头的请求，处理握手，在自己实现的Handler中将不需要判断http处理握手请求。
                            //开启后，发现握手走了N此自定义的handler中的userEventTriggered好几次。
                            //那么，如果想使用http普通请求，应该在这之前拦截http并且配置不同的url地址首位。
                            //如果开始不是“”将不走userEventTriggered好几次，也不做自动握手连接。（匹配使用的eques。必须完全一样且不能有后缀。）
                            //WebSocketServerProtocolHandler会拦截所有websocket的握手操作，放在此拦截器之后的handler不执行。是怎么做的呢？是不是fireXXX就会接着执行下面的操作，如果没有fireXXX不在执行下面操作。
                            //SimpleChannelInboundHandler的read0处理完以后，它后面的handler还会执行吗？不会，因为没有执行ctx.fireChannelRead(obj)
//                            pipeline.addLast(new WebSocketServerProtocolHandler(SerUtil.ws_suffix));//   /-x 标识是ws请求，，，其他均为普通http请求。，
//                            pipeline.addLast(new WebSocketServerCompressionHandler());
                            pipeline.addLast(new IdleStateHandler(320, 180, 0, TimeUnit.SECONDS));

                            pipeline.addLast(new WxSerHandler());   //websocket接收者。。。。。

                            //进入前安全拦截，放在拦截器的最后一步，如果走到这里标识有问题。
                            pipeline.addLast(new WxSafeHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 512).childOption(ChannelOption.SO_KEEPALIVE, true);
            // 绑定端口，开始接收进来的连接
            ChannelFuture future = sbs.bind(port).sync();
            System.out.println("端口:--- " + port + " -----启动成功--------" + WxUtil.getTim() + "  " + TimUtil.formatTimeToStr(WxUtil.getTim()));

            computer.setStop(RetNumUtil.n_0);
            computerMapper.updateByPrimaryKeySelective(computer);

            conns();
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            closeGroup();
        } finally {
            if (SerUtil.eventLoopGroup != null) {
                SerUtil.eventLoopGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
            }
        }
    }

    private static void closeGroup() {
        if (SerUtil.eventLoopGroup != null) {
            SerUtil.eventLoopGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        System.out.println("——启动错误-----———启动错误------err----");
    }

    /**
     * 连接所有。
     */
    private static void conns() {
        List<Computer> list = computerMapper.selectByExample(new ComputerExample());
        long tim = TimUtil.getTimReal();
        for (int i = 0; i < list.size(); i++) {
            /**---------    更新serList开始      ---------*/
            SerUtil.servers.add(list.get(i).getCid());
            SerUtil.servers.add(list.get(i).getPri());
            /**---------    更新serList结束      ---------*/
            if (SerUtil.curCid.equals(list.get(i).getCid())) {
//                System.out.println("---跳过自己---服务器-" + new Gson().toJson(list.get(i)));
            } else {
                SerUtil.connectServer(list.get(i),tim);
            }
        }
        SerUtil.servers.add("127.0.0.1");
        System.out.println("。。。。。。。。。。。。。。。。。。。完成检查连接服务器。。。。。。。。。。。。。。。。。。。。。。。。。");
    }
}
