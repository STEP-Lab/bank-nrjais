package com.step.bank;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
    transactions.debit(1000.0,"Another", 100.0);
    Date date = transactions.list.get(0).getDate();
    DebitTransaction debitTransaction = new DebitTransaction(date, 1000, "Another", 100.0);
    assertThat(transactions.list, hasItem(debitTransaction));
  }

  @Test
  public void mustRecordCreditTransaction() {
    transactions.credit(1000.0,"Another", 100.0);
    Date date = transactions.list.get(0).getDate();
    assertThat(transactions.list, hasItem(new CreditTransaction(date,1000,"Another", 100.0)));
  }

  @Test
  public void mustRecordBothCreditAndDebitTransaction(){
    transactions.debit(1000.0,"Another", 100.0);
    Date debitDate = transactions.list.get(0).getDate();
    transactions.credit(1000.0,"Another", 100.0);
    Date creditDate = transactions.list.get(1).getDate();
    assertThat(transactions.list, hasItem(new DebitTransaction(debitDate,1000,"Another", 100.0)));
    assertThat(transactions.list, hasItem(new CreditTransaction(creditDate,1000,"Another", 100.0)));
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
    transactions.credit(1100,"Neeraj", 100.0);
    transactions.credit(2400,"Arvind", 100.0);
    transactions.credit(6300,"Nitesh", 100.0);
    transactions.print(printWriter);
    printWriter.close();
    assertThat(result, hasItems(
        new CreditTransaction(transactions.list.get(0).getDate(),1100, "Neeraj", 100.0).toString()
        ,new CreditTransaction(transactions.list.get(1).getDate(),2400, "Arvind", 100.0).toString()
        ,new CreditTransaction(transactions.list.get(2).getDate(),6300, "Nitesh", 100.0).toString()));
  }

  @Test
  public void filterTransactionsByAmountGreaterThan() {
    transactions.credit(100,"Neeraj", 100.0);
    transactions.credit(1000,"Arvind", 100.0);
    transactions.credit(10000,"Nitesh", 100.0);
    transactions.credit(500,"Debarun", 100.0);
    transactions.credit(600,"Subham", 100.0);
    transactions.credit(1100,"Vivek", 100.0);
    transactions.credit(2400,"Ashish", 100.0);
    transactions.debit(6300,"Ravinder", 100.0);
    Transactions filteredTransactions = this.transactions.filterByAmountGreaterThan(1000);
    assertThat(filteredTransactions.list, hasItems(
        new CreditTransaction(transactions.list.get(2).getDate(),10000, "Nitesh", 100.0)
        ,new CreditTransaction(transactions.list.get(5).getDate(),1100, "Vivek", 100.0)
        ,new CreditTransaction(transactions.list.get(6).getDate(),2400, "Ashish", 100.0)
        ,new DebitTransaction(transactions.list.get(7).getDate(),6300, "Ravinder", 100.0)));
  }

  @Test
  public void filterTransactionsByAmountLessThan() {
    transactions.credit(100,"Neeraj", 100.0);
    transactions.credit(1000,"Arvind", 100.0);
    transactions.credit(10000,"Nitesh", 100.0);
    transactions.credit(500,"Debarun", 100.0);
    transactions.debit(600,"Subham", 100.0);
    transactions.credit(1100,"Vivek", 100.0);
    transactions.credit(2400,"Ashish", 100.0);
    transactions.credit(6300,"Ravinder", 100.0);
    Transactions filteredTransactions = this.transactions.filterByAmountLessThan(1000);
    assertThat(filteredTransactions.list, hasItems(
        new CreditTransaction(transactions.list.get(3).getDate(),500, "Debarun", 100.0)
        ,new DebitTransaction(transactions.list.get(4).getDate(),600, "Subham", 100.0)
        ,new CreditTransaction(transactions.list.get(0).getDate(),100, "Neeraj", 100.0)));
  }

  @Test
  public void writeCSVToFile() throws FileNotFoundException, UnsupportedEncodingException {
    String[] headers = {"Date","Amount","To","Balance"};
    ArrayList<String> result = new ArrayList<>();
    PrintWriter printWriter = new PrintWriter("file.txt", "UTF-8") {
      @Override
      public void println(String x) {
        result.add(x);
      }
    };
    transactions.credit(120.0,"name", 100.0);
    transactions.credit(1230.0,"name2", 100.0);
    transactions.credit(1220.0,"name3", 100.0);
    transactions.writeCSVTo(printWriter);
    assertThat(result, hasItems(String.join(",", Arrays.asList(headers))
        ,new DebitTransaction(transactions.list.get(0).getDate(), 120.0,"name", 100.0).toCSV()
        ,new DebitTransaction(transactions.list.get(1).getDate(), 1230.0,"name2", 100.0).toCSV()
        ,new DebitTransaction(transactions.list.get(2).getDate(), 1220.0,"name3", 100.0).toCSV()));
  }

  @Test
  public void getAllDebitTransactions() {
    transactions.credit(100,"Neeraj", 100.0);
    transactions.debit(1000,"Arvind", 100.0);
    transactions.credit(10000,"Nitesh", 100.0);
    transactions.credit(500,"Debarun", 100.0);
    transactions.debit(600,"Subham", 100.0);
    transactions.credit(1100,"Vivek", 100.0);
    transactions.debit(2400,"Ashish", 100.0);
    transactions.debit(6300,"Ravinder", 100.0);
    Transactions filteredTransactions = this.transactions.getAllDebitTransactions();
    assertThat(filteredTransactions.list, hasItems(
        new DebitTransaction(transactions.list.get(1).getDate(),1000, "Arvind", 100.0)
        ,new DebitTransaction(transactions.list.get(4).getDate(),600, "Subham", 100.0)
        ,new DebitTransaction(transactions.list.get(6).getDate(),6300, "Ravinder", 100.0)
        ,new DebitTransaction(transactions.list.get(7).getDate(),2400, "Ashish", 100.0)));
  }

  @Test
  public void getAllCreditTransactions() {
    transactions.debit(100,"Neeraj", 100.0);
    transactions.credit(1000,"Arvind", 100.0);
    transactions.debit(10000,"Nitesh", 100.0);
    transactions.debit(500,"Debarun", 100.0);
    transactions.credit(600,"Subham", 100.0);
    transactions.debit(1100,"Vivek", 100.0);
    transactions.credit(2400,"Ashish", 100.0);
    transactions.credit(6300,"Ravinder", 100.0);
    Transactions filteredTransactions = this.transactions.getAllCreditTransactions();
    assertThat(filteredTransactions.list, hasItems(
        new CreditTransaction(transactions.list.get(1).getDate(),1000, "Arvind", 100.0)
        ,new CreditTransaction(transactions.list.get(4).getDate(),600, "Subham", 100.0)
        ,new CreditTransaction(transactions.list.get(6).getDate(),6300, "Ravinder", 100.0)
        ,new CreditTransaction(transactions.list.get(7).getDate(),2400, "Ashish", 100.0)));
  }

  @Test
  public void getAllTransactionsAfter() {
    transactions.debit(100,"Neeraj", 100.0);
    transactions.credit(1000,"Arvind", 100.0);
    transactions.debit(10000,"Nitesh", 100.0);
    transactions.debit(500,"Debarun", 100.0);
    transactions.credit(600,"Subham", 100.0);
    transactions.debit(1100,"Vivek", 100.0);
    transactions.credit(2400,"Ashish", 100.0);
    transactions.credit(6300,"Ravinder", 100.0);
    transactions.list.get(0).getDate().setDate(25);
    transactions.list.get(1).getDate().setDate(22);
    transactions.list.get(2).getDate().setDate(21);
    transactions.list.get(3).getDate().setDate(20);
    transactions.list.get(4).getDate().setDate(15);
    transactions.list.get(5).getDate().setDate(12);
    transactions.list.get(6).getDate().setDate(11);
    transactions.list.get(7).getDate().setDate(11);
    Date date = new Date();
    date.setDate(21);
    Transactions filteredTransactions = this.transactions.getAllTransactionsAfter(date);
    assertThat(filteredTransactions.list, hasItems(
        new DebitTransaction(transactions.list.get(0).getDate(),100, "Neeraj", 100.0)
        ,new CreditTransaction(transactions.list.get(1).getDate(),1000, "Arvind", 100.0)));
  }

  @Test
  public void getAllTransactionsBefore() {
    transactions.debit(100,"Neeraj", 100.0);
    transactions.credit(1000,"Arvind", 100.0);
    transactions.debit(10000,"Nitesh", 100.0);
    transactions.debit(500,"Debarun", 100.0);
    transactions.credit(600,"Subham", 100.0);
    transactions.debit(1100,"Vivek", 100.0);
    transactions.credit(2400,"Ashish", 100.0);
    transactions.credit(6300,"Ravinder", 100.0);
    transactions.list.get(0).getDate().setDate(25);
    transactions.list.get(1).getDate().setDate(22);
    transactions.list.get(2).getDate().setDate(21);
    transactions.list.get(3).getDate().setDate(20);
    transactions.list.get(4).getDate().setDate(15);
    transactions.list.get(5).getDate().setDate(12);
    transactions.list.get(6).getDate().setDate(11);
    transactions.list.get(7).getDate().setDate(11);
    Date date = new Date();
    date.setDate(15);
    Transactions filteredTransactions = this.transactions.getAllTransactionsBefore(date);
    assertThat(filteredTransactions.list, hasItems(
        new DebitTransaction(transactions.list.get(5).getDate(),1100, "Vivek", 100.0)
        ,new CreditTransaction(transactions.list.get(6).getDate(),2400, "Ashish", 100.0)
        ,new CreditTransaction(transactions.list.get(7).getDate(),6300, "Ravinder", 100.0)));
  }
}
