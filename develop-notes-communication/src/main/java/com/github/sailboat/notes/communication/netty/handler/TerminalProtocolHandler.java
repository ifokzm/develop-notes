package com.github.sailboat.notes.communication.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhumeng
 * @version 1.0
 * @description: TODO
 * @date 2024/6/12
 */
@Slf4j
public class TerminalProtocolHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            // 接收数据
            ByteBuf reportBuf = (ByteBuf) msg;
            log.info("[TerminalProtocolHandler.channelRead] address:{}, msg:{}", ctx.channel().remoteAddress().toString(), ByteBufUtil.hexDump(reportBuf));


            // 响应数据
            ByteBuf resBuf = Unpooled.buffer(4);
            resBuf.writeInt(0x00000000);
            ChannelFuture future = ctx.writeAndFlush(resBuf);
            future.addListener(f -> {
                if (f.isSuccess()) {
                    log.info("[ChannelFuture.addListener] result:{}", f.isSuccess());
                } else {
                    log.info("[ChannelFuture.addListener] result:{}", f.cause().getMessage());
                }
            });
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        log.debug("[TerminalProtocolHandler.channelReadComplete] address:{}", ctx.channel().remoteAddress().toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        log.warn("[TerminalProtocolHandler.exceptionCaught] address:{}, cause:{}", ctx.channel().remoteAddress().toString(), cause.getMessage());

    }
}
