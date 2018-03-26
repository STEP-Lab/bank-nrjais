package com.step.bank;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

  private Account account;

  @Before
  public void setUp() throws MinimumBalanceException {
    account = new Account("1234-1234", 1000);
  }

  @Test
  public void checkBalance(){
    assertThat(account.getBalance(), is( 1000.0));
  }

  @Test(expected = MinimumBalanceException.class)
  public void checkMinimumBalance() throws MinimumBalanceException {
    new Account("1322-4234",200);
  }

  @Test
  public void checkAccountNumber() {
    assertThat(account.getAccountNumber(), is("1234-1234"));
  }

  @Test
  public void withdraw() throws MinimumBalanceException {
    Account account = new Account("1234-1264", 2000);
    assertThat(account.withdraw(200), is(1800.0));
    assertThat(account.getBalance(), is(1800.0));
  }

  @Test(expected = MinimumBalanceException.class)
  public void withdrawMinimumBalance() throws MinimumBalanceException {
    new Account("1234-1264", 1000).withdraw(200);
  }
}
