package repositories;

import model.Sale;
import utils.CsvReader;

import java.util.ArrayList;
import java.util.List;

public class SaleRepository {
    private final List<Sale> sales;

    public SaleRepository(String filePath) {
        sales = new ArrayList<>();
        List<String[]> data = CsvReader.readCsv(filePath, ",");
        for (String[] row : data) {
            int saleId = Integer.parseInt(row[0].trim());
            String saleDate = row[1].trim();
            int itemId = Integer.parseInt(row[2].trim());
            int quantity = Integer.parseInt(row[3].trim());
            sales.add(new Sale(saleId, saleDate, itemId, quantity));
        }
    }

    public List<Sale> getAllSales() {
        return sales;
    }
}