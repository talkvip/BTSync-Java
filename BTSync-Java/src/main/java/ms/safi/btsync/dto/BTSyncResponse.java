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
 * JavaBean implementation of a generic response from BitTorrent Sync. This response
 * contains an error code and message.
 * 
 * @author Omeed Safi
 *
 */
public class BTSyncResponse {
	
	private int error;
	private String message;
	
	public BTSyncResponse() {
		
	}

	/**
	 * Returns the error code. Error code 0 means no error and the
	 * message will be empty.
	 * 
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Returns <code>true</code> if this is actually an error.
	 * This is equivalent to getError() == 0
	 * 
	 * @return <code>true</code> if error; <code>false</code> otherwise
	 */
	public boolean isSuccess() {
		return error == 0;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + error;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
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
		BTSyncResponse other = (BTSyncResponse) obj;
		if (error != other.error)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BTSyncResponse [error=" + error + ", message=" + message + "]";
	}
}
