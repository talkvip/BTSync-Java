package ms.safi.btsync;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;

/**
 * BTSyncApp is used for starting an instance of BitTorrent Sync. A BitTorrent Sync v1.3.105 
 * executable for linux and windows platforms are bundled with this library.
 * 
 * @author Omeed Safi
 */
public class BTSyncApp implements AutoCloseable {

	private String deviceName;
	private int listeningPort;
	private boolean useUpnp;
	private String storagePath;
	private boolean useGui;
	private String listen;
	private String login;
	private String password;
	private String apiKey;
	
	private File btSyncTmpFolder = new File(System.getProperty("java.io.tmpdir"), "BTSyncJava");
	private File btSyncExecutable;
	private File btSyncConf = new File(btSyncTmpFolder, "sync.conf");
	
	private Process runningAppProcess;
	
	/**
	 * Constructs a new BTSyncApp used to start a BitTorrent Sync instance with default
	 * settings.
	 * 
	 * <p>Default Settings:
	 * <ul>
	 * <li>device_name = "BTSync-Java"
	 * <li>listening_port = 0 (Meaning random port assignment)
	 * <li>use_upnp = false
	 * <li>storage_path = ./
	 * <li>use_gui = false
	 * <li>listen = 127.0.0.1:18080
	 * <li>login = user
	 * <li>password = password
	 * 
	 * @param apiKey the BitTorrent Sync API Key
	 */
	public BTSyncApp(String apiKey) {
		this.deviceName = "BTSyncJava";
		this.listeningPort = 0;
		this.useUpnp = false;
		this.storagePath = "./";
		this.useGui = false;
		this.listen = "127.0.0.1:18080";
		this.login = "user";
		this.password = "password";
		this.apiKey = apiKey;
		
		try {
			FileUtils.deleteDirectory(btSyncTmpFolder);
			Files.createDirectory(btSyncTmpFolder.toPath());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Sets the device_name for BitTorrent Sync to use. The default is <code>BTSync-Java</code>
	 * 
	 * @param deviceName the deviceName to set
	 * @return the updated BTSyncApp instance
	 */
	public BTSyncApp setDeviceName(String deviceName) {
		this.deviceName = deviceName;
		return this;
	}
	
	/**
	 * Sets the listening_port for BitTorrent Sync to use. The default is <code>0</code> which 
	 * means a port is randomly assigned
	 * 
	 * @param listeningPort the listeningPort to set
	 * @return the updated BTSyncApp instance
	 */
	public BTSyncApp setListeningPort(int listeningPort) {
		this.listeningPort = listeningPort;
		return this;
	}
	
	/**
	 * Sets the use_upnp flag for BitTorrent Sync. The default is <code>false</code>.
	 * Need more research to understand what this flag actually is for better description.
	 * 
	 * @param useUpnp whether BitTorrent Sync will use UPNP or not
	 * @return the updated BTSyncApp instance
	 */
	public BTSyncApp setUseUpnp(boolean useUpnp) {
		this.useUpnp = useUpnp;
		return this;		
	}

	/**
	 * Sets the storage_path for BitTorrent Sync to use. The default is <code>./</code>
	 * 
	 * @param storagePath the storagePath to set
	 * @return the updated BTSyncApp instance
	 */
	public BTSyncApp setStoragePath(String storagePath) {
		this.storagePath = storagePath;
		return this;
	}

	/**
	 * Sets the use_gui for BitTorrent Sync. The default is <code>false</code>. 
	 * The use_gui option sets whether or not BitTorrent Sync should be shown when running.
	 * Effects of setting the use_gui value to <code>true</code> on linux is unknown as linux only has webui
	 * 
	 * @param useGui whether the BT Sync GUI is shown or not
	 * @return the updated BTSyncApp instance
	 */
	public BTSyncApp setUseGui(boolean useGui) {
		this.useGui = useGui;
		return this;
	}

	/**
	 * Sets the address:port that BitTorrent Sync will listen on. The default is <code>127.0.0.1:18080</code>.
	 * 
	 * @param listen the address:port
	 * @return the updated BTSyncApp instance
	 */
	public BTSyncApp setListen(String listen) {
		this.listen = listen;
		return this;
	}

	/**
	 * Sets the username for clients to use to accessing BitTorrent Sync API methods.
	 * The default is <code>user</code>.
	 * 
	 * @param login the username
	 * @return the updated BTSyncApp instance
	 */
	public BTSyncApp setLogin(String login) {
		this.login = login;
		return this;
	}

	/**
	 * Sets the password for clients to use to accessing BitTorrent Sync API methods.
	 * The default is <code>password</code>.
	 * 
	 * @param password the password
	 * @return the updated BTSyncApp instance
	 */
	public BTSyncApp setPassword(String password) {
		this.password = password;
		return this;
	}
	
	/**
	 * Starts BitTorrent Sync and returns an instance of BTSyncClient for accessing
	 * the BitTorrent Sync API.
	 * 
	 * <p>See the class description for the current limitations of this method. 
	 * 
	 * @return a BTSyncClient preconfigured for accessing the BitTorrent Sync started
	 */
	public BTSyncClient startBtSync() {
		if(SystemUtils.IS_OS_WINDOWS) {
			btSyncExecutable = new File(btSyncTmpFolder, "btsync.exe");
			return this.startBtSyncWindows();
		} else if(SystemUtils.IS_OS_UNIX) {
			btSyncExecutable = new File(btSyncTmpFolder, "btsync");
			return this.startBtSyncLinux();
		} else if(SystemUtils.IS_OS_MAC_OSX) {
			return this.startBtSyncMacOsx();
		} else {
			return null;
		}
	}

	private BTSyncClient startBtSyncWindows() {
		this.extractWinBtSync();
		this.buildConf();
		
		try {
			//Print this line using logging api
			//System.out.println(btSyncExecutable.getCanonicalPath() + " /config " + btSyncConf.getCanonicalPath());
			runningAppProcess = Runtime.getRuntime().exec(btSyncExecutable.getCanonicalPath() + " /config " + btSyncConf.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new BTSyncClient(this.listen, this.login, this.password);
	}
	
	private BTSyncClient startBtSyncLinux() {
		this.extractLinuxBtSync();
		this.buildConf();

		try {
			//Print this line using logging api
			//System.out.println(btSyncExecutable.getCanonicalPath() + " --config " + btSyncConf.getCanonicalPath());
			runningAppProcess = Runtime.getRuntime().exec(btSyncExecutable.getCanonicalPath() + " --config " + btSyncConf.getCanonicalPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new BTSyncClient(this.listen, this.login, this.password);
	}
	
	private BTSyncClient startBtSyncMacOsx() {
		return null;
	}
	
	private void buildConf() {
		try {
			Files.createFile(btSyncConf.toPath());
			btSyncConf.setReadable(true, false);
			btSyncConf.setWritable(true, false);
		
			JsonFactory jFactory = new JsonFactory();
			JsonGenerator jGenerator = jFactory.createJsonGenerator(btSyncConf, JsonEncoding.UTF8);
			jGenerator.useDefaultPrettyPrinter();
			
			jGenerator.writeStartObject(); // {
				jGenerator.writeStringField("device_name", this.deviceName);
				jGenerator.writeNumberField("listening_port", this.listeningPort);
				jGenerator.writeBooleanField("check_for_updates", false); // NEVER CHECK FOR UPDATES
				jGenerator.writeBooleanField("use_upnp", useUpnp);
				jGenerator.writeStringField("storage_path", this.storagePath);
				jGenerator.writeBooleanField("use_gui", this.useGui);
				jGenerator.writeFieldName("webui");
				jGenerator.writeStartObject(); // {
					jGenerator.writeStringField("listen", this.listen);
					jGenerator.writeStringField("login", this.login);
					jGenerator.writeStringField("password", this.password);
					jGenerator.writeStringField("api_key", this.apiKey);
				jGenerator.writeEndObject(); // }
			jGenerator.writeEndObject(); // }
			
			jGenerator.close();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void extractWinBtSync() {
		try {
			URL url = getClass().getClassLoader().getResource("btsync-win.exe");
			InputStream in = url.openStream();
			
			Files.copy(in, btSyncExecutable.toPath());
			btSyncExecutable.setExecutable(true, false);
			btSyncExecutable.setReadable(true, false);
			btSyncExecutable.setWritable(true, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void extractLinuxBtSync() {
		try {
			URL url = getClass().getClassLoader().getResource("btsync-linux-i386");
			InputStream in = url.openStream();
			
			Files.copy(in, btSyncExecutable.toPath());
			btSyncExecutable.setExecutable(true, false);
			btSyncExecutable.setReadable(true, false);
			btSyncExecutable.setWritable(true, false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() {
		// Close BTSync if running
		if(isAlive(runningAppProcess)) {
			runningAppProcess.destroy();
			try {
				runningAppProcess.waitFor();
			} catch (InterruptedException e) {
				System.out.println("Exception while waiting for the BTSync process to end");
				e.printStackTrace();
			}
		}
		
		// Cleanup files
		try {
			FileUtils.deleteDirectory(btSyncTmpFolder);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isAlive(Process p) {
		try {
			p.exitValue();
			return false;
		} catch (IllegalThreadStateException e) {
			return true;
		}
	}
}
