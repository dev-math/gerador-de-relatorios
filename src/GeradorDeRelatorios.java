package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import src.filters.FilterStrategy;
import src.sort.SortStrategy;

public class GeradorDeRelatorios {
  // operador bit a bit "ou" pode ser usado para combinar mais de
  // um estilo de formatacao simultaneamente (veja como no main)
  public static final int FORMATO_PADRAO = 0b0000;
  public static final int FORMATO_NEGRITO = 0b0001;
  public static final int FORMATO_ITALICO = 0b0010;

  private Produto[] produtos;
  private SortStrategy sortingStrategy;
  private Comparator<Produto> criterioOrdenacao;
  private FilterStrategy filter;
  private int format_flags;

  public GeradorDeRelatorios(Produto[] produtos, SortStrategy sortingStrategy,
                             Comparator<Produto> criterioOrdenacao,
                             FilterStrategy filter, int format_flags) {

    this.produtos = new Produto[produtos.length];

    for (int i = 0; i < produtos.length; i++) {

      this.produtos[i] = produtos[i];
    }

    this.sortingStrategy = sortingStrategy;
    this.criterioOrdenacao = criterioOrdenacao;
    this.format_flags = format_flags;
    this.filter = filter;
  }

  public void geraRelatorio(String arquivoSaida) throws IOException {
    sortingStrategy.sort(produtos, criterioOrdenacao);

    PrintWriter out = new PrintWriter(arquivoSaida);

    out.println("<!DOCTYPE html><html>");
    out.println("<head><title>Relatorio de produtos</title></head>");
    out.println("<body>");
    out.println("Relatorio de Produtos:");
    out.println("<ul>");

    int count = 0;

    for (int i = 0; i < produtos.length; i++) {

      Produto p = produtos[i];
      boolean selecionado = filter.test(p);

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
