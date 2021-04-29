package com.sabina.textractor;

public interface IStringConverter<T> {
  T convert(String value);
}
