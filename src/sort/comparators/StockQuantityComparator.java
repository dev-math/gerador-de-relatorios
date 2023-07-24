package src.sort.comparators;

import java.util.Comparator;

import src.produto.Produto;

/**
 * StockQuantityComparator
 */
public class StockQuantityComparator implements Comparator<Produto> {
  @Override
  public int compare(Produto p1, Produto p2) {
    return Integer.compare(p1.getQtdEstoque(), p2.getQtdEstoque());
  }
}
