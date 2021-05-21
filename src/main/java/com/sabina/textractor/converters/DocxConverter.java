package com.sabina.textractor.converters;

import com.sabina.textractor.exceptions.ExtractionException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class DocxConverter implements Converter {

  @Override
  public String convert(InputStream is) {
    try {
      XWPFDocument document = new XWPFDocument(is);
      XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
      String text = wordExtractor.getText();
      wordExtractor.close();
      return text;
    } catch (IOException e) {
      throw new ExtractionException("IOException occurred when extracting text from DOCX file", e);
    }
  }
}
