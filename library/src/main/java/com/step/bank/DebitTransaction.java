package com.step.bank;

import java.util.Date;

class DebitTransaction extends Transaction {
  protected DebitTransaction(Date date, final double amount, String to) {
    super(amount, date, to);
    System.out.println(amount);
  }

  DebitTransaction(double amount, String name) {
    this(new Date(), amount, name);
  }
}
