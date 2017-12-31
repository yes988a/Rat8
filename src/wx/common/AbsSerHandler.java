package wx.common;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import wx.common.generator.base.*;
import wx.common.utils_app.LoginUtilA;
import wx.common.utils_app.MineUtilA;
import wx.common.utils_app.TimUtilA;
import wx.common.utils_ser_comm.BlackUtilC;
import wx.common.utils_ser_comm.RedisUtilC;
import wx.common.utils_ser_comm.SerUtilC;
import wx.common.utils_ser_comm.TimUtilC;
import wx.common.utils_ser_netty.SerUtilN;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static io.netty.handler.codec.http.HttpHeaderNames.HOST;
import static io.netty.handler.codec.http.HttpMethod.GET;

//所有的请求，都会到这里来。对的，不对的，为了做安全判断。防止攻击。
public abstract class AbsSerHandler extends ChannelInboundHandlerAdapter {
    protected boolean inner = false;// 是否内部链接，true为内部，全部是ws访问协议。
    protected String uuid = null;    // 主键ID，用户de和服务器de和手机号de和用户名de。 null表示非法。

    //安全：：：：：：：：：：：：------------------------------------------------

    //普通http确定app的唯一性和安全性。

    //所有长连接，首次建立连接时，必然是登录操作或者重新连接操作。(所有长连接，都需要登录后才能使用。)

    // cat 服务器中不存在app的长连接。

    // 连接频率和次数限制。 和，连接不可中断性验证。

    //安全：：：：：：：：：：：：------------------------------------------------

    //  抽象方法。。。。。。。。。。start。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。

    // 所有app请求，以接收到数据为准，每个请求，服务器都要及时返回，即便需要等待其他服务器返回。
    // manager方法：开头ser_ 表示是服务器间通讯，是websocket的Text消息。
    // manager方法：结尾_http，表示仅仅可用于http请求，这样的方法中不需要返回url标识。
    // manager方法：server方法如果不返回，数据，就，不需要传入jout.

    /*
    是否存储，服务器间的失败重发，根据业务定吧。

    // 服务器，具体业务再说。消息准确为主。（所有通讯都需要返回确认信息，如果，没有确认，标识失败。
    // 长连接时，各种消息以到达可以存储的位置为准，标识发送成功。）
    如：聊天：  发送者--A服务器--b服务(存储)--a服务(不需要再给b返回，所以不需要传入jout)---发送者，标识发送成功,接受者是否收到，算另一个业务
    如：好友请求:请求者---A服务--b服务(存储)--a服务(不需要再给b返回，所以不需要传入jout)---请求者，标识完成啦。
    如：cat登录操作，cat是可以存储登录情况的(可以和rat登录拆分验证)。所以，直接返回给app登录成功。
    如：群聊消息：请求者-----A服务器，有存储能力(这个服务器是要存储消息的)------返回请求者成功。

    */

    //app、web 的http请求。
    public abstract void userHttp(ChannelHandlerContext ctx, HttpVersion httpVersion, JsonObject into);
/*
    暂放，所有App的发送请求，都以http进行吧。
    //app、web 的websocket。
    public abstract void userWs(ChannelHandlerContext ctx, JsonObject into);
*/

    //服务器间的Websocket。
    public abstract void serverWs(ChannelHandlerContext ctx, String msg);

    //  抽象方法。。。。。。。。。。end。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object obj) throws Exception {

        System.out.println("---channelReadchannel    Read------长连接个数：--------"+ SerUtilN.ctxSer.size());

