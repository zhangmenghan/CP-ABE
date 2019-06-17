package cpabe.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

import cpabe.systemEntity.ClientBusiness;

public class Client {
	// public static Socket socket = null;
	public static String STAFF_ID = null;
	public static String SERVER_IP = null;
	public static int SERVER_PORT = 58888;
	public static String message = null;

//	public static void main(String[] args) throws IOException {
//
//		Socket socket = new Socket();
//		SocketAddress socketAddress = new InetSocketAddress(SERVER_IP,
//				SERVER_PORT);
//		socket.connect(socketAddress, 500000000);
//
//		ClientBusiness clientBusiness = new ClientBusiness(socket);
//	}
}
