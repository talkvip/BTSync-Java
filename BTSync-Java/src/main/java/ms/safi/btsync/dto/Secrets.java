package ms.safi.btsync.dto;

import javax.xml.bind.annotation.XmlElement;

/**
 * JavaBean implementation of a set of secrets.
 * These secrets are the read_only, read_write, and encryption (if available)
 * to use for sharing a folder.
 * 
 * <p>An example of a set of secrets:
 * <pre>
 * {@code
 * {
 *      "read_only": "ECK2S6MDDD7EOKKJZOQNOWDTJBEEUKGME",
 *      "read_write": "DPFABC4IZX33WBDRXRPPCVYA353WSC3Q6",
 *      "encryption": "G3PNU7KTYM63VNQZFPP3Q3GAMTPRWDEZA"
 * }
 * }
 * </pre>
 * 
 * @author Omeed Safi
 *
 */
public class Secrets {
	@XmlElement(name = "read_only")
	private String readOnly;
	@XmlElement(name = "read_write")
	private String readWrite;
	private String encryption;
	
	public Secrets() {
		
	}

	/**
	 * @return the readOnly
	 */
	public String getReadOnly() {
		return readOnly;
	}

	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(String readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * @return the readWrite
	 */
	public String getReadWrite() {
		return readWrite;
	}

	/**
	 * @param readWrite the readWrite to set
	 */
	public void setReadWrite(String readWrite) {
		this.readWrite = readWrite;
	}

	/**
	 * @return the encryption
	 */
	public String getEncryption() {
		return encryption;
	}

	/**
	 * @param encryption the encryption to set
	 */
	public void setEncryption(String encryption) {
		this.encryption = encryption;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((encryption == null) ? 0 : encryption.hashCode());
		result = prime * result
				+ ((readOnly == null) ? 0 : readOnly.hashCode());
		result = prime * result
				+ ((readWrite == null) ? 0 : readWrite.hashCode());
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
		Secrets other = (Secrets) obj;
		if (encryption == null) {
			if (other.encryption != null)
				return false;
		} else if (!encryption.equals(other.encryption))
			return false;
		if (readOnly == null) {
			if (other.readOnly != null)
				return false;
		} else if (!readOnly.equals(other.readOnly))
			return false;
		if (readWrite == null) {
			if (other.readWrite != null)
				return false;
		} else if (!readWrite.equals(other.readWrite))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Secrets [readOnly=" + readOnly + ", readWrite=" + readWrite
				+ ", encryption=" + encryption + "]";
	}
	
	
}
