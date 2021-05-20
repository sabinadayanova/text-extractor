package com.sabina.textractor;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import org.junit.Test;

public class ExtractorTest {

  @Test
  public void testSimple() {
    String filename = "/file3.rtf";
    InputStream is = getClass().getResourceAsStream(filename);
    InputStream dis = getClass().getResourceAsStream(filename);
    OutputStream os = new ByteArrayOutputStream();
    new ExtractorRunner().run(new String[]{filename}, new InputStream[] {is},
        new InputStream[] {dis},  os);
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

    InputStream dis1 = getClass().getResourceAsStream(filename1);
    InputStream dis2 = getClass().getResourceAsStream(filename2);

    OutputStream os = new ByteArrayOutputStream();
    new ExtractorRunner().run(new String[]{filename1, filename2}, new InputStream[] {is1, is2},
        new InputStream[] {dis1, dis2}, os);
    String result = os.toString();
    String trueResult = "Кто прочитал, тот молодец.\n" + "\nКто прочитал, тот молодец.";
    assertEquals(trueResult, result);

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
    InputStream dis = getClass().getResourceAsStream(filename);
    OutputStream os = new ByteArrayOutputStream();
    assertThrows(IllegalArgumentException.class,
        () ->  new ExtractorRunner().run(new String[]{filename}, new InputStream[] {is},
            new InputStream[] {dis},  os));
  }

  @Test
  public void testDemo() {
    String filename = "/file2.pdf";
    InputStream is1 = getClass().getResourceAsStream(filename);
    InputStream is2 = getClass().getResourceAsStream(filename);

    OutputStream os = System.out;

    var er = new ExtractorRunner();
    er.run(new String[]{filename}, new InputStream[] {is1}, new InputStream[] {is2}, os);
  }

  @Test
  public void testCLI() {
    try {
      CLI cli = new CLI();
      String[] args = new String[] {"\\home\\sabina\\go\\src\\github.com\\java\\text-extractor\\src\\main\\resources\\file3.rtf", "-o", "output.txt"};
      cli.main(args);
      FileReader fr= new FileReader("output.txt");
      Scanner scan = new Scanner(fr);
      String result = "";
      while (scan.hasNextLine()) {
        result += scan.nextLine();
      }
      fr.close();
      assertEquals("You, foul, loathsome, evil, little cockroach.", result);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
