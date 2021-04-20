package com.sabina.textractor;

import java.io.IOException;
import javax.swing.text.BadLocationException;

public class Main {

  public static void main(String[] args) throws IOException, BadLocationException {
    String filename = "/home/sabina/go/src/github.com/java/text-extractor/src/test/java/com/sabina/textractor/file2.pdf";
    String filename2 = "/home/sabina/go/src/github.com/java/text-extractor/src/main/java/com/sabina/textractor/10_demo.docx";

    String res = new Extractor().extract(filename);
    System.out.println(res);
  }
}
