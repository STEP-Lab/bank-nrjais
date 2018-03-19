package com.step.bank;

public class Account {
  private final String accNumber;
  private float balance;

  public Account(String accNumber, float balance) throws MinimumBalanceException {
    this.accNumber = accNumber;
    if(balance < 1000){
      throw new MinimumBalanceException();
    }
    this.balance = balance;
  }

  public float getBalance() {
    return balance;
  }

  public String getAccountNumber() {
    return accNumber;
  }
}
