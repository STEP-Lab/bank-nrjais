package com.step.bank;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

public class Account {
  private final AccountNumber accountNumber;
  private Money balance;
  private static final Money MINIMUM_BALANCE = Money.of(CurrencyUnit.of("INR"), 1000.0);
  private Transactions transactions;

  public Account(AccountNumber accountNumber, double balance) throws MinimumBalanceException {
    this.accountNumber = accountNumber;
    Money money = Money.of(CurrencyUnit.of("INR"), balance);
    checkMinimumBalance(money);
    this.balance = money;
    transactions = new Transactions();
  }

  private static void checkMinimumBalance(Money balance) throws MinimumBalanceException {
    if(balance.isLessThan(MINIMUM_BALANCE)){
      throw new MinimumBalanceException();
    }
  }

  public Money getBalance() {
    return balance;
  }

  public Money withdraw(double amount, String by) throws MinimumBalanceException {
    Money balance = this.balance.minus(amount);
    checkMinimumBalance(balance);
    this.balance = balance;
    transactions.debit(amount, by);
    return this.getBalance();
  }

  public Money credit(double amount, String to) {
    Money balance = this.balance.plus(amount);
    this.balance = balance;
    transactions.credit(amount, to);
    return this.getBalance();
  }
}
