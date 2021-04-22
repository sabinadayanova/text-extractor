package com.sabina.textractor.converters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.text.BadLocationException;

public interface Converter {

  public void convert(InputStream is, OutputStream os) throws IOException, BadLocationException;

}
