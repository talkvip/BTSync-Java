package ms.safi.btsync.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MediaType;

/**
 * For some reason BitTorrent Sync sends its JSON responses using the text/javascript
 * content type. This Response filter is used to change to content type to application/json
 * so RESTEasy can automatically unmarshal it.
 * 
 * @author Omeed Safi
 *
 */
public class JsonContentTypeResponseFilter implements ClientResponseFilter {

	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
		List<String> contentType = new ArrayList<String>(1);
		contentType.add(MediaType.APPLICATION_JSON);
		responseContext.getHeaders().put("Content-Type", contentType);
	}
}
