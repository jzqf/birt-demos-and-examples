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
import org.eclipse.birt.data.engine.core.DataException;
import org.eclipse.birt.data.engine.i18n.ResourceConstants;

class OptimisticSumAccumulator extends Accumulator {
	private boolean isFinished = false;

	private double sum = 0.0D;

	public OptimisticSumAccumulator() {
		super();
		sum = 0.0D;
	}

	public void start() throws DataException {
		super.start();
		sum = 0D;
		isFinished = false;
	}

	public void finish() throws DataException {
		isFinished = true;
	}

	public void onRow(Object[] args) throws DataException {
		assert (args.length > 0);

		int rndOrder;
		if (args.length == 1 || args[1] == null)
			rndOrder = 1;
		else {
			try {
				rndOrder = DataConverter.toDouble(args[1]).intValue();
			} catch (TotalPlusException e) {
				throw new DataException(ResourceConstants.DATATYPEUTIL_ERROR, e);
			}
		}

		if (args[0] != null) {
			try {
				double rawValue = DataConverter.toDouble(args[0]).doubleValue();
				sum += roundUp(rawValue, rndOrder);
			} catch (TotalPlusException e) {
				throw new DataException(ResourceConstants.DATATYPEUTIL_ERROR, e);
			}
		}
	}

	public Object getValue() throws DataException {
		if (!isFinished) {
			throw new RuntimeException(
					"Error! Call summary total function before finished the dataset");
		}
		return new Double(sum);
	}

	private double roundUp(double startValue, int rndOrder) {
		// if the value is less than the order
		// the value would go to zero. This forces round up
		if (startValue < rndOrder) {
			startValue = 1 * rndOrder;
		}

		int baseMod = new Double(startValue).intValue();
		return ((baseMod / rndOrder) + 1) * rndOrder;
	}
}

/*
 * 
 * 
 */
