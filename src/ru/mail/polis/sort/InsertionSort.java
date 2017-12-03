package ru.mail.polis.sort;

public class InsertionSort {
  public static <T extends Comparable> void sort(T[] array, int left, int right) {
    for (int i = left; i < right+1; i++) {
      for (int j = i; j > 0; j--) {
        if (array[j-1].compareTo(array[j]) > 0)
          SortUtils.swap(array, j-1, j);
        else break;
      }
    }
  }
}
