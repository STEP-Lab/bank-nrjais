package com.step.bank;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Transactions {

  protected final ArrayList<Transaction> list;

  Transactions() {
    this.list = new ArrayList<>();
  }

  public void debit(double amount, String name, double balance) {
    this.list.add(new DebitTransaction(amount, name, balance));
  }

  public void credit(double amount, String name, double balance) {
    this.list.add(new CreditTransaction(amount,name, balance));
  }

  public Transactions filterByAmountGreaterThan(double amount) {
    Transactions transactions = new Transactions();
    for (Transaction transaction : list) {
      if (transaction.getAmount() > amount)
        transactions.list.add(transaction);
    }
    return transactions;
  }

  public void print(PrintWriter writer) {
    for (Transaction transaction : list) {
      writer.println(transaction.toString());
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transactions that = (Transactions) o;
    return Objects.equals(list, that.list);
  }

  @Override
  public int hashCode() {
    return Objects.hash(list);
  }

  public void writeCSVTo(PrintWriter printWriter) {
    String[] headers = {"Date","Amount","To","Balance"} ;
    CSVWriter writer = new CSVWriter(printWriter,headers);
    writer.write(list);
    writer.close();
  }

  public Transactions filterByAmountLessThan(double amount) {
    Transactions transactions = new Transactions();
    for (Transaction transaction : list) {
      if (transaction.getAmount() < amount)
        //Todo : move logic to transaction
        transactions.list.add(transaction);
    }
    return transactions;
  }

  public Transactions getAllDebitTransactions() {
    Transactions transactions = new Transactions();
    for (Transaction transaction : list) {
      if (transaction instanceof DebitTransaction)
        transactions.list.add(transaction);
    }
    return transactions;
  }

  public Transactions getAllCreditTransactions() {
    Transactions transactions = new Transactions();
    for (Transaction transaction : list) {
      if (transaction instanceof CreditTransaction)
        transactions.list.add(transaction);
    }
    return transactions;
  }

  public Transactions getAllTransactionsAfter(Date date) {
    Transactions transactions = new Transactions();
    for (Transaction transaction : list) {
      if (transaction.getDate().compareTo(date) == 1)
        transactions.list.add(transaction);
    }
    return transactions;
  }

  public Transactions getAllTransactionsBefore(Date date) {
    Transactions transactions = new Transactions();
    for (Transaction transaction : list) {
      if (transaction.getDate().compareTo(date) == -1)
        transactions.list.add(transaction);
    }
    return transactions;
  }
}
