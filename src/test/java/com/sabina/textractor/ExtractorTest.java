package com.sabina.textractor;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.junit.Test;

public class ExtractorTest {

  @Test
  public void testSimple() {
    String filename = "/file3.rtf";
    InputStream is = getClass().getResourceAsStream(filename);
    InputStream dis = getClass().getResourceAsStream(filename);
    OutputStream os = new ByteArrayOutputStream();
    new ExtractorRunner().run(
        new ArrayList<>(List.of(filename)),
        new ArrayList<>(List.of(is)),
        new ArrayList<>(List.of(dis)),
        os);
    String result = os.toString();
    String trueResult = "You, foul, loathsome, evil, little cockroach.\n";
    assertEquals(result, trueResult);
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
    new ExtractorRunner().run(
        new ArrayList<>(List.of(filename1, filename2)),
        new ArrayList<>(List.of(is1, is2)),
        new ArrayList<>(List.of(dis1, dis2)),
        os);
    String result = os.toString();
    String trueResult = "Кто прочитал, тот молодец.\n" + "\nКто прочитал, тот молодец.";
    assertEquals(result, trueResult);
  }

  @Test
  public void testFileNotSupported() {
    String filename = "/file4.xlsx";
    InputStream is = getClass().getResourceAsStream(filename);
    InputStream dis = getClass().getResourceAsStream(filename);
    OutputStream os = new ByteArrayOutputStream();
    assertThrows(IllegalArgumentException.class,
        () ->  new ExtractorRunner().run(
            new ArrayList<>(List.of(filename)),
            new ArrayList<>(List.of(is)),
            new ArrayList<>(List.of(dis)),
            os));
  }

  /*
  @Test
  public void testCLI() {
    try {
      String[] args = new String[] {"/home/sabina/go/src/github.com/java/text-extractor/src/main/resources/file3.rtf", "-o", "output.txt"};
      CLI.main(args);
      FileReader fr= new FileReader("output.txt");
      Scanner scan = new Scanner(fr);
      StringBuilder result = new StringBuilder();
      while (scan.hasNextLine()) {
        result.append(scan.nextLine());
      }
      fr.close();
      assertEquals("You, foul, loathsome, evil, little cockroach.", result.toString());
      } catch (IOException e) {
      e.printStackTrace();
    }
  }
   */
}
