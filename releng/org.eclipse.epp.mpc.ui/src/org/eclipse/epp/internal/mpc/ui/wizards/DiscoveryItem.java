/*******************************************************************************
 * Copyright (c) 2010 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/

package org.eclipse.epp.internal.mpc.ui.wizards;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.epp.internal.mpc.core.service.Node;
import org.eclipse.epp.internal.mpc.core.service.Tag;
import org.eclipse.epp.internal.mpc.core.service.Tags;
import org.eclipse.epp.internal.mpc.core.util.TextUtil;
import org.eclipse.epp.internal.mpc.ui.MarketplaceClientUiPlugin;
import org.eclipse.epp.internal.mpc.ui.util.Util;
import org.eclipse.equinox.internal.p2.discovery.AbstractCatalogSource;
import org.eclipse.equinox.internal.p2.discovery.model.CatalogItem;
import org.eclipse.equinox.internal.p2.discovery.model.Overview;
import org.eclipse.equinox.internal.p2.ui.discovery.util.WorkbenchUtil;
import org.eclipse.equinox.internal.p2.ui.discovery.wizards.AbstractDiscoveryItem;
import org.eclipse.equinox.internal.p2.ui.discovery.wizards.DiscoveryResources;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTError;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;

/**
 * @author Steffen Pingel
 * @author David Green
 */
@SuppressWarnings("unused")
public class DiscoveryItem<T extends CatalogItem> extends AbstractDiscoveryItem<T> implements PropertyChangeListener {

	private static final int MAX_IMAGE_HEIGHT = 40;

	private static final int MAX_IMAGE_WIDTH = 55;

	private Composite checkboxContainer;

	private final CatalogItem connector;

	private Label description;

	private Label iconLabel;

	private ToolItem infoButton;

	private Label nameLabel;

	private Link providerLabel;

	private final IShellProvider shellProvider;

	private ToolItem updateButton;

	private final MarketplaceViewer viewer;

	private ItemButtonController buttonController;

	private Link installInfoLink;

	private final IMarketplaceWebBrowser browser;

	private Link tagsLink;

	private static Boolean browserAvailable;

