package src.produto;

public class ProdutoColorido extends ProdutoDecorator {
  private final Produto produto;
  private final String cor;

  public ProdutoColorido(final Produto produto, String cor) {
    super(produto);
    this.produto = produto;
    this.cor = cor;
  }

  @Override
  public String formataParaImpressao() {
    return "<span style=\"color: " + cor + "\">" +
        produto.formataParaImpressao() + "</span>";
  }
}
