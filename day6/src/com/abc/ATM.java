package com.abc;

public class ATM {
	int balance;
	
	public ATM(int balance) {
		this.balance = balance;
	}
	
	public int withdraw(int amount) {
		if (amount <= balance) {
			balance = balance - amount;
			return balance;
		}
		else {
			return balance;
		}
	}
}
