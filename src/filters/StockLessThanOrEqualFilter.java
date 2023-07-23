package src.filters;

import src.Produto;

/**
 * StockLessThanOrEqualFilter
 */
public class StockLessThanOrEqualFilter implements FilterStrategy {
  private int maxStock;

  public void setFilterArg(final String filterArg) {
    this.maxStock = Integer.parseInt(filterArg);
  }

  @Override
  public boolean test(final Produto product) {
    return product.getQtdEstoque() <= maxStock;
  }
}
