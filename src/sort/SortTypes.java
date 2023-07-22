package src.sort;

/**
 * SortEnum
 */
public enum SortTypes {
  INSERTIONSORT("insertion", new InsertionSort()),
  QUICKSORT("quick", new QuickSort());

  private String sortAlgorithmName;
  private SortStrategy sortAlgorithm;

  SortTypes(String sortAlgorithmName, SortStrategy sortAlgorithm) {
    this.sortAlgorithmName = sortAlgorithmName;
    this.sortAlgorithm = sortAlgorithm;
  }

  public String getSortAlgorithmName() { return sortAlgorithmName; }

  public SortStrategy getSortAlgorithm() { return sortAlgorithm; }

  // Método para obter o SortingStrategy a partir do nome do algoritmo
  public static SortStrategy getSortingStrategyByName(String name) {
    for (SortTypes type : SortTypes.values()) {
      if (type.sortAlgorithmName.equals(name)) {
        return type.sortAlgorithm;
      }
    }
    // throw new RuntimeException("Algoritmo inválido!");
    throw new IllegalArgumentException("Algoritmo de ordenação inválido: " + name);
  }
}
