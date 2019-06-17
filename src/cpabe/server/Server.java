package cpabe.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static int port = 58888;
	public volatile static ServerSocket serverSocket = null;

	public static void startServer() throws IOException {
		serverSocket = new ServerSocket(port);// 瀹氫箟鏈嶅姟绔痵erverSocket
		while (serverSocket != null) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
				ServerThread st = new ServerThread(socket);
				new Thread(st).start();
			} catch (Exception e) {
				// Discard
			}
		}
	}


}
