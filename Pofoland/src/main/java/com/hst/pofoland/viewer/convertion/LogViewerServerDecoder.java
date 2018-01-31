package com.hst.pofoland.viewer.convertion;

import java.util.List;

import com.hst.pofoland.viewer.utils.ByteUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.viewer.convertion.LogViewerServerDecoder.java
 * 클래스 설명 : 로그뷰어 서버 디코딩
 *
 * @author 김영훈
 * @since 2017. 10. 19.
 * @version 1.0.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 * 수정일			수정자			수정내용
 * -------------------------------------------------
 * 2017. 10. 19.	김영훈			최초생성
 * </pre>
*/

public class LogViewerServerDecoder extends ByteToMessageDecoder{

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		int packetAllSize = in.readableBytes();
		
		//최소사이즈 판별
		if (packetAllSize < 4) {
			return;
		}
		
		byte[] read = new byte[packetAllSize];
		
		for (int i = 0 ; i < packetAllSize ; i++) {
			read[i] = in.readByte();
		}
		
		//중간에 끊길 시 기존 인덱스 저장
		if (!ByteUtils.byteToHexString(read).contains("0x02") || !ByteUtils.byteToHexString(read).contains("0x03")) {
			in.resetReaderIndex();
			return;
		} 
		byte[] packetDataSize = new byte[4];
		System.arraycopy(read, 2, packetDataSize, 0, 4);
		int dataSize = ByteUtils.byteToIntBigEndian(packetDataSize);
		
		
		byte[] packetMessageData = new byte[dataSize];
		System.arraycopy(read,6, packetMessageData, 0, read.length-6);
		System.arraycopy(packetMessageData, 0, packetMessageData, 0, packetMessageData.length-1);
		
		String reciveMsg = new String(packetMessageData,"UTF-8");
		
		String msg = reciveMsg.split("[&]")[1];
		
		if (msg.trim().length() > 0) {
			out.add(reciveMsg.trim());
		} else {
			out.add(reciveMsg);
		}
	}
}
