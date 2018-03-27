package com.step.bank;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class CSVWriterTest {
  @Test
  public void shouldWriteCSVToFile() throws UnsupportedEncodingException, FileNotFoundException {
    ArrayList<String> result = new ArrayList<>();
    PrintWriter printWriter = new PrintWriter("file.txt", "UTF-8") {
      @Override
      public void println(String x) {
        result.add(x);
      }
    };
    Date date = new Date();
    String[] headers = {"To","Amount","Date"};
    CSVWriter csvWriter = new CSVWriter(printWriter,headers);
    csvWriter.write(new DebitTransaction(date,1000.0,"Account"));
    assertThat(result, hasItems(String.join(",", Arrays.asList(headers))
    ,"Account,1000.0,"+date.toString()));
  }
}
