/*******************************************************************************
 * Copyright (c) 2010 The Eclipse Foundation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      The Eclipse Foundation - initial API and implementation
 *******************************************************************************/
package org.eclipse.epp.internal.mpc.core.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.epp.internal.mpc.core.MarketplaceClientCore;
import org.eclipse.osgi.util.NLS;

/**
 * @author David Green
 */
public class DefaultMarketplaceService extends RemoteMarketplaceService<Marketplace> implements MarketplaceService {

//	This provisional API will be identified by /api/p at the end of most urls.
//
//	/api/p - Returns Markets + Categories
//	/node/%/api/p OR /content/%/api/p - Returns a single listing's detail
//	/taxonomy/term/%/api/p - Returns a category listing of results
//	/featured/api/p - Returns a server-defined number of featured results.
//	/recent/api/p - Returns a server-defined number of recent updates
//	/favorites/top/api/p - Returns a server-defined number of top favorites
//	/popular/top/api/p - Returns a server-defined number of most active results
//
//	There is one exception to adding /api/p at the end and that is for search results.
//
//	/api/p/search/apachesolr_search/[query]?page=[]&filters=[] - Returns search result from the Solr Search DB.
//
//	Once we've locked down the provisional API it will likely be named api/1.

	public static final String DEFAULT_SERVICE_LOCATION = System.getProperty(MarketplaceService.class.getName()
			+ ".url", "http://marketplace.eclipse.org"); //$NON-NLS-1$//$NON-NLS-2$

	/**
	 * parameter identifying client
	 * 
	 * @see {@link #setRequestMetaParameters(Map)}
	 */
	public static final String META_PARAM_CLIENT = "client"; //$NON-NLS-1$

	/**
	 * parameter identifying windowing system as reported by {@link org.eclipse.core.runtime.Platform#getWS()}
	 * 
	 * @see {@link #setRequestMetaParameters(Map)}
	 */
	public static final String META_PARAM_WS = "ws"; //$NON-NLS-1$

	/**
	 * parameter identifying operating system as reported by {@link org.eclipse.core.runtime.Platform#getOS()}
	 * 
	 * @see {@link #setRequestMetaParameters(Map)}
	 */
	public static final String META_PARAM_OS = "os"; //$NON-NLS-1$

	/**
	 * parameter identifying the current local as reported by {@link org.eclipse.core.runtime.Platform#getNL()}
	 * 
	 * @see {@link #setRequestMetaParameters(Map)}
	 */
	public static final String META_PARAM_NL = "nl"; //$NON-NLS-1$

	/**
	 * parameter identifying Java version
	 * 
	 * @see {@link #setRequestMetaParameters(Map)}
	 */
	public static final String META_PARAM_JAVA_VERSION = "java.version"; //$NON-NLS-1$

	/**
	 * parameter identifying the Eclipse runtime version (the version of the org.eclipse.core.runtime bundle)
	 * 
	 * @see {@link #setRequestMetaParameters(Map)}
	 */
	public static final String META_PARAM_RUNTIME_VERSION = "runtime.version"; //$NON-NLS-1$

	/**
	 * parameter identifying the Eclipse product version
	 * 
	 * @see {@link #setRequestMetaParameters(Map)}
	 */
	public static final String META_PARAM_PRODUCT_VERSION = "product.version"; //$NON-NLS-1$

	/**
	 * parameter identifying the product id, as provided by <code>Platform.getProduct().getId()</code>
	 * 
	 * @see {@link #setRequestMetaParameters(Map)}
	 */
	public static final String META_PARAM_PRODUCT = "product"; //$NON-NLS-1$

	public DefaultMarketplaceService(URL baseUrl) {
		this.baseUrl = baseUrl;
	}

	public DefaultMarketplaceService() {
		try {
			baseUrl = new URL(DEFAULT_SERVICE_LOCATION);
		} catch (MalformedURLException e) {
			MarketplaceClientCore.error(e);
			baseUrl = null;
		}
	}

	public List<Market> listMarkets(IProgressMonitor monitor) throws CoreException {
		Marketplace marketplace = processRequest(API_URI_SUFFIX, monitor);
		return marketplace.getMarket();
	}

