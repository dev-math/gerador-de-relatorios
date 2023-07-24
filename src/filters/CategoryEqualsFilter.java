package src.filters;

import src.produto.Produto;

/**
 * CategoryEqualsFilter
 */
public class CategoryEqualsFilter implements FilterStrategy {
  private String category;

  public void setFilterArg(final String filterArg) { this.category = filterArg; }

  @Override
  public boolean test(final Produto product) {
    return product.getCategoria().equalsIgnoreCase(category);
  }
}
