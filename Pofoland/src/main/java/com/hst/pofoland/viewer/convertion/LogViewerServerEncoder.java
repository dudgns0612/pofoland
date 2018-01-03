package com.hst.pofoland.viewer.convertion;

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

public class LogViewerServerEncoder extends MessageToByteEncoder<String> {
	

	/**
	 * 데이터 인코딩
	 * object를 byte[]로 전송 (직렬화과정)
	 */
	@Override
	protected void encode(ChannelHandlerContext ctx, String sendStr, ByteBuf out) throws Exception {
		
		// String - >> byte[] 변환
		out = Unpooled.directBuffer();
		byte[] sendByteEncoding = ByteUtils.makeSendPacket(sendStr.getBytes("UTF-8"));
		out.writeBytes(sendByteEncoding);
		
		ctx.writeAndFlush(out);
		
	}

}
// object - >> byte[]로변환했을경우 
//		ByteArrayOutputStream byteArrayOutStream = new ByteArrayOutputStream();
//		ObjectOutputStream objectOutStream = new ObjectOutputStream(byteArrayOutStream);
//		
//		//JsonObject -> 바이트 
//		objectOutStream.writeObject(commuObject);
//		objectOutStream.flush();
//		
//		byte[] objectByte = byteArrayOutStream.toByteArray();
//		
//		//직접 버퍼 생성
//		out = Unpooled.directBuffer();
//		out.writeBytes(objectByte);
//		System.out.println(byteToHexString(objectByte));
//		System.out.println(objectByte.length);
//		ctx.writeAndFlush(out);
