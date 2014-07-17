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
 * JavaBean implementation of BitTorrent Sync's current
 * upload and download speeds.
 * 
 * <p>An example of the speeds:
 * <pre>
 * {@code
 * {
 *      "download": 61007,
 *      "upload": 0
 * }
 * }
 * </pre>
 * 
 * @author Omeed
 *
 */
public class Speed {
	private long download;
	private long upload;
	
	public Speed() {
		
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
		result = prime * result + (int) (download ^ (download >>> 32));
		result = prime * result + (int) (upload ^ (upload >>> 32));
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
		Speed other = (Speed) obj;
		if (download != other.download)
			return false;
		if (upload != other.upload)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Speed [download=" + download + ", upload=" + upload + "]";
	}
	
	
}