	public DiscoveryItem(Composite parent, int style, DiscoveryResources resources, IShellProvider shellProvider,
			IMarketplaceWebBrowser browser, final T connector, MarketplaceViewer viewer) {
		super(parent, style, resources, connector);
		this.shellProvider = shellProvider;
		this.browser = browser;
		this.connector = connector;
		this.viewer = viewer;
		connector.addPropertyChangeListener(this);
		this.addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				connector.removePropertyChangeListener(DiscoveryItem.this);
			}
		});
		createContent();
	}

	private void createContent() {
		GridLayout layout = new GridLayout(3, false);
		layout.marginLeft = 7;
		layout.marginTop = 2;
		layout.marginBottom = 2;
		setLayout(layout);

		checkboxContainer = new Composite(this, SWT.INHERIT_NONE);
		GridDataFactory.swtDefaults().align(SWT.CENTER, SWT.BEGINNING).span(1, 2).applyTo(checkboxContainer);
		GridLayoutFactory.fillDefaults().applyTo(checkboxContainer);

		iconLabel = new Label(checkboxContainer, SWT.NONE);
		GridDataFactory.swtDefaults()
		.align(SWT.CENTER, SWT.CENTER)
		.hint(MAX_IMAGE_WIDTH, MAX_IMAGE_HEIGHT)
		.minSize(MAX_IMAGE_WIDTH, MAX_IMAGE_HEIGHT)
		.applyTo(iconLabel);
		if (connector.getIcon() != null) {
			try {
				Image image = resources.getIconImage(connector.getSource(), connector.getIcon(), 32, false);
				Rectangle bounds = image.getBounds();
				if (bounds.width > MAX_IMAGE_WIDTH || bounds.height > MAX_IMAGE_HEIGHT) {
					final Image scaledImage = Util.scaleImage(image, MAX_IMAGE_WIDTH, MAX_IMAGE_HEIGHT);
					image = scaledImage;
					iconLabel.addDisposeListener(new DisposeListener() {
						public void widgetDisposed(DisposeEvent e) {
							scaledImage.dispose();
						}
					});
				}
				iconLabel.setImage(image);
			} catch (SWTException e) {
				// ignore, probably a bad image format
//				MarketplaceClientUi.error(NLS.bind(Messages.DiscoveryItem_cannotRenderImage_reason, connector.getIcon()
//						.getImage32(), e.getMessage()), e);
			}
		}
		if (iconLabel.getImage() == null) {
			iconLabel.setImage(MarketplaceClientUiPlugin.getInstance()
					.getImageRegistry()
					.get(MarketplaceClientUiPlugin.NO_ICON_PROVIDED));
		}

		Composite nameComposite = new Composite(this, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).align(SWT.FILL, SWT.CENTER).applyTo(nameComposite);

		nameComposite.setLayout(new GridLayout(2, false));

		nameLabel = new Label(nameComposite, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).align(SWT.BEGINNING, SWT.CENTER).applyTo(nameLabel);
		nameLabel.setFont(resources.getSmallHeaderFont());
		nameLabel.setText(TextUtil.escapeText(connector.getName()));

		ShareSolutionLink shareSolutionLink = new ShareSolutionLink(nameComposite, connector);
		GridDataFactory.fillDefaults().grab(true, false).align(SWT.END, SWT.CENTER).applyTo(shareSolutionLink);

		// bug 323257: don't display if there's no internal browser
		boolean internalBrowserAvailable = computeBrowserAvailable(this);
		if (internalBrowserAvailable && (hasTooltip(connector) || connector.isInstalled())) {
			ToolBar toolBar = new ToolBar(this, SWT.FLAT);
			GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).applyTo(toolBar);

			if (hasTooltip(connector)) {
				infoButton = new ToolItem(toolBar, SWT.PUSH);
				infoButton.setImage(resources.getInfoImage());
				infoButton.setToolTipText(Messages.DiscoveryItem_showOverview);
				hookTooltip(toolBar, infoButton, this, nameLabel, connector.getSource(), connector.getOverview(), null);
			}
		} else if (!internalBrowserAvailable && hasOverviewUrl(connector)) {
			ToolBar toolBar = new ToolBar(this, SWT.FLAT);
			GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER).applyTo(toolBar);
			infoButton = new ToolItem(toolBar, SWT.PUSH);
			infoButton.setImage(resources.getInfoImage());
			infoButton.setToolTipText(Messages.DiscoveryItem_showOverview);
			infoButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					WorkbenchUtil.openUrl(connector.getOverview().getUrl().trim(), IWorkbenchBrowserSupport.AS_EXTERNAL);
				}
			});
		} else {
			Label label = new Label(this, SWT.NULL);
			label.setText(" "); //$NON-NLS-1$
		}

		description = new Label(this, SWT.NULL | SWT.WRAP);
		GridDataFactory.fillDefaults().grab(true, false).span(2, 1).hint(100, SWT.DEFAULT).applyTo(description);
		String descriptionText = connector.getDescription();
		int maxDescriptionLength = 162;
		if (descriptionText == null) {
			descriptionText = ""; //$NON-NLS-1$
		} else {
			descriptionText = TextUtil.stripHtmlMarkup(descriptionText).trim();
		}
		if (descriptionText.length() > maxDescriptionLength) {
			int truncationIndex = maxDescriptionLength;
			for (int x = truncationIndex; x > 0; --x) {
				if (Character.isWhitespace(descriptionText.charAt(x))) {
					truncationIndex = x;
					break;
				}
			}
			descriptionText = descriptionText.substring(0, truncationIndex)
			+ Messages.DiscoveryItem_truncatedTextSuffix;
		}
		descriptionText = descriptionText.replaceAll("(\\r\\n)|\\n|\\r|\\s{2,}", " "); //$NON-NLS-1$ //$NON-NLS-2$
		description.setText(TextUtil.escapeText(descriptionText));

		new Label(this, SWT.NONE).setText(" "); // spacer //$NON-NLS-1$

		Composite composite = new Composite(this, SWT.NULL); // prevent the button from changing the layout of the title
		{
			GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(composite);

			createProviderLabel(composite);

			if (hasInstallMetadata()) {
				Button button = new Button(composite, SWT.INHERIT_NONE);
				Button secondaryButton = null;
				if (connector.isInstalled()) {
					secondaryButton = new Button(composite, SWT.INHERIT_NONE);
				}

				buttonController = new ItemButtonController(viewer, this, button, secondaryButton);

				GridDataFactory.swtDefaults().align(SWT.END, SWT.TOP).applyTo(button);
				if (secondaryButton != null) {
					GridDataFactory.swtDefaults().align(SWT.END, SWT.TOP).applyTo(secondaryButton);
				}
			} else {
				installInfoLink = new Link(composite, SWT.NULL);
				installInfoLink.setText(Messages.DiscoveryItem_installInstructions);
				installInfoLink.setToolTipText(Messages.DiscoveryItem_installInstructionsTooltip);
				installInfoLink.setBackground(null);
				installInfoLink.addListener(SWT.Selection, new Listener() {
					public void handleEvent(Event event) {
						browser.openUrl(((Node) connector.getData()).getUrl());
					}
				});
				GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).applyTo(installInfoLink);
			}
			createTagsLabel(composite);
			GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0).spacing(5, 0).applyTo(composite);
		}
	}

	private boolean hasOverviewUrl(CatalogItem connector) {
		return connector.getOverview() != null && connector.getOverview().getUrl() != null
		&& connector.getOverview().getUrl().trim().length() > 0;
	}

	private synchronized boolean computeBrowserAvailable(Composite composite) {
		if (browserAvailable == null) {
			// SWT Snippet148: detect if a browser is available by attempting to create one
			// SWTError is thrown if not available.
			try {
				Browser browser = new Browser(composite, SWT.NULL);
				browser.dispose();
				browserAvailable = true;
			} catch (SWTError e) {
				browserAvailable = false;
			}
		}
		return browserAvailable;
	}

	private boolean hasInstallMetadata() {
		return !connector.getInstallableUnits().isEmpty() && connector.getSiteUrl() != null;
	}

	protected void createProviderLabel(Composite parent) {
		providerLabel = new Link(parent, SWT.RIGHT);
		GridDataFactory.fillDefaults()
		.span(1, 1)
		.align(SWT.BEGINNING, SWT.CENTER)
		.grab(true, false)
		.applyTo(providerLabel);
		// always disabled color to make it less prominent
		providerLabel.setForeground(resources.getColorDisabled());

		providerLabel.setText(NLS.bind(Messages.DiscoveryItem_byProviderLicense, connector.getProvider(),
				connector.getLicense()));
	}

	protected void createTagsLabel(Composite parent) {
		tagsLink = new Link(parent, SWT.NONE);
		GridDataFactory.fillDefaults().span(1, 1).align(SWT.BEGINNING, SWT.CENTER).grab(true, false).applyTo(tagsLink);

		StringBuffer tagsText = new StringBuffer();
		Tags tags = ((Node) connector.getData()).getTags();
		if (tags == null) {
			return;
		}
		for (Tag tag : tags.getTags()) {
			String tagName = tag.getName();
			tagsText.append("<a href=\""); //$NON-NLS-1$
			tagsText.append(tagName);
			tagsText.append("\">"); //$NON-NLS-1$
			tagsText.append(tagName.replace("&", "&&")); //$NON-NLS-1$//$NON-NLS-2$
			tagsText.append("</a>"); //$NON-NLS-1$
			tagsText.append(" "); //$NON-NLS-1$
		}
		tagsLink.setText(tagsText.toString());
		tagsLink.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				viewer.doQueryForTag(event.text);
			}
		});
	}

	protected boolean hasTooltip(final CatalogItem connector) {
		return connector.getOverview() != null && connector.getOverview().getSummary() != null
		&& connector.getOverview().getSummary().length() > 0;
	}

	protected boolean maybeModifySelection(Operation operation) {
		viewer.modifySelection(connector, operation);
		return true;
	}

	@Override
	public boolean isSelected() {
		return getData().isSelected();
	}

	public Operation getOperation() {
		return viewer.getSelectionModel().getOperation(getData());
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (!isDisposed()) {
			getDisplay().asyncExec(new Runnable() {
				public void run() {
					if (!isDisposed()) {
						refresh();
					}
				}
			});
		}
	}

	@Override
	protected void refresh() {
		Color foreground = getForeground();

		nameLabel.setForeground(foreground);
		description.setForeground(foreground);
		if (installInfoLink != null) {
			installInfoLink.setForeground(foreground);
		}
		if (buttonController != null) {
			buttonController.refresh();
		}
	}

	private void hookRecursively(Control control, Listener listener) {
		control.addListener(SWT.Dispose, listener);
		control.addListener(SWT.MouseHover, listener);
		control.addListener(SWT.MouseMove, listener);
		control.addListener(SWT.MouseExit, listener);
		control.addListener(SWT.MouseDown, listener);
		control.addListener(SWT.MouseWheel, listener);
		if (control instanceof Composite) {
			for (Control child : ((Composite) control).getChildren()) {
				hookRecursively(child, listener);
			}
		}
	}

	@Override
	protected void hookTooltip(final Control parent, final Widget tipActivator, final Control exitControl,
			final Control titleControl, AbstractCatalogSource source, Overview overview, Image image) {
		final OverviewToolTip toolTip = new OverviewToolTip(parent, browser, source, overview, image);
		Listener listener = new Listener() {
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.MouseHover:
					toolTip.show(titleControl);
					break;
				case SWT.Dispose:
				case SWT.MouseWheel:
					toolTip.hide();
					break;
				}

			}
		};
		tipActivator.addListener(SWT.Dispose, listener);
		tipActivator.addListener(SWT.MouseWheel, listener);
		if (image != null) {
			tipActivator.addListener(SWT.MouseHover, listener);
		}
		Listener selectionListener = new Listener() {
			public void handleEvent(Event event) {
				toolTip.show(titleControl);
			}
		};
		tipActivator.addListener(SWT.Selection, selectionListener);
		Listener exitListener = new Listener() {
			public void handleEvent(Event event) {
				switch (event.type) {
				case SWT.MouseWheel:
					toolTip.hide();
					break;
				case SWT.MouseExit:
					/*
					 * Check if the mouse exit happened because we move over the tooltip
					 */
					Rectangle containerBounds = exitControl.getBounds();
					Point displayLocation = exitControl.getParent().toDisplay(containerBounds.x, containerBounds.y);
					containerBounds.x = displayLocation.x;
					containerBounds.y = displayLocation.y;
					if (containerBounds.contains(Display.getCurrent().getCursorLocation())) {
						break;
					}
					toolTip.hide();
					break;
				}
			}
		};
		hookRecursively(exitControl, exitListener);
	}
}
