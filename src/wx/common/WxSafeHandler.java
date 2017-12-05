package wx.common;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 上一个handler不写fireXXX就不会到这个类里面来。  暂时放这里吧。
 */
public class WxSafeHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        //handler执行：当前一个执行完成，才会执行后一个。
        //如果，没有执行super.channelRead(ctx, msg);，后面的handler不执行。
        //如果，此处返回消息，ctx.write();ctx.writeAndFlush();  下面还会执行不？ 会
        //WxSafeHandler放在握手前和握手后不一样。
        //       super.channelRead(ctx, msg);
        System.out.println("管道的尽头，没有符合要求。");
        ctx.close();
    }
}
