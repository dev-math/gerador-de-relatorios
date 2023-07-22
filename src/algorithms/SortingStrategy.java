package src.algorithms;

import java.util.Comparator;

import src.Produto;

/**
 * SortingStrategy
 */
public interface SortingStrategy {
  void sort(Produto[] produtos, Comparator<Produto> comparator);
}
