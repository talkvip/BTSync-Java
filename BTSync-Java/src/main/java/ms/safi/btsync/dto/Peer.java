/*
 * Copyright 2014 Omeed Safi
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package ms.safi.btsync.dto;

/**
 * JavaBean implementation of a folder peer's information.
 * 
 * <p>An example of a peer's information:
 * <pre>
 * {@code
 * {
 *      "id": "ARRdk5XANMb7RmQqEDfEZE-k5aI=",
 *      "connection": "direct", // direct or relay
 *      "name": "GT-I9500",
 *      "synced": 0, // timestamp when last sync completed
 *      "download": 0,
 *      "upload": 22455367417
 *  }
 *  }
 *  </pre>
 * 
 * @author Omeed Safi
 *
 */
public class Peer {
	private String id;
	private String connection;
	private String name;
	private long synced;
	private long download;
	private long upload;
	
	public Peer() {
		
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the connection
	 */
	public String getConnection() {
		return connection;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(String connection) {
		this.connection = connection;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the synced
	 */
	public long getSynced() {
		return synced;
	}

	/**
	 * @param synced the synced to set
	 */
	public void setSynced(long synced) {
		this.synced = synced;
	}

	/**
	 * @return the download
	 */
	public long getDownload() {
		return download;
	}

	/**
	 * @param download the download to set
	 */
	public void setDownload(long download) {
		this.download = download;
	}

	/**
	 * @return the upload
	 */
	public long getUpload() {
		return upload;
	}

	/**
	 * @param upload the upload to set
	 */
	public void setUpload(long upload) {
		this.upload = upload;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Peer other = (Peer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Peer [id=" + id + ", connection=" + connection + ", name="
				+ name + ", synced=" + synced + ", download=" + download
				+ ", upload=" + upload + "]";
	}
}
