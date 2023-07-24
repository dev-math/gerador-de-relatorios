package src.sort;

import java.util.Comparator;
import java.util.List;
import src.produto.Produto;

public class QuickSort implements SortStrategy {
  private static void quickSort(final List<Produto> produtos, final int left,
                                final int right,
                                final Comparator<Produto> comparator) {
    if (left < right) {
      final int partitionIndex = partition(produtos, left, right, comparator);
      quickSort(produtos, left, partitionIndex - 1, comparator);
      quickSort(produtos, partitionIndex + 1, right, comparator);
    }
  }

  private static int partition(final List<Produto> produtos, final int left,
                               final int right,
                               final Comparator<Produto> comparator) {
    final Produto pivot = produtos.get(right);
    int i = left - 1;

    for (int j = left; j < right; j++) {
      if (comparator.compare(produtos.get(j), pivot) < 0) {
        i++;
        final Produto temp = produtos.get(i);
        produtos.set(i, produtos.get(j));
        produtos.set(j, temp);
      }
    }

    final Produto temp = produtos.get(i + 1);
    produtos.set(i + 1, produtos.get(right));
    produtos.set(right, temp);

    return i + 1;
  }

  @Override
  public void sort(final List<Produto> produtos,
                   final Comparator<Produto> comparator) {
    quickSort(produtos, 0, produtos.size() - 1, comparator);
  }
}
