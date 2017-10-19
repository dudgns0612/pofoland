package log.viewer.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import io.netty.channel.ChannelHandlerContext;

public class ClientWindowEvent extends WindowAdapter{
	
	ChannelHandlerContext ctx = null;
	
	public ClientWindowEvent(ChannelHandlerContext ctx) {
		this.ctx = ctx;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		ctx.close();
		System.out.println("종료");
	}
}
