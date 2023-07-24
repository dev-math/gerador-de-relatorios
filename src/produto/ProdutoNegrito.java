package src.produto;

public class ProdutoNegrito extends ProdutoDecorator {
  private Produto produto;

  public ProdutoNegrito(final Produto produto) { super(produto); }

  @Override
  public String formataParaImpressao() {
    return "<span style=\"font-weight:bold\">" +
        produto.formataParaImpressao() + "</span>";
  }
}
