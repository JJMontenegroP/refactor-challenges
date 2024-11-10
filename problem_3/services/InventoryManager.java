package services;

import model.Order;
import model.Product;
import model.Sale;
import repositories.OrderRepository;
import repositories.ProductRepository;
import repositories.SaleRepository;

public class InventoryManager {
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final OrderRepository orderRepository;

    public InventoryManager(ProductRepository productRepo, SaleRepository saleRepo, OrderRepository orderRepo) {
        this.productRepository = productRepo;
        this.saleRepository = saleRepo;
        this.orderRepository = orderRepo;
    }

    public void updateInventory() {
        for (Order order : orderRepository.getAllOrders()) {
            Product product = productRepository.findById(order.getItemId());
            if (product != null) {
                product.setQuantity(product.getQuantity() + order.getQuantity());
            }
        }

        for (Sale sale : saleRepository.getAllSales()) {
            Product product = productRepository.findById(sale.getItemId());
            if (product != null) {
                product.setQuantity(product.getQuantity() - sale.getQuantity());
            }
        }
    }

    public void printInventory() {
        for (Product product : productRepository.getAllProducts()) {
            System.out.println(product.getItem() + " " + product.getQuantity());
        }
    }
}