package com.sabina.textractor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.text.BadLocationException;

public class ExtractorRunner {
    public void run(String[] filenames, InputStream[] streams, OutputStream os) throws IOException, BadLocationException {
      Extractor extractor = new Extractor();
      int len = filenames.length;
      for (int i = 0; i < len; i++) {
        extractor.extract(filenames[i], streams[i], os);
      }
    }
}
