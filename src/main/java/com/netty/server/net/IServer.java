package com.netty.server.net;

/**
 * 
 * Server核心
 * 
 * @author yangbin
 *
 */
public interface IServer {
  /**
   * 服务启动
   * @throws Exception 启动时异常
   */
  public void start() throws Exception;

  /**
   * 服务关闭
   * @throws Exception 关闭异常
   */
  public void stop() throws Exception;

  /**
   * 服务重启
   * @throws Exception 重启时异常
   */
  public void restart() throws Exception;
}