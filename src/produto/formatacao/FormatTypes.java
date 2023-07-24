package src.produto.formatacao;

/**
 * FormatTypes
 */
public enum FormatTypes {
  DEFAULT("padrao", 0b0000),
  BOLD("negrito", 0b0001),
  ITALIC("italico", 0b0010);

  String formatName;
  int formatCode;

  private FormatTypes(final String formatName, final int formatCode) {
    this.formatName = formatName;
    this.formatCode = formatCode;
  }

  public int getFormatCode() { return formatCode; }

  public static FormatTypes getFormatTypeByName(final String name) {
    for (final FormatTypes type : FormatTypes.values()) {
      if (type.formatName.equals(name)) {
        return type;
      }
    }

    throw new IllegalArgumentException("Formato inválido: " + name);
  }
}
