package src.sort.comparators;

import java.util.Comparator;
import src.Produto;

/**
 * DescriptionComparator
 */
public class DescriptionComparator implements Comparator<Produto> {
  @Override
  public int compare(Produto p1, Produto p2) {
    return p1.getDescricao().compareToIgnoreCase(p2.getDescricao());
  }
}
