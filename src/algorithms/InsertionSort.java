package src.algorithms;

import java.util.Comparator;
import src.Produto;

public class InsertionSort implements SortingStrategy {
  public void sort(Produto[] array, Comparator<Produto> comparator) {
    for (int i = 1; i < array.length; i++) {
      Produto current = array[i];
      int j = i - 1;

      while (j >= 0 && comparator.compare(array[j], current) > 0) {
        array[j + 1] = array[j];
        j--;
      }

      array[j + 1] = current;
    }
  }
}
