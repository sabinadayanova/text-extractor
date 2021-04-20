package com.sabina.textractor;

import java.io.IOException;
import javax.swing.text.BadLocationException;

public class ExtractorRunner {
    public String[] run(String[] filenames) throws IOException, BadLocationException {
      Extractor extractor = new Extractor();
      String[] result = new String[filenames.length];
      for (int i = 0; i < filenames.length; i++) {
        result[i] = extractor.extract(filenames[i]);
      }
      return result;
    }
}
