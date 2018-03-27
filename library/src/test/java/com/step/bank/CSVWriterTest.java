package com.step.bank;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class CSVWriterTest {

  private ArrayList<String> result;
  private PrintWriter printWriter;
  private Date date;
  private String[] headers;
  private CSVWriter csvWriter;

  @Before
  public void setUp() throws Exception {
    result = new ArrayList<>();
    printWriter = new PrintWriter("file.txt", "UTF-8") {
      @Override
      public void println(String x) {
        result.add(x);
      }
    };
    date = new Date();
    headers = new String[]{"To","Amount","Date"};
    csvWriter = new CSVWriter(printWriter, headers);
  }

  @Test
  public void shouldWriteCSVToFile() {
    csvWriter.write(new DebitTransaction(date,1000.0,"Account"));
    csvWriter.close();
    assertThat(result, hasItems(String.join(",", Arrays.asList(headers))
    ,"Account,1000.0,"+ date.toString()));
  }

  @Test
  public void shouldWriteCSVOfListToFile() {
    String[] headers = {"To","Amount","Date"};
    ArrayList<Transaction> transactions = new ArrayList<>();
    transactions.add(new DebitTransaction(date, 120.0,"name"));
    transactions.add(new DebitTransaction(date, 1230.0,"name2"));
    transactions.add(new DebitTransaction(date, 1220.0,"name3"));
    csvWriter.write(transactions);
    csvWriter.close();
    assertThat(result.size(), is(4));
    assertThat(result, hasItems(String.join(",", Arrays.asList(headers))
        ,new DebitTransaction(date, 120.0,"name").toCSV()
        ,new DebitTransaction(date, 1230.0,"name2").toCSV()
        ,new DebitTransaction(date, 1220.0,"name3").toCSV()));
  }
}
