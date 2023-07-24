package src.filters;

import java.util.function.Predicate;

import src.produto.Produto;

/**
 * FilterStrategy
 */
public interface FilterStrategy extends Predicate<Produto> {
  boolean test(Produto product);

  void setFilterArg(String filterArg);
}
