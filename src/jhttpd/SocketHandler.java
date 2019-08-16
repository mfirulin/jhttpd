/**
 * 
 */
package jhttpd;

import java.io.*;
import java.net.*;

/**
 * @author Mikhail Firulin
 *
 */
public class SocketHandler implements Runnable {
    private Socket socket;

    /**
     * Constructor: start a background thread
     */
    public SocketHandler(Socket socket) {
    	this.socket = socket;
    	Thread thread = new Thread(this);
    	thread.start();
    }
    
    /**
     * Background thread:
     * Call THTTPSD.processTransaction() to process
     * this socket in this background thread
     */
	@Override
	public void run() {
		try {
	        InputStream in = socket.getInputStream();
	        OutputStream out = socket.getOutputStream();

	        processTransaction(socket, in, out);
	    } catch (IOException e) {
	    }
	}
}
