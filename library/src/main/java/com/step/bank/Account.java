package com.step.bank;

public class Account {
  private final String accNumber;
  private int balance;

  public Account(String accNumber, int balance) throws MinimumBalanceException {
    this.accNumber = accNumber;
    if(balance < 1000){
      throw new MinimumBalanceException();
    }
    this.balance = balance;
  }

  public int getBalance() {
    return balance;
  }

  public String getAccountNumber() {
    return accNumber;
  }
}
