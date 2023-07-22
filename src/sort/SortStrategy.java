package src.sort;

import java.util.Comparator;

import src.Produto;

/**
 * SortingStrategy
 */
public interface SortStrategy {
  void sort(Produto[] produtos, Comparator<Produto> comparator);
}
