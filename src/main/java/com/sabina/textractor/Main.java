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
    ConsoleArgs consoleArgs = new ConsoleArgs();
    JCommander.newBuilder()
        .addObject(consoleArgs)
        .build()
        .parse(args);
    new ExtractorRunner().run(new String[]{consoleArgs.files},
        Arrays.stream(new String[]{consoleArgs.files})
            .map(item -> {
              try {
                return new FileInputStream(item);
              } catch (FileNotFoundException e) {
                e.printStackTrace();
              }
              return null;
            })
            .toArray(FileInputStream[]::new), System.out);
//    String filename = "/file2.pdf";
//    try{
//      InputStream is = Main.class.getResourceAsStream(filename);
//      OutputStream os = System.out;
//      Extractor extractor = new Extractor();
//      extractor.extract(filename, is, os);
//      if (is != null) {
//        is.close();
//      }
//    } catch (IOException e) {
//      System.err.println("can not create or close file input stream");
//      e.printStackTrace();
//    } catch (BadLocationException e) {
//      System.err.println("a bad location exception has occurred while parsing file");
//      e.printStackTrace();
//    }
  }
}
