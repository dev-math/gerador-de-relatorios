package src.produto.formatacao;

/**
 * FormatTypes
 */
public enum FormatTypes {
  DEFAULT("padrao"),
  BOLD("negrito"),
  ITALIC("italico");

  String formatName;

  private FormatTypes(final String formatName) { this.formatName = formatName; }

  public static FormatTypes getFormatTypeByName(final String name) {
    for (final FormatTypes type : FormatTypes.values()) {
      if (type.formatName.equals(name)) {
        return type;
      }
    }

    throw new IllegalArgumentException("Formato inválido: " + name);
  }
}
