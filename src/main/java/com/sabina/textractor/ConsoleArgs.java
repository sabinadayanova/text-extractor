package com.sabina.textractor;

import java.util.List;
import java.util.ArrayList;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class ConsoleArgs {

  @Parameter(
      names = {"--files", "-f"},
      description = "File names",
      required = false
  )
  public String files;

  @Parameter(
      names = {"-dir"},
      description = "File directory",
      required = false
  )
  public String directory = "-1";

  @Parameter(
      names = {"-o"},
      description = "Output",
      required = false
  )
  public String output = "-1";
}
