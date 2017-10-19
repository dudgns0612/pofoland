package log.viewer.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import io.netty.channel.ChannelHandlerContext;

public class ClientLoggingWindow {
	
	ChannelHandlerContext ctx  = null;
	JTextArea logArea = null;
	
	public ClientLoggingWindow() {
	}
	
	public ClientLoggingWindow(ChannelHandlerContext ctx) {
		this.ctx = ctx;
		showWindow();
	}
	
	public void showWindow() {
		JFrame container = new JFrame();
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		JPanel headerPanel = new JPanel(new GridLayout(1, 1));
		JButton startBtn = new JButton("시작");
		JButton endBtn = new JButton("중지");
		headerPanel.add(startBtn);
		headerPanel.add(endBtn);
		
		logArea = new JTextArea();
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		logArea.append("================================================================LOG VIEWER(VER_0.1)========================================================= \n");
		logArea.append("================================================================="+dateFormat.format(date)+"========================================================== \n");
		JScrollPane contentPane = new JScrollPane(logArea);
		
		mainPanel.add(headerPanel,"North");
		mainPanel.add(contentPane, "Center");
		
		container.add(mainPanel);
		container.setTitle("Pofoland LogView(Ver 0.1)");
		container.setSize(1000,600);
		container.setVisible(true);
		container.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.addWindowListener(new ClientWindowEvent(ctx));
	}
	
	public void writeLogger(String msg) {
		logArea.append(msg+"\n");
		logArea.setCaretPosition(logArea.getDocument().getLength());
	}
}
