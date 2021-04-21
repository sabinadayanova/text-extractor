package com.sabina.textractor;
import java.util.List;
import java.util.ArrayList;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class ConsoleArgs {
    @Parameter(
            names = {"--files", "-f"},
            description = "File names",
            required = true
    )
    public String[] files = {};
}
