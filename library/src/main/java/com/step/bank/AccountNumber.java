package com.step.bank;

public class AccountNumber {

  private String accountNumber;

  public AccountNumber(String accountNumber) throws InvalidAccountNumberException {
    if(!accountNumber.matches("^\\d{4}-\\d{4}$")){
      throw new InvalidAccountNumberException();
    }
    this.accountNumber = accountNumber;
  }
}
