package log.viewer.client;

import org.json.simple.JSONObject;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import log.viewer.constant.NetworkProtocolConstant;
import log.viewer.utils.JsonUtils;
import log.viewer.view.ClientLoggingWindow;

public class LogViewerTcpClientHandler extends SimpleChannelInboundHandler<Object>{
	
	ClientLoggingWindow clientLoggingWindow = null;
	public static ChannelHandlerContext ctx;
	
	@SuppressWarnings("static-access")
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		this.ctx = ctx;
		clientLoggingWindow = new ClientLoggingWindow();
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		JSONObject commuObject = (JSONObject)msg;
		String protocol = String.valueOf(commuObject.get("PROTOCOL"));
		if (NetworkProtocolConstant.SERVER_SEND_LOG_MESSAGE.equals(protocol)) {
			clientLoggingWindow.writeLogger(String.valueOf(commuObject.get("VALUE")));
		} else if (NetworkProtocolConstant.TEST_PROTOCOL.equals(protocol)) {
			clientLoggingWindow.writeLogger(String.valueOf(commuObject.get("VALUE")));
		} else if (NetworkProtocolConstant.CLINET_SEND_START.equals(protocol)) {
			clientLoggingWindow.writeLogger(String.valueOf(commuObject.get("VALUE")));
		} else if (NetworkProtocolConstant.CLINET_SEND_STOP.equals(protocol)) {
			clientLoggingWindow.writeLogger(String.valueOf(commuObject.get("VALUE")));
		}
	}
	
	private void sendMessage() {
	}
	
	public static void viewerStateChange(String state) {
		if (state.equals("Y")) {
			JSONObject sendJsonObject = JsonUtils.setJsonValue(NetworkProtocolConstant.CLINET_SEND_START);
			ctx.writeAndFlush(sendJsonObject);
		} else {
			JSONObject sendJsonObject = JsonUtils.setJsonValue(NetworkProtocolConstant.CLINET_SEND_STOP);
			ctx.writeAndFlush(sendJsonObject);
		}
	}

}
