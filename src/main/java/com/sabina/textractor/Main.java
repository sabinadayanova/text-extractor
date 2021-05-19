package com.sabina.textractor;

import com.beust.jcommander.JCommander;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import javax.swing.text.BadLocationException;

public class Main {

  public static void main(String[] args)  {

    String filename = "/file2.pdf";
    try{
      InputStream is = Main.class.getResourceAsStream(filename);
      OutputStream os = System.out;
      Extractor extractor = new Extractor();
      extractor.extract(filename, is, os, FileCache.getInstance(), "hash");
      if (is != null) {
        is.close();
      }
    } catch (IOException e) {
      System.err.println("can not create or close file input stream");
      e.printStackTrace();
    } catch (BadLocationException e) {
      System.err.println("a bad location exception has occurred while parsing file");
      e.printStackTrace();
    }
  }
}
