package com.step.bank;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

  private Transactions transactions;

  @Before
  public void setUp() throws Exception {
    transactions = new Transactions();
  }

  @Test
  public void mustRecordDebitTransaction() {
    transactions.debit(1000.0,"Another");
    Date date = transactions.list.get(0).getDate();
    DebitTransaction debitTransaction = new DebitTransaction(date, 1000, "Another");
    assertThat(transactions.list, hasItem(debitTransaction));
  }

  @Test
  public void mustRecordCreditTransaction() {
    transactions.credit(1000.0,"Another");
    Date date = transactions.list.get(0).getDate();
    assertThat(transactions.list, hasItem(new CreditTransaction(date,1000,"Another")));
  }

  @Test
  public void mustRecordBothCreditAndDebitTransaction(){
    transactions.debit(1000.0,"Another");
    Date debitDate = transactions.list.get(0).getDate();
    transactions.credit(1000.0,"Another");
    Date creditDate = transactions.list.get(1).getDate();
    assertThat(transactions.list, hasItem(new DebitTransaction(debitDate,1000,"Another")));
    assertThat(transactions.list, hasItem(new CreditTransaction(creditDate,1000,"Another")));
  }
}
