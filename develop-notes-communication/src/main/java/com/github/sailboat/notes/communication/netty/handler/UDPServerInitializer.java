package com.github.sailboat.notes.communication.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioDatagramChannel;

public class UDPServerInitializer extends ChannelInitializer<NioDatagramChannel> {

    @Override
    protected void initChannel(NioDatagramChannel channel) throws Exception {
        channel.pipeline()
                .addLast(new UDPProtocolHandler());
    }
}
