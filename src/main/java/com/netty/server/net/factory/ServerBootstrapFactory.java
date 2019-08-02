/*
 * Copyright (C), 2015-2018
 * FileName: ServerBootstrapFactory
 * Author:   zhao
 * Date:     2018/6/12 10:56
 * Description: Bootstrap工厂类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.netty.server.net.factory;

import com.netty.server.constant.ConstantValue;
import com.netty.server.exceptions.ServerErrException;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.oio.OioServerSocketChannel;

/**
 * 〈Bootstrap工厂类〉
 * 
 * 
 * @author yangbin
 *
 */
public class ServerBootstrapFactory {
  private ServerBootstrapFactory() {
  }

  public static ServerBootstrap createServerBootstrap(String channelType) throws ServerErrException {
    ServerBootstrap serverBootstrap = new ServerBootstrap();
    if(ConstantValue.CHANNEL_TYPE_NIO.equals(channelType)) {
    	EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        serverBootstrap.group(bossGroup, workerGroup);
        serverBootstrap.channel(NioServerSocketChannel.class);
        return serverBootstrap;
    }else if(ConstantValue.CHANNEL_TYPE_OIO.equals(channelType)) {
    	serverBootstrap.group(new OioEventLoopGroup());
        serverBootstrap.channel(OioServerSocketChannel.class);
        return serverBootstrap;
    }else {
    	throw new ServerErrException("create ServerBootstrap 失败, 不支持类型" + channelType);
    }
  }
}