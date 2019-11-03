/**
 * Copyright (c) 2019 by SAP Labs Bulgaria, url: http://www.sap.com All rights reserved. This software is the
 * confidential and proprietary information
 * of SAP SE, Walldorf. You shall not disclose such Confidential Information and shall use it only in accordance with
 * the terms of the license
 * agreement you entered into with SAP. Created on Aug 28, 2019 by I333859
 */
package tsetso.home.tdd.by.example;

public class Sum implements Expression {

	public Expression augend;
	public Expression addend;

	public Sum(Expression augend, Expression addend) {
		this.augend = augend;
		this.addend = addend;
	}

	@Override
	public Expression plus(Expression tenFrancs) {
		return new Sum(this, addend);
	}

	@Override
	public Money reduce(Bank bank, String to) {
		int amount = augend.reduce(bank, to).amount + addend.reduce(bank, to).amount;
		return new Money(amount, to);
	}

	@Override
	public Expression times(int multiplier) {
		return new Sum(augend.times(multiplier), addend.times(multiplier));
	}

}
