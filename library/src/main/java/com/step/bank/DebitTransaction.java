package com.step.bank;

import java.util.Date;

class DebitTransaction extends Transaction {
  protected DebitTransaction(Date date, final double amount, String to, double balance) {
    super(date, amount, to, balance);
  }

  DebitTransaction(double amount, String name, double balance) {
    this(new Date(), amount, name, balance);
  }
}
