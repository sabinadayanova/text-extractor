package com.sabina.textractor;


import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class Parser {

  public FileType parse(String filename) {
    FileType type = null;
    String lowercaseFilename = filename.toLowerCase(Locale.ROOT);
    if (lowercaseFilename.endsWith(".pdf")) {
      type = FileType.PDF;
    } else if (lowercaseFilename.endsWith(".docx")) {
      type = FileType.DOCX;
    } else if (lowercaseFilename.endsWith(".rtf")) {
      type = FileType.RTF;
    }

    if (type == null) {
      throw new IllegalArgumentException("unknown filetype");
    }
    return type;
  }
}
