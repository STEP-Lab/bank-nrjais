package com.step.bank;

public class Account {
  private final String accountNumber;
  private float balance;
  private static final float MINIMUM_BALANCE = 1000;

  public Account(String accountNumber, float balance) throws MinimumBalanceException, InvalidAccountNumberException {
    if(!accountNumber.matches("^\\d{4}-\\d{4}$")){
      throw new InvalidAccountNumberException();
    }
    this.accountNumber = accountNumber;
    checkMinimumBalance(balance);
    this.balance = balance;
  }

  private static void checkMinimumBalance(float balance) throws MinimumBalanceException {
    if(balance < MINIMUM_BALANCE){
      throw new MinimumBalanceException();
    }
  }

  public float getBalance() {
    return balance;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public float withdraw(float amount) throws MinimumBalanceException {
    float balance = this.balance - amount;
    checkMinimumBalance(balance);
    this.balance = balance;
    return this.getBalance();
  }
}
