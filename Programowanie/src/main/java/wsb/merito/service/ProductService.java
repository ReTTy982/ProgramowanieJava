package wsb.merito.service;

import wsb.merito.model.Category;
import wsb.merito.model.Product;

public class ProductService {
    private static ProductService INSTANCE;

    private ProductService() {
    }

    public static ProductService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProductService();
        }
        return INSTANCE;
    }

    public Product createProduct(String name, Float price, Category category, Integer stock) {
        Product product = new Product(name, price, category, stock);
        product.validate();
        return product;
    }

    public void updateProductPrice(Product product, float newPrice) {
        if (product != null){
            product.setPrice(newPrice);
            product.validate();
        }
    }

    public void updateProductStock(Product product, int newStock) {
        if (product != null){
            product.setStock(newStock);
            product.validate();
        }
    }

    public void removeProduct(Product product) {
        if (product != null){
            // Repo delete Product
        }
    }
}
