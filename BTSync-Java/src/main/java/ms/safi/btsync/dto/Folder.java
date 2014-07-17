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
 * JavaBean implementation of a folder's information from BitTorrent Sync.
 * 
 * <p>An example of a folder's information:
 * <pre>
 * {@code
 * {
 *      "dir": "\\\\?\\D:\\share",
 *      "secret": "A54HDDMPN4T4BTBT7SPBWXDB7JVYZ2K6D",
 *      "size": 23762511569,
 *      "type": "read_write",
 *      "files": 3206,
 *      "error": 0,
 *      "indexing": 0
 * }}
 * </pre>
 * 
 * @author Omeed Safi
 *
 */
public class Folder {
	
	private String dir;
	private String secret;
	private long size;
	private String type;
	private long files;
	private int error;
	private int indexing;
	
	public Folder() {
		
	}

	/**
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * @param dir the dir to set
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}

	/**
	 * @return the secret
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * @param secret the secret to set
	 */
	public void setSecret(String secret) {
		this.secret = secret;
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
	 * @return the files
	 */
	public long getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(long files) {
		this.files = files;
	}

	/**
	 * @return the error
	 */
	public int getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(int error) {
		this.error = error;
	}

	/**
	 * @return the indexing
	 */
	public int getIndexing() {
		return indexing;
	}

	/**
	 * @param indexing the indexing to set
	 */
	public void setIndexing(int indexing) {
		this.indexing = indexing;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((secret == null) ? 0 : secret.hashCode());
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
		Folder other = (Folder) obj;
		if (secret == null) {
			if (other.secret != null)
				return false;
		} else if (!secret.equals(other.secret))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Folder [dir=" + dir + ", secret=" + secret + ", size=" + size
				+ ", type=" + type + ", files=" + files + ", error=" + error
				+ ", indexing=" + indexing + "]";
	}
}
