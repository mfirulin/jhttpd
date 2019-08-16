package jhttpd;

/**
 * All constants should be defined here.
 */

/**
 * @author Mikhail Firulin
 *
 */
public final class Constants {
	static public final String CONFIGURATION_FILE = "jhttpd.cfg";
	
	// Regex for parsing "GET" requests
	static public final String GET_PARSER = "^GET\\s+(.+)\\s+HTTP/[\\d\\.]+$";
	
	// Regex for parsing server config variable names
	static public final String SERVER_VAR_PARSER = "^server\\.([^\\.]+)\\.([^\\.]+)$";
	
	// Size of buffer used to send file contents to a browser
	static public final int BUFFER_LENGTH = 1024;
}
