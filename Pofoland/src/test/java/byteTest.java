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
		
		byte dd = 0x02;
		int size = 1024;
		
		String aaa = String.valueOf(size);
		
		char[] aa = aaa.toCharArray();
		
		System.out.println(aa.length);
		
		byte[] a = {1,2,5};
		
		System.out.println(byteToHexString(a));
		

	}
}
