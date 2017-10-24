package com.hst.pofoland.viewer.convertion;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import org.json.simple.JSONObject;

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

public class LogViewerServerEncoder extends MessageToByteEncoder<JSONObject> {
	
	public static String byteToHexString(byte buf[]) {
		String format = "0x%02X, ";
		StringBuffer packet = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			if (i == buf.length - 1) {
				format = format.replace(", ", "");
			}
			packet.append(String.format(format, buf[i]));
		}
		return packet.toString();
	}

	/**
	 * 데이터 인코딩
	 * object를 byte[]로 전송 (직렬화과정)
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, JSONObject commuObject, ByteBuf out) throws Exception {
		ByteArrayOutputStream byteArrayOutStream = new ByteArrayOutputStream();
		ObjectOutputStream objectOutStream = new ObjectOutputStream(byteArrayOutStream);
		
		//JsonObject -> 바이트 
		objectOutStream.writeObject(commuObject);
		objectOutStream.flush();
		
		byte[] objectByte = byteArrayOutStream.toByteArray();
		
		//직접 버퍼 생성
		out = Unpooled.directBuffer();
		out.writeBytes(objectByte);
		System.out.println(byteToHexString(objectByte));
		System.out.println(objectByte.length);
		ctx.writeAndFlush(out);
	}

}
