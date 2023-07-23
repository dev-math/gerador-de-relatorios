package src.filters;

import src.Produto;

/**
 * NoFilter
 */
public class NoFilter implements FilterStrategy {
  public void setFilterArg(final String filterArg) {}

  @Override
  public boolean test(final Produto product) {
    return true;
  }
}
