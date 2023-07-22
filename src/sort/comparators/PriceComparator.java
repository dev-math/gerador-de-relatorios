package src.sort.comparators;

import java.util.Comparator;
import src.Produto;

/**
 * PriceComparator
 */
public class PriceComparator implements Comparator<Produto> {
  @Override
  public int compare(Produto p1, Produto p2) {
    return Double.compare(p1.getPreco(), p2.getPreco());
  }
}
