package com.sabina.textractor;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertArrayEquals;

import java.io.IOException;
import javax.swing.text.BadLocationException;
import org.junit.Test;

public class ExtractorTest {

  @Test
  public void simple() throws IOException, BadLocationException {
    String filename1 = "/home/sabina/go/src/github.com/java/text-extractor/src/test/java/com/sabina/textractor/file1.docx";
    String filename2 = "/home/sabina/go/src/github.com/java/text-extractor/src/test/java/com/sabina/textractor/file2.pdf";

    String[] result = new ExtractorRunner().run(new String[]{filename1, filename2});
    String[] trueResult = new String[]{ "Кто прочитал, тот молодец.\n", "\nКто прочитал, тот молодец."};
    assertArrayEquals(result, trueResult);
  }

}
