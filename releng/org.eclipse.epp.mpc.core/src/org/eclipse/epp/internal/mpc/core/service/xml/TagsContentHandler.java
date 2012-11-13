/*******************************************************************************
 * Copyright (c) 2011 Tasktop Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Tasktop Technologies - initial API and implementation
 *******************************************************************************/
package org.eclipse.epp.internal.mpc.core.service.xml;

import org.eclipse.epp.internal.mpc.core.service.Tags;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Benjamin Muskalla
 */
public class TagsContentHandler extends UnmarshalContentHandler {

	private Tags model;

	@Override
	public void startElement(String uri, String localName, Attributes attributes) {
		if (localName.equals("tags")) { //$NON-NLS-1$
			model = new Tags();

		} else if (localName.equals("tag")) { //$NON-NLS-1$
			TagContentHandler childHandler = new TagContentHandler();
			childHandler.setParentModel(model);
			childHandler.setParentHandler(this);
			childHandler.setUnmarshaller(getUnmarshaller());
			getUnmarshaller().setCurrentHandler(childHandler);
			childHandler.startElement(uri, localName, attributes);
		}
	}

	@Override
	public boolean endElement(String uri, String localName) throws SAXException {
		if (localName.equals("tags")) { //$NON-NLS-1$
			if (parentModel instanceof org.eclipse.epp.internal.mpc.core.service.Node) {
				((org.eclipse.epp.internal.mpc.core.service.Node) parentModel).setTags(model);
			}
			getUnmarshaller().setModel(model);
			model = null;
			getUnmarshaller().setCurrentHandler(parentHandler);
			if (parentHandler != null) {
				parentHandler.endElement(uri, localName);
			}
			return true;
		} else if (localName.equals("tag")) { //$NON-NLS-1$
			// nothing to do
		}
		return false;
	}

}
