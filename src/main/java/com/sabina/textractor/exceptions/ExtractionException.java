package com.sabina.textractor.exceptions;

public class ExtractionException extends RuntimeException {
  private final String message;

  public ExtractionException (String message, Exception e) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
