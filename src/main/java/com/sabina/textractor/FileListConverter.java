package com.sabina.textractor;

import java.util.ArrayList;
import java.util.List;

public class FileListConverter implements IStringConverter<String[]> {

  @Override
  public String[] convert(String files) {
    return files.split(", ");
  }
}
