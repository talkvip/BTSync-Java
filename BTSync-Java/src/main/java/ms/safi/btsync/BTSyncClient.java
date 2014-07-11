package ms.safi.btsync;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.GenericType;

import ms.safi.btsync.dto.BTSyncPreferences;
import ms.safi.btsync.dto.BTSyncResponse;
import ms.safi.btsync.dto.File;
import ms.safi.btsync.dto.Folder;
import ms.safi.btsync.dto.FolderPreferences;
import ms.safi.btsync.dto.Hosts;
import ms.safi.btsync.dto.Peer;
import ms.safi.btsync.dto.Secrets;
import ms.safi.btsync.dto.Speed;
import ms.safi.btsync.util.JsonContentTypeResponseFilter;

import org.apache.commons.lang3.SystemUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.jboss.resteasy.client.jaxrs.BasicAuthentication;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;

/**
 * BTSyncClient provides a number of methods used to interact with a running instance
 * of BitTorrent Sync. These methods are based on the 
 * <a href="http://www.bittorrent.com/sync/developers/api">BitTorrent Sync Beta API</a>
 * 
 * @author Omeed Safi
 *
 */
public class BTSyncClient {
	
	private ResteasyClient client;
	private final String baseUrl;
	private BTSyncResponse lastError;
	
	/**
	 * Constructs a new BTSyncClient to interact with a running BitTorrent Sync instance.
	 * This constructor is identical to {@link #BTSyncClient(String, String, String, String)}
	 * that is provided only for convenience.
	 * 
	 * 
	 * @param listenAddress the address:port combination where BT Sync is listening 
	 * @param username the username for accessing BTSync
	 * @param password the password for the user
	 */
	public BTSyncClient(String listenAddress, String username, String password) {
		this(listenAddress.split(":")[0], listenAddress.split(":")[1], username, password);
	}
	
	/**
	 * Constructs a new BTSyncClient to interact with a running BitTorrent Sync instance.
	 * 
	 * @param address the address of the device running BT Sync
	 * @param port the port BT Sync is listening to
	 * @param username the username for accessing BTSync
	 * @param password the password for the user
	 */
	public BTSyncClient(String address, String port, String username, String password) {
		this.baseUrl = String.format("http://%s:%s/api?", address, port);
		
		client = new ResteasyClientBuilder().build();
		client.register(new BasicAuthentication(username, password))
		      .register(JsonContentTypeResponseFilter.class);
	}
	
	/**
	 * Returns info for all Folders being managed by BT Sync.
	 * 
	 * @return the Folders containing all folder info
	 */
	public List<Folder> getFolders() {
		return this.client.target(this.baseUrl + "method=get_folders")
							.request()
							.get(new GenericType<List<Folder>>(){});
	}
	
	/**
	 * Returns info for the Folder being managed by BT Sync identified by the given secret.
	 * 
	 * @param secret the folder's secret
	 * @return the Folder containing info
	 */
	public Folder getFolder(String secret) {
		return this.client.target(this.baseUrl + "method=get_folders&secret=" + secret)
							.request()
							.get(Folder.class);
	}
	
	/**
	 * Adds the given folder to BT Sync. The folder MUST exist before calling this method.
	 * 
	 * @param dir the File describing the folder to be added
	 * @return <code>true</code> if the folder was added to Sync; <code>false</code> otherwise (call
	 * {@link #getLastError()} for cause)
	 */
	public boolean addFolder(java.io.File dir) {
		try {
			return this.addFolder(dir.getCanonicalPath());
		} catch (IOException e) {
			// Add bogus file that is invalid in windows and unix environments so BitTorrent Sync will definitely deny
			this.addFolder("\u0000");
			return false;
		}
	}
	
	/**
	 * Adds the given folder to BT Sync. The folder MUST exist before calling this method. 
	 * 
	 * @param dir the path to the folder to be added
	 * @return <code>true</code> if the folder was added to Sync; <code>false</code> otherwise (call
	 * {@link #getLastError()} for cause)
	 */
	public boolean addFolder(String dir) {
		BTSyncResponse response = this.client.target(this.baseUrl + "method=add_folder&dir=" + dir)
												.request()
												.get(BTSyncResponse.class);
		
		if(response.isSuccess()) {
			return true;
		} else {
			this.lastError = response;
			return false;
		}
	}
	
