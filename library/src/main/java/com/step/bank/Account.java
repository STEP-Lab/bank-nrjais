package com.step.bank;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Account {
  private final String accNumber;
  private float balance;

  public Account(String accNumber, float balance) throws MinimumBalanceException, InvalidAccountNumberException {
    Pattern regex = Pattern.compile("[\\d]{4}-[\\d]{4}");
    Matcher matcher = regex.matcher(accNumber);
    if(!matcher.matches()){
      throw new InvalidAccountNumberException();
    }
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

  public float withdraw(float amount) {
    this.balance -= amount;
    return this.getBalance();
  }
}