	public Market getMarket(Market market, IProgressMonitor monitor) throws CoreException {
		Marketplace marketplace = processRequest(market.getUrl(), API_URI_SUFFIX, monitor);
		if (marketplace.getMarket().isEmpty()) {
			throw new CoreException(createErrorStatus(Messages.DefaultMarketplaceService_marketNotFound, null));
		} else if (marketplace.getMarket().size() > 1) {
			throw new CoreException(createErrorStatus(Messages.DefaultMarketplaceService_unexpectedResponse, null));
		}
		return marketplace.getMarket().get(0);
	}

	public Category getCategory(Category category, IProgressMonitor monitor) throws CoreException {
		Marketplace marketplace = processRequest(category.getUrl(), API_URI_SUFFIX, monitor);
		if (marketplace.getCategory().isEmpty()) {
			throw new CoreException(createErrorStatus(Messages.DefaultMarketplaceService_categoryNotFound, null));
		} else if (marketplace.getCategory().size() > 1) {
			throw new CoreException(createErrorStatus(Messages.DefaultMarketplaceService_unexpectedResponse, null));
		}
		return marketplace.getCategory().get(0);
	}

	public Node getNode(Node node, IProgressMonitor monitor) throws CoreException {
		Marketplace marketplace;
		if (node.getId() != null) {
			// bug 304928: prefer the id method rather than the URL, since the id provides a stable URL and the
			// URL is based on the name, which could change.
			String encodedId;
			try {
				encodedId = URLEncoder.encode(node.getId(), UTF_8);
			} catch (UnsupportedEncodingException e) {
				// should never happen
				throw new IllegalStateException(e);
			}
			marketplace = processRequest("node/" + encodedId + '/' + API_URI_SUFFIX, monitor); //$NON-NLS-1$
		} else {
			marketplace = processRequest(node.getUrl(), API_URI_SUFFIX, monitor);
		}
		if (marketplace.getNode().isEmpty()) {
			throw new CoreException(createErrorStatus(Messages.DefaultMarketplaceService_nodeNotFound, null));
		} else if (marketplace.getNode().size() > 1) {
			throw new CoreException(createErrorStatus(Messages.DefaultMarketplaceService_unexpectedResponse, null));
		}
		return marketplace.getNode().get(0);
	}

	public SearchResult search(Market market, Category category, String queryText, IProgressMonitor monitor)
	throws CoreException {
		// per bug 302825 - http://www.eclipseplugincentral.net/api/v2/search/apachesolr_search/e?filters=tid:31%20tid:38
		SearchResult result = new SearchResult();
		if (queryText == null || queryText.trim().length() == 0) {
			// search with no text gives us HTTP 404
			result.setMatchCount(0);
			result.setNodes(new ArrayList<Node>());
		} else {
			String relativeUrl;
			try {
				relativeUrl = API_URI_SUFFIX + "/search/apachesolr_search/" //$NON-NLS-1$
				+ URLEncoder.encode(queryText.trim(), UTF_8);
				String queryString = ""; //$NON-NLS-1$
				if (market != null || category != null) {
					queryString += "filters="; //$NON-NLS-1$
					if (market != null) {
						queryString += "tid:" + URLEncoder.encode(market.getId(), UTF_8); //$NON-NLS-1$
						if (category != null) {
							queryString += "%20"; //$NON-NLS-1$
						}
					}
					if (category != null) {
						queryString += "tid:" + URLEncoder.encode(category.getId(), UTF_8); //$NON-NLS-1$
					}
				}
				if (queryString.length() > 0) {
					relativeUrl += '?' + queryString;
				}
			} catch (UnsupportedEncodingException e) {
				throw new IllegalStateException();
			}
			Marketplace marketplace = processRequest(relativeUrl, monitor);
			Search search = marketplace.getSearch();
			if (search == null) {
				throw new CoreException(createErrorStatus(Messages.DefaultMarketplaceService_unexpectedResponse, null));
			}
			result.setMatchCount(search.getCount());
			result.setNodes(search.getNode());
		}
		return result;
	}

