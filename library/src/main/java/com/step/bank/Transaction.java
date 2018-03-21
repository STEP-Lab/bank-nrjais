package com.step.bank;

import java.util.Date;
import java.util.Objects;

public class Transaction {
  private final float amount;
  private final String to;
  private Date date;

  Transaction(float amount, Date date, String to) {
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
    return Float.compare(that.amount, amount) == 0 &&
        Objects.equals(to, that.to) &&
        Objects.equals(date, that.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, to, date);
  }
}
