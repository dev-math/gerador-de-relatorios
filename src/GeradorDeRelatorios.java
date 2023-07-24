package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import src.filters.FilterStrategy;
import src.produto.Produto;
import src.produto.ProdutoItalico;
import src.produto.ProdutoNegrito;
import src.produto.formatacao.FormatTypes;
import src.sort.SortStrategy;

public class GeradorDeRelatorios {
  private Produto[] produtos;
  private SortStrategy sortingStrategy;
  private Comparator<Produto> criterioOrdenacao;
  private FilterStrategy filter;
  private List<FormatTypes> formatDecorators;

  public GeradorDeRelatorios(Produto[] produtos, SortStrategy sortingStrategy,
                             Comparator<Produto> criterioOrdenacao,
                             FilterStrategy filter,
                             List<FormatTypes> formatDecorators) {

    this.produtos = new Produto[produtos.length];

    for (int i = 0; i < produtos.length; i++) {

      this.produtos[i] = produtos[i];
    }

    this.sortingStrategy = sortingStrategy;
    this.criterioOrdenacao = criterioOrdenacao;
    this.formatDecorators = formatDecorators;
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
        for (FormatTypes formatType : formatDecorators) {
          switch (formatType) {
          case BOLD:
            p = new ProdutoNegrito(p);
            break;
          case ITALIC:
            p = new ProdutoItalico(p);
            break;
          default:
            break;
          }
        }

        out.println("<li>" + p.formataParaImpressao() + "</li>");
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
