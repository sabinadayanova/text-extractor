package com.sabina.textractor;

import java.util.Locale;

public class FileTypeParser {

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
      throw new IllegalArgumentException("unknown filetype: " + lowercaseFilename);
    }
    return type;
  }
}
