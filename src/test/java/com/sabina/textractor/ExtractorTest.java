package com.sabina.textractor;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.junit.Test;

public class ExtractorTest {

  @Test
  public void testSimple() {
    String filename = "/file3.rtf";
    InputStream is = getClass().getResourceAsStream(filename);
    OutputStream os = new ByteArrayOutputStream();
    new ExtractorRunner().run(new String[]{filename}, new InputStream[] {is}, os);
    String result = os.toString();
    String trueResult = "You, foul, loathsome, evil, little cockroach.\n";
    assertEquals(result, trueResult);

    try {
      if (is != null) {
        is.close();
      }
      os.close();
    } catch (IOException e) {
      System.err.println("can not close Input or Output streams");
      e.printStackTrace();
    }
  }

  @Test
  public void testFileSequence() {
    String filename1 = "/file1.docx";
    String filename2 = "/file2.pdf";

    InputStream is1 = getClass().getResourceAsStream(filename1);
    InputStream is2 = getClass().getResourceAsStream(filename2);

    OutputStream os = new ByteArrayOutputStream();
    new ExtractorRunner().run(new String[]{filename1, filename2}, new InputStream[] {is1, is2}, os);
    String result = os.toString();
    String trueResult = "Кто прочитал, тот молодец.\n" + "\nКто прочитал, тот молодец.";
    assertEquals(result, trueResult);

    try {
      if (is1 != null) {
        is1.close();
      }
      if (is2 != null) {
        is2.close();
      }
      os.close();
    } catch (IOException e) {
      System.err.println("can not close Input or Output streams");
      e.printStackTrace();
    }
  }

  @Test
  public void testFileNotSupported() {
    String filename = "/file4.xlsx";
    InputStream is = getClass().getResourceAsStream(filename);
    OutputStream os = new ByteArrayOutputStream();
    assertThrows(IllegalArgumentException.class, () ->  new ExtractorRunner().run(new String[]{filename}, new InputStream[] {is}, os));
  }
}
