package com.netty.server.net.tcp;

import org.springframework.stereotype.Component;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

@Component("tcpServerStringInitializer")
public class TcpServerStringInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		 // 以("\n")为结尾分割的 解码器 
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
	    pipeline.addLast("decoder", new StringDecoder());
	    pipeline.addLast("encoder", new StringEncoder());
	    pipeline.addLast(new TcpMessageStringHandler());
		
	}

}
