package com.sabina.textractor;

import com.sabina.textractor.exceptions.ExtractionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.security.DigestInputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;
import org.apache.commons.io.IOUtils;

public class ExtractorRunner {
  private final Extractor extractor = new Extractor();
  private static final byte[] HEX_ARRAY = "0123456789abcdef".getBytes(StandardCharsets.US_ASCII);

  public void run(ArrayList<String> filenames, ArrayList<InputStream> streams, ArrayList<InputStream> disStreams, OutputStream os) {
    FileCache fileCache = FileCache.getInstance();
    int len = filenames.size();
    for (int i = 0; i < len; i++) {
      String hash = calculateHash(disStreams.get(i), i);
      if (fileCache.contains(hash)) {
        try {
          IOUtils.write(fileCache.get(hash), os, StandardCharsets.UTF_8.name());
        } catch (IOException e) {
          throw new ExtractionException("IOException occurred when writing extracted text to the stream", e);
        }
      } else {
        extractor.extract(filenames.get(i), streams.get(i), os, fileCache, hash);
      }
    }
    fileCache.write();
  }

  public static String bytesToHex(byte[] bytes) {
    byte[] hexChars = new byte[bytes.length * 2];
    for (int j = 0; j < bytes.length; j++) {
      int v = bytes[j] & 0xFF;
      hexChars[j * 2] = HEX_ARRAY[v >>> 4];
      hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
    }
    return new String(hexChars, StandardCharsets.UTF_8);
  }

  public String calculateHash(InputStream stream, int pos) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      DigestInputStream dis = new DigestInputStream(stream, md);
      while (dis.read() != -1);
      return bytesToHex(md.digest());
    } catch (IOException e) {
      throw new ExtractionException("IOException occurred when calculating the hash of the file #" + pos, e);
    } catch (NoSuchAlgorithmException e) {
      throw new ExtractionException("NoSuchAlgorithmException occurred when calculating the hash of the file #" + pos, e);
    }
  }
}
