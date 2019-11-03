/**
 * Copyright (c) 2019 by SAP Labs Bulgaria, url: http://www.sap.com All rights reserved. This software is the
 * confidential and proprietary information
 * of SAP SE, Walldorf. You shall not disclose such Confidential Information and shall use it only in accordance with
 * the terms of the license
 * agreement you entered into with SAP. Created on Aug 28, 2019 by I333859
 */
package tsetso.home.tdd.by.example;

import java.util.HashMap;

public class Bank {
	private final HashMap<Pair, Integer> rates = new HashMap<>();

	public void addRate(String from, String to, int rate) {
		rates.put(new Pair(from, to), rate);
	}

	int rate(String from, String to) {
		if (from.equals(to)) {
			return 1;
		}
		return rates.get(new Pair(from, to));
	}

	public Money reduce(Expression source, String to) {
		return source.reduce(this, to);
	}
}
