package com.sabina.textractor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class DocxConverter implements Converter {

  @Override
  public String convert(String filename) throws IOException {
    File file = new File(filename);
    XWPFDocument doc = new XWPFDocument(new FileInputStream(file));
    XWPFWordExtractor we = new XWPFWordExtractor(doc);
    String text = we.getText();
    we.close();
    return text;
  }
}
