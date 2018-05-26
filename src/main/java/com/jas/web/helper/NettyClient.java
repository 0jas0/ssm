package com.jas.web.helper;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class NettyClient {

    @Value("${netty.hosts}")
    private String host;

    @Value("${netty.port}")
    private Integer port;

    //客户端用来发送消息的对象
    public static Channel channel;

    @PostConstruct
    public  void initMethod() {
        try {
            final EventLoopGroup worker = new NioEventLoopGroup();
            Bootstrap b = new Bootstrap();
            b.group(worker)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            //可以使接受到的数据变成字符串
                            sc.pipeline().addLast(new StringDecoder());
                        }
                    });
            final ChannelFuture f = b.connect(host, port).sync();
            channel = f.channel();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        channel.closeFuture().sync();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        worker.shutdownGracefully();
                    }
                }
            }).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
