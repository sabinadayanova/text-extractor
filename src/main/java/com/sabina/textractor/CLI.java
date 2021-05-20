package com.sabina.textractor;

import com.beust.jcommander.JCommander;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class CLI {

  public static void main(String... args) throws IOException {
    ConsoleArgs consoleArgs = new ConsoleArgs();
    JCommander.newBuilder()
        .addObject(consoleArgs)
        .build()
        .parse(args);

    List<String> files = new ArrayList<>();
    if (consoleArgs.directory != null) {
      File dir = new File(consoleArgs.directory);
      String[] filesOnly = dir.list();
      // TODO incorrect files
      assert filesOnly != null;
      for (String f : filesOnly) {
        files.add(consoleArgs.directory + "/" + f);
        System.out.println(f);
      }
    } else {
      files = consoleArgs.files;
    }

    OutputStream out;
    if (consoleArgs.output == null || consoleArgs.output.equals("-")) {
      out = System.out;
    } else {
      out = new FileOutputStream(consoleArgs.output);
    }

    ExtractorRunner runner = new ExtractorRunner();
    runner.run(files.toArray(new String[0]),
        files.stream()
            .map(item -> {
              try {
                return new FileInputStream(item);
              } catch (FileNotFoundException e) {
                e.printStackTrace();
              }
              return null;
            })
            .toArray(FileInputStream[]::new),
        files.stream()
            .map(item -> {
              try {
                return new FileInputStream(item);
              } catch (FileNotFoundException e) {
                e.printStackTrace();
              }
              return null;
            })
            .toArray(FileInputStream[]::new),
        out);
  }
}
