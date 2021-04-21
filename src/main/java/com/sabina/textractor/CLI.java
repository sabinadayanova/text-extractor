package com.sabina.textractor;
import com.beust.jcommander.JCommander;

import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.util.List;

public class CLI {


    public static void main(String... args) throws IOException, BadLocationException {
        ConsoleArgs consoleArgs = new ConsoleArgs();
        JCommander.newBuilder()
                .addObject(consoleArgs)
                .build()
                .parse(args);
        String[] result = new ExtractorRunner().run(consoleArgs.files);
        System.out.println(result);
    }
}
