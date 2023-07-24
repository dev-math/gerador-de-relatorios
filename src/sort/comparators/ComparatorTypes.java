package src.sort.comparators;

import java.util.Comparator;
import src.produto.Produto;

/**
 * ComparatorTypes
 */
public enum ComparatorTypes {
  DESCRIPTION_ASC("descricao_c", new DescriptionComparator(true)),
  DESCRIPTION_DES("descricao_d", new DescriptionComparator(false)),
  PRICE_ASC("preco_c", new PriceComparator(true)),
  PRICE_DES("preco_d", new PriceComparator(false)),
  STOCK_QUANTITY_ASC("estoque_c", new StockQuantityComparator(true)),
  STOCK_QUANTITY_DES("estoque_d", new StockQuantityComparator(false));

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
