package com.step.bank;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionTest {
  @Test
  public void mustCreateADebitTransaction() {
    Date date = new Date();
    DebitTransaction transaction = new DebitTransaction(date,1000.0, "Another Account");
    assertThat(transaction.getDate(),is(date));
  }
}
