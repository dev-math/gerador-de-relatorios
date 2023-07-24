package src.filters;

import src.produto.Produto;

/**
 * DescriptionContainsFilter - Filtra produtos cuja descrição contém uma
 * determinada substring.
 */
public class DescriptionContainsFilter implements FilterStrategy {
  private String substring;

  @Override
  public void setFilterArg(final String filterArg) {
    this.substring = filterArg;
  }

  @Override
  public boolean test(final Produto produto) {
    final String descricao = produto.getDescricao();
    return descricao != null && descricao.contains(substring);
  }
}
