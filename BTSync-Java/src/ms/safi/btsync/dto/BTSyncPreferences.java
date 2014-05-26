package ms.safi.btsync.dto;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;

/**
 * JavaBean implementation of BitTorrent Sync preferences. For a description
 * of each setting, see the <a href="http://syncapp.bittorrent.com/18TDE4IPRO/BitTorrentSyncUserGuide.pdf">
 * BitTorrent Sync User Guide</a>
 * 
 * @author Omeed Safi
 *
 */
public class BTSyncPreferences {
	@XmlElement(name = "device_name") 
	private String deviceName;
	
	@XmlElement(name = "disk_low_priority")
	private boolean diskLowPriority;
	
	@XmlElement(name = "download_limit")
	private long downloadLimit;
	
	@XmlElement(name = "external_port")
	private String externalPort;
	
	@XmlElement(name = "folder_rescan_interval")
	private String folderRescanInterval;
	
	@XmlElement(name = "lan_encrypt_data")
	private boolean lanEncryptData;
	
	private long lang;
	
	@XmlElement(name = "listening_port")
	private long listeningPort;
	
	@XmlElement(name = "log_size")
	private String logSize;
	
	@XmlElement(name = "max_file_size_diff_for_patching")
	private String maxFileSizeDiffForPathching;
	
	@XmlElement(name = "max_file_size_for_versioning")
	private String maxFileSizeForVersioning;
	
	@XmlElement(name = "rate_limit_local_peers")
	private boolean rateLimitLocalPeers;
	
	@XmlElement(name = "recv_buf_size")
	private String recvBufSize;
	
	@XmlElement(name = "send_buf_size")
	private String sendBufSize;
	
	@XmlElement(name = "sync_max_time_diff")
	private String syncMaxTimeDiff;
	
	@XmlElement(name = "sync_trash_ttl")
	private String syncTrashTtl;
	
	@XmlElement(name = "upload_limit")
	private long uploadLimit;

	@XmlElement(name = "use_upnp")
	private boolean useUpnp;
	
	public BTSyncPreferences() {
		
	}

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * @return the diskLowPriority
	 */
	public boolean isDiskLowPriority() {
		return diskLowPriority;
	}

	/**
	 * @param diskLowPriority the diskLowPriority to set
	 */
	public void setDiskLowPriority(boolean diskLowPriority) {
		this.diskLowPriority = diskLowPriority;
	}

	/**
	 * @return the downloadLimit
	 */
	public long getDownloadLimit() {
		return downloadLimit;
	}

	/**
	 * @param downloadLimit the downloadLimit to set
	 */
	public void setDownloadLimit(long downloadLimit) {
		this.downloadLimit = downloadLimit;
	}

	/**
	 * @return the externalPort
	 */
	public String getExternalPort() {
		return externalPort;
	}

	/**
	 * @param externalPort the externalPort to set
	 */
	public void setExternalPort(String externalPort) {
		this.externalPort = externalPort;
	}

	/**
	 * @return the folderRescanInterval
	 */
	public String getFolderRescanInterval() {
		return folderRescanInterval;
	}

	/**
	 * @param folderRescanInterval the folderRescanInterval to set
	 */
	public void setFolderRescanInterval(String folderRescanInterval) {
		this.folderRescanInterval = folderRescanInterval;
	}

	/**
	 * @return the lanEncryptData
	 */
	public boolean isLanEncryptData() {
		return lanEncryptData;
	}

	/**
	 * @param lanEncryptData the lanEncryptData to set
	 */
	public void setLanEncryptData(boolean lanEncryptData) {
		this.lanEncryptData = lanEncryptData;
	}

	/**
	 * @return the lang
	 */
	public long getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(long lang) {
		this.lang = lang;
	}

	/**
	 * @return the listeningPort
	 */
	public long getListeningPort() {
		return listeningPort;
	}

	/**
	 * @param listeningPort the listeningPort to set
	 */
	public void setListeningPort(long listeningPort) {
		this.listeningPort = listeningPort;
	}

	/**
	 * @return the logSize
	 */
	public String getLogSize() {
		return logSize;
	}

	/**
	 * @param logSize the logSize to set
	 */
	public void setLogSize(String logSize) {
		this.logSize = logSize;
	}

	/**
	 * @return the maxFileSizeDiffForPathching
	 */
	public String getMaxFileSizeDiffForPathching() {
		return maxFileSizeDiffForPathching;
	}

	/**
	 * @param maxFileSizeDiffForPathching the maxFileSizeDiffForPathching to set
	 */
	public void setMaxFileSizeDiffForPathching(String maxFileSizeDiffForPathching) {
		this.maxFileSizeDiffForPathching = maxFileSizeDiffForPathching;
	}

	/**
	 * @return the maxFileSizeForVersioning
	 */
	public String getMaxFileSizeForVersioning() {
		return maxFileSizeForVersioning;
	}

	/**
	 * @param maxFileSizeForVersioning the maxFileSizeForVersioning to set
	 */
	public void setMaxFileSizeForVersioning(String maxFileSizeForVersioning) {
		this.maxFileSizeForVersioning = maxFileSizeForVersioning;
	}

	/**
	 * @return the rateLimitLocalPeers
	 */
	public boolean isRateLimitLocalPeers() {
		return rateLimitLocalPeers;
	}

	/**
	 * @param rateLimitLocalPeers the rateLimitLocalPeers to set
	 */
	public void setRateLimitLocalPeers(boolean rateLimitLocalPeers) {
		this.rateLimitLocalPeers = rateLimitLocalPeers;
	}

	/**
	 * @return the recvBufSize
	 */
	public String getRecvBufSize() {
		return recvBufSize;
	}

