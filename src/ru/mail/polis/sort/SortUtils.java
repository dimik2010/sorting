package ru.mail.polis.sort;

import ru.mail.polis.structures.*;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SortUtils {

  private static final Random r = ThreadLocalRandom.current();

  public static void swap(int[] a, int i, int j) {
    int x = a[i];
    a[i] = a[j];
    a[j] = x;
  }

  public static <T> void swap(T[] a, int i, int j) {
    T x = a[i];
    a[i] = a[j];
    a[j] = x;
  }

  public static SimpleInteger[] generateSimpleIntegerArray(int n) {
    SimpleInteger[] res = new SimpleInteger[n];
    Random rand = new Random();
    for (int i = 0; i < n; i++) {
      res[i] = new SimpleInteger(rand.nextInt(10000) + 10000);
    }
    for (int i = res.length - 1; i > 0; i--) {
      int j = rand.nextInt(i + 1);
      SortUtils.swap(res, i, j);
    }
    return res;
  }

  public static SimpleString[] generateSimpleStringArray(int n) {
    SimpleString[] arr = new SimpleString[2*n];
    for (int i = 0; i < arr.length; i++) {
      StringBuilder stringBuilder = new StringBuilder();
      for (int j = 0; j < 5; j++) {
        stringBuilder.append(String.valueOf((char)(r.nextInt('z' - 'a' + 1) + 'a')));
      }
      arr[i] = new SimpleString(stringBuilder.toString());
    }
    return arr;
  }

  public static IntKeyStringValueObject[] generateIntKeyStringValueObjectArray(int n) {
    IntKeyStringValueObject[] arr = new IntKeyStringValueObject[2*n];
    int[] nums = generateArray(n);
    for (int i = 0; i < arr.length; i++) {
      arr[i] = new IntKeyStringValueObject(nums[i], String.valueOf((char)(r.nextInt('z' - 'a' + 1) + 'a')));
    }
    return arr;

  }

  public static int[] generateArray(int n) {
    int[] a = new int[2 * n];
    for (int i = 0; i < a.length; i += 2) {
      a[i] = i;
      a[i + 1] = i;
    }
    for (int i = a.length - 1; i > 0; i--) {
      int j = r.nextInt(i + 1);
      SortUtils.swap(a, i, j);
    }
    return a;
  }


  public static boolean isArraySorted(int[] a) {
    boolean isSorted = true;
    for (int i = 0; i < a.length - 1 && isSorted; i++) {
      isSorted = a[i] <= a[i + 1];
    }
    return isSorted;
  }

  public static <T extends Comparable<? super T>> boolean isArraySorted(T[] array) {
    boolean isSorted = true;
    for (int i = 0; i < array.length - 1 && isSorted; i++) {
      isSorted = array[i].compareTo(array[i + 1]) <= 0;
    }
    return isSorted;
  }

  public static <T> boolean isArraySorted(T[] array, Comparator<T> comparator) {
    boolean isSorted = true;
    for (int i = 0; i < array.length - 1 && isSorted; i++) {
      isSorted = comparator.compare(array[i], array[i + 1]) <= 0;
    }
    return isSorted;
  }
}
