package wsb.merito.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {

    private final Map<Product, Integer> items = new HashMap<>();

    public void addProduct(Product product) {
        if (product == null) return;

        items.merge(product, 1, Integer::sum);
    }

    public Map<Product, Integer> getItems() {
        return new HashMap<>(items);
    }

    public void removeProduct(Product product) {
        if (product == null || !items.containsKey(product)) return;

        int currentQuantity = items.get(product);
        if (currentQuantity > 1) {
            items.put(product, currentQuantity - 1);
        } else {
            items.remove(product);
        }
    }

    public void clear() {
        items.clear();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
