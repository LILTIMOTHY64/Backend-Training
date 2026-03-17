package com.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.abc.ATM;


class ATMTest {

	@Test
	public void testWithdraw() {
		ATM atm = new ATM(5000);
		int remainingBalance=atm.withdraw(2000);
		assertEquals(3000, remainingBalance);
	}
	
	@Test
	public void testWithdrawMoreThanBalance() {
		ATM atm = new ATM(5000);
		int remainingBalance=atm.withdraw(7000);
		assertEquals(5000, remainingBalance);
	}

}
