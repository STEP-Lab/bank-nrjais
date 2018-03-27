package com.step.bank;

import java.util.Date;
import java.util.Objects;

public class Transaction {
  private final double amount;
  private final String to;
  private Date date;

  Transaction(Date date, double amount, String to) {
    this.amount = amount;
    this.date = date;
    this.to = to;
  }

  public Date getDate() {
    return date;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Transaction that = (Transaction) o;
    return Double.compare(that.amount, amount) == 0 &&
        Objects.equals(to, that.to) &&
        Objects.equals(date, that.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, to, date);
  }

  @Override
  public String toString() {
    return "Transaction{" +
        "amount=" + amount +
        ", to='" + to + '\'' +
        ", date=" + date +
        '}';
  }

  public double getAmount() {
    return amount;
  }

  public String toCSV() {
    return String.format("%s,%s,%s",to,amount,date);
  }
}
