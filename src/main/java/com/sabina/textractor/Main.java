package com.sabina.textractor;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.text.BadLocationException;

public class Main {

  public static void main(String[] args) throws IOException, BadLocationException {
    String filename = "/home/sabina/go/src/github.com/java/text-extractor/src/test/resources/file2.pdf";
    InputStream is = new FileInputStream(filename);
    OutputStream os = System.out;
    Extractor extractor = new Extractor();
    extractor.extract(filename, is, os);
  }
}
