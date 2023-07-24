package src.sort;

import java.util.Comparator;

import src.produto.Produto;

public class QuickSort implements SortStrategy {
  public void sort(Produto[] array, Comparator<Produto> comparator) {
    quickSort(array, 0, array.length - 1, comparator);
  }

  private static void quickSort(Produto[] array, int left, int right,
                                Comparator<Produto> comparator) {
    if (left < right) {
      int partitionIndex = partition(array, left, right, comparator);
      quickSort(array, left, partitionIndex - 1, comparator);
      quickSort(array, partitionIndex + 1, right, comparator);
    }
  }

  private static int partition(Produto[] array, int left, int right,
                               Comparator<Produto> comparator) {
    Produto pivot = array[right];
    int i = left - 1;

    for (int j = left; j < right; j++) {
      if (comparator.compare(array[j], pivot) < 0) {
        i++;
        Produto temp = array[i];
        array[i] = array[j];
        array[j] = temp;
      }
    }

    Produto temp = array[i + 1];
    array[i + 1] = array[right];
    array[right] = temp;

    return i + 1;
  }
}