	/**
	 * Adds a folder to BT Sync using a secret in either selective sync
	 * or full download mode. The folder MUST exist before calling this method.
	 * 
	 * @param dir the File describing the folder to be added
	 * @param secret the secret of the folder to be added
	 * @param selectiveSync whether the folder should use selective syncing or not
	 * @return <code>true</code> if the folder was added to Sync; <code>false</code> otherwise (call
	 * {@link #getLastError()} for cause)
	 */
	public boolean addFolder(java.io.File dir, String secret, boolean selectiveSync) {
		try {
			return this.addFolder(dir.getCanonicalPath(), secret, selectiveSync);
		} catch (IOException e) {
			// Add bogus file that is invalid in windows and unix environments so BitTorrent Sync will definitely deny
			this.addFolder("\u0000", "", false);
			return false;
		}
	}
	
	/**
	 * Adds a folder to BT Sync using a secret in either selective sync
	 * or full download mode. The folder MUST exist before calling this method.
	 * 
	 * @param dir the path to the folder to be added
	 * @param secret the secret of the folder to be added
	 * @param selectiveSync whether the folder should use selective syncing or not
	 * @return <code>true</code> if the folder was added to Sync; <code>false</code> otherwise (call
	 * {@link #getLastError()} for cause)
	 */
	public boolean addFolder(String dir, String secret, boolean selectiveSync) {
		BTSyncResponse response = this.client.target(this.baseUrl + "method=add_folder&dir=" + dir + "&secret=" + secret + "&selective_sync=" + (selectiveSync ? "1" : "0"))
												.request()
												.get(BTSyncResponse.class);
		
		if(response.isSuccess()) {
			return true;
		} else {
			this.lastError = response;
			return false;
		}
	}
	
	/**
	 * Removes the folder from BT Sync using the folder's secret WITHOUT deleting anything from the disk.
	 * 
	 * @param secret the secret of the folder to be removed
	 * @return <code>true</code> if the folder was removed from Sync; <code>false</code> otherwise (call
	 * {@link #getLastError()} for cause)
	 */
	public boolean removeFolder(String secret) {
		BTSyncResponse response = this.client.target(this.baseUrl + "method=remove_folder&secret=" + secret)
												.request()
												.get(BTSyncResponse.class);
		
		if(response.isSuccess()) {
			return true;
		} else {
			this.lastError = response;
			return false;
		}
	}
	
	/**
	 * Returns info for the contents of a folder identified by the given secret.
	 * The information returned is for both files and folders in the directory.
	 *   
	 * @param secret the folder's secret
	 * @return the file information for the contents of the folder
	 */
	public List<File> getFiles(String secret) {
		return this.client.target(this.baseUrl + "method=get_files&secret=" + secret)
							.request()
							.get(new GenericType<List<File>>(){});
	}
	
	/**
	 * Returns info for the contents of a subdirectory of a folder identified by the given secret.
	 * The information returned is for both files and folders in the directory.
	 * 
	 * @param secret the folder's secret
	 * @param path the path within the root folder
	 * @return the file information for the contents of the folder
	 */
	public List<File> getFiles(String secret, String path) {
		return this.client.target(this.baseUrl + "method=get_files&secret=" + secret + "&path=" + path)
							.request()
							.get(new GenericType<List<File>>(){});
	}
	
	/**
	 * Sets the selective sync option for a file at the given path within
	 * the folder identified by the given secret.
	 * 
	 * @param secret the folder's secret
	 * @param path the path to the file within the root folder
	 * @param download whether the file should be selectively synced or not
	 * @return the file with applied preferences
	 */
	public File setFilePreferences(String secret, String path, boolean download) {
		// TODO: if invalid string/path then returns an empty file - need to do some sort of error handling
		// or allow user of API to know of problem details.
		return this.client.target(this.baseUrl + "method=set_file_prefs&secret=" + secret + "&path=" + path + "&download=" + (download ? "1" : "0"))
				.request()
				.get(File.class);
	}
	
