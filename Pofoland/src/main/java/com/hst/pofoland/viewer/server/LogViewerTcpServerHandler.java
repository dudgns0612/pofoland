package com.hst.pofoland.viewer.server;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.hst.pofoland.common.utils.LoggerManager;
import com.hst.pofoland.viewer.constant.NetworkProtocolConstant;
import com.hst.pofoland.viewer.utils.FileUtils;
import com.hst.pofoland.viewer.utils.JsonUtils;
import com.hst.pofoland.viewer.vo.ChannelVO;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

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

public class LogViewerTcpServerHandler extends SimpleChannelInboundHandler<Object> {
	@Inject
	FileUtils fileUtils;
	
	ChannelHandlerContext ctx;
	
	public LogViewerTcpServerHandler() {
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	//channel 연결 (클라이언트 접속)
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		this.ctx = ctx;
		ChannelVO channel = new ChannelVO();
		channel.setCtx(ctx);
		ChannelVO.channelList.add(channel);
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		String[] clinetMsg = String.valueOf(msg).split("[&]");
		String protocol = clinetMsg[0];
		String value = clinetMsg[1].trim();
		if (NetworkProtocolConstant.CLINET_SEND_START.equals(protocol)) {
			ChannelVO channelVO = ChannelVO.getChannelVO(ctx);
			channelVO.setWorkStateYn("Y");
			sendBroadcastMessage(NetworkProtocolConstant.CLINET_SEND_START, "======================================================================LogViewer(Ver_0.1) Start======================================================================");
		} else if (NetworkProtocolConstant.CLINET_SEND_STOP.equals(protocol)) {
			ChannelVO channelVO = ChannelVO.getChannelVO(ctx);
			channelVO.setWorkStateYn("N");
			sendBroadcastMessage(NetworkProtocolConstant.CLINET_SEND_STOP, "======================================================================LogViewer(Ver_0.1) Stop======================================================================");
		} else if (NetworkProtocolConstant.CLIENT_LOG_SIZE_CHANGE.equals(protocol)) {
			ChannelVO channelVO = ChannelVO.getChannelVO(ctx);
			channelVO.setLogSize(value);
		} else if (NetworkProtocolConstant.CLIENT_LOG_SIZE_DEFUALT.equals(protocol)) {
			ChannelVO channelVO = ChannelVO.getChannelVO(ctx);
			channelVO.setLogSize(value);
		} else if (NetworkProtocolConstant.CLIENT_LOG_SIZE.equals(protocol)) {
			ChannelVO channelVO = ChannelVO.getChannelVO(ctx);
			sendBroadcastMessage(NetworkProtocolConstant.CLIENT_LOG_SIZE, String.valueOf(channelVO.getLogSize()));
		} else if (NetworkProtocolConstant.CLIENT_LOG_DATE.equals(protocol)) {
			try {
				double fileSize = fileUtils.getFileSize(value);
				if (fileSize > 1) {
					StringBuffer resBuffer = new StringBuffer();
					resBuffer.append("WANNING>>1MB가 넘는 로그파일은 볼 수 없습니다.").append("\n");
					resBuffer.append("WANNING>>옵션 -d 이용하여 다운로드하여 로깅하여 주시기 바랍니다.").append("\n");
					sendBroadcastMessage(NetworkProtocolConstant.CLIENT_LOG_DATE, resBuffer.toString());
					return;
				}
				String sendMsg = fileUtils.getLogContent(value, ctx);
				sendBroadcastMessage(NetworkProtocolConstant.CLIENT_LOG_DATE, sendMsg);
			} catch(Exception e) {
				e.printStackTrace();
			}
		} else if (NetworkProtocolConstant.CLIENT_LOG_DIR.equals(protocol)) {
			try {
				List<Map<String,String>> fileMapList = fileUtils.getLogDirInfo();
				sendMessage(JsonUtils.setJsonValue(NetworkProtocolConstant.CLIENT_LOG_DIR, fileMapList));
			} catch (Exception e) {
				e.printStackTrace();
			}
				
		}
	}
	
	//오류 발생
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
	}
	
	public void sendMessage(Object value) {
		ctx.writeAndFlush(value);
	}
	
	public void sendMessage(String protocol, String value) {
		if (value.length() <= 0) {
			value = " ";
		}
		String logSendValue = protocol + "&" + value;
		ctx.writeAndFlush(logSendValue);
	}
	
	public static void sendBroadcastMessage(String protocol, String value) {
		for (ChannelVO channelVO : ChannelVO.channelList) {
			if (NetworkProtocolConstant.SERVER_SEND_LOG_MESSAGE.equals(protocol)) {
				if (!channelVO.getWorkStateYn().equals("N")) {
					ChannelHandlerContext ctx = channelVO.getCtx();
					int valueLength = value.length();
					int clientSize = Integer.parseInt(channelVO.getLogSize()); 
					
					if (valueLength < clientSize) {
						if (valueLength <= 0) {
							value = " ";
						} 
						
						String logSendValue = protocol + "&" + value;
						ctx.writeAndFlush(logSendValue);
					} else {
						int arrlength = (valueLength / clientSize) + 1;
						String [] arrLogMsg = new String[arrlength];
						
						arrLogMsg[0] = value.substring(0, clientSize);
						for (int i=1 ; arrlength > i ; i++) {
							if (i == arrlength-1) {
								arrLogMsg[i] = value.substring((i*clientSize), valueLength);
							} else {
								arrLogMsg[i] = value.substring((i*clientSize), (i+1)*clientSize);
							}
						}
						
						for (int i=0; arrlength > i; i++) {
							if (value.length() <= 0) {
								value = " ";
							}
							try {
								String logSendValue = protocol + "&" + arrLogMsg[i];
								ctx.writeAndFlush(logSendValue);
								
								Thread.sleep(50);
							} catch (InterruptedException e) {
								LoggerManager.debug(LogViewerTcpServerHandler.class, "ERROR {}", e.getMessage());
							}
						}
					}
				}
			} else {
				ChannelHandlerContext ctx = channelVO.getCtx();
				if (value.length() <= 0) {
					value = " ";
				}
				String logSendValue = protocol + "&" + value;
				ctx.writeAndFlush(logSendValue);
			}
		}
	}
}
