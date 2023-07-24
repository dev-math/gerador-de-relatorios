package src.sort;

import java.util.Comparator;
import java.util.List;
import src.produto.Produto;

/**
 * SortingStrategy
 */
public interface SortStrategy {
  void sort(List<Produto> produtos, Comparator<Produto> comparator);
}
