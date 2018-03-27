package com.step.bank;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Transactions {

  protected ArrayList<Transaction> list;

  Transactions() {
    this.list = new ArrayList<>();
  }

  public void debit(double amount, String name) {
    this.list.add(new DebitTransaction(amount, name));
  }

  public void credit(double amount, String name) {
    this.list.add(new CreditTransaction(amount,name));
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

  public void writeCSVTo(PrintWriter printWriter) {
    String[] headers = {"To","Amount","Date"};
    CSVWriter writer = new CSVWriter(printWriter,headers);
    writer.write(list);
    writer.close();
  }
}
