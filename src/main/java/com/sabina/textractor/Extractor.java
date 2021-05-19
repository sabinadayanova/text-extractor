package com.sabina.textractor;

import com.sabina.textractor.converters.DocxConverter;
import com.sabina.textractor.converters.PdfConverter;
import com.sabina.textractor.converters.RtfConverter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.text.BadLocationException;

public class Extractor {

  private final Parser parser;

  public Extractor() {
    parser = new Parser();
  }

  public void extract(String filename, InputStream is,  OutputStream os, FileCache fileCache, String hash)
      throws IOException, BadLocationException {
    FileType fileType = parser.parse(filename);
    switch (fileType) {
      case PDF:
        new PdfConverter().convert(is, os, fileCache, hash);
        break;
      case DOCX:
        new DocxConverter().convert(is, os, fileCache, hash);
        break;
      case RTF:
        new RtfConverter().convert(is, os, fileCache, hash);
        break;
      default:
        System.out.println("unsupported file type");
    }
  }
}
