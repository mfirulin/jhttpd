package jhttpd;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ServerSocketFactory;

/**
 * 
 */

/**
 * @author mfiru
 *
 */
public class Listener implements Runnable {

	private int port;
	private ServerSocketFactory serverSocketFactory;
	/**
	 * Constructor: start a background thread
	 */
	public Listener(int port, ServerSocketFactory serverSocketFactory) {
		this.serverSocketFactory = serverSocketFactory;
		this.port = port;
		Thread thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Background thread: listen on a port and accept
	 * new connections; The new connections are passed
	 * to JHTTPD.processSocket().
	 */
	public void run() {
		try {
			// Create a ServerSocket from the ServerSocketFactory
			ServerSocket serverSocket = serverSocketFactory.createServerSocket(port);
	
			System.out.println("Listening on port " + port);
	
			// Accept connections and process them
			while (true) {
				try {
					Socket socket = serverSocket.accept();
					System.out.println("Connection from " + socket.getInetAddress());
					processSocket(socket);
				} catch (IOException e) {
					System.err.println("Listener exception: " + e);
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.err.println("Listener exception: " + e);
			e.printStackTrace();
		}
	}
	
	/**
	 * Deal with a new Socket: create a SocketHandler
	 * to process the new connection
	 */
	private void processSocket(Socket socket) {
		new SocketHandler(socket);		
	}
}
