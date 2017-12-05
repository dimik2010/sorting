package ru.mail.polis.sort;

import ru.mail.polis.structures.Numerical;
import ru.mail.polis.structures.SimpleInteger;
import ru.mail.polis.structures.SimpleString;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Nechaev Mikhail
 * Since 27/11/2017.
 */
public class LSDSort<T extends Numerical<T>> implements Sort<T> {

  public LSDSort() {
        /* empty */
  }

  @Override
  @SuppressWarnings("unchecked")
  public void sort(T[] array) {
    final int radix = array[0].getDigitMaxValue();
    int digits = array[0].getDigitCount();
    for (T val : array) {
      if (val.getDigitCount() > digits)
        digits = val.getDigitCount();
    }
    for (int i = digits-1; i >=0; i--) {
      int[] blocks = new int[radix];
      for (T val : array) {
        blocks[val.getDigit(i)]++;
      }
      for (int j = 1; j < blocks.length; j++) {
        blocks[j] += blocks[j - 1];
      }
      T[] result = (T[]) new Numerical[array.length];
      for (int j = array.length - 1; j >= 0; j--) {
        result[--blocks[(array[j].getDigit(i))]] = array[j];
      }
      System.arraycopy(result, 0, array, 0, result.length);
    }

  }

  public static void main(String[] args) {
    LSDSort<SimpleInteger> sorter = new LSDSort<>();
    SimpleInteger[] arr = SortUtils.generateSimpleIntegerArray(100, 100);
    sorter.sort(arr);
    System.out.println(Arrays.toString(arr));
  }
}
