package src.sort;

import java.util.Comparator;

import src.produto.Produto;

/**
 * SortingStrategy
 */
public interface SortStrategy {
  void sort(Produto[] produtos, Comparator<Produto> comparator);
}
