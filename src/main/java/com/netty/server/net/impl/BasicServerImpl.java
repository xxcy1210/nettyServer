package com.netty.server.net.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netty.server.base.redis.Redis;
import com.netty.server.config.configmannager.ServerConfig;
import com.netty.server.constant.ConstantValue;
import com.netty.server.exceptions.ServerErrException;
import com.netty.server.net.IServer;
import com.netty.server.net.factory.ServerChannelFactory;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 基本实现
 * @author yangbin
 *
 */
@Component
public class BasicServerImpl implements IServer {
  private Channel acceptorChannel;
  private static final Logger logger = LoggerFactory.getLogger(BasicServerImpl.class);

  public void start(){
    try {
      init();
      ServerConfig.getInstance().printServerInfo();
      acceptorChannel.closeFuture().sync();
    } catch (Exception e) {
      logger.debug("服务启动失败，程序即将退出", e);
      stop();
      System.exit(0);
    }
  }

  private void init() throws Exception {
    ServerConfig serverConfig = ServerConfig.getInstance();

    serverConfig.printXmlCfgInfo();
//    // 初始化xml-cfg工具类
//    logger.info("初始化xml-cfg工具类  开始");
//    ConfigDataManager.getInstance()
//            .loadXml(serverConfig.getCfgPackageName(), serverConfig.getCfgPrefix(), serverConfig.getCatalogDir(),
//                    serverConfig.getCatalogFile(), serverConfig.getCatalogMainNode(),
//                    serverConfig.getCatalogAttribute(), serverConfig.getXmlFileDir());
//    logger.info("初始化xml-cfg工具类  结束");

    // 初始化redis工具类
//    logger.info("redis工具类  开始");
//    JedisPoolConfig poolConfig = new JedisPoolConfig();
//    //设置最大连接数（100个足够用了，没必要设置太大）
//    poolConfig.setMaxTotal(serverConfig.getRedisPoolConfigMaxTotal());
//    //最大空闲连接数
//    poolConfig.setMaxIdle(serverConfig.getRedisPoolConfigMaxIdle());
//    //获取Jedis连接的最大等待时间（50秒）
//    poolConfig.setMaxWaitMillis(serverConfig.getRedisPoolConfigMaxWaitMillis());
//    //在获取Jedis连接时，自动检验连接是否可用
//    poolConfig.setTestOnBorrow(serverConfig.getRedisPoolConfigTestOnBorrow());
//    //在将连接放回池中前，自动检验连接是否有效
//    poolConfig.setTestOnReturn(serverConfig.getRedisPoolConfigTestOnReturn());
//    //自动测试池中的空闲连接是否都是可用连接
//    poolConfig.setTestWhileIdle(serverConfig.getRedisPoolConfigTestWhileIdle());
//    Redis.getInstance().createJedisPool(poolConfig, serverConfig.getRedisHost(), serverConfig.getRedisPort(),
//            serverConfig.getRedisTimeout(), serverConfig.getRedisPassword(), serverConfig.getRedisDatabaseIndex());
//    // 测试线程池是否已经建立完毕
//    logger.info("redis工具类  结束");

    // 启动通讯服务
    logger.info("启动通讯服务  开始");
    Integer port = serverConfig.getPort();
    String channelType = serverConfig.getChannelType();
    String protocolType = serverConfig.getProtocolType();
    ChannelInitializer<SocketChannel> channelInitializer = null;
    
    if(ConstantValue.PROTOCOL_TYPE_TCP.equals(protocolType)) {
    	 channelInitializer = (ChannelInitializer<SocketChannel>) serverConfig.getApplicationContext()
                 .getBean("tcpServerStringInitializer");
    }
//    else if(ConstantValue.PROTOCOL_TYPE_WEBSOCKET.equals(protocolType)) {
//    	channelInitializer = (ChannelInitializer<SocketChannel>) serverConfig.getApplicationContext()
//                .getBean("webSocketChannelInitializer");
//    }
    else {
    	 throw new ServerErrException("ChannelInitializer is not defind");
    }
   
    acceptorChannel = ServerChannelFactory.createAcceptorChannel(port, channelType, channelInitializer);
    logger.info("启动通讯服务  结束");

  }

  public void stop() {
    if (acceptorChannel != null) {
      acceptorChannel.close().addListener(ChannelFutureListener.CLOSE);
    }
  }

  public void restart() throws Exception {
    stop();
    start();
  }
}