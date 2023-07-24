package src.filters;

/**
 * FilterTypes
 */
public enum FilterTypes {
  NONE("todos", new NoFilter()),
  STOCK_LESS_EQUAL("estoque_menor_igual", new StockLessThanOrEqualFilter()),
  CATEGORY_EQUALS("categoria_igual", new CategoryEqualsFilter()),
  DESCRIPTION_CONTAINS("descricao_contem", new DescriptionContainsFilter()),
  PRICE_RANGE("preco_intervalo", new PriceRangeFilter());

  // Método para obter o FilterStrategy a partir do nome do filtro
  public static FilterStrategy getFilterStrategyByName(final String name,
                                                       final String filterArg) {
    for (final FilterTypes type : FilterTypes.values()) {
      if (type.filterName.equals(name)) {
        type.filter.setFilterArg(filterArg);
        return type.filter;
      }
    }
    // throw new RuntimeException("Filtro invalido!");
    throw new IllegalArgumentException("Filtro inválido: " + name);
  }

  private final String filterName;

  private final FilterStrategy filter;

  private FilterTypes(final String filterName, final FilterStrategy filter) {
    this.filterName = filterName;
    this.filter = filter;
  }
}
