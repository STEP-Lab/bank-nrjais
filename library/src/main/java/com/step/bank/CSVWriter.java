package com.step.bank;

import java.io.PrintWriter;
import java.util.Arrays;

public class CSVWriter{


  private PrintWriter printWriter;

  public CSVWriter(PrintWriter printWriter, String[] headers) {
    this.printWriter = printWriter;
    printWriter.println(String.join(",", Arrays.asList(headers)));
  }

  public void write(Transaction transaction) {
    printWriter.println(transaction.toCSV());
  }
}
