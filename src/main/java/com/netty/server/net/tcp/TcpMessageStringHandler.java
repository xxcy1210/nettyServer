package com.netty.server.net.tcp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 
 * SocketServer的消息处理类
 * 
 * @author yangbin
 *
 */
@Component
@Scope("prototype")//每次使用getBean，都会重新生成一个对象
public class TcpMessageStringHandler extends SimpleChannelInboundHandler<String> {
  private static final Logger logger = LoggerFactory.getLogger(TcpMessageStringHandler.class);
 
  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable throwable) {
	  	logger.debug("异常发生", throwable);
  }
 
  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	  	super.channelRead(ctx, msg);
  }
 
  @Override
  protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
	  logger.info("数据内容：data=" + msg);
//	    String result = "小李，我是服务器，我收到你的信息了。";
//	    //这行很重要，StringDecoder以这个作为消息分割，
//	    // 如果没有换行符的话，服务端就没办法接受到
//	    result += "\r\n";
	    ctx.writeAndFlush(msg);
  }
 
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
	    logger.info("建立连接");
	    super.channelActive(ctx);
  }
 
  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
	    logger.info("连接断开");
	    super.channelInactive(ctx);
  }
  
}