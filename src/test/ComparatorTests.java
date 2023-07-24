import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.Test;
import src.produto.ProdutoPadrao;
import src.sort.comparators.ComparatorTypes;

public class ComparatorTests {

  @Test
  public void testDescriptionComparator() {
    final List<ProdutoPadrao> produtos =
        Arrays.asList(new ProdutoPadrao(1, "Mouse", "Eletrônicos", 10, 25.0),
                      new ProdutoPadrao(2, "Teclado", "Eletrônicos", 5, 50.0),
                      new ProdutoPadrao(3, "Monitor", "Eletrônicos", 2, 200.0));

    // Ordenação crescente por descrição
    Collections.sort(produtos,
                     ComparatorTypes.getComparatorByName("descricao_c"));
    assertEquals("Monitor", produtos.get(0).getDescricao());
    assertEquals("Mouse", produtos.get(1).getDescricao());
    assertEquals("Teclado", produtos.get(2).getDescricao());

    // Ordenação decrescente por descrição
    Collections.sort(
        produtos,
        ComparatorTypes.getComparatorByName("descricao_c").reversed());
    assertEquals("Teclado", produtos.get(0).getDescricao());
    assertEquals("Mouse", produtos.get(1).getDescricao());
    assertEquals("Monitor", produtos.get(2).getDescricao());
  }

  @Test
  public void testPriceComparator() {
    final List<ProdutoPadrao> produtos =
        Arrays.asList(new ProdutoPadrao(1, "Mouse", "Eletrônicos", 10, 25.0),
                      new ProdutoPadrao(2, "Teclado", "Eletrônicos", 5, 50.0),
                      new ProdutoPadrao(3, "Monitor", "Eletrônicos", 2, 200.0));

    // Ordenação crescente por preço
    Collections.sort(produtos, ComparatorTypes.getComparatorByName("preco_c"));
    assertEquals(25.0, produtos.get(0).getPreco(), 0.01);
    assertEquals(50.0, produtos.get(1).getPreco(), 0.01);
    assertEquals(200.0, produtos.get(2).getPreco(), 0.01);

    // Ordenação decrescente por preço
    Collections.sort(produtos,
                     ComparatorTypes.getComparatorByName("preco_c").reversed());
    assertEquals(200.0, produtos.get(0).getPreco(), 0.01);
    assertEquals(50.0, produtos.get(1).getPreco(), 0.01);
    assertEquals(25.0, produtos.get(2).getPreco(), 0.01);
  }

  @Test
  public void testStockQuantityComparator() {
    final List<ProdutoPadrao> produtos =
        Arrays.asList(new ProdutoPadrao(1, "Mouse", "Eletrônicos", 10, 25.0),
                      new ProdutoPadrao(2, "Teclado", "Eletrônicos", 5, 50.0),
                      new ProdutoPadrao(3, "Monitor", "Eletrônicos", 2, 200.0));

    // Ordenação crescente por quantidade em estoque
    Collections.sort(produtos,
                     ComparatorTypes.getComparatorByName("estoque_c"));
    assertEquals(2, produtos.get(0).getQtdEstoque());
    assertEquals(5, produtos.get(1).getQtdEstoque());
    assertEquals(10, produtos.get(2).getQtdEstoque());

    // Ordenação decrescente por quantidade em estoque
    Collections.sort(
        produtos, ComparatorTypes.getComparatorByName("estoque_c").reversed());
    assertEquals(10, produtos.get(0).getQtdEstoque());
    assertEquals(5, produtos.get(1).getQtdEstoque());
    assertEquals(2, produtos.get(2).getQtdEstoque());
  }
}
