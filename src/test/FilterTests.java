import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import src.filters.FilterStrategy;
import src.filters.FilterTypes;
import src.produto.ProdutoPadrao;

public class FilterTests {

  @Test
  public void testNoFilter() {
    FilterStrategy filter = FilterTypes.getFilterStrategyByName("todos", null);
    assertTrue(
        filter.test(new ProdutoPadrao(1, "O Hobbit", "Livros", 2, 34.90)));
  }

  @Test
  public void testStockLessThanOrEqualFilter() {
    FilterStrategy filter =
        FilterTypes.getFilterStrategyByName("estoque_menor_igual", "15");

    assertTrue(
        filter.test(new ProdutoPadrao(1, "Produto A", "Categoria 1", 2, 12)));
    assertTrue(
        filter.test(new ProdutoPadrao(2, "Produto B", "Categoria 2", 15, 1)));
    assertFalse(
        filter.test(new ProdutoPadrao(3, "Produto C", "Categoria 3", 20, 19)));
  }

  @Test
  public void testCategoryEqualsFilter() {
    FilterStrategy filter =
        FilterTypes.getFilterStrategyByName("categoria_igual", "Categoria 1");

    assertTrue(
        filter.test(new ProdutoPadrao(1, "Produto A", "Categoria 1", 2, 12)));
    assertTrue(
        filter.test(new ProdutoPadrao(2, "Produto B", "Categoria 1", 15, 1)));
    assertFalse(
        filter.test(new ProdutoPadrao(3, "Produto C", "Categoria 2", 20, 19)));
  }

  @Test
  public void testPriceRangeFilter() {
    FilterStrategy filter =
        FilterTypes.getFilterStrategyByName("preco_intervalo", "10@30");

    assertTrue(
        filter.test(new ProdutoPadrao(1, "Produto A", "Categoria 1", 2, 12)));
    assertTrue(
        filter.test(new ProdutoPadrao(2, "Produto B", "Categoria 2", 15, 20)));
    assertFalse(
        filter.test(new ProdutoPadrao(3, "Produto C", "Categoria 3", 25, 5)));
  }

  @Test
  public void testDescriptionContainsFilter() {
    FilterStrategy filter =
        FilterTypes.getFilterStrategyByName("descricao_contem", "Produto");

    assertTrue(
        filter.test(new ProdutoPadrao(1, "Produto A", "Categoria 1", 2, 12)));
    assertTrue(
        filter.test(new ProdutoPadrao(2, "Produto B", "Categoria 2", 15, 20)));
    assertTrue(filter.test(
        new ProdutoPadrao(3, "Outro Produto", "Categoria 3", 25, 5)));
    assertFalse(
        filter.test(new ProdutoPadrao(4, "Outro", "Categoria 3", 25, 5)));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidFilterName() {
    FilterTypes.getFilterStrategyByName("filtro_invalido", "parametro");
  }
}
