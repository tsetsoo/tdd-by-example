/**
 * Copyright (c) 2019 by SAP Labs Bulgaria, url: http://www.sap.com All rights reserved. This software is the
 * confidential and proprietary information
 * of SAP SE, Walldorf. You shall not disclose such Confidential Information and shall use it only in accordance with
 * the terms of the license
 * agreement you entered into with SAP. Created on Nov 3, 2019 by I333859
 */
package tsetso.home.tdd.by.example;

/**
 * @author i333859
 *
 */
public class Pair {
	private final String from;
	private final String to;

	public Pair(String from, String to) {
		super();
		this.from = from;
		this.to = to;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Pair other = (Pair) obj;
		return from.equals(other.from) && to.equals(other.to);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (from == null ? 0 : from.hashCode());
		result = prime * result + (to == null ? 0 : to.hashCode());
		return result;
	}
}
