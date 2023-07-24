package src.sort.comparators;

import java.util.Comparator;
import src.produto.Produto;

/**
 * DescriptionComparator
 */
public class DescriptionComparator implements Comparator<Produto> {
  private final boolean ascending;

  public DescriptionComparator(final boolean ascending) {
    this.ascending = ascending;
  }

  @Override
  public int compare(final Produto p1, final Produto p2) {
    if (ascending) {
      return p1.getDescricao().compareToIgnoreCase(p2.getDescricao());
    }
    return p2.getDescricao().compareToIgnoreCase(p1.getDescricao());
  }
}
