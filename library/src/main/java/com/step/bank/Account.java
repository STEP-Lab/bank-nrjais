package com.step.bank;

public class Account {
  private final String accountNumber;
  private double balance;
  private static final float MINIMUM_BALANCE = 1000;

  public Account(String accountNumber, double balance) throws MinimumBalanceException {
    this.accountNumber = accountNumber;
    checkMinimumBalance(balance);
    this.balance = balance;
  }

  private static void checkMinimumBalance(double balance) throws MinimumBalanceException {
    if(balance < MINIMUM_BALANCE){
      throw new MinimumBalanceException();
    }
  }

  public double getBalance() {
    return balance;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public double withdraw(float amount) throws MinimumBalanceException {
    double balance = this.balance - amount;
    checkMinimumBalance(balance);
    this.balance = balance;
    return this.getBalance();
  }
}
