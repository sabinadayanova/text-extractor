package com.sabina.textractor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.text.BadLocationException;

public class ExtractorRunner {
    public void run(String[] filenames, InputStream[] streams, OutputStream os) {
      Extractor extractor = new Extractor();
      int len = filenames.length;
      for (int i = 0; i < len; i++) {
        try {
          extractor.extract(filenames[i], streams[i], os);
        } catch (IOException e) {
          System.err.println("an input/output exception has occurred while parsing the " + i + "th file");
          e.printStackTrace();
        } catch (BadLocationException e) {
          System.err.println("a bad location exception has occurred while parsing the " + i + "th file");
          e.printStackTrace();
        }
      }
    }
}
