package com.hst.pofoland.viewer.reader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.hst.pofoland.viewer.server.LogViewerTcpServerHandler;

public class RealTimeFileAccess {
	
	Thread fileThread = null;
	
	public void start() {
		
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				RandomAccessFile randomAccessFile = null;
				try {
					randomAccessFile = new RandomAccessFile("C://pofoland/logs/pofoland.log", "r");
					
					long fileLength = randomAccessFile.length();
					randomAccessFile.seek(fileLength);
					
					String line = "";
					StringBuffer sb = new StringBuffer();
					
					while (true) {
						if ((line = randomAccessFile.readLine()) != null) {
							if (sb.length() < 5) {
								sb.append(line);
							} else {
								Thread.sleep(100);
								LogViewerTcpServerHandler.logSendMessage(sb.toString());
								sb.setLength(0);
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
				} finally {
					try {
						randomAccessFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		};
		
		fileThread = new Thread(runnable);
		fileThread.start();
	}
	
	public void end() {
		if (fileThread != null) {
			fileThread.interrupt();
		}
	}
}