	/**
	 * @param recvBufSize the recvBufSize to set
	 */
	public void setRecvBufSize(String recvBufSize) {
		this.recvBufSize = recvBufSize;
	}

	/**
	 * @return the sendBufSize
	 */
	public String getSendBufSize() {
		return sendBufSize;
	}

	/**
	 * @param sendBufSize the sendBufSize to set
	 */
	public void setSendBufSize(String sendBufSize) {
		this.sendBufSize = sendBufSize;
	}

	/**
	 * @return the syncMaxTimeDiff
	 */
	public String getSyncMaxTimeDiff() {
		return syncMaxTimeDiff;
	}

	/**
	 * @param syncMaxTimeDiff the syncMaxTimeDiff to set
	 */
	public void setSyncMaxTimeDiff(String syncMaxTimeDiff) {
		this.syncMaxTimeDiff = syncMaxTimeDiff;
	}

	/**
	 * @return the syncTrashTtl
	 */
	public String getSyncTrashTtl() {
		return syncTrashTtl;
	}

	/**
	 * @param syncTrashTtl the syncTrashTtl to set
	 */
	public void setSyncTrashTtl(String syncTrashTtl) {
		this.syncTrashTtl = syncTrashTtl;
	}

	/**
	 * @return the uploadLimit
	 */
	public long getUploadLimit() {
		return uploadLimit;
	}

	/**
	 * @param uploadLimit the uploadLimit to set
	 */
	public void setUploadLimit(long uploadLimit) {
		this.uploadLimit = uploadLimit;
	}

	/**
	 * @return the useUpnp
	 */
	public boolean isUseUpnp() {
		return useUpnp;
	}

	/**
	 * @param useUpnp the useUpnp to set
	 */
	public void setUseUpnp(boolean useUpnp) {
		this.useUpnp = useUpnp;
	}
	
	public Map<String, Object> buildPrefMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("device_name", this.deviceName);
		map.put("disk_low_priority", this.diskLowPriority);
		map.put("download_limit", this.downloadLimit);
		map.put("external_port", this.externalPort);
		map.put("folder_rescan_interval", this.folderRescanInterval);
		map.put("lan_encrypt_data", this.lanEncryptData);
		map.put("lang", this.lang);
		map.put("listening_port", this.listeningPort);
		map.put("log_size", this.logSize);
		map.put("max_file_size_diff_for_patching", this.maxFileSizeDiffForPathching);
		map.put("max_file_size_for_versioning", this.maxFileSizeForVersioning);
		map.put("rate_limit_local_peers", this.rateLimitLocalPeers);
		map.put("recv_buf_size", this.recvBufSize);
		map.put("send_buf_size", this.sendBufSize);
		map.put("sync_max_time_diff", this.syncMaxTimeDiff);
		map.put("sync_trash_ttl", this.syncTrashTtl);
		map.put("upload_limit", this.uploadLimit);
		map.put("use_upnp", this.useUpnp);
		return map;
	}
	
	public String buildPrefMapString() {
		return "device_name=" + this.deviceName +
				"&disk_low_priority=" + this.diskLowPriority +
				"&download_limit=" + this.downloadLimit +
				"&external_port=" + this.externalPort +
				"&folder_rescan_interval=" + this.folderRescanInterval +
				"&lan_encrypt_data=" + this.lanEncryptData +
				"&lang=" + this.lang +
				"&listening_port=" + this.listeningPort +
				"&log_size=" + this.logSize +
				"&max_file_size_diff_for_patching=" + this.maxFileSizeDiffForPathching +
				"&max_file_size_for_versioning=" + this.maxFileSizeForVersioning +
				"&rate_limit_local_peers=" + this.rateLimitLocalPeers +
				"&recv_buf_size=" + this.recvBufSize +
				"&send_buf_size=" + this.sendBufSize +
				"&sync_max_time_diff=" + this.syncMaxTimeDiff +
				"&sync_trash_ttl=" + this.syncTrashTtl +
				"&upload_limit=" + this.uploadLimit +
				"&use_upnp=" + this.useUpnp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceName == null) ? 0 : deviceName.hashCode());
		result = prime * result
				+ ((externalPort == null) ? 0 : externalPort.hashCode());
		result = prime * result
				+ (int) (listeningPort ^ (listeningPort >>> 32));
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
		BTSyncPreferences other = (BTSyncPreferences) obj;
		if (deviceName == null) {
			if (other.deviceName != null)
				return false;
		} else if (!deviceName.equals(other.deviceName))
			return false;
		if (externalPort == null) {
			if (other.externalPort != null)
				return false;
		} else if (!externalPort.equals(other.externalPort))
			return false;
		if (listeningPort != other.listeningPort)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BTSyncPreferences [deviceName=" + deviceName
				+ ", diskLowPriority=" + diskLowPriority + ", downloadLimit="
				+ downloadLimit + ", externalPort=" + externalPort
				+ ", folderRescanInterval=" + folderRescanInterval
				+ ", lanEncryptData=" + lanEncryptData + ", lang=" + lang
				+ ", listeningPort=" + listeningPort + ", logSize=" + logSize
				+ ", maxFileSizeDiffForPathching="
				+ maxFileSizeDiffForPathching + ", maxFileSizeForVersioning="
				+ maxFileSizeForVersioning + ", rateLimitLocalPeers="
				+ rateLimitLocalPeers + ", recvBufSize=" + recvBufSize
				+ ", sendBufSize=" + sendBufSize + ", syncMaxTimeDiff="
				+ syncMaxTimeDiff + ", syncTrashTtl=" + syncTrashTtl
				+ ", uploadLimit=" + uploadLimit + ", useUpnp=" + useUpnp + "]";
	}
}
