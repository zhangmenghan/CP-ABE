package cpabe.server;

import java.io.IOException;

import login_gui.Fault;

public class GUIServerThread implements Runnable {

	public void run() {
		try {
			Server.startServer();
		} catch (IOException e) {
			new Fault("服务器开启失败！");
			// Discard
		}
	}
}
