package com.sabina.textractor.converters;

import com.sabina.textractor.FileCache;
import com.snowtide.PDF;
import com.snowtide.pdf.Document;
import com.snowtide.pdf.OutputTarget;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;

public class PdfConverter implements Converter {

  @Override
  public void convert(InputStream is, OutputStream os, FileCache fileCache, String hash) throws IOException {
    StringBuilder parsedText = new StringBuilder(1024);
    Document pdf = PDF.open(is, "none");
    pdf.pipe(new OutputTarget(parsedText));
    pdf.close();
    IOUtils.write(parsedText.toString(), os, StandardCharsets.UTF_8.name());
    fileCache.updateCache(hash, parsedText.toString());
  }
}
