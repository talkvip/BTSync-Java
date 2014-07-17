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
