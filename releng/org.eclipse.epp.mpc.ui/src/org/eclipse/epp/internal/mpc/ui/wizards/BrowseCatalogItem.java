/*******************************************************************************
 * Copyright (c) 2010 The Eclipse Foundation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * 	The Eclipse Foundation - initial API and implementation
 *******************************************************************************/
package org.eclipse.epp.internal.mpc.ui.wizards;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.epp.internal.mpc.ui.MarketplaceClientUi;
import org.eclipse.epp.internal.mpc.ui.catalog.MarketplaceCategory;
import org.eclipse.epp.internal.mpc.ui.wizards.MarketplaceViewer.ContentType;
import org.eclipse.epp.mpc.ui.CatalogDescriptor;
import org.eclipse.equinox.internal.p2.ui.discovery.wizards.AbstractDiscoveryItem;
import org.eclipse.equinox.internal.p2.ui.discovery.wizards.DiscoveryResources;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * @author David Green
 */
@SuppressWarnings("unused")
public class BrowseCatalogItem extends AbstractDiscoveryItem<CatalogDescriptor> {

	private static final String TID = "tid:"; //$NON-NLS-1$

	private static final String UTF_8 = "UTF-8"; //$NON-NLS-1$

	private final MarketplaceViewer viewer;

	private final DiscoveryResources resources;

	private final IShellProvider shellProvider;

	private final MarketplaceCategory category;

	private final IMarketplaceWebBrowser browser;

	public BrowseCatalogItem(Composite parent, DiscoveryResources resources, IShellProvider shellProvider,
			IMarketplaceWebBrowser browser, MarketplaceCategory category, CatalogDescriptor element,
			MarketplaceViewer viewer) {
		super(parent, SWT.NULL, resources, element);
		this.resources = resources;
		this.shellProvider = shellProvider;
		this.browser = browser;
		this.category = category;
		this.viewer = viewer;
		createContent();
	}

	private void createContent() {
		Composite parent = this;

		GridLayoutFactory.swtDefaults().applyTo(parent);

		Link link = new Link(parent, SWT.NULL);
		if (viewer.getQueryContentType() == ContentType.SEARCH) {
			link.setText(NLS.bind(Messages.BrowseCatalogItem_browseMoreLink, category.getMatchCount()));
		} else {
			link.setText(Messages.BrowseCatalogItem_browseMoreLinkNoCount);
		}
		link.setToolTipText(NLS.bind(Messages.BrowseCatalogItem_openUrlBrowser, getData().getUrl()));
		link.setBackground(null);
		link.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				openMarketplace();
			}
		});

		GridDataFactory.swtDefaults().grab(true, false).align(SWT.CENTER, SWT.CENTER).applyTo(link);
	}

	protected void openMarketplace() {
		CatalogDescriptor catalogDescriptor = getData();

		try {
			URL url = catalogDescriptor.getUrl();
			try {
				ContentType contentType = viewer.getQueryContentType();
				if (contentType == ContentType.SEARCH) {
					String path = null;
					String queryText = viewer.getQueryText();
					if (queryText != null && queryText.trim().length() > 0) {
						// append something like this:
						// /search/apachesolr_search/mylyn%20wikitext?filters=tid:38%20tid:31
						path = "search/apachesolr_search/" + URLEncoder.encode(queryText.trim(), UTF_8); //$NON-NLS-1$
						String filter = ""; //$NON-NLS-1$
						if (viewer.getQueryMarket() != null) {
							filter += TID;
							filter += URLEncoder.encode(viewer.getQueryMarket().getId(), UTF_8);
						}
						if (viewer.getQueryCategory() != null) {
							if (filter.length() > 0) {
								filter += ' ';
							}
							filter += TID;
							filter += URLEncoder.encode(viewer.getQueryCategory().getId(), UTF_8);
						}
						if (filter.length() > 0) {
							path += "?filters=" + URLEncoder.encode(filter, UTF_8); //$NON-NLS-1$
						}
					} else if (viewer.getQueryCategory() != null) {
						// http://marketplace.eclipse.org/taxonomy/term/8
						path = "taxonomy/term/" + URLEncoder.encode(viewer.getQueryCategory().getId(), UTF_8); //$NON-NLS-1$
					} else if (viewer.getQueryMarket() != null) {
						path = "taxonomy/term/" + URLEncoder.encode(viewer.getQueryMarket().getId(), UTF_8); //$NON-NLS-1$
					}
					if (path != null) {
						url = new URL(url, path);
					}
				}
			} catch (UnsupportedEncodingException e) {
				// should never happen
				MarketplaceClientUi.error(e);
			} catch (MalformedURLException e) {
				// should never happen
				MarketplaceClientUi.error(e);
			}

			browser.openUrl(url.toURI().toString());
		} catch (URISyntaxException e) {
			String message = String.format(Messages.BrowseCatalogItem_cannotOpenBrowser);
			IStatus status = new Status(IStatus.ERROR, MarketplaceClientUi.BUNDLE_ID, IStatus.ERROR, message, e);
			StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.BLOCK | StatusManager.LOG);
		}
	}

	@Override
	protected void refresh() {
		// nothing to do
	}

}
