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

import org.eclipse.birt.data.engine.api.aggregation.Accumulator;
import org.eclipse.birt.data.engine.api.aggregation.Aggregation;

/**
 * Implements a custom Optimistic Sum Aggregation Function
 * 
 */
public class OptimisticSum extends Aggregation {

	public String getName() {
		return "OPTIMISTICSUM";
	}

	public int getType() {
		return SUMMARY_AGGR;
	}

	public boolean[] getParameterDefn() {
		return new boolean[] { true, true };
	}

	public Accumulator newAccumulator() {
		return new OptimisticSumAccumulator();
	}

}
