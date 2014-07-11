package ms.safi.btsync.dto;

import javax.xml.bind.annotation.XmlElement;

/**
 * JavaBean implementation of a folder's preferences from BitTorrent Sync.
 * 
 * <p>An example of a folder's preferences:
 * <pre>
 * {@code
 * {
 *      "search_lan":1,
 *      "use_dht":0,
 *      "use_hosts":0,
 *      "use_relay_server":1,
 *      "use_sync_trash":1,
 *      "use_tracker":1
 * }}
 *</pre>
 * 
 * 
 * @author Omeed Safi
 *
 */
public class FolderPreferences {
	@XmlElement(name = "search_lan")
	private boolean searchLan;
	@XmlElement(name = "use_dht")
	private boolean useDht;
	@XmlElement(name = "use_hosts")
	private boolean useHosts;
	@XmlElement(name = "use_relay_server")
	private boolean useRelayServer;
	@XmlElement(name = "use_sync_trash")
	private boolean useSyncTrash;
	@XmlElement(name = "use_tracker")
	private boolean useTracker;
	
	public FolderPreferences() {
		
	}

	/**
	 * @return the searchLan
	 */
	public boolean isSearchLan() {
		return searchLan;
	}

	/**
	 * @param searchLan the searchLan to set
	 */
	public void setSearchLan(boolean searchLan) {
		this.searchLan = searchLan;
	}

	/**
	 * @return the useDht
	 */
	public boolean isUseDht() {
		return useDht;
	}

	/**
	 * @param useDht the useDht to set
	 */
	public void setUseDht(boolean useDht) {
		this.useDht = useDht;
	}

	/**
	 * @return the useHosts
	 */
	public boolean isUseHosts() {
		return useHosts;
	}

	/**
	 * @param useHosts the useHosts to set
	 */
	public void setUseHosts(boolean useHosts) {
		this.useHosts = useHosts;
	}

	/**
	 * @return the useRelayServer
	 */
	public boolean isUseRelayServer() {
		return useRelayServer;
	}

	/**
	 * @param useRelayServer the useRelayServer to set
	 */
	public void setUseRelayServer(boolean useRelayServer) {
		this.useRelayServer = useRelayServer;
	}

	/**
	 * @return the useSyncTrash
	 */
	public boolean isUseSyncTrash() {
		return useSyncTrash;
	}

	/**
	 * @param useSyncTrash the useSyncTrash to set
	 */
	public void setUseSyncTrash(boolean useSyncTrash) {
		this.useSyncTrash = useSyncTrash;
	}

	/**
	 * @return the useTracker
	 */
	public boolean isUseTracker() {
		return useTracker;
	}

	/**
	 * @param useTracker the useTracker to set
	 */
	public void setUseTracker(boolean useTracker) {
		this.useTracker = useTracker;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (searchLan ? 1231 : 1237);
		result = prime * result + (useDht ? 1231 : 1237);
		result = prime * result + (useHosts ? 1231 : 1237);
		result = prime * result + (useRelayServer ? 1231 : 1237);
		result = prime * result + (useSyncTrash ? 1231 : 1237);
		result = prime * result + (useTracker ? 1231 : 1237);
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FolderPreferences other = (FolderPreferences) obj;
		if (searchLan != other.searchLan)
			return false;
		if (useDht != other.useDht)
			return false;
		if (useHosts != other.useHosts)
			return false;
		if (useRelayServer != other.useRelayServer)
			return false;
		if (useSyncTrash != other.useSyncTrash)
			return false;
		if (useTracker != other.useTracker)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FolderPreferences [searchLan=" + searchLan + ", useDht="
				+ useDht + ", useHosts=" + useHosts + ", useRelayServer="
				+ useRelayServer + ", useSyncTrash=" + useSyncTrash
				+ ", useTracker=" + useTracker + "]";
	}
	
	
}
