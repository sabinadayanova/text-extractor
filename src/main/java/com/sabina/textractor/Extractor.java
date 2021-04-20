package com.sabina.textractor;

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.text.BadLocationException;

public class Extractor {

  private final Parser parser;

  public Extractor() {
    parser = new Parser();
  }

  public String extract(String filename) throws IOException, BadLocationException {
    FileType fileType = parser.parse(filename);
    String result;
    switch (fileType) {
      case PDF:
        result = new PdfConverter().convert(filename);
        break;
      case DOCX:
        result = new DocxConverter().convert(filename);
        break;
      case RTF:
        result = new RtfConverter().convert(filename);
        break;
      default:
        System.out.println("unsupported file type");
        result = "";
    }
    return result;
  }

}
