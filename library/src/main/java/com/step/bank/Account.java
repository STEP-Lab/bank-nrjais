package com.step.bank;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class Account {
  private final AccountNumber accountNumber;
  private Money balance;
  private static final Money MINIMUM_BALANCE = Money.of(CurrencyUnit.of("INR"), 1000.0);

  public Account(AccountNumber accountNumber, double balance) throws MinimumBalanceException {
    this.accountNumber = accountNumber;
    Money money = Money.of(CurrencyUnit.of("INR"), balance);
    checkMinimumBalance(money);
    this.balance = money;
  }

  private static void checkMinimumBalance(Money balance) throws MinimumBalanceException {
    if(balance.isLessThan(MINIMUM_BALANCE)){
      throw new MinimumBalanceException();
    }
  }

  public Money getBalance() {
    return balance;
  }

  public Money withdraw(float amount) throws MinimumBalanceException {
    Money balance = this.balance.minus(amount);
    checkMinimumBalance(balance);
    this.balance = balance;
    return this.getBalance();
  }
}
