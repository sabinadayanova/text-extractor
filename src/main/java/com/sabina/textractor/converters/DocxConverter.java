package com.sabina.textractor.converters;

import com.sabina.textractor.FileCache;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class DocxConverter implements Converter {

  @Override
  public void convert(InputStream is, OutputStream os, FileCache fileCache, String hash) throws IOException {
    XWPFDocument document = new XWPFDocument(is);
    XWPFWordExtractor wordExtractor = new XWPFWordExtractor(document);
    String text = wordExtractor.getText();
    wordExtractor.close();
    IOUtils.write(text, os, StandardCharsets.UTF_8.name());
    fileCache.updateCache(hash, text);
  }
}
