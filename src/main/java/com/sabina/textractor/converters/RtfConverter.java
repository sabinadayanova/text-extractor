package com.sabina.textractor.converters;

import com.sabina.textractor.FileCache;
import java.io.IOException;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.rtf.RTFEditorKit;
import org.apache.commons.io.IOUtils;

public class RtfConverter implements Converter {

  @Override
  public void convert(InputStream is, OutputStream os, FileCache fileCache, String hash) throws IOException, BadLocationException {
    DefaultStyledDocument styledDoc = new DefaultStyledDocument();
    new RTFEditorKit().read(is, styledDoc, 0);
    String result = styledDoc.getText(0, styledDoc.getLength());
    IOUtils.write(result, os, StandardCharsets.UTF_8.name());
    fileCache.updateCache(hash, result);
  }
}
