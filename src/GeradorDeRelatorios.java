package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import src.filters.FilterStrategy;
import src.produto.Produto;
import src.produto.formatacao.FormatTypes;
import src.sort.SortStrategy;

public class GeradorDeRelatorios {
  private final List<Produto> produtos;
  private final SortStrategy sortingStrategy;
  private final Comparator<Produto> criterioOrdenacao;
  private final FilterStrategy filter;

  public GeradorDeRelatorios(final List<Produto> produtos,
                             final SortStrategy sortingStrategy,
                             final Comparator<Produto> criterioOrdenacao,
                             final FilterStrategy filter,
                             final List<FormatTypes> formatDecorators) {

    this.produtos = new ArrayList<>(produtos);
    this.sortingStrategy = sortingStrategy;
    this.criterioOrdenacao = criterioOrdenacao;
    this.filter = filter;
  }

  public void geraRelatorio(final String arquivoSaida) throws IOException {
    sortingStrategy.sort(produtos, criterioOrdenacao);

    final PrintWriter out = new PrintWriter(arquivoSaida);

    out.println("<!DOCTYPE html><html>");
    out.println("<head><title>Relatorio de produtos</title></head>");
    out.println("<body>");
    out.println("Relatorio de Produtos:");
    out.println("<ul>");

    int count = 0;

    for (final Produto p : produtos) {
      final boolean selecionado = filter.test(p);

      if (!selecionado) {
        continue;
      }

      out.println("<li>" + p.formataParaImpressao() + "</li>");
      count++;
    }

    out.println("</ul>");
    out.println(count + " produtos listados, de um total de " +
                produtos.size() + ".");
    out.println("</body>");
    out.println("</html>");

    out.close();
  }
}
