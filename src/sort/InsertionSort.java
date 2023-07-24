package src.sort;

import java.util.Comparator;
import java.util.List;
import src.produto.Produto;

public class InsertionSort implements SortStrategy {
  @Override
  public void sort(final List<Produto> produtos,
                   final Comparator<Produto> comparator) {
    for (int i = 1; i < produtos.size(); i++) {
      final Produto current = produtos.get(i);
      int j = i - 1;

      while (j >= 0 && comparator.compare(produtos.get(j), current) > 0) {
        produtos.set(j + 1, produtos.get(j));
        j--;
      }

      produtos.set(j + 1, current);
    }
  }
}
