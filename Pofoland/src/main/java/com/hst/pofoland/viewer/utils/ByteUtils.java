package com.hst.pofoland.viewer.utils;

public class ByteUtils {
	
	// 바이트 -> 헥사코드
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
		
		byte[] sendPacket = new byte[valueLength+6];
		
		//2 = 0x02 STX
		sendPacket[0] = 0x02;
		
		//빅인디안 
		byte[] valueLengthBytes = intToByteBigEndian(valueLength);
		System.arraycopy(valueLengthBytes, 0, sendPacket, 1, 4);
		
		//value 가변
		System.arraycopy(sendValue, 0, sendPacket, 5, valueLength);
		
		//3 = 0x03 ETX
		sendPacket[sendPacket.length+1] = 0x03;
		
		return sendPacket;
	}
	
	// 리틀인디안 바이트 -> 인트
	public int littleEndianBytesToInt(byte[] byteArray) {
		return (byteArray[0] & 0xff) | (byteArray[1] & 0xff) << 8 | (byteArray[2] & 0xff) << 16 | (byteArray[3] & 0xff) << 24;
	}
	
	// 빅인디안 바이트 -> 인트
	public int bigEndianBytesToInt(byte[] byteArray) {
		return (byteArray[0] & 0xff) << 24 | (byteArray[1] & 0xff) << 16 | (byteArray[2] & 0xff) << 8 | (byteArray[3] & 0xff);
	}
	
	// 리틀인디안 인트 -> 바이트
	public static byte[] intToByteLittleEndian(int num) {
		byte[] byteArray = new byte[4];
		
		byteArray[3] = (byte) (num >> 24);
		byteArray[2] = (byte) (num >> 16);
		byteArray[1] = (byte) (num >> 8);
		byteArray[0] = (byte) (num);

		return byteArray;
	}
	
	// 빅인디안 인트 -> 바이트
	public static byte[] intToByteBigEndian(int num) {
		byte[] byteArray = new byte[4];
		
		byteArray[0] = (byte) (num);
		byteArray[1] = (byte) (num >> 8);
		byteArray[2] = (byte) (num >> 16);
		byteArray[3] = (byte) (num >> 24);
		
		return byteArray;
	}
}
