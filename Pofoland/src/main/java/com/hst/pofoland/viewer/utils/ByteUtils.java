package com.hst.pofoland.viewer.utils;

public class ByteUtils {
	
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
	 * stx - 시작 세그먼트 1byte 
	 * value = value 가변
	 * etx = 종료 세그먼트 1byte 
	 * @return
	 */
	public static byte[] makeSendPacket(byte[] sendValue) {
		
		int valueLength = sendValue.length;
		
		byte[] sendPacket = new byte[valueLength+2];
		
		//2 = 0x02
		sendPacket[0] = 0x02;
		
		//value 가변
		System.arraycopy(sendValue, 0, sendPacket, 1, valueLength);
		
		//3 = 0x03
		sendPacket[valueLength+1] = 0x03;
		
		return sendPacket;
	}
	
}
