import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import src.GeradorDeRelatorios;
import src.Produto;
import src.ProdutoPadrao;
import src.sort.QuickSort;
import src.sort.SortStrategy;
import src.sort.comparators.DescriptionComparator;
import src.sort.comparators.PriceComparator;

public class GeradorDeRelatoriosTest {
  private Produto[] produtos;
  private GeradorDeRelatorios gerador;
  private SortStrategy sortingStrategy = new QuickSort();

  @Before
  public void setUp() {
    produtos = new Produto[] {
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

  @After
  public void tearDown() {
    // Deletar o arquivo após o teste
    try {
      Files.deleteIfExists(Paths.get("saida_teste.html"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testGeraRelatorioTodos() {
    gerador = new GeradorDeRelatorios(produtos, sortingStrategy,
                                      new DescriptionComparator(), "todos", "",
                                      GeradorDeRelatorios.FORMATO_PADRAO);
    try {
      gerador.geraRelatorio("saida_teste.html");
      String content = Files.readString(Paths.get("saida_teste.html"));
      assertTrue(content.contains("Relatorio de Produtos"));
      assertTrue(content.contains("32 produtos listados, de um total de 32."));

      // Verificar a ordem crescente das descrições na lista e extrai os
      // produtos do relatório
      Document doc = Jsoup.parse(content);
      Elements lis = doc.select("ul li");
      String descricaoAnterior = "";

      Produto[] produtosDoRelatorio = new Produto[lis.size()];
      int index = 0;

      for (Element li : lis) {
        String[] parts = li.text().split(", ");
        String descricao = parts[0];
        String categoria = parts[1];
        double preco = Double.parseDouble(parts[2].replaceAll("[^\\d.]", ""));
        int quantidade = Integer.parseInt(parts[3].split(" ")[0]);
        produtosDoRelatorio[index++] =
            new ProdutoPadrao(0, descricao, categoria, quantidade, preco);

        assertTrue("As descrições não estão em ordem crescente",
                   descricao.compareToIgnoreCase(descricaoAnterior) >= 0);
        descricaoAnterior = descricao;
      }

      // Verificar se todos os produtos do vetor produtos estão presentes no
      // relatório
      assertTrue("Não gerou todos os produtos.",
                 produtosDoRelatorio.length == produtos.length);
      for (Produto produto : produtos) {
        assertTrue("Não gerou todos os produtos.",
                   produtoEstaPresente(produto, produtosDoRelatorio));
      }
    } catch (IOException e) {
      fail("Falha ao gerar o relatório: " + e.getMessage());
    }
  }

  private boolean produtoEstaPresente(Produto produto, Produto[] produtos) {
    for (Produto p : produtos) {
      if (produto.getDescricao().equals(p.getDescricao()) &&
          produto.getCategoria().equals(p.getCategoria()) &&
          produto.getPreco() == p.getPreco() &&
          produto.getQtdEstoque() == p.getQtdEstoque()) {
        return true;
      }
    }
    return false;
  }

  @Test
  public void testGeraRelatorioFiltragemEstoque() {
    gerador = new GeradorDeRelatorios(
        produtos, sortingStrategy, new PriceComparator(), "estoque_menor_igual",
        "10", GeradorDeRelatorios.FORMATO_NEGRITO);
    try {
      gerador.geraRelatorio("saida_teste.html");
      String content = Files.readString(Paths.get("saida_teste.html"));
      assertTrue(content.contains("26 produtos listados, de um total de 32."));

      // Verificar a ordem crescente dos preços na lista
      Document doc = Jsoup.parse(content);
      Elements lis = doc.select("ul li");
      double precoAnterior = 0;
      for (Element li : lis) {
        String[] parts = li.text().split(", ");
        double preco =
            Double.parseDouble(parts[2].substring(1).replace(",", ""));
        int quantidade = Integer.parseInt(parts[3].split(" ")[0]);

        assertTrue("A quantidade de estoque não é menor ou igual a 10",
                   quantidade <= 10);
        assertTrue("Os preços não estão em ordem crescente",
                   preco >= precoAnterior);
        precoAnterior = preco;
      }
    } catch (IOException e) {
      fail("Falha ao gerar o relatório: " + e.getMessage());
    }
  }

  @Test
  public void testGeraRelatorioFiltragemCategoria() {
    gerador = new GeradorDeRelatorios(
        produtos, sortingStrategy, new DescriptionComparator(),
        "categoria_igual", "Games", GeradorDeRelatorios.FORMATO_ITALICO);
    try {
      gerador.geraRelatorio("saida_teste.html");
      String content = Files.readString(Paths.get("saida_teste.html"));
      assertTrue(content.contains("9 produtos listados, de um total de 32."));

      // Verificar se todos os produtos no relatório são da categoria "Games"
      Document doc = Jsoup.parse(content);
      Elements lis = doc.select("ul li");
      for (Element li : lis) {
        String categoria = li.text().split(", ")[1];
        assertTrue("O produto não é da categoria 'Games'",
                   categoria.equalsIgnoreCase("Games"));
      }
    } catch (IOException e) {
      fail("Falha ao gerar o relatório: " + e.getMessage());
    }
  }

  @Test
  public void testGeraRelatorioFiltroInvalido() {
    gerador = new GeradorDeRelatorios(produtos, sortingStrategy,
                                      new DescriptionComparator(), "invalido",
                                      "", GeradorDeRelatorios.FORMATO_PADRAO);
    try {
      gerador.geraRelatorio("saida_teste.html");
      fail("Deveria lançar uma exceção para filtro inválido.");
    } catch (RuntimeException e) {
      assertEquals("Filtro invalido!", e.getMessage());
    } catch (IOException e) {
      fail("Falha ao gerar o relatório: " + e.getMessage());
    }
  }
}
