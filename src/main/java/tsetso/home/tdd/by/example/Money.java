/**
 * Copyright (c) 2019 by SAP Labs Bulgaria, url: http://www.sap.com All rights reserved. This software is the
 * confidential and proprietary information
 * of SAP SE, Walldorf. You shall not disclose such Confidential Information and shall use it only in accordance with
 * the terms of the license
 * agreement you entered into with SAP. Created on Aug 19, 2019 by I333859
 */
package tsetso.home.tdd.by.example;

public class Money implements Expression {
	public static Money dollar(int amount) {
		return new Money(amount, "USD");
	}

	public static Money franc(int amount) {
		return new Money(amount, "CHF");
	}

	protected final int amount;
	protected final String currency;

	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}

	protected String currency() {
		return currency;
	};

	@Override
	public boolean equals(Object obj) {
		Money other = (Money) obj;
		return amount == other.amount && currency.equals(other.currency);
	}

	@Override
	public Expression plus(Expression addend) {
		return new Sum(this, addend);
	}

	@Override
	public Money reduce(Bank bank, String to) {
		int rate = bank.rate(currency, to);
		return new Money(amount / rate, to);
	}

	@Override
	public Expression times(int multiplier) {
		return new Money(amount * multiplier, currency);
	}

	@Override
	public String toString() {
		return amount + " " + currency;
	}
}
