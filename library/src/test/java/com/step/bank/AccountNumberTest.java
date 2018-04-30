package com.step.bank;


import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class AccountNumberTest {
  @Test
  public void checkAccountNumberValidityWhenOnlyNumbers() {
    Throwable exception = catchThrowable(() -> new AccountNumber("12343456"));
    assertThat(exception).isInstanceOf(InvalidAccountNumberException.class);
  }

  @Test
  public void checkAccountNumberValidityWhenExtraNumbers() {
    Throwable exception = catchThrowable(() -> new AccountNumber("12345-1234"));
    assertThat(exception).isInstanceOf(InvalidAccountNumberException.class);
  }

  @Test
  public void checkAccountNumberValidityWhenNumbersAreLessThanFourDigits() {
    Throwable exception = catchThrowable(() -> new AccountNumber("123-12"));
    assertThat(exception).isInstanceOf(InvalidAccountNumberException.class);
  }

  @Test
  public void checkAccountNumberValidityWhenLetters() {
    Throwable exception = catchThrowable(() -> new AccountNumber("abcd-abcd"));
    assertThat(exception).isInstanceOf(InvalidAccountNumberException.class);
  }
}