	/**
	 * Returns the peers connected to the folder identified by the given secret.
	 * 
	 * @param secret the folder's secret
	 * @return the peers of a folder
	 */
	public List<Peer> getFolderPeers(String secret) {
		return this.client.target(this.baseUrl + "method=get_folder_peers&secret=" + secret)
				.request()
				.get(new GenericType<List<Peer>>(){});
	}
	
	/**
	 * Generates read-write, read-only, and encryption read-only secrets. 
	 * The encryption secret is to grant read only permission but the contents remains encrypted.
	 * 
	 * @return the secrets
	 */
	public Secrets getSecrets() {
		return this.client.target(this.baseUrl + "method=get_secrets&type=encryption")
				.request()
				.get(Secrets.class);
	}
	
	/**
	 * Returns the secrets available to use for sharing a folder identified by the given secret.
	 * 
	 * @param secret the folder's secret
	 * @return the secrets
	 */
	public Secrets getSecrets(String secret) {
		return this.client.target(this.baseUrl + "method=get_secrets&secret=" + secret + "&type=encryption")
				.request()
				.get(Secrets.class);
	}
	
	/**
	 * Returns the preferences of the folder identified by the given secret.
	 * 
	 * @param secret the folder's secret
	 * @return the folder preferences
	 */
	public FolderPreferences getFolderPreferences(String secret) {
		return this.client.target(this.baseUrl + "method=get_folder_prefs&secret=" + secret)
				.request()
				.get(FolderPreferences.class);
	}
	
	/**
	 * Sets the preferences for the folder identified by the given secret.
	 * 
	 * @param secret the folder's secret
	 * @param prefs the preferences
	 * @return the updated preferences for the folder
	 */
	public FolderPreferences setFolderPreferences(String secret, FolderPreferences prefs) {
		return this.setFolderPreferences(
				secret,
				prefs.isUseDht(),
				prefs.isUseHosts(),
				prefs.isSearchLan(),
				prefs.isUseRelayServer(),
				prefs.isUseTracker(),
				prefs.isUseSyncTrash()
				);
	}
	
	/**
	 * Sets the preferences for the folder identified by the given secret.
	 * 
	 * @param secret the folder's secret
	 * @param useDht whether the folder should use DHT
	 * @param useHosts whether the folder should use predefined hosts
	 * @param searchLan whether to search lan
	 * @param useRelayServer whether to use a relay server
	 * @param useTracker whether to use a tracker server
	 * @param useSyncTrash whether files deleted on clients become hidden
	 * @return the updated preferences for the folder
	 */
	public FolderPreferences setFolderPreferences(String secret, boolean useDht, boolean useHosts, boolean searchLan, boolean useRelayServer, boolean useTracker, boolean useSyncTrash) {
		return this.client.target(this.baseUrl + "method=set_folder_prefs&secret=" + secret + "&use_dht=" + (useDht ? "1" : "0")+ "&use_hosts=" + (useHosts ? "1" : "0")+ "&search_lan=" + (searchLan ? "1" : "0")+ "&use_relay_server=" + (useRelayServer ? "1" : "0")+ "&use_tracker=" + (useTracker ? "1" : "0")+ "&use_sync_trash=" + (useSyncTrash ? "1" : "0"))
				.request()
				.get(FolderPreferences.class);
	}
	
	
	/**
	 * Returns the predefined hosts for a folder identified by the given secret.
	 * 
	 * @param secret the folder's secret
	 * @return the hosts
	 */
	public Hosts getFolderHosts(String secret) {
		return this.client.target(this.baseUrl + "method=get_folder_hosts&secret=" + secret)
				.request()
				.get(Hosts.class);
	}
	
