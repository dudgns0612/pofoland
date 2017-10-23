package com.hst.pofoland.viewer.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.hst.pofoland.common.utils.LoggerManager;
import com.hst.pofoland.viewer.server.LogViewerTcpServerHandler;

public class RealTimeFileAccess {
	
	Thread fileThread = null;
	RandomAccessFile randomAccessFile = null;
	
	public void start() {
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {
					randomAccessFile = new RandomAccessFile("C://pofoland/logs/pofoland.log", "r");
					
					long fileLength = randomAccessFile.length();
					randomAccessFile.seek(fileLength);
					
					String line = "";
					
					while (true) {
						if ((line = randomAccessFile.readLine()) != null) {
							byte[] lineByte = line.getBytes();
							if (lineByte.length > 190) {
								String[] subValue = byteSubString(lineByte,190);
								for (int i = 0 ; i < subValue.length ; i++) {
									LogViewerTcpServerHandler.logSendMessage(subValue[i]);
									Thread.sleep(150);
								}
							} else {
								LogViewerTcpServerHandler.logSendMessage(line);
								Thread.sleep(150);
							}
						} else {
							fileLength = randomAccessFile.length();
							randomAccessFile.seek(fileLength);
						}
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		fileThread = new Thread(runnable);
		fileThread.start();
	}
	
	public void end() {
		try {
			if (fileThread != null) {
				randomAccessFile.close();
				fileThread.interrupt();
			}
		} catch (IOException e) {
			LoggerManager.error(getClass(), "ERROR : {}", e.getMessage());
		}
	}
	
	public String[] byteSubString(byte[] bytes, int size) {
		String[] subString = null;
		int bytesLength = bytes.length;
		int offset = 0;
		
		int count = bytesLength/size;
		int remainder = bytesLength%size;
		if (remainder > 0) {
			subString = new String[count+1];
		} else {
			subString = new String[count];

		}
		for (int i = 0; i < count ; i++) {
			subString[i] = new String(bytes, offset, size);
			offset += size;
		}
		if (remainder > 0) {
			subString[count] =  new String(bytes, offset, remainder);
		}
 		
		return subString;
	}
}
