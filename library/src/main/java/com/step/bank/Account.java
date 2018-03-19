package com.step.bank;

public class Account {
  private final String accNumber;
  private int balance;

  public Account(String accNumber, int balance) {
    this.accNumber = accNumber;
    this.balance = balance;
  }

  public int getBalance() {
    return balance;
  }

  public String getAccountNumber() {
    return accNumber;
  }
}