	/**
	 * Sets the hosts for a folder identified by the given secret.
	 * The exists hosts for the folder will be overwritten.
	 * 
	 * @param secret the folder's secret
	 * @param hosts the hosts
	 * @return the updated hosts
	 */
	public Hosts setFolderHosts(String secret, Hosts hosts) {
		return this.setFolderHosts(secret, hosts.getHosts().toArray(new String[0]));
	}
	
	/**
	 * Sets the hosts (in the address:port format) for a folder identified by the given secret.
	 * The exists hosts for the folder will be overwritten.
	 * 
	 * @param secret the folder's secret
	 * @param hosts the hosts
	 * @return the updated hosts
	 */
	public Hosts setFolderHosts(String secret, String...hosts) {
		String hostList = "";
		if(hosts.length > 0) {
			hostList += hosts[0];
			for(int i = 1; i < hosts.length; i++) {
				hostList += "," + hosts[i];
			}
		}
		
		return this.client.target(this.baseUrl + "method=get_folder_hosts&secret=" + secret + "&hosts=" + hostList)
				.request()
				.get(Hosts.class);
	}

	/**
	 * Returns the preferences for the running instance of BitTorrent Sync, including the advanced preferences.
	 * 
	 * @return the preferences
	 */
	public BTSyncPreferences getPreferences() {
		return this.client.target(this.baseUrl + "method=get_prefs")
				.request()
				.get(BTSyncPreferences.class);
	}
	
	/**
	 * Sets the preferences (including advanced preferences) for the running instance of BitTorrent Sync.
	 * 
	 * @param prefs the preferences
	 * @return the updated preferences
	 */
	public BTSyncPreferences setPreferences(BTSyncPreferences prefs) {
		return this.client.target(this.baseUrl + "method=set_prefs&" + prefs.buildPrefMapString())
				.request()
				.get(BTSyncPreferences.class);
	}
	
	/**
	 * Returns the name of the OS that BitTorrent Sync is running on.
	 * 
	 * @return the OS name
	 */
	public String getOSName() {
		String response = this.client.target(this.baseUrl + "method=get_os").request().get(String.class);
		
		try {
			JsonNode json = new ObjectMapper().readTree(response);
			return json.get("os").asText();
		} catch (IOException e) {
			// This code should never be executed have to put it for compiling only
			e.printStackTrace();
		}
		
		return "UNKNOWN OS";
	}
	
	
	/**
	 * Returns the version of BitTorrent Sync.
	 * 
	 * @return the version
	 */
	public String getVersion() {
		String response = this.client.target(this.baseUrl + "method=get_version").request().get(String.class);
		
		try {
			JsonNode json = new ObjectMapper().readTree(response);
			return json.get("version").asText();
		} catch (IOException e) {
			// This code should never be executed
			// have to put it for compiling only
			e.printStackTrace();
		}
		
		return "0.0.0";
	}
	
	/**
	 * Returns the upload and download speed of BitTorrent Sync.
	 * 
	 * @return the speeds
	 */
	public Speed getSpeed() {
		return this.client.target(this.baseUrl + "method=get_speed").request().get(Speed.class);
	}
	
	/**
	 * Shuts down BitTorrent Sync.
	 * 
	 */
	public void shutdown() {
		/*
		 * Using post instead of get because once BTSync receives the shutdown command it immediately
		 * closes without sending a response back. This causes a javax.ws.rs.ProcessingException when
		 * using the get call because it expects a response.
		 * 
		 * This problem doesn't occur on Linux...must use get as usual (preferred method)
		 * Make this better in future (BTSyncClient interface, abstract class implementing interface with identical methods
		 * with BTSyncLinuxClient/BTSyncWinClient/etc for methods requiring different implementation).
		 */
		if(SystemUtils.IS_OS_WINDOWS) {
			this.client.target(this.baseUrl + "method=shutdown").request().post(null);
		} else if(SystemUtils.IS_OS_LINUX) {
			this.client.target(this.baseUrl + "method=shutdown").request().get();
		}
		
	}

	/**
	 * Returns the last error that was produced by BitTorrent Sync.
	 * 
	 * @return the last error
	 */
	public BTSyncResponse getLastError() {
		return lastError;
	}
}
