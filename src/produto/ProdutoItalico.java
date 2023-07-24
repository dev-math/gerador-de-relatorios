package src.produto;

public class ProdutoItalico extends ProdutoDecorator {
  private final Produto produto;

  public ProdutoItalico(final Produto produto) {
    super(produto);
    this.produto = produto;
  }

  @Override
  public String formataParaImpressao() {
    return "<span style=\"font-style:italic\">" +
        produto.formataParaImpressao() + "</span>";
  }
}
