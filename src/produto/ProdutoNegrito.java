package src.produto;

public class ProdutoNegrito extends ProdutoDecorator {
  private final Produto produto;

  public ProdutoNegrito(final Produto produto) {
    super(produto);
    this.produto = produto;
  }

  @Override
  public String formataParaImpressao() {
    return "<span style=\"font-weight:bold\">" +
        produto.formataParaImpressao() + "</span>";
  }
}
