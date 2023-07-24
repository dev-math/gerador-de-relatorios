package src.filters;

import src.produto.Produto;

/**
 * PriceRangeFilter - Filtra produtos por preço dentro de um intervalo.
 */
public class PriceRangeFilter implements FilterStrategy {
  private double minPrice;
  private double maxPrice;

  @Override
  public void setFilterArg(final String filterArg) {
    final String[] prices = filterArg.split("@");
    if (prices.length == 2) {
      try {
        minPrice = Double.parseDouble(prices[0]);
        maxPrice = Double.parseDouble(prices[1]);
      } catch (final NumberFormatException e) {
        throw new IllegalArgumentException("Intervalo de preço inválido: " +
                                           filterArg);
      }
    } else {
      throw new IllegalArgumentException("Intervalo de preço inválido: " +
                                         filterArg);
    }
  }

  @Override
  public boolean test(final Produto produto) {
    final double preco = produto.getPreco();
    return preco >= minPrice && preco <= maxPrice;
  }
}
