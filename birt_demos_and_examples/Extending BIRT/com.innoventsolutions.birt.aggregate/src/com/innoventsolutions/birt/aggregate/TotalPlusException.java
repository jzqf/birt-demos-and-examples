/***********************************************************************
 * Copyright (c) 2004 Innovent Solutions, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Innovent Solutions, Inc. - Scott Rosenbaum
 ***********************************************************************/
package com.innoventsolutions.birt.aggregate;

class TotalPlusException extends Exception {
	private String msg;
	private Throwable cause;
	
	public TotalPlusException(String description, Exception e) {
		msg = description;
		cause = e;
	}
	public TotalPlusException(String description) {
		msg = description;
	}

	public String getMessage() {
		return msg;
	}

	public Throwable getCause(){
		return cause;
	}
}
