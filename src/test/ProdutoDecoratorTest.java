import static org.junit.Assert.assertEquals;

import org.junit.Test;
import src.produto.Produto;
import src.produto.ProdutoColorido;
import src.produto.ProdutoDecorator;
import src.produto.ProdutoItalico;
import src.produto.ProdutoNegrito;
import src.produto.ProdutoPadrao;

public class ProdutoDecoratorTest {

  @Test
  public void testProdutoItalico() {
    final Produto produto =
        new ProdutoPadrao(1, "Mouse", "Eletrônicos", 10, 25.0);
    final ProdutoDecorator produtoDecorado = new ProdutoItalico(produto);

    assertEquals(
        "<span style=\"font-style:italic\">Mouse, Eletrônicos, $25.00, 10 unidade(s) em estoque</span>",
        produtoDecorado.formataParaImpressao());
  }

  @Test
  public void testProdutoNegrito() {
    final Produto produto =
        new ProdutoPadrao(1, "Mouse", "Eletrônicos", 10, 25.0);
    final ProdutoDecorator produtoDecorado = new ProdutoNegrito(produto);

    assertEquals(
        "<span style=\"font-weight:bold\">Mouse, Eletrônicos, $25.00, 10 unidade(s) em estoque</span>",
        produtoDecorado.formataParaImpressao());
  }

  @Test
  public void testProdutoItalicoNegrito() {
    final Produto produto =
        new ProdutoPadrao(1, "Mouse", "Eletrônicos", 10, 25.0);
    final ProdutoDecorator produtoDecorado =
        new ProdutoNegrito(new ProdutoItalico(produto));

    assertEquals(
        "<span style=\"font-weight:bold\"><span style=\"font-style:italic\">Mouse, Eletrônicos, $25.00, 10 unidade(s) em estoque</span></span>",
        produtoDecorado.formataParaImpressao());
  }

  @Test
  public void testCorProdutoDecorator() {
    Produto produto = new ProdutoPadrao(1, "Mouse", "Eletrônicos", 10, 25.0);
    String cor = "#0000ff";
    ProdutoDecorator produtoDecorado = new ProdutoColorido(produto, cor);

    assertEquals(
        "<span style=\"color: #0000ff\">Mouse, Eletrônicos, $25.00, 10 unidade(s) em estoque</span>",
        produtoDecorado.formataParaImpressao());
  }
}
