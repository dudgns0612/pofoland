package com.hst.pofoland.viewer.server;

import java.net.InetSocketAddress;

import com.hst.pofoland.common.utils.LoggerManager;
import com.hst.pofoland.viewer.convertion.LogViewerServerDecoder;
import com.hst.pofoland.viewer.convertion.LogViewerServerEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.viewer.server.LoggerViewerTcpServer.java
 * 클래스 설명 : 로그 뷰어 서버
 *
 * @author 김영훈
 * @since 2017. 10. 18.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 10. 18.	김영훈			최초생성
 * </pre>
*/

public class LogViewerTcpServer {
	
	int port;
	
	/**
	 * 기본생성자
	 */
	public LogViewerTcpServer(int port) {
		this.port = port;
		logViewerServerStart();
	}
	
	public void logViewerServerStart() {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				// NIO로 설정
				EventLoopGroup group = new NioEventLoopGroup();
				EventLoopGroup worker =  new NioEventLoopGroup();
				
				try {
					//부트스트랩 설정
					ServerBootstrap bootstrap = new ServerBootstrap();
					bootstrap.group(group,worker);
					bootstrap.channel(NioServerSocketChannel.class);
					bootstrap.handler(new LoggingHandler(LogLevel.INFO));
					bootstrap.localAddress(new InetSocketAddress(port));
					//새로운 Channel처리
					bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new LogViewerServerEncoder());
							ch.pipeline().addLast(new LogViewerServerDecoder());
							ch.pipeline().addLast(new LogViewerTcpServerHandler());
						}
					});
					
					//커넥션 연결
					ChannelFuture channelFuture = bootstrap.bind().sync();
					//종료 될때까지 lock
					channelFuture.channel().closeFuture().sync();
				} catch (Exception e) {
					LoggerManager.error(getClass(), "ERROR : {}",  e.getMessage());
				} finally {
					try {
						group.shutdownGracefully().sync();
					} catch (InterruptedException e) {
						LoggerManager.error(getClass(), "ERROR : {}",  e.getMessage());
					}
				}
			}
		}).start();
	}
}