	public SearchResult featured(IProgressMonitor monitor) throws CoreException {
		return featured(monitor, null, null);
	}

	public SearchResult featured(IProgressMonitor monitor, Market market, Category category) throws CoreException {
		String nodePart = ""; //$NON-NLS-1$
		try {
			if (market != null) {
				nodePart += URLEncoder.encode(market.getId(), UTF_8);
			}
			if (category != null) {
				if (nodePart.length() > 0) {
					nodePart += ","; //$NON-NLS-1$
				}
				nodePart += URLEncoder.encode(category.getId(), UTF_8);
			}
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException();
		}
		String uri = "featured/"; //$NON-NLS-1$
		if (nodePart.length() > 0) {
			uri += nodePart + '/';
		}
		Marketplace marketplace = processRequest(uri + API_URI_SUFFIX, monitor);
		return createSearchResult(marketplace.getFeatured());
	}

	public SearchResult recent(IProgressMonitor monitor) throws CoreException {
		Marketplace marketplace = processRequest("recent/" + API_URI_SUFFIX, monitor); //$NON-NLS-1$
		return createSearchResult(marketplace.getRecent());
	}

	public SearchResult favorites(IProgressMonitor monitor) throws CoreException {
		Marketplace marketplace = processRequest("favorites/top/" + API_URI_SUFFIX, monitor); //$NON-NLS-1$
		return createSearchResult(marketplace.getFavorites());
	}

	public SearchResult popular(IProgressMonitor monitor) throws CoreException {
		Marketplace marketplace = processRequest("popular/top/" + API_URI_SUFFIX, monitor); //$NON-NLS-1$
		return createSearchResult(marketplace.getPopular());
	}

	protected SearchResult createSearchResult(NodeListing nodeList) throws CoreException {
		if (nodeList == null) {
			throw new CoreException(createErrorStatus(Messages.DefaultMarketplaceService_unexpectedResponse, null));
		}
		SearchResult result = new SearchResult();
		result.setMatchCount(nodeList.getCount());
		result.setNodes(nodeList.getNode());
		return result;
	}

	public void reportInstallError(IProgressMonitor monitor, IStatus result, Set<Node> nodes,
			Set<String> iuIdsAndVersions, String resolutionDetails) throws CoreException {
		HttpClient client = new HttpClient();
		client.getParams().setParameter(HttpMethodParams.USER_AGENT, MarketplaceClientCore.BUNDLE_ID);

		URL location;
		PostMethod method;
		try {
			location = new URL(baseUrl, "install/error/report"); //$NON-NLS-1$
			method = new PostMethod(location.toURI().toString());
		} catch (URISyntaxException e) {
			throw new IllegalStateException(e);
		} catch (MalformedURLException e) {
			throw new IllegalStateException(e);
		}
		try {
			List<NameValuePair> parameters = new ArrayList<NameValuePair>();
			parameters.add(new NameValuePair("status", Integer.toString(result.getSeverity()))); //$NON-NLS-1$
			parameters.add(new NameValuePair("statusMessage", result.getMessage())); //$NON-NLS-1$
			for (Node node : nodes) {
				parameters.add(new NameValuePair("node", node.getId())); //$NON-NLS-1$
			}
			if (iuIdsAndVersions != null && !iuIdsAndVersions.isEmpty()) {
				for (String iuAndVersion : iuIdsAndVersions) {
					parameters.add(new NameValuePair("iu", iuAndVersion)); //$NON-NLS-1$
				}
			}
			parameters.add(new NameValuePair("detailedMessage", resolutionDetails)); //$NON-NLS-1$
			if (!parameters.isEmpty()) {
				method.setRequestBody(parameters.toArray(new NameValuePair[parameters.size()]));
				client.executeMethod(method);
			}
		} catch (IOException e) {
			String message = NLS.bind(Messages.DefaultMarketplaceService_cannotCompleteRequest_reason,
					location.toString(), e.getMessage());
			throw new CoreException(createErrorStatus(message, e));
		} finally {
			method.releaseConnection();
		}
	}

}
