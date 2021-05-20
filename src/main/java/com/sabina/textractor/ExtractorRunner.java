package com.sabina.textractor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;
import java.security.MessageDigest;
import org.apache.commons.io.IOUtils;


public class ExtractorRunner {

  static Logger LOGGER;

  static {
    try (FileInputStream ins = new FileInputStream(
        "/log.config")) {
      LogManager.getLogManager().readConfiguration(ins);
      LOGGER = Logger.getLogger(ExtractorRunner.class.getName());
    } catch (Exception ignore) {
      ignore.printStackTrace();
    }
  }

  public void run(String[] filenames, InputStream[] streams, InputStream[] disStreams,
      OutputStream os) {
    Extractor extractor = new Extractor();
    FileCache fileCache = FileCache.getInstance();
    int len = filenames.length;
    for (int i = 0; i < len; i++) {
      String mes = "";
      if (((i + 1) % 10 == 1) && ((i + 1) % 100 / 10 != 1)) {
        mes = (i + 1) + "st file";
      } else if ((((i + 1) % 10 == 2) || ((i + 1) % 10 == 3)) && (i + 1) % 100 / 10 != 1) {
        mes = (i + 1) + "d file";
      } else {
        mes = (i + 1) + "th file";
      }
      try {
        MessageDigest md = MessageDigest.getInstance("MD5");
        DigestInputStream dis = new DigestInputStream(disStreams[i], md);
        while (dis.read() != -1) {
          ;
        }
        String digest = Arrays.toString(md.digest());
        if (fileCache.contains(digest)) {
          System.out.println("cache");
          IOUtils.write(fileCache.get(digest), os, StandardCharsets.UTF_8.name());
        } else {
          extractor.extract(filenames[i], streams[i], os, fileCache, digest);
        }
        dis.close();
      } catch (NullPointerException e) {
        LOGGER.log(Level.WARNING, "can't find " + mes, e);
        e.printStackTrace();
      } catch (IOException e) {
        LOGGER.log(Level.WARNING,
            "an input/output exception has occurred while parsing the " + mes, e);
        e.printStackTrace();
      } catch (BadLocationException e) {
        LOGGER.log(Level.WARNING,
            "a bad location exception has occurred while parsing the " + mes, e);
        e.printStackTrace();
      } catch (NoSuchAlgorithmException e) {
        LOGGER.log(Level.WARNING, e.getMessage(), e);
        // for hash
        e.printStackTrace();
      }
    }

    try {
      fileCache.write();
    } catch (FileNotFoundException | URISyntaxException e) {
      LOGGER.log(Level.WARNING, e.getMessage(), e);
      e.printStackTrace();
    }
  }
}
