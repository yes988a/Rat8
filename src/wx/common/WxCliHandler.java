package wx.common;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import wx.common.utils_ser_comm.SerUtilC;
import wx.common.utils_ser_netty.SerUtilN;
import wx.service.dao.ExtComputerMapper;

/**
 * 此类，几乎不走业务，因为，当想另一个服务发送信息或者回复信息时，都是以客户端的身份“发送”，（WxCliHandler 是接收服务端发回给客户端的“回复”。），
 * <p>
 * 建立连接，和接收心跳使用。 验证安全性。
 */
public class WxCliHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    protected String cid = null;

    public WxCliHandler(String cid) {
        this.cid = cid;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame textWebSocketFrame) throws Exception {
        String mm = textWebSocketFrame.text();
//        textWebSocketFrame.release();   当做为客户端时，只有一个handler所以，作者，在接受read后，做release()，所以，如果这里写啦，就会报错。
        //作为服务端时，有多个handler，所以，netty作者不知道什么时候release()，没有做此操作，只要不用了，可以手动做release()操作。
        System.out.println("client>>>>>" + mm);
        if (SerUtilN.send_heart.equals(mm)) {
            ctx.writeAndFlush(new TextWebSocketFrame(SerUtilN.res_heart));
        } else if (SerUtilN.res_heart.equals(mm)) {
            ctx.close();
        } else {
            //正常业务处理。
            //不应该发生。
            System.out.println(cid + ";;;;WxCliHandler-----------channelRead0");
        }
        //读取其他服务器返回信息。
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        SerUtilN.ctxCli.put(cid, ctx);
        SerUtilN.conning.remove(cid);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        SerUtilN.ctxCli.remove(cid);
        SerUtilN.conning.remove(cid);
        //日志。并在当前服务器标识成不可用，
        ExtComputerMapper extComputerMapper = SerUtilC.SPRING.getBean(ExtComputerMapper.class);
        extComputerMapper.updateStop(cid);
        System.out.println("clien 断开：" + cid);
    }
}
