package com.lege.peony.server;


import com.lege.peony.common.annotation.RpcService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author 了个
 * @date 2020/3/30 10:57
 */
//@Component
public class ContextStartedEventListener implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private ApplicationContext applicationContext;
	public static Map<String, Object> rpcServiceMap = new HashMap<String, Object>();

	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		System.out.println("2.ContextStartedEvent");
		try {
			run();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() throws IOException {
		Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(RpcService.class);
		for (Object bean : beansWithAnnotation.values()) {
			Class<?> clazz = bean.getClass();
			Class<?>[] interfaces = clazz.getInterfaces();
			for (Class<?> inter : interfaces) {
				rpcServiceMap.put(getClassName(inter.getName()), bean);
				//已经加载的服务:com.lege.demo.server.service.SendMessageService
				System.out.println("已经加载的服务:" + inter.getName());
			}
		}
		startPort();
	}

	private String getClassName(String beanClassName) {
		String className = beanClassName.substring(beanClassName.lastIndexOf(".") + 1);
		className = className.substring(0, 1).toLowerCase() + className.substring(1);
		return className;
	}

	public void startPort() throws IOException{
		//服务端在20006端口监听客户端请求的TCP连接
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		NioEventLoopGroup boos = new NioEventLoopGroup();
		NioEventLoopGroup worker = new NioEventLoopGroup();
		serverBootstrap
				.group(boos, worker)
				.channel(NioServerSocketChannel.class)
				.childHandler(new ChannelInitializer<NioSocketChannel>() {
					@Override
					protected void initChannel(NioSocketChannel ch) {
						ch.pipeline().addLast(new StringDecoder());
						ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
							@Override
							protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
								//获得实现类处理过后的返回值
								String invokeMethodMes = CommonDeal.getInvokeMethodMes(msg);
								ByteBuf encoded = ctx.alloc().buffer(4 * invokeMethodMes.length());
								encoded.writeBytes(invokeMethodMes.getBytes());
								ctx.writeAndFlush(encoded);
							}
						});
					}
				}).bind(20006);
	}
}
