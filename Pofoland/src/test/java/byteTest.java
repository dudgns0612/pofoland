import org.junit.Test;

public class byteTest {
	
	
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
	
	@Test
	public void byteTest() {
		
		double a = 3 % 10;
		
		System.out.println(a);
		
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
		
		byteArray[3] = (byte) (num);
		byteArray[2] = (byte) (num >> 8);
		byteArray[1] = (byte) (num >> 16);
		byteArray[0] = (byte) (num >> 24);
		
		return byteArray;
	}
}
