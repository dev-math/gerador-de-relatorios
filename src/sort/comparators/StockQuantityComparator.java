package src.sort.comparators;

import java.util.Comparator;
import src.produto.Produto;

/**
 * StockQuantityComparator
 */
public class StockQuantityComparator implements Comparator<Produto> {
  private final boolean ascending;

  public StockQuantityComparator(final boolean ascending) {
    this.ascending = ascending;
  }

  @Override
  public int compare(final Produto p1, final Produto p2) {
    if (ascending) {
      return Integer.compare(p1.getQtdEstoque(), p2.getQtdEstoque());
    }

    return Integer.compare(p2.getQtdEstoque(), p1.getQtdEstoque());
  }
}
