package repositories;

import model.Product;
import utils.CsvReader;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    private final List<Product> products;

    public ProductRepository(String filePath) {
        products = new ArrayList<>();
        List<String[]> data = CsvReader.readCsv(filePath, ",");
        for (String[] row : data) {
            int itemId = Integer.parseInt(row[0]);
            String item = row[1];
            int quantity = Integer.parseInt(row[2]);
            products.add(new Product(itemId, item, quantity));
        }
    }

    public Product findById(int id) {
        return products.stream().filter(p -> p.getItemId() == id).findFirst().orElse(null);
    }

    public List<Product> getAllProducts() {
        return products;
    }
}