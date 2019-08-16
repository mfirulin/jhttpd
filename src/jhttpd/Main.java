package jhttpd;

/**
 * 
 */

/**
 * @author Mikhail Firulin
 *
 */

import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import javax.net.*;
import javax.net.ssl.*;

public class Main {
	
	// The port we will listen on
	private int port;

	// The document root of the server.  All requested filenames
	// are relative to this directory
	private File docroot;

	// Are we running as a secure server or as a regular server?
	private boolean secure = false;
	
	// Number of threads
	private int threads;
	
	// Thread executor
	private ThreadPoolExecutor executor;
	
	// Configuration variables read from 'configurationFile'
	private Properties properties;
	
	static private SecureRandom secureRandom;
	
	// Pattern for parsing "GET" requests
	static private final Pattern getPattern = Pattern.compile(Constants.GET_PARSER);
	
	// Regex for parsing server config variable names
	static private final Pattern serverVarPattern = Pattern.compile(Constants.SERVER_VAR_PARSER);
	
	
	/**
	 * Constructor:
	 * Configure and start the server
	 */
	public Main() throws IOException {
	  readConfiguration();
	  executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(threads);	  
	  new Listener();
	}
	
	private void readConfiguration() throws IOException {
		FileInputStream fin = new FileInputStream(Constants.CONFIGURATION_FILE);
		properties = new Properties();
		properties.load(fin);
		fin.close();
	
		// Show the configuration values on the console
		System.out.println("Configuration: ");
		properties.list(System.out);
	
		// Get 'port', 'docroot', and 'secure' values from properties
		port = Integer.parseInt((String)properties.get("port"));
		docroot = new File((String)properties.get("docroot"));
		String param = (String)properties.get("secure");
		if (param != null && param.equals("true")) {
			secure = true;
		}
		threads = Integer.parseInt((String)properties.get("threads"));
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
