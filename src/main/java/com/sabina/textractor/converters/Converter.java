package com.sabina.textractor.converters;

import com.sabina.textractor.FileCache;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.text.BadLocationException;

public interface Converter {

  String convert(InputStream is) throws IOException, BadLocationException;

}
