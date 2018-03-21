package com.step.bank;

import java.util.Date;

class DebitTransaction extends Transaction {

  protected DebitTransaction(Date date,float amount, String to) {
    super(amount, date, to);
  }

  DebitTransaction(float amount, String name) {
    this(new Date(),amount, name);
  }
}
