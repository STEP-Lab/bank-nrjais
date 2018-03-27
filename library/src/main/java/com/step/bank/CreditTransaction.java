package com.step.bank;

import java.util.Date;

public class CreditTransaction extends Transaction{
  protected CreditTransaction(Date date, double amount, String to, double balance) {
    super(date,amount,to,balance);
  }

  CreditTransaction(double amount, String to, double balance) {
    this(new Date(),amount,to,balance);
  }
}
