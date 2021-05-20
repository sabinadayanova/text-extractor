package com.sabina.textractor.converters;

import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class DocxConverter implements Converter {

  @Override
  public String convert(InputStream is) throws IOException {
    XWPFDocument document = new XWPFDocument(is);
    XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
    String text = wordExtractor.getText();
    wordExtractor.close();
    return text;
  }
}
