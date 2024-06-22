package com.github.sailboat.notes.communication.netty;

import com.github.sailboat.notes.communication.netty.handler.TimerServerInitializer;
import com.github.sailboat.notes.communication.netty.handler.UDPProtocolHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhumeng
 * @version 1.0
 * @description: TODO
 * @date 2024/6/12
 */
@Slf4j
public class NettyServer {

    public static ConcurrentHashMap<String, String> 在线终端集合 = new ConcurrentHashMap<>();
    /**
     * 总的channel池
     */
    public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public NettyServer() {
    }

    public void bind(int tcpPort) {
        /*
        NioEventLoopGroup是线程池组
        包含了一组NIO线程,专门用于网络事件的处理
        bossGroup:服务端,接收客户端连接
        workGroup:进行SocketChannel的网络读写
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    //配置TCP参数,能够设置很多,这里就只设置了backlog=1024
                    .option(ChannelOption.SO_BACKLOG, 4096)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //绑定I/O事件处理类
                    .childHandler(new TimerServerInitializer());
            log.info("监听TCP端口:{}", tcpPort);
            /*
            bind:绑定端口
            sync:同步阻塞方法,等待绑定完成,完成后返回 ChannelFuture ,主要用于通知回调
             */
            ChannelFuture channelFuture = serverBootstrap.bind(tcpPort).sync();
            /*
             *   ("等待服务端监听窗口关闭");
             * closeFuture().sync():为了阻塞,服务端链路关闭后才退出.也是一个同步阻塞方法
             */
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }


    public void bindUDP(int udpPort) {
        // 配置服务端的NIO线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 启动引导类
            Bootstrap b = new Bootstrap();
            b.group(workerGroup)
                    // 使用NioDatagramChannel作为UDP传输
                    .channel(io.netty.channel.socket.nio.NioDatagramChannel.class)
                    .handler(new UDPProtocolHandler());
            log.info("监听UDP端口:{}", udpPort);
            // 绑定端口并同步，等待绑定完成
            ChannelFuture f = b.bind(udpPort).sync();

            // 等待服务端监听通道关闭
            f.channel().closeFuture().await();
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        } finally {
            // 优雅关闭线程组
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

}
