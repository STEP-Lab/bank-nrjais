package com.step.bank;

public class MinimumBalanceException extends Throwable {
  MinimumBalanceException() {
    super("Insufficient minimum balance");
  }
}
