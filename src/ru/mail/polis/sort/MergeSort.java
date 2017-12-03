package ru.mail.polis.sort;


import java.io.*;
import java.util.Arrays;

public class MergeSort<T extends Comparable<T>> extends AbstractSortOnComparisons<T> {

  private void merge(T[] array, T[] temp, int left, int mid, int right) {
    int n = array.length;
    for (int i = left; i <= right; i++) {
      temp[i] = array[i];
    }
    int i = left;
    int j = mid+1;
    for(int k = left; k <= right; k++) {
      if (i > mid) array[k] = temp[j++];
      else if (j > right) array[k] = temp[i++];
      else if (temp[i].compareTo(temp[j]) <= 0) array[k] = temp[i++];
      else array[k] = temp[j++];
    }
  }

  @Override
  public void sort(T[] array) {
    T[] temp = Arrays.copyOf(array, array.length);
    for (int len = 1; len < array.length; len *= 2) {
      for (int left = 0; left < array.length - len; left += len*2) {
        int mid = left + len - 1;
        int right = Math.min(left + len*2 - 1, array.length-1);
        merge(array,temp, left, mid, right);
      }
    }
  }
}
