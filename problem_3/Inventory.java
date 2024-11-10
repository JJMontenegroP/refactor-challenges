import repositories.OrderRepository;
import repositories.ProductRepository;
import repositories.SaleRepository;
import services.InventoryManager;

public class Inventory {
    public static void main(String[] args) {
        String csvFileProducts = "src/data/products.csv";
        String csvFileSales = "src/data/sales.csv";
        String csvFileOrders = "src/data/orders.csv";

        ProductRepository productRepo = new ProductRepository(csvFileProducts);
        SaleRepository saleRepo = new SaleRepository(csvFileSales);
        OrderRepository orderRepo = new OrderRepository(csvFileOrders);

        InventoryManager inventoryManager = new InventoryManager(productRepo, saleRepo, orderRepo);
        inventoryManager.updateInventory();
        inventoryManager.printInventory();
    }
}