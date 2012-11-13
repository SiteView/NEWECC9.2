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
package org.eclipse.epp.internal.mpc.ui.actions;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.epp.mpc.ui.MarketplaceClient;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * @author David Green
 */
public class WelcomeIntroAction implements IViewActionDelegate {

	public void run(IAction action) {
		try {
			MarketplaceClient.openMarketplaceWizard(null);
		} catch (ExecutionException e) {
			String message = String.format(Messages.WelcomeIntroAction_cannotOpenWizard);
			IStatus status = new Status(IStatus.ERROR, org.eclipse.epp.internal.mpc.ui.MarketplaceClientUi.BUNDLE_ID,
					IStatus.ERROR, message, e);
			StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.BLOCK | StatusManager.LOG);
		}
	}

	public void selectionChanged(IAction action, ISelection selection) {
		// nothing to do
	}

	public void init(IViewPart view) {
		// nothing to do.
	}

}