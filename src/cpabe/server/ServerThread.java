package cpabe.server;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import cpabe.systemEntity.ServerBusiness;

public class ServerThread implements Runnable {
	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		ServerBusiness serverBusiness = new ServerBusiness(socket);
		serverBusiness.handleRequest();
	}
}
