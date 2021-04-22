package com.sabina.textractor;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertArrayEquals;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.text.BadLocationException;
import org.junit.Test;

public class ExtractorTest {

  @Test
  public void simple() throws IOException, BadLocationException {
    String filename1 = "/file1.docx";
    String filename2 = "/file2.pdf";

    InputStream is1 = getClass().getResourceAsStream(filename1);
    InputStream is2 = getClass().getResourceAsStream(filename2);

    OutputStream os = new ByteArrayOutputStream();
    new ExtractorRunner().run(new String[]{filename1, filename2}, new InputStream[] {is1, is2}, os);
    String result = os.toString();
    String trueResult = "Кто прочитал, тот молодец.\n" + "\nКто прочитал, тот молодец.";
    assertEquals(result, trueResult);

    if (is1 != null) {
      is1.close();
    }
    if (is2 != null) {
      is2.close();
    }
    os.close();
  }

}
