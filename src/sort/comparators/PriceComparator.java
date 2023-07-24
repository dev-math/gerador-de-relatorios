package src.sort.comparators;

import java.util.Comparator;
import src.produto.Produto;

/**
 * PriceComparator
 */
public class PriceComparator implements Comparator<Produto> {
  private final boolean ascending;

  public PriceComparator(boolean ascending) { this.ascending = ascending; }

  @Override
  public int compare(Produto p1, Produto p2) {
    if (ascending) {
      return Double.compare(p1.getPreco(), p2.getPreco());
    }
    return Double.compare(p2.getPreco(), p1.getPreco());
  }
}
