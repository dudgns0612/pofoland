package log.viewer.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import log.viewer.convertion.LogViewerClientDecoder;
import log.viewer.convertion.LogViewerClientEncoder;


public class LogViewerTcpClient {
	
	public LogViewerTcpClient() {
	
	}
	
	public static void main(String[] args) {
		String inetHost = "localhost";
		int inetPort = 8088;
		
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
			Bootstrap bootStrap = new Bootstrap();
			bootStrap.group(group);
			bootStrap.channel(NioSocketChannel.class);
			bootStrap.remoteAddress(inetHost, inetPort);
			bootStrap.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline cp = ch.pipeline();
					cp.addLast(new LogViewerClientEncoder());
					cp.addLast(new LogViewerClientDecoder());
					cp.addLast(new LogViewerTcpClientHandler());
				}
			});
			
			ChannelFuture channelFuture = bootStrap.connect().sync();
			channelFuture.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}
}
