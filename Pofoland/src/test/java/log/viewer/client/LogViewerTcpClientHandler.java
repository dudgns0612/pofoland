package log.viewer.client;

import org.json.simple.JSONObject;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import log.viewer.constant.NetworkProtocolConstant;
import log.viewer.utils.JsonUtils;
import log.viewer.view.ClientLoggingWindow;

public class LogViewerTcpClientHandler extends SimpleChannelInboundHandler<Object>{
	
	ClientLoggingWindow clientLoggingWindow = null;
	ChannelHandlerContext ctx;
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		this.ctx = ctx;
		sendMessage();
		clientLoggingWindow = new ClientLoggingWindow(ctx);
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		JSONObject commuObject = (JSONObject)msg;
		clientLoggingWindow.writeLogger((String)commuObject.get("msg"));
	}
	
	private void sendMessage() {
		JSONObject sendJsonObject = JsonUtils.setJsonValue(NetworkProtocolConstant.TEST_PROTOCOL, "msg", "나야나나야나");
		
		ctx.writeAndFlush(sendJsonObject);
	}

}
