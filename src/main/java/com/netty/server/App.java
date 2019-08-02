package com.netty.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.netty.server.config.configmannager.ServerConfig;
import com.netty.server.core.Test;
import com.netty.server.net.IServer;
import com.netty.server.net.demo.SocketServer;



/**
 * Hello world!
 *
 */
public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	static {
	    // 先加载spring
	    logger.info("准备载入spring...");
	    ApplicationContext applicationContext = new FileSystemXmlApplicationContext(
	            "classpath:spring/spring-content.xml");
	    ServerConfig.getInstance().setApplicationContext(applicationContext);
	    logger.info("载入spring 完毕...");
	  }
	
    public static void main( String[] args ) throws Exception{
//    	Test test = (Test) ServerConfig.getInstance().getApplicationContext().getBean("myTest");
//    	test.test();
//    	SocketServer socketServer = ServerConfig.getInstance().getApplicationContext().getBean(SocketServer.class);
//    	socketServer.run();
    	IServer server = (IServer) ServerConfig.getInstance().getApplicationContext().getBean("basicServerImpl");
        server.start();
    }
}
