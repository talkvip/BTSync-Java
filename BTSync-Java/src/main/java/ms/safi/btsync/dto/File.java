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

import javax.xml.bind.annotation.XmlElement;

/**
 * JavaBean implementation of a file's information from BitTorrent Sync.
 * 
 * <p>An example of a file's information:
 * <pre>
 * {@code
 * {
 *      "have_pieces": 1,
 *      "name": "index.html",
 *      "size": 2726,
 *      "state": "created",
 *      "total_pieces": 1,
 *      "type": "file",
 *      "download": 1 // only for selective sync folders
 * }}
 * </pre>
 * 
 * @author Omeed Safi
 *
 */
public class File {
	private String name;
	private String state;
	private String type;
	@XmlElement(name = "have_pieces")
	private long havePieces;
	private long size;
	@XmlElement(name = "total_pieces")
	private long totalPieces;
	private int download;
	
	public File() {
		
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
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the havePieces
	 */
	public long getHavePieces() {
		return havePieces;
	}

	/**
	 * @param havePieces the havePieces to set
	 */
	public void setHavePieces(long havePieces) {
		this.havePieces = havePieces;
	}

	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}

	/**
	 * @return the totalPieces
	 */
	public long getTotalPieces() {
		return totalPieces;
	}

	/**
	 * @param totalPieces the totalPieces to set
	 */
	public void setTotalPieces(long totalPieces) {
		this.totalPieces = totalPieces;
	}

	/**
	 * @return the download
	 */
	public int getDownload() {
		return download;
	}

	/**
	 * @param download the download to set
	 */
	public void setDownload(int download) {
		this.download = download;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (int) (size ^ (size >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		File other = (File) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (size != other.size)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "File [name=" + name + ", state=" + state + ", type=" + type
				+ ", havePieces=" + havePieces + ", size=" + size
				+ ", totalPieces=" + totalPieces + ", download=" + download
				+ "]";
	}
}
