package com.github.sailboat.notes.communication.netty.handler;

import com.github.sailboat.notes.communication.netty.coder.TerminalDecoder;
import com.github.sailboat.notes.communication.netty.coder.TerminalEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhumeng
 * @version 1.0
 * @description: TODO
 * @date 2024/6/12
 */
@Slf4j
public class TimerServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline()
//                .addLast(new TerminalDecoder())
//                .addLast(new TerminalEncoder())
//                .addLast(new TimerServerHandler())
                .addLast(new TerminalProtocolHandler());
    }
}
