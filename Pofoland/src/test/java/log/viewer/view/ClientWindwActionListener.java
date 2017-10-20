package log.viewer.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import log.viewer.client.LogViewerTcpClientHandler;

public class ClientWindwActionListener implements ActionListener{

	public ClientWindwActionListener() {
	}
	
	public void actionPerformed(ActionEvent e) {
		JButton jButton = (JButton)e.getSource();
		if (jButton.getText().equals("시작")) {
			LogViewerTcpClientHandler.viewerStateChange("Y");
		} else if (jButton.getText().equals("중지")) {
			LogViewerTcpClientHandler.viewerStateChange("N");
		}
	}

}
