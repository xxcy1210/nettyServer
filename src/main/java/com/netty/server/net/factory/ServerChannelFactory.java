package com.netty.server.net.factory;

import java.net.BindException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.netty.server.exceptions.ServerErrException;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;

/**
 * 〈ServerChannel的工厂类〉
 * 
 * 
 * @author yangbin
 *
 */
public class ServerChannelFactory {
  private static final Logger logger = LoggerFactory.getLogger(ServerChannelFactory.class);

  public static Channel createAcceptorChannel(Integer port, String channelType,
          ChannelInitializer<? extends Channel> childChannel) throws ServerErrException {
    final ServerBootstrap serverBootstrap = ServerBootstrapFactory.createServerBootstrap(channelType);
    serverBootstrap.childHandler(childChannel);
    //        serverBootstrap.childHandler()
    logger.info("创建Server...");
    try {
      ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
      channelFuture.awaitUninterruptibly();
      if (channelFuture.isSuccess()) {
        return channelFuture.channel();
      } else {
        String errMsg = "Failed to open socket! Cannot bind to port: " + port ;
        logger.error(errMsg);
        throw new ServerErrException(errMsg);
      }
      //      java.net.BindException: Address already in use: bind
    } catch (Exception e) {
      logger.error(port + "mybe is bind");
      throw new ServerErrException(e);
    }
  }
 
  public static Channel createAcceptorChannel(String ipHost,Integer port, String channelType,
          ChannelInitializer<? extends Channel> childChannel) throws ServerErrException {
    final ServerBootstrap serverBootstrap = ServerBootstrapFactory.createServerBootstrap(channelType);
    serverBootstrap.childHandler(childChannel);
    //        serverBootstrap.childHandler()
    logger.info("创建Server...");
    try {
      ChannelFuture channelFuture = serverBootstrap.bind(ipHost,port).sync();
      channelFuture.awaitUninterruptibly();
      if (channelFuture.isSuccess()) {
        return channelFuture.channel();
      } else {
        String errMsg = "Failed to open socket! Cannot bind to address: " +ipHost+":"+ port;
        logger.error(errMsg);
        throw new ServerErrException(errMsg);
      }
    } catch (Exception e) {
      logger.error(port + "mybe is used");
      throw new ServerErrException(e);
    }
  }
}