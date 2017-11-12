package com.hst.pofoland.viewer.convertion;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import org.json.simple.JSONObject;

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
		int dataLength = in.readableBytes();
		byte[] read = new byte[dataLength];
		for (int i = 0 ; i < dataLength ; i++) {
			read[i] = in.readByte();
		}
		
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(read);
		ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
		JSONObject commuObject = (JSONObject)objectInputStream.readObject();
		out.add(commuObject);
	}

}
