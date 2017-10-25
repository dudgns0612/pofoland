package com.hst.pofoland.viewer.server;

import org.json.simple.JSONObject;

import com.hst.pofoland.common.constnat.NetworkProtocolConstant;
import com.hst.pofoland.common.utils.JsonUtils;
import com.hst.pofoland.common.utils.LoggerManager;
import com.hst.pofoland.viewer.vo.ChannelVO;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.viewer.server.LoggerViewerTcpServerHandler.java
 * 클래스 설명 : 로그 뷰어 서버 핸들러
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

public class LogViewerTcpServerHandler extends ChannelInboundHandlerAdapter{
	
	ChannelHandlerContext ctx;
	
	//channel 연결 (클라이언트 접속)
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.ctx = ctx;
		ChannelVO channel = new ChannelVO();
		channel.setCtx(ctx);
		ChannelVO.channelList.add(channel);
	}
	
	//  이벤트 발생 시 채널 동작
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		JSONObject commuObject = (JSONObject) msg;
		String protocol = String.valueOf(commuObject.get("PROTOCOL"));
		if (NetworkProtocolConstant.CLINET_SEND_START.equals(protocol)) {
			ChannelVO channelVO = ChannelVO.getChannelVO(ctx);
			channelVO.setWorkStateYn("Y");
			JSONObject sendJsonObject = JsonUtils.setJsonValue(NetworkProtocolConstant.CLINET_SEND_START, 
					"VALUE", "======================================================================LogViewer(Ver_0.1) Start======================================================================");
			ctx.writeAndFlush(sendJsonObject);
		} else if (NetworkProtocolConstant.CLINET_SEND_STOP.equals(protocol)) {
			ChannelVO channelVO = ChannelVO.getChannelVO(ctx);
			channelVO.setWorkStateYn("N");
			JSONObject sendJsonObject = JsonUtils.setJsonValue(NetworkProtocolConstant.CLINET_SEND_START, 
					"VALUE", "======================================================================LogViewer(Ver_0.1) Stop======================================================================");
			ctx.writeAndFlush(sendJsonObject);
		}
	}
	
	// 이벤트 동작 완료
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
	}
	
	//오류 발생
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	}
	
	private void sendMessage(JSONObject sendJsonMessage) {
		ctx.writeAndFlush(sendJsonMessage);
	}
	
	public static void logSendMessage(String logMessage) {
		for (ChannelVO channelVO : ChannelVO.channelList) {
			if (!channelVO.getWorkStateYn().equals("N")) {
				ChannelHandlerContext ctx = channelVO.getCtx();
				String logSendValue = NetworkProtocolConstant.SERVER_SEND_LOG_MESSAGE + "," + logMessage;
				//LoggerManager.info(LogViewerTcpServerHandler.class, "CLIENT SEND LOG = {}", logSendValue);
				ctx.writeAndFlush(logSendValue);
			}
		}
	}
	
}
