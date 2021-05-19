package com.sabina.textractor;

import java.util.List;
import java.util.ArrayList;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class ConsoleArgs {

  @Parameter(
      description = "File names",
      required = true
  )
  public List<String> files = new ArrayList<>();

  @Parameter(
      names = {"--dir"},
      description = "File directory",
      required = false
  )
  public String directory = null;

  @Parameter(
      names = {"-o", "--output"},
      description = "Output",
      required = true
  )
  public String output = null;
}
