package com.sabina.textractor;

import com.sabina.textractor.converters.Converter;
import com.sabina.textractor.converters.DocxConverter;
import com.sabina.textractor.converters.PdfConverter;
import com.sabina.textractor.converters.RtfConverter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import javax.swing.text.BadLocationException;
import org.apache.commons.io.IOUtils;

public class Extractor {

  private final FileTypeParser fileTypeParser = new FileTypeParser();
  private final Map<FileType, Converter> converterMap = Map.of(
      FileType.PDF, new PdfConverter(),
      FileType.DOCX, new DocxConverter(),
      FileType.RTF, new RtfConverter());

  public void extract(String filename, InputStream is,  OutputStream os, FileCache fileCache, String hash)
      throws IOException, BadLocationException {
    FileType fileType = fileTypeParser.parse(filename);
    String text = converterMap.get(fileType).convert(is);
    IOUtils.write(text, os, StandardCharsets.UTF_8.name());
    fileCache.updateCache(hash, text);
  }
}
