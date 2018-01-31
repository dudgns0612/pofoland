package com.hst.pofoland.viewer.reader;

import java.io.IOException;
import java.io.RandomAccessFile;

import com.hst.pofoland.viewer.constant.NetworkProtocolConstant;
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
						line = randomAccessFile.readLine();

						if (line != null && line != "") {
							line = new String(line.getBytes("ISO-8859-1"), "UTF-8");
							LogViewerTcpServerHandler.sendBroadcastMessage(NetworkProtocolConstant.SERVER_SEND_LOG_MESSAGE, line);
							Thread.sleep(80);
						} else {
							fileLength = randomAccessFile.length();
							randomAccessFile.seek(fileLength);
							Thread.sleep(150);
						}
					}
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
	
	
}
