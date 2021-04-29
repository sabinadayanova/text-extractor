package com.sabina.textractor;

import com.beust.jcommander.JCommander;

import com.beust.jcommander.Parameter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.stream.Stream;
import javax.swing.text.BadLocationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class CLI {

  public static void main(String... args) throws IOException, BadLocationException {
    ConsoleArgs consoleArgs = new ConsoleArgs();
    JCommander.newBuilder()
        .addObject(consoleArgs)
        .build()
        .parse(args);
    String[] files;
    if (!consoleArgs.directory.equals("-1")) {
      File dir = new File(consoleArgs.directory);
      String[] filesOnly = dir.list();
      files = new String[filesOnly.length];
      int i = 0;
      for (String f : filesOnly) {
        files[i] = consoleArgs.directory + f;
        System.out.println(f);
        i++;
      }
    } else {
      files = new String[]{consoleArgs.files};
    }
    OutputStream out;
    if (consoleArgs.output.equals("-1")) {
      out = System.out;
    } else {
      out = new FileOutputStream(consoleArgs.output);
    }
    new ExtractorRunner().run(files,
        Arrays.stream(files)
            .map(item -> {
              try {
                return new FileInputStream((String) item);
              } catch (FileNotFoundException e) {
                e.printStackTrace();
              }
              return null;
            })
            .toArray(FileInputStream[]::new), out);
  }
}
