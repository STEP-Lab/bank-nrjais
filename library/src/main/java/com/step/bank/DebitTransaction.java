package com.step.bank;

import java.util.Date;

class DebitTransaction extends Transaction {
  protected DebitTransaction(Date date, final double amount, String to) {
    super(date, amount, to);
  }

  DebitTransaction(double amount, String name) {
    this(new Date(), amount, name);
  }
}
