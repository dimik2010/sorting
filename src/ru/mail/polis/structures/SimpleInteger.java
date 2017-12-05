package ru.mail.polis.structures;

import java.util.Arrays;

/**
 * Created by Nechaev Mikhail
 * Since 12/11/2017.
 */
public class SimpleInteger implements Numerical<SimpleInteger> {

  private static final int DIGIT_COUNT = 10;
  private final int intData;
  private final int[] data;
  private final int length;

  public SimpleInteger(Integer data) throws IllegalArgumentException {
    if (data == null) {
      throw new IllegalArgumentException("Source must be not null");
    }
    this.length = String.valueOf(data).length();
    this.data = new int[length];
    this.intData = data;
    int copyData = data;
    for (int i = length - 1; i >= 0; i--) {
      this.data[i] = copyData % 10;
      copyData /= 10;
    }
  }

  @Override
  public int getDigit(int index) throws IndexOutOfBoundsException {
    if (index < 0) {
      throw new IndexOutOfBoundsException("Negative index " + index);
    } else if (index >= getDigitCount()) {
      return 0;
    } else {
      return data[index];
    }
  }

  @Override
  public int getDigitMaxValue() {
    return DIGIT_COUNT;
  }

  @Override
  public int getDigitCount() {
    return length;
  }

  @Override
  public int compareTo(SimpleInteger anotherSimpleInteger) {
    return Integer.compare(intData, anotherSimpleInteger.intData);
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < data.length; i--) {
      stringBuilder.append(data[i]);
    }
    return stringBuilder.toString();
  }
}
