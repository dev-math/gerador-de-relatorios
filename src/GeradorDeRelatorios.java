package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import src.algorithms.SortingStrategy;
import src.comparators.DescriptionComparator;
import src.comparators.PriceComparator;
import src.comparators.StockQuantityComparator;

public class GeradorDeRelatorios {

  public static final String CRIT_DESC_CRESC = "descricao_c";
  public static final String CRIT_PRECO_CRESC = "preco_c";
  public static final String CRIT_ESTOQUE_CRESC = "estoque_c";

  public static final String FILTRO_TODOS = "todos";
  public static final String FILTRO_ESTOQUE_MENOR_OU_IQUAL_A =
      "estoque_menor_igual";
  public static final String FILTRO_CATEGORIA_IGUAL_A = "categoria_igual";

  // operador bit a bit "ou" pode ser usado para combinar mais de
  // um estilo de formatacao simultaneamente (veja como no main)
  public static final int FORMATO_PADRAO = 0b0000;
  public static final int FORMATO_NEGRITO = 0b0001;
  public static final int FORMATO_ITALICO = 0b0010;

  private Produto[] produtos;
  private SortingStrategy sortingStrategy;
  private String criterio;
  private String filtro;
  private String argFiltro;
  private int format_flags;

  public GeradorDeRelatorios(Produto[] produtos,
                             SortingStrategy sortingStrategy, String criterio,
                             String filtro, String argFiltro,
                             int format_flags) {

    this.produtos = new Produto[produtos.length];

    for (int i = 0; i < produtos.length; i++) {

      this.produtos[i] = produtos[i];
    }

    this.sortingStrategy = sortingStrategy;
    this.criterio = criterio;
    this.format_flags = format_flags;
    this.filtro = filtro;
    this.argFiltro = argFiltro;
  }

  public void debug() {

    System.out.println("Gerando relatório para array contendo " +
                       produtos.length + " produto(s)");
    System.out.println("parametro filtro = '" + argFiltro + "'");
  }

  public void geraRelatorio(String arquivoSaida) throws IOException {
    debug();

    Comparator<Produto> comparator;
    if (criterio.equals(CRIT_DESC_CRESC)) {
      comparator = new DescriptionComparator();
    } else if (criterio.equals(CRIT_PRECO_CRESC)) {
      comparator = new PriceComparator();
    } else if (criterio.equals(CRIT_ESTOQUE_CRESC)) {
      comparator = new StockQuantityComparator();
    } else {
      throw new RuntimeException("Critério de ordenação inválido!");
    }
    sortingStrategy.sort(produtos, comparator);

    PrintWriter out = new PrintWriter(arquivoSaida);

    out.println("<!DOCTYPE html><html>");
    out.println("<head><title>Relatorio de produtos</title></head>");
    out.println("<body>");
    out.println("Relatorio de Produtos:");
    out.println("<ul>");

    int count = 0;

    for (int i = 0; i < produtos.length; i++) {

      Produto p = produtos[i];
      boolean selecionado = false;

      if (filtro.equals(FILTRO_TODOS)) {

        selecionado = true;
      } else if (filtro.equals(FILTRO_ESTOQUE_MENOR_OU_IQUAL_A)) {

        if (p.getQtdEstoque() <= Integer.parseInt(argFiltro))
          selecionado = true;
      } else if (filtro.equals(FILTRO_CATEGORIA_IGUAL_A)) {

        if (p.getCategoria().equalsIgnoreCase(argFiltro))
          selecionado = true;
      } else {
        throw new RuntimeException("Filtro invalido!");
      }

      if (selecionado) {

        out.print("<li>");

        if ((format_flags & FORMATO_ITALICO) > 0) {

          out.print("<span style=\"font-style:italic\">");
        }

        if ((format_flags & FORMATO_NEGRITO) > 0) {

          out.print("<span style=\"font-weight:bold\">");
        }

        out.print(p.formataParaImpressao());

        if ((format_flags & FORMATO_NEGRITO) > 0) {

          out.print("</span>");
        }

        if ((format_flags & FORMATO_ITALICO) > 0) {

          out.print("</span>");
        }

        out.println("</li>");
        count++;
      }
    }

    out.println("</ul>");
    out.println(count + " produtos listados, de um total de " +
                produtos.length + ".");
    out.println("</body>");
    out.println("</html>");

    out.close();
  }
}
