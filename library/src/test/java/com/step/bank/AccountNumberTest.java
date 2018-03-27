package com.step.bank;

import org.junit.Test;

public class AccountNumberTest {

  @Test(expected = InvalidAccountNumberException.class)
  public void checkAccountNumberValidityWhenOnlyNumbers() throws InvalidAccountNumberException {
    new AccountNumber("12343456");
  }

  @Test(expected = InvalidAccountNumberException.class)
  public void checkAccountNumberValidityWhenExtraNumbers() throws InvalidAccountNumberException {
    new AccountNumber("12345-1234");
  }

  @Test(expected = InvalidAccountNumberException.class)
  public void checkAccountNumberValidityWhenNumbersAreLessThanFourDigits() throws InvalidAccountNumberException {
    new AccountNumber("12-12");
  }

  @Test(expected = InvalidAccountNumberException.class)
  public void checkAccountNumberValidityWhenLetters() throws InvalidAccountNumberException {
    new AccountNumber("abcd-abcd");
  }
}

