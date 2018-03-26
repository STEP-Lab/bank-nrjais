package com.step.bank;

import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {
  @Test
  public void mustRecordDebitTransaction() {
    Transactions transactions = new Transactions();
    transactions.debit(1000.0,"Another");
    assertThat(transactions.list, hasItem(new DebitTransaction(new Date(),1000,"Another")));
  }
}
