package com.lege.peony.client;

import com.alibaba.fastjson.JSON;
import com.lege.peony.common.ResponseResult;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Author 了个
 * @date 2020/5/26 19:12
 */
public class NettyClientConnect {

    private final Map<Long,MessageFuture> futureMap = new ConcurrentHashMap<Long, MessageFuture>();
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    public void connect(final String requestJson,final Long threadId){
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) {
                        ch.pipeline().
                        addLast(new StringDecoder()).
                        addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
                                ResponseResult response = JSON.parseObject(msg, ResponseResult.class);
                                MessageFuture messageFuture = futureMap.get(threadId);
                                messageFuture.setMessage(response);
                            }

                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                futureMap.put(threadId,new MessageFuture());
                                countDownLatch.countDown();
                                ByteBuf encoded = ctx.alloc().buffer(4 * requestJson.length());
                                encoded.writeBytes(requestJson.getBytes());
                                ctx.writeAndFlush(encoded);
                            }
                        });
            }
        }).connect("127.0.0.1", 20006);
    }

    public ResponseResult getResponse(Long threadId){
        MessageFuture messageFuture = null;
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        messageFuture = futureMap.get(threadId);
        return messageFuture.getMessage();
    }
}
