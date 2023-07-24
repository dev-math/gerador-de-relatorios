import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import src.produto.ProdutoPadrao;

public class ProdutoPadraoTest {

  private ProdutoPadrao produto;

  @Before
  public void setUp() {
    // Configuração antes de cada teste
    produto = new ProdutoPadrao(1, "Produto A", "Categoria X", 10, 50.0);
  }

  @Test
  public void testGetId() {
    assertEquals(1, produto.getId());
  }

  @Test
  public void testGetDescricao() {
    assertEquals("Produto A", produto.getDescricao());
  }

  @Test
  public void testGetCategoria() {
    assertEquals("Categoria X", produto.getCategoria());
  }

  @Test
  public void testGetQtdEstoque() {
    assertEquals(10, produto.getQtdEstoque());
  }

  @Test
  public void testGetPreco() {
    assertEquals(50.0, produto.getPreco(),
                 0.001); // Utilizamos delta para lidar com possíveis erros de
                         // arredondamento
  }

  @Test
  public void testFormataParaImpressao() {
    String expected =
        "Produto A, Categoria X, $50.00, 10 unidade(s) em estoque";
    assertEquals(expected, produto.formataParaImpressao());
  }

  @Test
  public void testSetQtdEstoque() {
    produto.setQtdEstoque(20);
    assertEquals(20, produto.getQtdEstoque());
  }

  @Test
  public void testSetPreco() {
    produto.setPreco(40.0);
    assertEquals(40.0, produto.getPreco(), 0.001);
  }
}
