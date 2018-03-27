package com.step.bank;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

  private Transactions transactions;

  @Before
  public void setUp() {
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

  @Test
  public void printTransactions() throws FileNotFoundException, UnsupportedEncodingException {
    ArrayList<String> result = new ArrayList<>();
    PrintWriter printWriter = new PrintWriter("file.txt", "UTF-8") {
      @Override
      public void println(String x) {
        result.add(x);
      }
    };
    transactions.credit(1100,"Neeraj");
    transactions.credit(2400,"Arvind");
    transactions.credit(6300,"Nitesh");
    transactions.print(printWriter);
    printWriter.close();
    assertThat(result, hasItems(new CreditTransaction(1100, "Neeraj").toString()
        ,new CreditTransaction(2400, "Arvind").toString()
        ,new CreditTransaction(6300, "Nitesh").toString()));
  }

  @Test
  public void filterTransactionsByAmount() {
    transactions.credit(100,"Neeraj");
    transactions.credit(1000,"Arvind");
    transactions.credit(10000,"Nitesh");
    transactions.credit(500,"Debarun");
    transactions.credit(600,"Subham");
    transactions.credit(1100,"Vivek");
    transactions.credit(2400,"Ashish");
    transactions.credit(6300,"Ravinder");
    Transactions filteredTransactions = this.transactions.filterByAmountGreaterThan(1000);
    assertThat(filteredTransactions.list, hasItems(new CreditTransaction(10000, "Nitesh")
        ,new CreditTransaction(1100, "Vivek")
        ,new CreditTransaction(2400, "Ashish")
        ,new CreditTransaction(6300, "Ravinder")));
  }

  @Test
  public void writeCSVToFile() throws FileNotFoundException, UnsupportedEncodingException {
    String[] headers = {"To","Amount","Date"};
    ArrayList<String> result = new ArrayList<>();
    PrintWriter printWriter = new PrintWriter("file.txt", "UTF-8") {
      @Override
      public void println(String x) {
        result.add(x);
      }
    };
    transactions.credit(120.0,"name");
    transactions.credit(1230.0,"name2");
    transactions.credit(1220.0,"name3");
    transactions.writeCSVTo(printWriter);
    assertThat(result, hasItems(String.join(",", Arrays.asList(headers))
        ,new DebitTransaction(transactions.list.get(0).getDate(), 120.0,"name").toCSV()
        ,new DebitTransaction(transactions.list.get(1).getDate(), 1230.0,"name2").toCSV()
        ,new DebitTransaction(transactions.list.get(2).getDate(), 1220.0,"name3").toCSV()));
  }
}
