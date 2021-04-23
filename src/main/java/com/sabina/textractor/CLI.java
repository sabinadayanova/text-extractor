package com.sabina.textractor;
import com.beust.jcommander.JCommander;

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
        new ExtractorRunner().run(consoleArgs.files,
                Arrays.stream(consoleArgs.files)
                        .map(item -> {
                            try {
                                return new FileInputStream(item);
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                            return null;
                        })
                        .toArray(FileInputStream[]::new), System.out);
    }
}