        if (obj instanceof TextWebSocketFrame) {
            String msg = ((TextWebSocketFrame) obj).text();
            ReferenceCountUtil.release(obj);
            text_ChannelRead(ctx, msg);

        } else if (obj instanceof FullHttpRequest) {
            http_ChannelRead(ctx, (FullHttpRequest) obj);

        } else if (obj instanceof PingWebSocketFrame) {
            ReferenceCountUtil.release(obj);
            ctx.writeAndFlush(new PongWebSocketFrame());

        } else if (obj instanceof PongWebSocketFrame) {
            ReferenceCountUtil.release(obj);

        } else {
            ctx.close();
        }

    }

    //   http.....
    private void http_ChannelRead(ChannelHandlerContext ctx, FullHttpRequest fullhttp) {
        if (fullhttp.decoderResult().isSuccess()) {
            if (fullhttp.method() == GET) {// get 请求，根据项目规定：ws的握手请求。握手成功不需要返回任何消息
                Map<String, List<String>> parameters = new QueryStringDecoder(fullhttp.uri()).parameters();
                JsonObject into = null;
                //参数格式正确性判断。
                if (parameters.size() != 1) {
                    ctx.close();
                    return;
                } else {
                    if (parameters.containsKey(SerUtilN.ht_suffix)) {  //http
                        if (inner) {//-----------------------------------------      内部连接 http   start    ================================================================-------------------------------
                            ctx.close();
                            return;
                            //-----------------------------------------      内部连接 http     end    ================================================================---------------------------------------
                        } else {
                            //-----------------------------------------      app连接 http     start   ================================================================----------------------------------------------
                            try {
                                into = new JsonParser().parse(parameters.get(SerUtilN.ht_suffix).get(0)).getAsJsonObject();
                                into.remove(RedisUtilC.para_login_uid);//安全，防止用户自己写入
                            } catch (Exception e) {
                            }
                            if (into == null) {
                                ctx.close();
                                return;
                            } else {
                                HttpVersion httpVersion = fullhttp.protocolVersion();
                                ReferenceCountUtil.release(fullhttp);

                                //测试时，全获取Wxutil.para_uid
                                if (into.has(LoginUtilA.para_login_tid)) {  //已经登录操作。0000000000000000000

                                    String tid = into.get(LoginUtilA.para_login_tid).getAsString();
                                    //在aes没有变之前每次都成固定的啦，不对。
                                    String uidaes = into.get(LoginUtilA.para_login_aes_safedes).getAsString();

                                    LoginExample exampleLogin = new LoginExample();
                                    exampleLogin.createCriteria().andTidEqualTo(tid);
                                    List<Login> lll = SerUtilC.SPRING.getBean(LoginMapper.class).selectByExample(exampleLogin);
                                    if (lll.size() == 1) {
                                        into.addProperty(RedisUtilC.para_login_uid, lll.get(0).getUid());
                                        userHttp(ctx, httpVersion, into);
                                    } else {
                                        // 参数不正确，安全日志。
                                        ctx.close();
                                        return;
                                    }
                                } else {        // 未登录操作。00000000000000000000000000000000000000
                                    userHttp(ctx, httpVersion, into);
                                }
                                // 安全校验接受：-----------------------     ：-----------------------      ：-----------------------
                            }
                        }
                        //-----------------------------------------      app连接 http   end     ================================================================-------------------------------------------------

                    } else if (parameters.containsKey(SerUtilN.ws_suffix)) {  //ws握手

                        //-----------------------------------------      握手   构造参数     ================================================================-------------------------------------------------
                        try {
                            into = new JsonParser().parse(parameters.get(SerUtilN.ws_suffix).get(0)).getAsJsonObject();
                            into.remove(RedisUtilC.para_login_uid);//安全，防止用户自己写入
                        } catch (Exception e) {
                        }

                        //服务器和app的登录操作是否正确。
                        if (into == null) {
                            //日志
                            ctx.close();
                            return;
                        } else {
                            //暂放
                            if (inner) {// 服务器间安全验证。
                                //-----------------------------------------      握手    安全验证   内部握手  start     ================================================================-------------------------------------------------
                                if (into == null || !into.has(SerUtilC.para_cid)) {
                                    ctx.close();
                                    return;
                                } else {
                                    //判断cid和address是否吻合。
                                    if (SerUtilN.servers.contains(into.get(SerUtilC.para_cid).getAsString())
                                            && SerUtilN.servers.contains(ctx.channel().remoteAddress().toString().split(":")[0].substring(1))) {

                                        uuid = into.get(SerUtilC.para_cid).getAsString();//第一次时，是cid
                                        ComputerMapper cd = SerUtilC.SPRING.getBean(ComputerMapper.class);
                                        Computer comp = cd.selectByPrimaryKey(uuid);
                                        if (comp == null) {
                                            ctx.close();
                                            return;
                                        } else {
                                            SerUtilN.connectServer(comp, null);
                                            boolean bwos = woshou(ctx, fullhttp);
                                            if (bwos) {
                                                System.out.println("---被连接---成功。。：：：内部：：：" + uuid + "  tim:  " + TimUtilA.formatTimeToStr(TimUtilC.getTimReal()));
                                            } else {
                                                System.out.println("---被连接---失败。。：：：内部：：：" + uuid + "  tim:  " + TimUtilA.formatTimeToStr(TimUtilC.getTimReal()));
                                            }
                                            //服务器连接成功，其他返回参数。
                                        }
                                    } else {
                                        ctx.close();
                                        return;
                                    }
                                }
                                //-----------------------------------------      握手    安全验证    内部握手  end     ================================================================-------------------------------------------------

                            } else { //用户app安全验证。。。
                                //-----------------------------------------      握手   安全验证   app握手请求    start     ================================================================-------------------------------------------------

                                //这里仅仅接受登录后，断开从新连接的操作。采用握手前验证，握手成功代表已经成功。
                                //握手时安全验证，采用http结合，需要在握手前先发送一次http，返回成功后，在尝试长连接（带有http的返回标识）
                                if (into.has(MineUtilA.para_uid)) {
                                    uuid = into.get(MineUtilA.para_uid).getAsString();
                                    woshou(ctx, fullhttp);
                                } else {
                                    ctx.close();
                                    return;
                                }
                            }
                            //-----------------------------------------      握手  安全验证   app握手请求   end     ================================================================-------------------------------------------------
                        }
                    } else {//固定前缀标识。
                        ctx.close();
                        return;
                    }
                }
            } else {//错误
                ctx.close();
                return;
            }
        } else {//解码错误，直接返回，并关闭流。
            ctx.close();
            return;
        }
    }

    //建立握手，调用此方法前不可以对FullHttpRequest进行fullhttp.release();
    // 关于UUID是否合法应该在调用此方法前判断，包括判断其归宿。
    private boolean woshou(ChannelHandlerContext ctx, FullHttpRequest fullhttp) {
        //握手连接。
        try {
            new WebSocketServerHandshakerFactory(fullhttp.headers().get(HOST), null, true)
                    .newHandshaker(fullhttp).handshake(ctx.channel(), fullhttp);
            //ws握手成功，不需要做任何操作。
            //这里应该也发送不出去。未验证。ctx.writeAndFlush(new TextWebSocketFrame(jout.toString()));
            SerUtilN.ctxSer.put(uuid, ctx);
            return true;
        } catch (Exception e) {
            SerUtilN.ctxSer.remove(uuid, ctx);
            ctx.close();
        } finally {
            fullhttp.release();
        }
        return false;
    }

    //  text.....websocket
    private void text_ChannelRead(ChannelHandlerContext ctx, String msg) {
        if (inner) {//内部，    全部是websocket的请求。没有普通的http。
            if (SerUtilN.send_heart.equals(msg)) {
                //不应该，收到验证心跳的请求。
                //日志
                ctx.close();
                return;
            } else if (SerUtilN.res_heart.equals(msg)) {
                //客户端返回心跳回应。
                return;
            } else {
                serverWs(ctx, msg);
                return;
            }
        } else { //用户
            if (SerUtilN.send_heart.equals(msg) || "@heart".equals(msg)) {
//                    ctx.writeAndFlush(new TextWebSocketFrame(SerUtilN.res_heart));
                //服务器才可以发送心跳请求，客户端只能回应。
                //服务器对收到的心跳请求做关闭操作。对收到的回复心跳操作不做任何操作。
//                    ctx.close();  由于浏览器测试，暂时放开关闭操作。
                return;
            } else if (SerUtilN.res_heart.equals(msg)) {
                //客户端返回心跳回应。
                return;
            } else {
                ctx.close();
                return;
            }
        }
    }

    /**
     * @param ctx
     * @param evt
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleState state = ((IdleStateEvent) evt).state();
            if (state == IdleState.WRITER_IDLE) {
                ctx.writeAndFlush(new TextWebSocketFrame(SerUtilN.send_heart));
            } else if (state == IdleState.READER_IDLE) {
//测试，暂时注销，应该关闭连接，具体时间，次数等，待优化。         ctx.close();
                //日志，网络不好？
            } else {
                if (!inner) {
                    //测试，暂时注销。  ctx.close();
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);

        Boolean bb = BlackUtilC.safe(ctx.channel().remoteAddress().toString().split(":")[0].substring(1), 0);
        if (bb == null) {
            ctx.close();
        } else if (bb) {
            //服务器校验在黑白名单中(完全)校验，不再去channelRead做安全校验。但是确定cid还必须有以为一个服务器可能部署多个server
            inner = true;
        } else {
            // app，app校验在http请求握手时。
            inner = false;
        }

        System.out.println("---------长数连接------channelRegistered--"+SerUtilN.ctxSer.size());
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        //  这些地方有待优化，现在都是瞎写的，没考虑是否要进行其他handler中的方法。
        if (uuid != null && ctx == SerUtilN.ctxSer.remove(uuid)) {
            System.out.println("---111--channelUnregistered----长连接个数：--------"+SerUtilN.ctxSer.size());
            super.channelUnregistered(ctx);
        } else {
            if (SerUtilN.ctxSer.containsValue(ctx)) {
                Iterator<String> it = SerUtilN.ctxSer.keySet().iterator();
                while (it.hasNext()) {
                    String ne = it.next();
                    if (SerUtilN.ctxSer.get(ne) == ctx) {
                        SerUtilN.ctxSer.remove(ne);
                        break;
                    }
                }
            }
            System.out.println("---222--channelUnregistered----长连接个数：--------"+SerUtilN.ctxSer.size());
            super.channelUnregistered(ctx);
        }
    }
}
