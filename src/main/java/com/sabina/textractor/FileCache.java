package com.sabina.textractor;

import com.sabina.textractor.exceptions.ExtractionException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;

public class FileCache {

  private final String filename = "cache.json";
  private static FileCache fileCache = null;
  private HashMap<String, String> cache;
  private final Gson gson;

  private FileCache() {
    gson = new GsonBuilder().create();
    File file = new File(filename);
    try {
      file.createNewFile();
    } catch (IOException e) {
      throw new ExtractionException("IOException occurred when creating FileCache class", e);
    }
    try {
      cache = gson.fromJson(new InputStreamReader(new FileInputStream(file)), HashMap.class);
    } catch (FileNotFoundException e) {
      throw new ExtractionException("FileNotFoundException occurred when creating FileCache class", e);
    }
    if (cache == null) {
      cache = new HashMap<>();
    }
  }

  public static FileCache getInstance() {
    if (fileCache == null) {
      fileCache = new FileCache();
    }
    return fileCache;
  }

  public void updateCache(String hash, String content) {
    cache.putIfAbsent(hash, content);
  }

  public String get(String hash) {
    return cache.get(hash);
  }

  public boolean contains(String hash) {
    return cache != null && cache.containsKey(hash);
  }

  public void write() {

    try {
      OutputStream os = new FileOutputStream(filename);
      String json = gson.toJson(cache);
      IOUtils.write(json, os, StandardCharsets.UTF_8.name());
    } catch (FileNotFoundException e) {
      throw new ExtractionException("FileNotFoundException occurred when creating output stream for cache", e);
    } catch (IOException e) {
      throw new ExtractionException("IOException occurred when writing cache to .json", e);
    }
  }

}
