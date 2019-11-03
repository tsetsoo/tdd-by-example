/**
 * Copyright (c) 2019 by SAP Labs Bulgaria, url: http://www.sap.com All rights reserved. This software is the
 * confidential and proprietary information
 * of SAP SE, Walldorf. You shall not disclose such Confidential Information and shall use it only in accordance with
 * the terms of the license
 * agreement you entered into with SAP. Created on Aug 28, 2019 by I333859
 */
package tsetso.home.tdd.by.example;

/**
 * @author i333859
 *
 */
public interface Expression {

	Expression plus(Expression tenFrancs);

	Money reduce(Bank bank, String to);

	Expression times(int multiplier);
}
