package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import src.filters.FilterStrategy;
import src.filters.FilterTypes;
import src.produto.Produto;
import src.produto.ProdutoLoader;
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
      printHelpMessage();
    }

    String opcao_algoritmo = args[0];
    SortStrategy sortStrategy =
        SortTypes.getSortingStrategyByName(opcao_algoritmo);

    String opcao_criterio_ord = args[1];
    Comparator<Produto> comparador =
        ComparatorTypes.getComparatorByName(opcao_criterio_ord);

    String opcao_criterio_filtro = args[2];
    String opcao_parametro_filtro = args[3];
    FilterStrategy filter = FilterTypes.getFilterStrategyByName(
        opcao_criterio_filtro, opcao_parametro_filtro);

    List<Produto> produtosList = ProdutoLoader.loadProductsFromCSV(args[4]);
    List<FormatTypes> formatOptions = parseFormatFlags(args);

    GeradorDeRelatorios gdr = new GeradorDeRelatorios(
        produtosList, sortStrategy, comparador, filter, formatOptions);

    try {
      gdr.geraRelatorio("saida.html");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void printHelpMessage() {
    System.out.println("Uso:");
    System.out.println(
        "\tjava " + Main.class.getName() +
        " <algoritmo> <critério de ordenação> <critério de filtragem> <parâmetro de filtragem> <caminho do arquivo CSV>");
    System.out.println("Onde:");
    System.out.println("\talgoritmo: 'quick' ou 'insertion'");
    System.out.println(
        "\tcritério de ordenação: 'preco_c' ou 'preco_d' ou 'descricao_c' ou 'descricao_d' ou 'estoque_c' ou 'estoque_d'");
    System.out.println(
        "\tcritério de filtragem: 'todos' ou 'estoque_menor_igual' ou 'categoria_igual', 'descricao_contem', 'preco_intervalo'");
    System.out.println(
        "\t\t'preco_intervalo' deve conter o intervalo no seguinte formato: 'preco_minimo@preco_maximo'");
    System.out.println(
        "\t\texemplo: '5@10' => intervalo de 5 até $10 (incluso/fechado no valor mínimo e máximo)");
    System.out.println(
        "\tparâmetro de filtragem: argumetno adicional necessário para a filtragem");
    System.out.println(
        "\tcaminho do arquivo CSV: caminho para o arquivo CSV contendo os dados dos produtos");
    System.out.println();
    System.exit(1);
  }
}
