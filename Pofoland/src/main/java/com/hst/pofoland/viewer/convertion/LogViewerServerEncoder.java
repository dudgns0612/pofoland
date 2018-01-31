package com.hst.pofoland.viewer.convertion;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import com.hst.pofoland.viewer.utils.ByteUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 
 * 시스템명 : 포트폴리오 관리 시스템
 * $com.hst.pofoland.viewer.convertion.LogViewerServerEncoder.java
 * 클래스 설명 : 로그뷰어 서버 인코딩
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

public class LogViewerServerEncoder extends MessageToByteEncoder<Object> {
	/**
	 * 데이터 인코딩
	 * object를 byte[]로 전송 (직렬화과정)
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
		String returnType = msg.getClass().getName();
		
		if (returnType.contains("String")) {
			// String - >> byte[] 변환
			byte[] sendByteEncoding = ByteUtils.makeSendPacket(String.valueOf(msg).getBytes("UTF-8"), (byte)0x00);
			out = Unpooled.directBuffer();
			out.writeBytes(sendByteEncoding);
			
			ctx.writeAndFlush(out);
		} else {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(msg);
			objectOutputStream.flush();
			
			byte[] objectBytes = ByteUtils.makeSendPacket(byteArrayOutputStream.toByteArray(), (byte)0x01);
			out = Unpooled.directBuffer();
			out.writeBytes(objectBytes);
			
			ctx.writeAndFlush(out);
		}
	}
}