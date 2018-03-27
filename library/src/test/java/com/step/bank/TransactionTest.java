package com.step.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionTest {

  private Date date;
  private DebitTransaction transaction;

  @Before
  public void setUp() throws Exception {
    date = new Date();
    transaction = new DebitTransaction(date,1000.0, "Another Account");
  }

  @Test
  public void mustCreateADebitTransaction() {
    assertThat(transaction.getDate(),is(date));
  }

  @Test
  public void mustCreateACreditTransaction() {
    assertThat(transaction.getDate(),is(date));
  }

  @Test
  public void mustReturnCSV() {
    assertThat(transaction.toCSV(),is("Another Account,1000.0,"+date.toString()));
  }
}
