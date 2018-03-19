package com.step.bank;

public class InvalidAccountNumberException extends Throwable {
  InvalidAccountNumberException() {
    super("Account number is invalid.");
  }
}
