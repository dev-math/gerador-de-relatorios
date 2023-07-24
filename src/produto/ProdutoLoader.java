package src.produto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoLoader {

  public static List<Produto> loadProductsFromCSV(String csvFilePath) {
    List<Produto> products = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
      // Skip the header line of the CSV file
      String line = br.readLine();
      while ((line = br.readLine()) != null) {
        Produto product = createProductFromCSVLine(line);
        if (product != null) {
          products.add(product);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return products;
  }

  private static Produto createProductFromCSVLine(String line) {
    String[] parts = line.split(",");
    if (parts.length >= 8) {
      int id = Integer.parseInt(parts[0].trim());
      String description = parts[1].trim();
      String category = parts[2].trim();
      int stockQuantity = Integer.parseInt(parts[3].trim());
      double price = Double.parseDouble(parts[4].trim());
      boolean bold = Boolean.parseBoolean(parts[5].trim());
      boolean italic = Boolean.parseBoolean(parts[6].trim());
      String color = parts[7].trim();

      Produto product =
          new ProdutoPadrao(id, description, category, stockQuantity, price);

      if (bold) {
        product = new ProdutoNegrito(product);
      }
      if (italic) {
        product = new ProdutoItalico(product);
      }
      if (!color.isEmpty()) {
        product = new ProdutoColorido(product, color);
      }

      return product;
    }

    return null;
  }
}
