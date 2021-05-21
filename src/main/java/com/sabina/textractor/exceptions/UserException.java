package com.sabina.textractor.exceptions;

public class UserException extends RuntimeException {
  private final String message;

  public UserException (String message, Exception e) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

}
