package log.viewer.convertion;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import org.json.simple.JSONObject;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class LogViewerClientDecoder extends ByteToMessageDecoder{

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
