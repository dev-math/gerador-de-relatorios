package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import src.filters.FilterStrategy;
import src.filters.FilterTypes;
import src.produto.Produto;
import src.produto.ProdutoPadrao;
import src.produto.formatacao.FormatTypes;
import src.sort.SortStrategy;
import src.sort.SortTypes;
import src.sort.comparators.ComparatorTypes;

/**
 * Main
 */
public class Main {
  private static List<FormatTypes> parseFormatFlags(String[] args) {
    String[] formatOptions = new String[2];

    if (args.length > 4) {
      formatOptions[0] = args[4];
    }

    if (args.length > 5) {
      formatOptions[1] = args[5];
    }

    List<FormatTypes> formatList = new ArrayList<>();

    for (String option : formatOptions) {
      FormatTypes formatType = FormatTypes.getFormatTypeByName(option);
      formatList.add(formatType);
    }

    return formatList;
  }

  public static void main(String[] args) {
    if (args.length < 4) {

      System.out.println("Uso:");
      System.out.println(
          "\tjava " + Main.class.getName() +
          " <algoritmo> <critério de ordenação> <critério de filtragem> <parâmetro de filtragem> <opções de formatação>");
      System.out.println("Onde:");
      System.out.println("\talgoritmo: 'quick' ou 'insertion'");
      System.out.println(
          "\tcriterio de ordenação: 'preco_c' ou 'descricao_c' ou 'estoque_c'");
      System.out.println(
          "\tcriterio de filtragem: 'todos' ou 'estoque_menor_igual' ou 'categoria_igual'");
      System.out.println(
          "\tparâmetro de filtragem: argumentos adicionais necessários para a filtragem");
      System.out.println("\topções de formatação: 'negrito' e/ou 'italico'");
      System.out.println();
      System.exit(1);
    }

    String opcao_algoritmo = args[0];
    SortStrategy sortStrategy = SortTypes.getSortingStrategyByName(opcao_algoritmo);

    String opcao_criterio_ord = args[1];
    Comparator<Produto> comparador =
        ComparatorTypes.getComparatorByName(opcao_criterio_ord);

    String opcao_criterio_filtro = args[2];
    String opcao_parametro_filtro = args[3];
    FilterStrategy filter = FilterTypes.getFilterStrategyByName(
        opcao_criterio_filtro, opcao_parametro_filtro);

    List<Produto> produtosList = Arrays.asList(carregaProdutos());
    List<FormatTypes> formatOptions = parseFormatFlags(args);

    GeradorDeRelatorios gdr = new GeradorDeRelatorios(
        produtosList, sortStrategy, comparador, filter, formatOptions);

    try {
      gdr.geraRelatorio("saida.html");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Produto[] carregaProdutos() {
    return new Produto[] {
        new ProdutoPadrao(1, "O Hobbit", "Livros", 2, 34.90),
        new ProdutoPadrao(2, "Notebook Core i7", "Informatica", 5, 1999.90),
        new ProdutoPadrao(3, "Resident Evil 4", "Games", 7, 79.90),
        new ProdutoPadrao(4, "iPhone", "Telefonia", 8, 4999.90),
        new ProdutoPadrao(5, "Calculo I", "Livros", 20, 55.00),
        new ProdutoPadrao(6, "Power Glove", "Games", 3, 499.90),
        new ProdutoPadrao(7, "Microsoft HoloLens", "Informatica", 1, 19900.00),
        new ProdutoPadrao(8, "OpenGL Programming Guide", "Livros", 4, 89.90),
        new ProdutoPadrao(9, "Vectrex", "Games", 1, 799.90),
        new ProdutoPadrao(10, "Carregador iPhone", "Telefonia", 15, 499.90),
        new ProdutoPadrao(11, "Introduction to Algorithms", "Livros", 7,
                          315.00),
        new ProdutoPadrao(12, "Daytona USA (Arcade)", "Games", 1, 12000.00),
        new ProdutoPadrao(13, "Neuromancer", "Livros", 5, 45.00),
        new ProdutoPadrao(14, "Nokia 3100", "Telefonia", 4, 249.99),
        new ProdutoPadrao(15, "Oculus Rift", "Games", 1, 3600.00),
        new ProdutoPadrao(16, "Trackball Logitech", "Informatica", 1, 250.00),
        new ProdutoPadrao(17, "After Burner II (Arcade)", "Games", 2, 8900.0),
        new ProdutoPadrao(18, "Assembly for Dummies", "Livros", 30, 129.90),
        new ProdutoPadrao(19, "iPhone (usado)", "Telefonia", 3, 3999.90),
        new ProdutoPadrao(20, "Game Programming Patterns", "Livros", 1, 299.90),
        new ProdutoPadrao(21, "Playstation 2", "Games", 10, 499.90),
        new ProdutoPadrao(22, "Carregador Nokia", "Telefonia", 14, 89.00),
        new ProdutoPadrao(23, "Placa Aceleradora Voodoo 2", "Informatica", 4,
                          189.00),
        new ProdutoPadrao(24, "Stunts", "Games", 3, 19.90),
        new ProdutoPadrao(25, "Carregador Generico", "Telefonia", 9, 30.00),
        new ProdutoPadrao(26, "Monitor VGA 14 polegadas", "Informatica", 2,
                          199.90),
        new ProdutoPadrao(27, "Nokia N-Gage", "Telefonia", 9, 699.00),
        new ProdutoPadrao(
            28, "Disquetes Maxell 5.25 polegadas (caixa com 10 unidades)",
            "Informatica", 23, 49.00),
        new ProdutoPadrao(29, "Alone in The Dark", "Games", 11, 59.00),
        new ProdutoPadrao(30, "The Art of Computer Programming Vol. 1",
                          "Livros", 3, 240.00),
        new ProdutoPadrao(31, "The Art of Computer Programming Vol. 2",
                          "Livros", 2, 200.00),
        new ProdutoPadrao(32, "The Art of Computer Programming Vol. 3",
                          "Livros", 4, 270.00)};
  }
}
