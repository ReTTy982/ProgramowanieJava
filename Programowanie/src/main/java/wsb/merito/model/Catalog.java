package wsb.merito.model;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    private List<Product> products;

    public Catalog(List<Product> products) {
        this.products = products;
    }

    public Catalog() {
        this.products = new ArrayList<>();
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        if (products != null) {
            this.products = products;
        }
    }

    public void addProduct(Product product){
        if (product == null || this.products.contains(product))
            return;
        this.products.add(product);
    }

    public void removeProduct(Product product){
        if (product == null || !this.products.contains(product))
            return;
        this.products.remove(product);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }
}
