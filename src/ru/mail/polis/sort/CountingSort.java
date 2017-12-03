package ru.mail.polis.sort;

import ru.mail.polis.structures.IntKeyObject;

/**
 * Created by Nechaev Mikhail
 * Since 27/11/2017.
 */
public class CountingSort<T extends IntKeyObject> implements Sort<T> {

  public CountingSort() {
        /* empty */
  }

  private int findMaxKey(T[] array) {
    int maxKey = array[0].getKey();
    for (int i = 0; i < array.length; i++) {
      if (maxKey < array[i].getKey())
        maxKey = array[i].getKey();
    }
    return maxKey;
  }

  @Override
  @SuppressWarnings("unchecked")
  public void sort(T[] array) {
    int[] blocks = new int[findMaxKey(array) + 1];
    for (T i : array) {
      blocks[i.getKey()]++;
    }
    for (int i = 1; i < blocks.length; i++) {
      blocks[i] += blocks[i - 1];
    }
    T[] result = (T[]) new IntKeyObject<?>[array.length];
    for (int i = array.length - 1; i >= 0; i--) {
      result[--blocks[(array[i].getKey())]] = array[i];
    }
    System.arraycopy(result, 0, array, 0, result.length);
  }

}
