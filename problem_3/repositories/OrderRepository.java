package repositories;

import model.Order;
import utils.CsvReader;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private final List<Order> orders;

    public OrderRepository(String filePath) {
        orders = new ArrayList<>();
        List<String[]> data = CsvReader.readCsv(filePath, ",");
        for (String[] row : data) {
            int orderId = Integer.parseInt(row[0].trim());
            String orderDate = row[1].trim();
            int itemId = Integer.parseInt(row[2].trim());
            int quantity = Integer.parseInt(row[3].trim());
            orders.add(new Order(orderId, orderDate, itemId, quantity));
        }
    }

    public List<Order> getAllOrders() {
        return orders;
    }
}
