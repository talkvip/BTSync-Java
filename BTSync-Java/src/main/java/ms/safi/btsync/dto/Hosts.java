package ms.safi.btsync.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * JavaBean implementation of a list of Hosts.
 * The hosts are in the format address:port.
 * 
 * <p>An example of a hosts listing:
 * <pre>
 * {@code
 * {
 *      "hosts" : ["192.168.1.1:4567",
 *      "example.com:8975"]
 * }}
 * </pre>
 * 
 * 
 * 
 * @author Omeed Safi
 *
 */
public class Hosts {
	List<String> hosts;
	
	public Hosts() {
		hosts = new ArrayList<String>();
	}

	/**
	 * @return the hosts
	 */
	public List<String> getHosts() {
		return hosts;
	}

	/**
	 * @param hosts the hosts to set
	 */
	public void setHosts(List<String> hosts) {
		this.hosts = hosts;
	}

	public void addHost(String host) {
		this.hosts.add(host);
	}
	
	public boolean removeHost(String host) {
		return this.hosts.remove(host);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hosts == null) ? 0 : hosts.hashCode());
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
		Hosts other = (Hosts) obj;
		if (hosts == null) {
			if (other.hosts != null)
				return false;
		} else if (!hosts.equals(other.hosts))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Hosts [hosts=" + hosts + "]";
	}
}
