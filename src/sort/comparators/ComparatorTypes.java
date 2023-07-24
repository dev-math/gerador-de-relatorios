package src.sort.comparators;

import java.util.Comparator;

import src.produto.Produto;

/**
 * ComparatorTypes
 */
public enum ComparatorTypes {
  DESCRIPTION("descricao_c", new DescriptionComparator()),
  PRICE("preco_c", new PriceComparator()),
  STOCK_QUANTITY("estoque_c", new StockQuantityComparator());

  private String comparatorName;
  private Comparator<Produto> comparator;

  private ComparatorTypes(String comparatorName,
                          Comparator<Produto> comparator) {
    this.comparatorName = comparatorName;
    this.comparator = comparator;
  }

  // Método para obter o Comparator a partir do argumento de ordenação recebido
  public static Comparator<Produto> getComparatorByName(String name) {
    for (ComparatorTypes type : ComparatorTypes.values()) {
      if (type.comparatorName.equals(name)) {
        return type.comparator;
      }
    }
    // throw new RuntimeException("Critério de ordenação inválido!");
    throw new IllegalArgumentException("Critério de ordenação inválido: " +
                                       name);
  }
}
